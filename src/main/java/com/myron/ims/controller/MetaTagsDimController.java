package  com.myron.ims.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.myron.common.util.JsonUtil;
import com.myron.common.util.StringUtils;
import com.myron.db.mybatis.bean.ExtjsPage;
import com.myron.ims.bean.MetaTagsDim;
import com.myron.ims.bean.Resource;
import com.myron.ims.service.MetaTagsDimService;


@Controller
@RequestMapping("/product/system/metaTagsDimManager")
public class MetaTagsDimController {
	private static final Logger logger = LoggerFactory.getLogger(MetaTagsDimController.class);
	
	@Autowired
	private MetaTagsDimService metaTagsDimService;
	

	@RequestMapping("findListByPage.do")
	@ResponseBody
	public  Map<String, Object> findListByPage(String filter, MetaTagsDim metaTagsDim, ExtjsPage<Map<String, Object>> page, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		
		if (! StringUtils.isBlank(filter)) {
			metaTagsDim = JsonUtil.toBean(filter, MetaTagsDim.class);			
		}
		//设置默认排序属性
		//page.setDefaultSort("createTime", "desc");
		page = (ExtjsPage<Map<String, Object>>)this.metaTagsDimService.findMapListByPage(metaTagsDim, page);
		map.put("total", page.getTotalCount());
		map.put("records", page.getResultList());
		return map;
	}
	
	@RequestMapping("/loadByJson.do")
	@ResponseBody
	public Map<String, Object> getAllMenuItem(String node, String id){
		Map<String, Object> map=new HashMap<>();
		List<MetaTagsDim> menuList=new ArrayList<MetaTagsDim>();
		MetaTagsDim metaTagsDim=new MetaTagsDim();
		if("root".equals(node)){
			metaTagsDim.setId(0);
		}else{
			metaTagsDim.setId(Integer.parseInt(node));			
		}
		menuList=this.metaTagsDimService.getChildList(metaTagsDim);
		map.put("data", menuList);
		return map;
	}
	
	@RequestMapping("createMetaTagsDim.do")
	@ResponseBody
	public Map<String, Object> createMetaTagsDim(@RequestBody List<MetaTagsDim> metaTagsDimList, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map = this.metaTagsDimService.createMetaTagsDim(metaTagsDimList);
		return map;
	}
	
	@RequestMapping("updateMetaTagsDim.do")
	@ResponseBody
	public Map<String, Object> updateMetaTagsDim(@RequestBody List<MetaTagsDim> metaTagsDimList, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map = this.metaTagsDimService.updateMetaTagsDim(metaTagsDimList);
		return map;
	}
	
	@RequestMapping("deleteMetaTagsDim.do")
	@ResponseBody
	public Map<String, Object> deleteMetaTagsDim(@RequestBody List<MetaTagsDim> metaTagsDimList, HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<>();
		map = this.metaTagsDimService.deleteMetaTagsDim(metaTagsDimList);
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
			String fileName = StringUtils.toUtf8String("MetaTagsDim_"+dateStr);
			//String fileName = "Test";
			resp.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".xlsx\"");
			
