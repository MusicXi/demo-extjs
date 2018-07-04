package com.myron.ims.security.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.myron.common.util.StringUtils;
import com.myron.db.redis.dao.RedisDao;

/**
 * 可以查本地缓存和分布式缓存
 * @author Administrator
 *
 */
public class RedisSessionDao extends AbstractSessionDAO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//保存在redis的sessionId的前缀
	private final String SHIRO_REDIS_SESSION = "shiro-redis-session:"; 
	
	private RedisDao redisDao;
	
	public RedisDao getRedisDao() {
		return redisDao;
	}

	public void setRedisDao(RedisDao redisDao) {
		this.redisDao = redisDao;
	}

	@Override
	public void delete(Session session) {
		if(session ==null){
			//TODO 日志未处理
			//logger.info("Xxx");
			return;
		}
		Serializable sessionId=session.getId();
		if(sessionId !=null){
			//this.redisSessionDao.deleteSession(sessionId);
			this.redisDao.delete(SHIRO_REDIS_SESSION+session.getId());
		}
		
	}

	@Override
	public Collection<Session> getActiveSessions() {
		this.logger.debug("获取所有Session信息集合");
		Set<Session> sessionSet = new HashSet<Session>(); 
	    Session session=null;
	    Set<String> stringKeys=this.redisDao.keys(this.SHIRO_REDIS_SESSION+"*");
		for(String key: stringKeys){
			session=this.redisDao.get(this.SHIRO_REDIS_SESSION+key);
			sessionSet.add(session);
		}
	    
	    return sessionSet;
	}

	@Override
	public void update(Session session) throws UnknownSessionException {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
		HttpServletRequest request = attr.getRequest();
		//请求为"Xxx.do"时才执行update操作
		if(StringUtils.endsWith(request.getServletPath(), ".do")){
			this.logger.debug("当前更新 Session id: {} 的URI:{}", session.getId(),request.getServletPath());
			//this.redisSessionDao.saveSession(session);	
			String key=this.SHIRO_REDIS_SESSION+session.getId();
			this.redisDao.save(key,session);
		}
		
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);  
        this.assignSessionId(session, sessionId);  
        this.logger.debug("创建 Session id: {}", sessionId);
        //this.redisSessionDao.saveSession(session);  
        String key=this.SHIRO_REDIS_SESSION+session.getId();
        this.redisDao.save(key, session);
        
        //设置session失效时间
        this.redisDao.expire(key, session.getTimeout(), TimeUnit.MILLISECONDS);
        return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session=null;
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
		HttpServletRequest request = attr.getRequest();
		//TODO 处理非.do请求对session的读取
		this.logger.debug("当前读取Session id: {} 的URI:{}", sessionId,request.getServletPath());
		//session=this.redisSessionDao.getSession(sessionId); 
		session=this.redisDao.get(this.SHIRO_REDIS_SESSION+sessionId); 
		
		return session;
	}

	
}
