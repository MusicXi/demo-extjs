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


import com.myron.ims.bean.DictionaryItem;
import com.myron.ims.service.DictionaryItemService;


@Controller
@RequestMapping("/product/system/dictionaryItemManager")
public class DictionaryItemController {
	private static final Logger logger=LoggerFactory.getLogger(DictionaryItemController.class);
	
	@Autowired
	private DictionaryItemService dictionaryItemService;
	

	@RequestMapping("read.do")
	@ResponseBody
	public  Map<String, Object>read(String filter, ExtjsPage extjsPage){
		Map<String, Object> map=new HashMap<>();
		//TODO filter json转对象
		DictionaryItem dictionaryItem=new DictionaryItem();
	
		Map<String, Object> resultMap=this.dictionaryItemService.findListByPage(dictionaryItem, extjsPage);
		extjsPage= (ExtjsPage) resultMap.get("page");
		map.put("total", extjsPage.getTotalCount());
		map.put("records", resultMap.get("data"));
		return map;
	}
	
	@RequestMapping("create.do")
	@ResponseBody
	public Map<String, Object> create(@RequestBody List<DictionaryItem> list){
		Map<String, Object> map=new HashMap<>();
		for(DictionaryItem dictionaryItem: list){
			dictionaryItem.preInsert();
			this.dictionaryItemService.createDictionaryItem(dictionaryItem);			
		}
		map.put("tip", "操作成功");
		map.put("valid", true);
		return map;
	}
	
	@RequestMapping("update.do")
	@ResponseBody
	public Map<String, Object> update(@RequestBody List<DictionaryItem> list){
		Map<String, Object> map=new HashMap<>();
		int flag=0;
		for(DictionaryItem dictionaryItem: list){
			flag=this.dictionaryItemService.updateDictionaryItem(dictionaryItem);
		}
		if(flag!=0){
			map.put("tip", "操作成功");
			map.put("valid", true);			
		}
		return map;
	}
	
	@RequestMapping("destroy.do")
	@ResponseBody
	public Map<String, Object> destroy(@RequestBody List<DictionaryItem> list){
		Map<String, Object> map=new HashMap<>();
		int count=0;
		for(DictionaryItem dictionaryItem: list){
			count=count+this.dictionaryItemService.deleteDictionaryItem(dictionaryItem);
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
			String fileName=StringUtils.toUtf8String("DictionaryItem_"+dateStr);
			//String fileName="Test";
			resp.setHeader("Content-Disposition","attachment;filename=\""+fileName+ ".xlsx\"");
			
			List<Map<String,Object>> fieldData=this.dictionaryItemService.findMapList();
			Map<String,String> fieldName=new LinkedHashMap<String,String>();
			fieldName.put("dictionaryItemId", "字典项ID");
			fieldName.put("dictionaryId", "字典类型ID");
			fieldName.put("value", "字典值");
			fieldName.put("text", "显示名称");
			fieldName.put("parentId", "父级ID");
			fieldName.put("grade", "层级");
			fieldName.put("sort", "排序");
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
				DictionaryItem dictionaryItem=new DictionaryItem();
				BeanUtils.populate(dictionaryItem, obj);
				dictionaryItem.preInsert();
				this.dictionaryItemService.createDictionaryItem(dictionaryItem);				
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
				fieldName.put("dictionaryId", "字典类型ID");				
				fieldName.put("value", "字典值");				
				fieldName.put("text", "显示名称");				
				fieldName.put("parentId", "父级ID");				
				fieldName.put("grade", "层级");				
				fieldName.put("sort", "排序");				
			}
			ServletOutputStream outputStream;
			
			outputStream = resp.getOutputStream();
			String fileName=StringUtils.toUtf8String("dictionaryItemTemplate");

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
