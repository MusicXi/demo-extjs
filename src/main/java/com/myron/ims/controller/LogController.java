package  com.myron.ims.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myron.common.util.ExcelUtil;
import com.myron.common.util.StringUtils;
import com.myron.db.mybatis.bean.ExtjsPage;


import com.myron.ims.bean.Log;
import com.myron.ims.service.LogService;


@Controller
@RequestMapping("/product/system/logManager")
public class LogController {
	private static final Logger logger=LoggerFactory.getLogger(LogController.class);
	
	@Autowired
	private LogService logService;
	

	@RequestMapping
	public String logManager(ModelMap map,String filter, ExtjsPage extjsPage){
	
		//TODO filter json转对象
		Log log=new Log();
		extjsPage.setLength(5);
		Map<String, Object> resultMap=this.logService.findListByPage(log, extjsPage);
		extjsPage= (ExtjsPage) resultMap.get("page");
		map.put("total", extjsPage.getTotalCount());
		map.put("pageInfo", extjsPage);
		return "/demo/list.jsp";
	}
	
	@RequestMapping("read.do")
	@ResponseBody
	public  Map<String, Object>read(String filter, ExtjsPage extjsPage){
		Map<String, Object> map=new HashMap<>();
		//TODO filter json转对象
		Log log=new Log();
		
		Map<String, Object> resultMap=this.logService.findListByPage(log, extjsPage);
		extjsPage= (ExtjsPage) resultMap.get("page");
		map.put("total", extjsPage.getTotalCount());
		map.put("records", resultMap.get("data"));
		return map;
	}
	
	@RequestMapping("create.do")
	@ResponseBody
	public Map<String, Object> create(@RequestBody List<Log> list){
		Map<String, Object> map=new HashMap<>();
		for(Log log: list){
			log.preInsert();
			this.logService.createLog(log);			
		}
		map.put("tip", "操作成功");
		map.put("valid", true);
		return map;
	}
	
	@RequestMapping("update.do")
	@ResponseBody
	public Map<String, Object> update(@RequestBody List<Log> list){
		Map<String, Object> map=new HashMap<>();
		int flag=0;
		for(Log log: list){
			flag=this.logService.updateLog(log);
		}
		if(flag!=0){
			map.put("tip", "操作成功");
			map.put("valid", true);			
		}
		return map;
	}
	
	@RequestMapping("destroy.do")
	@ResponseBody
	public Map<String, Object> destroy(@RequestBody List<Log> list){
		Map<String, Object> map=new HashMap<>();
		int count=0;
		for(Log log: list){
			count=count+this.logService.deleteLog(log.getLogId());
		}
		map.put("count", count);//返回成功删除个数
		return map;
	}
	
	/**
	 * 导出Excel文件
	 * @param req
	 * @param resp
	 * @param model
	 */
	@RequestMapping("exportExcel.do")
	public void exportExcel(HttpServletRequest req, HttpServletResponse resp, ModelMap model){
		
		resp.setContentType("application/x-msdownload");
		try {
			ServletOutputStream output=resp.getOutputStream();
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = String.valueOf(simpleDateFormat.format(date));
			String fileName=StringUtils.toUtf8String("Log_"+dateStr);
			//String fileName="Test";
			resp.setHeader("Content-Disposition","attachment;filename=\""+fileName+ ".xlsx\"");
			Log log=new Log();
			List<Map<String,Object>> fieldData=this.logService.findMapList(log);
			Map<String,String> fieldName=new LinkedHashMap<String,String>();
			fieldName.put("logId", "日志主键");
			fieldName.put("type", "日志类型");
			fieldName.put("title", "日志标题");
			fieldName.put("remoteAddr", "请求地址");
			fieldName.put("requestUri", "URI");
			fieldName.put("method", "请求方式");
			fieldName.put("params", "提交参数");
			fieldName.put("exception", "异常");
			fieldName.put("operateDate", "开始时间");
			fieldName.put("timeout", "结束时间");
			fieldName.put("userId", "用户ID");
			ExcelUtil.export(fieldName, fieldData, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * excel导入
	 * TODO 导入成功回调提示
	 * @param req
	 * @param file
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping("importExcel.do")
	@ResponseBody
	public Map<String, Object> importExcel(HttpServletRequest req, 
			@RequestParam(value="excelFile", required=false) MultipartFile file){
		logger.debug("执行导入操作开始");
		Map<String, Object> map=new HashMap<>();
		CommonsMultipartFile commonsMultipartFile=(CommonsMultipartFile) file;
		try {
			List<Map<String,Object>> list=ExcelUtil.readExcelData(commonsMultipartFile);
			for(Map<String,Object> obj:list){
				Log log=new Log();
				BeanUtils.populate(log, obj);
				log.preInsert();
				this.logService.createLog(log);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		map.put("tip", "导入成功啦啦啦");
		map.put("success", true);
		return map;
	}

	/**
	 * 下载模板
	 * @param model
	 * @param req
	 * @param resp
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("downloadTemplate.do")
	public void downloadTemplate(String model,HttpServletRequest req, HttpServletResponse resp){
		Map<String, String> fieldName;
		try {
			if(model!=null && !"".equals(model) ){
				ObjectMapper mapper = new ObjectMapper();
				fieldName = mapper.readValue(model, LinkedHashMap.class);								
			}else{
				fieldName=new LinkedHashMap<String,String>();
				fieldName.put("type", "日志类型");				
				fieldName.put("title", "日志标题");				
				fieldName.put("remoteAddr", "请求地址");				
				fieldName.put("requestUri", "URI");				
				fieldName.put("method", "请求方式");				
				fieldName.put("params", "提交参数");				
				fieldName.put("exception", "异常");				
				fieldName.put("operateDate", "开始时间");				
				fieldName.put("timeout", "结束时间");				
				fieldName.put("userId", "用户ID");				
			}
			ServletOutputStream outputStream;
			
			outputStream = resp.getOutputStream();
			String fileName=StringUtils.toUtf8String("logTemplate");

			resp.setContentType("application/x-msdownload");
			resp.setHeader("Content-Disposition","attachment;filename=\""+fileName+ ".xlsx\"");

			ExcelUtil.exportTemplate(fieldName, outputStream);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
	
	
	
	
}
