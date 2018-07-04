package com.myron.ims.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myron.common.util.FreemarkerUtil;
import com.myron.ims.bean.Article;
import com.myron.ims.service.ArticleService;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
@RequestMapping("/news")
public class NewsController {
	private static final Logger LOGGER=LoggerFactory.getLogger(NewsController.class);
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/index")
	public String newsIndex(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {
	 
	    //首页新闻列表路径
	    ServletContext context=request.getSession().getServletContext();
	    String indexPath=context.getRealPath("/news/index.html");
        
        
        //文件是否存在
        File file=new File(indexPath);
        if(!file.exists()){
        	if(!file.getParentFile().exists()){//文件目录不存在
        		file.getParentFile().mkdirs();        		
        	}

            //如果新闻列表不存在，生成新闻列表
            Map<String, Object> articleData = new HashMap<>();
            List<Article> articles=articleService.findList(new Article());
            articleData.put("articles", articles);
          
            //从设置的目录中获得模板
            Template template=FreemarkerUtil.getFreemarkTemplate("newsList.ftl");
            //合并模板和数据模型
            try {
            	FreemarkerUtil.createHtmlFile(file, template, articleData);

  
              
                articleData.clear();
                template=FreemarkerUtil.getFreemarkTemplate("news.ftl");
                //生成单个新闻文件
                Writer writer=null;
                for (Article article : articles) {
                    articleData.put("article", article);
                    //单个新闻文件
                    String link="/news/"+article.getArticleId()+".html";
                    file=new File(context.getRealPath(link));
                    article.setLink(link);
                    articleService.updateArticle(article);
                    //文件输出流写入器
                    writer=new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
                    //将模板+数据生成的结果写入文件中，得到一个静态文件
                    template.process(articleData, writer);
                    writer.flush();
                    LOGGER.info("创建文章【标题：{}】{}.html 状态:{}",article.getArticleId(),article.getLink()+article.getTitle(),file.exists());
                    System.out.println("News.doPost()"+article.getTitle()+":"+article.getArticleId()+".html 是否创建成功:"+file.exists());
                }
                writer.close();
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
        //如果新闻单页下存在，生成新闻单页
        return "/news/index.html";
        //request.getRequestDispatcher("index.html").forward(request, response);
	}
	
	@RequestMapping("/index2")
	public String newsIndex2(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {
	 
	    //首页新闻列表路径
	    ServletContext context=request.getSession().getServletContext();
	    String indexPath=context.getRealPath("/news/index.html");
        
        
        //文件是否存在
        File file=new File(indexPath);
        if(!file.exists()){
        	if(!file.getParentFile().exists()){//文件目录不存在
        		file.getParentFile().mkdirs();        		
        	}

            //如果新闻列表不存在，生成新闻列表
            Map<String, Object> articleData = new HashMap<>();
            List<Article> articles=articleService.findList(new Article());
            articleData.put("articles", articles);
          
            //从设置的目录中获得模板
            Template template=FreemarkerUtil.getFreemarkTemplate("newsList.ftl");
            //合并模板和数据模型
            try {
            	FreemarkerUtil.createHtmlFile(file, template, articleData);

  
              
                articleData.clear();
                template=FreemarkerUtil.getFreemarkTemplate("news.ftl");
                //生成单个新闻文件
                Writer writer=null;
                for (Article article : articles) {
                    articleData.put("article", article);
                    //单个新闻文件
                    String link="/news/"+article.getArticleId()+".html";
                    file=new File(context.getRealPath(link));
                    article.setLink(link);
                    articleService.updateArticle(article);
                    //文件输出流写入器
                    writer=new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
                    //将模板+数据生成的结果写入文件中，得到一个静态文件
                    template.process(articleData, writer);
                    writer.flush();
                    LOGGER.info("创建文章【标题：{}】{}.html 状态:{}",article.getArticleId(),article.getLink()+article.getTitle(),file.exists());
                    System.out.println("News.doPost()"+article.getTitle()+":"+article.getArticleId()+".html 是否创建成功:"+file.exists());
                }
                writer.close();
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
        //如果新闻单页下存在，生成新闻单页
        return "/news/index.html";
        //request.getRequestDispatcher("index.html").forward(request, response);
	}
}
