//package com.myron.ims.aop;
//
//import java.lang.reflect.Method;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.NamedThreadLocal;
//import org.springframework.stereotype.Component;
//
//import com.myron.common.util.DateUtils;
//import com.myron.common.util.UuidUtils;
//import com.myron.ims.annotation.SystemControllerLog;
//import com.myron.ims.annotation.SystemServiceLog;
//import com.myron.ims.bean.Log;
//import com.myron.ims.bean.User;
//import com.myron.ims.service.LogService;
//
///**
// * 系统日志切点类
// * @author linrx
// *
// */
//@Aspect
//@Component
//public class SystemLogAspectOld {
//	private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAspectOld. class);
//	
//	private static final ThreadLocal<Date> beginTimeThreadLocal =
//			new NamedThreadLocal<Date>("ThreadLocal beginTime");
//	
//	@Autowired(required=false)
//	private HttpServletRequest request;
//
//	@Autowired
//	private LogService logService;
//
//	/**
//	 * Service层切点 
//	 */
//	@Pointcut("@annotation(com.myron.ims.annotation.SystemServiceLog)")
//	public void serviceAspect(){}
//	
//	/**
//	 * Controller层切点
//	 */
//	@Pointcut("@annotation(com.myron.ims.annotation.SystemControllerLog)")
//	public void controllerAspect(){}
//	
//	
//	/**
//	 * 前置通知 用于拦截Controller层记录用户的操作的开始时间
//	 * @param joinPoint 切点
//	 */
//	@Before("controllerAspect()")
//	public void doBefore(JoinPoint joinPoint){
//		if (logger.isDebugEnabled()){//这里日志级别为debug
//			Date beginTime=new Date();
//			//long beginTime2 = System.currentTimeMillis();//new Date()所做的事情其实就是调用了System.currentTimeMillis()。
//			beginTimeThreadLocal.set(beginTime);		//线程绑定变量（该数据只有当前请求的线程可见）  
//	        logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
//	        	.format(beginTime), request.getRequestURI());
//		}
//		//Date beginTime=new Date();
//		//beginTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
//	}
//	
//	/**
//	 * 后置通知 用于拦截Controller层记录用户的操作
//	 * @param joinPoint 切点
//	 */
//	@SuppressWarnings("unchecked")
//	@After("controllerAspect()")
//	public void doAfter(JoinPoint joinPoint) {
//        HttpSession session = request.getSession();       
//        User user = (User) session.getAttribute("ims_user"); //读取session中的用户    
//        Date endDate=new Date();
//        if(user !=null){
//        	String title="";
//        	String type="info";						  //日志类型(info:入库,error:错误)
//        	String remoteAddr=request.getRemoteAddr();//请求的IP
//        	String requestUri=request.getRequestURI();//请求的Uri
//        	String method=request.getMethod();		  //请求的方法类型(post/get)
//        	Map<String,String[]> params=request.getParameterMap(); //请求提交的参数
//        	
//        	//后端调用类的方法
//        	//String method2=joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()";
//        	//这是注入到controller后的提交参数
//        	//Object[] params2=joinPoint.getArgs(); 
//        	// Object userAgent=joinPoint.getThis();
//       
//        	try {
//        		title=getControllerMethodDescription(joinPoint);
//        	} catch (Exception e) {
//        		e.printStackTrace();
//        	}    
//        	/*System.out.println("日志类型:"+type);
//        	System.out.println("请求人:" + user.getName());    
//        	System.out.println("请求IP:" + remoteAddr); 
//        	System.out.println("Uri:"+requestUri);
//        	System.out.println("请求方法:" +method );   
//        	System.out.println("=====前置通知结束====="); */ 
//        	
//        	// 打印JVM信息。
//    		if (logger.isDebugEnabled()){
//    			long beginTime = beginTimeThreadLocal.get().getTime();//得到线程绑定的局部变量（开始时间）  
//    			long endTime = System.currentTimeMillis(); 	//2、结束时间  
//    	        logger.debug("计时结束：{}  URI: {}  耗时： {}   最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
//    	        		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(endTime), request.getRequestURI(), DateUtils.formatDateTime(endTime - beginTime),
//    					Runtime.getRuntime().maxMemory()/1024/1024, Runtime.getRuntime().totalMemory()/1024/1024, Runtime.getRuntime().freeMemory()/1024/1024, 
//    					(Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024); 
//    		}
//        	
//        	Log log=new Log();
//        	log.setId(UuidUtils.creatUUID());
//        	log.setTitle(title);
//        	log.setType(type);
//        	log.setRemoteAddr(remoteAddr);
//        	log.setRequestUri(requestUri);
//        	log.setMethod(method);
//        	log.setParams(params);
//        	log.setUser(user);
//        	log.setEndDate(endDate);
//        	Date beginDate=beginTimeThreadLocal.get();
//        	log.setBeginDate(beginDate);
//        	//1.直接执行保存操作
//        	//this.logService.createSystemLog(log);
//        	
//        	//// 异步保存日志
//        	//TODO 2.通过抛出一个线程专门执行日志保存操作,是否有必要?
//        	new SaveLogThread(log, logService).start();
//        }
// 
//	}
//	
//	/**
//	 *  异常通知 
//	 * @param joinPoint
//	 * @param e
//	 */
//	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")  
//	public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
//		System.out.println("例外通知");
//	}
//
//
//
//	
//	
//	 /**  
//     * 获取注解中对方法的描述信息 用于service层注解  
//     *  
//     * @param joinPoint 切点  
//     * @return 方法描述  
//     * @throws Exception  
//     */    
//     public  static String getServiceMthodDescription(JoinPoint joinPoint)    
//             throws Exception {    
//        String targetName = joinPoint.getTarget().getClass().getName();    
//        String methodName = joinPoint.getSignature().getName();    
//        Object[] arguments = joinPoint.getArgs();    
//        Class<?> targetClass = Class.forName(targetName);    
//        Method[] methods = targetClass.getMethods();    
//        String description = "";    
//         for (Method method : methods) {    
//             if (method.getName().equals(methodName)) {    
//                Class<?>[] clazzs = method.getParameterTypes();    
//                 if (clazzs.length == arguments.length) {    
//                    description = method.getAnnotation(SystemServiceLog. class).description();    
//                     break;    
//                }    
//            }    
//        }    
//         return description;    
//    } 
//     
//     /**  
//      * 获取注解中对方法的描述信息 用于Controller层注解  
//      *  
//      * @param joinPoint 切点  
//      * @return 方法描述  
//      * @throws Exception  
//      */    
//      public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
//         String targetName = joinPoint.getTarget().getClass().getName();    
//         String methodName = joinPoint.getSignature().getName();    
//         Object[] arguments = joinPoint.getArgs();    
//         Class<?> targetClass = Class.forName(targetName);    
//         Method[] methods = targetClass.getMethods();    
//         String description = "";    
//          for (Method method : methods) {    
//              if (method.getName().equals(methodName)) {    
//                 Class<?>[] clazzs = method.getParameterTypes();    
//                  if (clazzs.length == arguments.length) {    
//                     description = method.getAnnotation(SystemControllerLog. class).description();    
//                      break;    
//                 }    
//             }    
//         }    
//          return description;    
//     }
//      
//      /**
//       * 保存日志线程 
//       * @author Administrator
//       *
//       */
//      public static class SaveLogThread extends Thread{
//     	private Log log;
//     	private LogService logService;
//     	
// 		public SaveLogThread(Log log, LogService logService) {
// 			super(SaveLogThread.class.getSimpleName());
// 			this.log=log;
// 			this.logService=logService;
// 		}
// 		@Override
// 		public void run() {
// 			logService.createSystemLog(log);
// 		}
//      }
//}