			List<Map<String,Object>> fieldData = this.metaTagsDimService.findMapList(new MetaTagsDim());
			Map<String,String> fieldName = new LinkedHashMap<String,String>();
			fieldName.put("id", "标签ID");
			fieldName.put("name", "标签名称");
			fieldName.put("type", "1:分类，2:标签组，3:标签");
			fieldName.put("parentId", "父标签");
			fieldName.put("arrparentId", "父标签集合");
			fieldName.put("value", "标签值");
			fieldName.put("description", "业务含义");
			fieldName.put("status", "状态：-1:废止,0:创建,99上线");
			fieldName.put("releaseTime", "上线时间");
			fieldName.put("trashTime", "废止时间");
			fieldName.put("demand", "需求方");
			fieldName.put("isValMulti", "标签多值  ，1是，2否");
			fieldName.put("isValEnum", "标签值枚举  ，1是，2否");
			fieldName.put("valType", "字段类型，DATE_INT支持yyyymmdd,yyyymm等格式");
			fieldName.put("valWeightType", "标签权重  1:0/1  2:(0,1]");
			fieldName.put("valWeightNorm", "标签权重归一化  ，1是，2否");
			fieldName.put("freq", "更新频率  1:按天,2:按周,3:按月 4:实时");
			fieldName.put("lifecycle", "生命周期即有效期，单位为天   0表示长期有效");
			fieldName.put("ruleDesc", "标签规则描述");
			fieldName.put("domain", "标签域，对应列簇");
			fieldName.put("field", "标签列，对应列");
			fieldName.put("dataFlow", "数据流");
			fieldName.put("dataNode", "数据节点");
			fieldName.put("committer", "开发负责人");
			fieldName.put("isIndex", "是否建索引，指是否保存到solr中建立索引，1是，2否");
			fieldName.put("createTime", "创建时间");
			fieldName.put("createBy", "创建人");
			fieldName.put("modifiedTime", "修改时间");
			fieldName.put("modifiedBy", "修改人");
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
			@RequestParam(value="excelFile", required=false) MultipartFile file) throws Exception{
		logger.debug("执行导入操作开始");
		Map<String, Object> map = new HashMap<>();
		CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) file;
		try {
			List<Map<String,Object>> list = ExcelUtil.readExcelData(commonsMultipartFile);
			for (Map<String,Object> obj : list) {
				MetaTagsDim metaTagsDim = new MetaTagsDim();
				BeanUtils.populate(metaTagsDim, obj);
				//TODO 插入主键
				this.metaTagsDimService.createMetaTagsDim(metaTagsDim);				
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
			if (model != null && ! "".equals(model)) {
				ObjectMapper mapper = new ObjectMapper();
				fieldName = mapper.readValue(model, LinkedHashMap.class);								
			} else {
				fieldName = new LinkedHashMap<String,String>();
				fieldName.put("name", "标签名称");				
				fieldName.put("type", "1:分类，2:标签组，3:标签");				
				fieldName.put("parentId", "父标签");				
				fieldName.put("arrparentId", "父标签集合");				
				fieldName.put("value", "标签值");				
				fieldName.put("description", "业务含义");				
				fieldName.put("status", "状态：-1:废止,0:创建,99上线");				
				fieldName.put("releaseTime", "上线时间");				
				fieldName.put("trashTime", "废止时间");				
				fieldName.put("demand", "需求方");				
				fieldName.put("isValMulti", "标签多值  ，1是，2否");				
				fieldName.put("isValEnum", "标签值枚举  ，1是，2否");				
				fieldName.put("valType", "字段类型，DATE_INT支持yyyymmdd,yyyymm等格式");				
				fieldName.put("valWeightType", "标签权重  1:0/1  2:(0,1]");				
				fieldName.put("valWeightNorm", "标签权重归一化  ，1是，2否");				
				fieldName.put("freq", "更新频率  1:按天,2:按周,3:按月 4:实时");				
				fieldName.put("lifecycle", "生命周期即有效期，单位为天   0表示长期有效");				
				fieldName.put("ruleDesc", "标签规则描述");				
				fieldName.put("domain", "标签域，对应列簇");				
				fieldName.put("field", "标签列，对应列");				
				fieldName.put("dataFlow", "数据流");				
				fieldName.put("dataNode", "数据节点");				
				fieldName.put("committer", "开发负责人");				
				fieldName.put("isIndex", "是否建索引，指是否保存到solr中建立索引，1是，2否");				
				fieldName.put("createTime", "创建时间");				
				fieldName.put("createBy", "创建人");				
				fieldName.put("modifiedTime", "修改时间");				
				fieldName.put("modifiedBy", "修改人");				
			}
			ServletOutputStream outputStream;
			
			outputStream = resp.getOutputStream();
			String fileName = StringUtils.toUtf8String("metaTagsDimTemplate");

			resp.setContentType("application/x-msdownload");
			resp.setHeader("Content-Disposition","attachment;filename=\"" + fileName + ".xlsx\"");

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
