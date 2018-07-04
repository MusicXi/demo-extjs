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
import com.myron.common.util.JsonUtil;
import com.myron.common.util.StringUtils;
import com.myron.db.mybatis.bean.ExtjsPage;
import com.myron.ims.bean.Goods;
import com.myron.ims.service.GoodsService;


//@Controller
//@RequestMapping("/product/system/goodsManager")
public class GoodsController {
	private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
	
	@Autowired
	private GoodsService goodsService;
		
	@RequestMapping("findListByPage.do")
	@ResponseBody
	public  Map<String, Object> findListByPage(String filter, Goods goods, ExtjsPage<Map<String, Object>> page, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		
		if (! StringUtils.isBlank(filter)) {
			goods = JsonUtil.toBean(filter, Goods.class);			
		}
		//设置默认排序属性
		page.setDefaultSort("createTime", "ASC");
		page = (ExtjsPage<Map<String, Object>>)this.goodsService.findMapListByPage(goods, page);
		map.put("total", page.getTotalCount());
		map.put("records", page.getResultList());
		return map;
	}
	
	@RequestMapping("createGoods.do")
	@ResponseBody
	public Map<String, Object> createGoods(@RequestBody List<Goods> goodsList, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map = this.goodsService.createGoods(goodsList);
		return map;
	}
	
	@RequestMapping("updateGoods.do")
	@ResponseBody
	public Map<String, Object> updateGoods(@RequestBody List<Goods> goodsList, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map = this.goodsService.updateGoods(goodsList);
		return map;
	}
	
	@RequestMapping("deleteGoods.do")
	@ResponseBody
	public Map<String, Object> deleteGoods(@RequestBody List<Goods> goodsList, HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<>();
		map = this.goodsService.deleteGoods(goodsList);
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
			String fileName = StringUtils.toUtf8String("Goods_"+dateStr);
			//String fileName = "Test";
			resp.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".xlsx\"");
			
			List<Map<String,Object>> fieldData = this.goodsService.findMapList(new Goods());
			Map<String,String> fieldName = new LinkedHashMap<String,String>();
			fieldName.put("goodsId", "商品ID");
			fieldName.put("name", "商品名称");
			fieldName.put("saleStartDate", "销售开始时间");
			fieldName.put("saleEndDate", "销售截止时间");
			fieldName.put("createTime", "创建时间");
			fieldName.put("createBy", "创建人");
			fieldName.put("status", "状态");
			fieldName.put("comment", "商品说明");
			fieldName.put("data", "流量");
			fieldName.put("flowType", "流量类型");
			fieldName.put("number", "商品数量");
			fieldName.put("effectiveMonth", "有效月");
			fieldName.put("price", "单价");
			fieldName.put("updateTime", "修改时间");
			fieldName.put("updateBy", "修改人");
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
				Goods goods = new Goods();
				BeanUtils.populate(goods, obj);
				//TODO 插入主键
				this.goodsService.createGoods(goods);				
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
				fieldName.put("name", "商品名称");				
				fieldName.put("saleStartDate", "销售开始时间");				
				fieldName.put("saleEndDate", "销售截止时间");				
				fieldName.put("createTime", "创建时间");				
				fieldName.put("createBy", "创建人");				
				fieldName.put("status", "状态");				
				fieldName.put("comment", "商品说明");				
				fieldName.put("data", "流量");				
				fieldName.put("flowType", "流量类型");				
				fieldName.put("number", "商品数量");				
				fieldName.put("effectiveMonth", "有效月");				
				fieldName.put("price", "单价");				
				fieldName.put("updateTime", "修改时间");				
				fieldName.put("updateBy", "修改人");				
			}
			ServletOutputStream outputStream;
			
			outputStream = resp.getOutputStream();
			String fileName = StringUtils.toUtf8String("goodsTemplate");

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
