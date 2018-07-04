package com.myron.ims.security.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.ehcache.Ehcache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.support.SimpleValueWrapper;

/**
 * 包装Spring cache抽象
 * Shiro提供类似于Spring的cache抽象,即Shiro本身不实现Cache
 */
public class SpringCacheManagerWrapper implements CacheManager {
	private org.springframework.cache.CacheManager cacheManager;

	/**
	 * 设置spring cache manager
	 *
	 * @param cacheManager
	 */
	public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		org.springframework.cache.Cache springCache = cacheManager.getCache(name);
		return new SpringCacheWrapper<K, V>(springCache);
	}

	/**
	 * 自定义授权缓存管理类
	 * @author Administrator
	 *
	 * @param <K>
	 * @param <V>
	 */
	public static class SpringCacheWrapper<K, V> implements Cache<K, V>  {
		private Logger logger = LoggerFactory.getLogger(getClass());
		private org.springframework.cache.Cache springCache;

		SpringCacheWrapper(org.springframework.cache.Cache springCache) {
			this.springCache = springCache;
		}

		/**
		 * 根据key获取缓存中的value
		 */
		@SuppressWarnings("unchecked")
		@Override
		public Object get(Object key) throws CacheException {
			Object value = springCache.get(key);
			if (value instanceof SimpleValueWrapper) {
				return ((SimpleValueWrapper) value).get();
			}
			return value;
		}

		/**
		 * 往缓存中放入key-value
		 */
		@SuppressWarnings("unchecked")
		@Override
		public Object put(Object key, Object value) throws CacheException {
			springCache.put(key, value);
			logger.debug("put {} = {}", key, value);
			return value;
		}

		/**
		 * 移除缓存中key对应的value
		 */
		@SuppressWarnings("unchecked")
		@Override
		public Object remove(Object key) throws CacheException {
			springCache.evict(key);
			logger.debug("remove {}", key);
			return null;
		}

		/**
		 * 清空整个缓存
		 */
		@Override
		public void clear() throws CacheException {
			springCache.clear();
		}

		/**
		 * 返回缓存大小
		 */
		@Override
		public int size() {
			if (springCache.getNativeCache() instanceof Ehcache) {
				Ehcache ehcache = (Ehcache) springCache.getNativeCache();
				return ehcache.getSize();
			}
			throw new UnsupportedOperationException(
					"invoke spring cache abstract size method not supported");
		}

		/**
		 * 获取缓存中所有的key
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public Set keys() {
			if (springCache.getNativeCache() instanceof Ehcache) {
				Ehcache ehcache = (Ehcache) springCache.getNativeCache();
				return new HashSet(ehcache.getKeys());
			}
			throw new UnsupportedOperationException(
					"invoke spring cache abstract keys method not supported");
		}

		/**
		 * 获取缓存中所有的value
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Collection values() {
			if (springCache.getNativeCache() instanceof Ehcache) {
				Ehcache ehcache = (Ehcache) springCache.getNativeCache();
				List keys = ehcache.getKeys();
				if (!CollectionUtils.isEmpty(keys)) {
					List values = new ArrayList(keys.size());
					for (Object key : keys) {
						Object value = get(key);
						if (value != null) {
							values.add(value);
						}
					}
					return Collections.unmodifiableList(values);
				} else {
					return Collections.emptyList();
				}
			}
			throw new UnsupportedOperationException(
					"invoke spring cache abstract values method not supported");
		}

	}

}
