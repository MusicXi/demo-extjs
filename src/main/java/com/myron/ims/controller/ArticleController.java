package  com.myron.ims.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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


import com.myron.ims.bean.Article;
import com.myron.ims.service.ArticleService;


@Controller
@RequestMapping("/product/system/articleManager")
public class ArticleController {
	private static final Logger logger=LoggerFactory.getLogger(ArticleController.class);
	
	@Autowired
	private ArticleService articleService;
	

	@RequestMapping("read.do")
	@ResponseBody
	public  Map<String, Object>read(String filter, ExtjsPage extjsPage){
		Map<String, Object> map=new HashMap<>();
		//TODO filter json转对象
		Article article=new Article();
	
		Map<String, Object> resultMap=this.articleService.findListByPage(article, extjsPage);
		extjsPage= (ExtjsPage) resultMap.get("page");
		map.put("total", extjsPage.getTotalCount());
		map.put("records", resultMap.get("data"));
		return map;
	}
	
	@RequestMapping("create.do")
	@ResponseBody
	public Map<String, Object> create(@RequestBody List<Article> list){
		Map<String, Object> map=new HashMap<>();
		for(Article article: list){
			article.preInsert();
			this.articleService.createArticle(article);			
		}
		map.put("tip", "操作成功");
		map.put("valid", true);
		return map;
	}
	
	@RequestMapping("update.do")
	@ResponseBody
	public Map<String, Object> update(@RequestBody List<Article> list, HttpServletRequest request){
		Map<String, Object> map=new HashMap<>();
		int flag=0;
		for(Article article: list){
			flag=this.articleService.updateArticle(article);
		}
		if(flag!=0){
			map.put("tip", "操作成功");
			map.put("valid", true);
			 //首页新闻列表路径
		    ServletContext context=request.getSession().getServletContext();
		    String indexPath=context.getRealPath("/news/index.html");
		    //文件是否存在
	        File file=new File(indexPath);
	        if(file.exists()){
	        	file.delete();
	        }
		        
		        
		     
		}
		return map;
	}
	
	@RequestMapping("destroy.do")
	@ResponseBody
	public Map<String, Object> destroy(@RequestBody List<Article> list, HttpServletRequest request){
		Map<String, Object> map=new HashMap<>();
		int count=0;
		File file=null;
		for(Article article: list){
			count=count+this.articleService.deleteArticle(article.getArticleId());
			String path=request.getSession().getServletContext().getRealPath(article.getLink());
			String index=request.getSession().getServletContext().getRealPath("/news/index.html");
			file=new File(path);
			if(file.exists()){
				System.out.println("ArticleController.destroy()"+file.delete());
			}
			file=new File(index);
			if(file.exists()){
				System.out.println("ArticleController.destroy()"+file.delete());
			}
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
			String fileName=StringUtils.toUtf8String("Article_"+dateStr);
			//String fileName="Test";
			resp.setHeader("Content-Disposition","attachment;filename=\""+fileName+ ".xlsx\"");
			
			List<Map<String,Object>> fieldData=this.articleService.findMapList(new Article());
			Map<String,String> fieldName=new LinkedHashMap<String,String>();
			fieldName.put("articleId", "编号");
			fieldName.put("categoryId", "栏目编号");
			fieldName.put("title", "标题");
			fieldName.put("link", "文章链接");
			fieldName.put("color", "标题颜色");
			fieldName.put("image", "文章图片");
			fieldName.put("keywords", "关键字");
			fieldName.put("description", "摘要");
			fieldName.put("weight", "权重");
			fieldName.put("weightDate", "权重期限");
			fieldName.put("hits", "点击数");
			fieldName.put("posid", "推荐位");
			fieldName.put("createBy", "创建者");
			fieldName.put("createDate", "创建时间");
			fieldName.put("updateBy", "更新者");
			fieldName.put("updateDate", "更新时间");
			fieldName.put("remarks", "备注信息");
			fieldName.put("delFlag", "删除标记");
			fieldName.put("content", "文章内容");
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
				Article article=new Article();
				BeanUtils.populate(article, obj);
				article.preInsert();
				this.articleService.createArticle(article);				
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
				fieldName.put("categoryId", "栏目编号");				
				fieldName.put("title", "标题");				
				fieldName.put("link", "文章链接");				
				fieldName.put("color", "标题颜色");				
				fieldName.put("image", "文章图片");				
				fieldName.put("keywords", "关键字");				
				fieldName.put("description", "摘要");				
				fieldName.put("weight", "权重");				
				fieldName.put("weightDate", "权重期限");				
				fieldName.put("hits", "点击数");				
				fieldName.put("posid", "推荐位");				
				fieldName.put("createBy", "创建者");				
				fieldName.put("createDate", "创建时间");				
				fieldName.put("updateBy", "更新者");				
				fieldName.put("updateDate", "更新时间");				
				fieldName.put("remarks", "备注信息");				
				fieldName.put("delFlag", "删除标记");				
				fieldName.put("content", "文章内容");				
			}
			ServletOutputStream outputStream;
			
			outputStream = resp.getOutputStream();
			String fileName=StringUtils.toUtf8String("articleTemplate");

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
