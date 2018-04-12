/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.dao.mybatis.interceptor;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.hd.sfw.dao.mybatis.dialect.Dialect;

/**
 * mybatis分页插件，目前的实现是使用sql拼接达到的，所以是非参数化查询性能会受到影响
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-8-25 上午11:04:40
 */
@Intercepts({@Signature(
		type= Executor.class,
		method = "query",
		args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PageInterceptor implements Interceptor{
	
	private final static int MAPPED_STATEMENT_INDEX = 0;
	private final static int PARAMETER_INDEX = 1;
	
	private Dialect dialect;
	
	private String sqlId = ".findByPage";

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement)args[MAPPED_STATEMENT_INDEX];
		
		//根据sqlid判断是否是分页查询
		if(ms.getId().endsWith(sqlId)){
			Map<?, ?> parameter = (Map<?, ?>)args[PARAMETER_INDEX];
			
			BoundSql boundSql = ms.getBoundSql(parameter);
			String sql = boundSql.getSql().trim();
			
			int offset = (Integer)parameter.get("start");
			int limit = (Integer)parameter.get("limit");
			
			//根据方言，自动生成对应的sql语句 
			sql = dialect.getLimitString(sql, offset, limit);
 
			//生成新的sql绑定
			BoundSql newBoundSql = new BoundSql(ms.getConfiguration(),sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
 			
			//生成新的绑定mapper对象，新的对象实际是分页的 mapper
			MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));	
			
			args[MAPPED_STATEMENT_INDEX] = newMs;	
			
		}
		
		return invocation.proceed();
	}
	
	private MappedStatement copyFromMappedStatement(MappedStatement ms,SqlSource newSqlSource) {
		
		Builder builder = new MappedStatement.Builder(ms.getConfiguration(),ms.getId(),newSqlSource,ms.getSqlCommandType());
 
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());		
		builder.timeout(ms.getTimeout());
 
		builder.parameterMap(ms.getParameterMap());		
		builder.resultMaps(ms.getResultMaps());
		
		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
 
		return builder.build();
	}

	@Override
	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	@Override
	public void setProperties(Properties properties) {
		if(dialect==null){
			throw new IllegalArgumentException("请设置方言实现类");
		}
		
		if(StringUtils.isEmpty(sqlId)){
			throw new IllegalArgumentException("请设置需要拦截的sqlId");
		}
		
	}
	
	public Dialect getDialect() {
		return dialect;
	}
	
	/**
	 * 设置sql方言
	 * @param dialect
	 */
	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}
	
	/**
	 * @return the sqlId
	 */
	public String getSqlId() {
		return sqlId;
	}

	/**
	 * 需要拦截的SqlId,默认为 .findByPage
	 * @param sqlId 
	 */
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;
		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}

}
