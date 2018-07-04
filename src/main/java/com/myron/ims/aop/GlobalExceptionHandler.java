package com.myron.ims.aop;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.myron.ims.exception.WrongVerifyCodeException;

/**
 * 全局异常处理类
 * @author lin.r.x
 *
 */
@ControllerAdvice 
public class GlobalExceptionHandler {
	private static Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SQLException.class)  
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
    @ResponseBody  
    public ExceptionResponse handleSQLException(HttpServletRequest request, Exception ex) {  
        String message = ex.getMessage();  
        return ExceptionResponse.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);  
    } 
    
/*    @ExceptionHandler(Exception.class)  
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
    @ResponseBody  
    public ExceptionResponse handlerUnknownException(HttpServletRequest request, Exception ex) {  
    	String message = ex.getMessage();  
    	return ExceptionResponse.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);  
    } */
    
    @ExceptionHandler(Exception.class)  
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
    @ResponseBody  
    public Map<String, Object> handlerUnknownException(HttpServletRequest request, Exception ex) {  
    	Map<String, Object> map=new HashMap<String, Object>();
    	String info=this.getRequestInfo(request);
    	ex.printStackTrace();
    	logger.error(info+ex);
    	
    	map.put("msg", "服务出现内部错误："+ex);
    	map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    	map.put("success", false);
    	return map;
    } 
    
    /**
     * 验证码错误异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(WrongVerifyCodeException.class)  
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
    @ResponseBody  
    public Map<String, Object> WrongVerifyCodeException(HttpServletRequest request, Exception exception) {  
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("msg", exception.getMessage());
    	map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    	map.put("success", false);
    	return map;
    } 
    
    
    /**
     * 无操作权限异常处理
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)  
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
    @ResponseBody  
    public Map<String, Object> handlerUnauthorizedException(HttpServletRequest request, Exception ex) {  
    	Map<String, Object> map=new HashMap<String, Object>();
    	String message = ex.getMessage();  
    	System.out.println("GlobalExceptionHandler.handlerUnauthorizedException():"+message);
        map.put("msg", ex+" 您没有操作权限,请联系管理员!");
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    	map.put("success", false);
    	return map;
    } 
    
    /**
     * 获取
     * @param request
     * @return
     */
    private String getRequestInfo(HttpServletRequest request){
    	String remoteAddr = request.getRemoteAddr();
    	String requestUri = request.getRequestURI();
    	String method = request.getMethod(); 
    	return "IP:"+remoteAddr+" "+method+" "+requestUri+"\n";
    }
}
