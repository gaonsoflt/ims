package com.gaonsoft.ims.config;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class MysqlRowBoundsInterceptor implements Interceptor {
	private static final Logger logger = LoggerFactory.getLogger(MysqlRowBoundsInterceptor.class);

	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		RowBounds rb = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");

		logger.debug("originalSql = {}", originalSql);
		logger.debug("RowBounds = {}", rb);

		if (rb == null || rb == RowBounds.DEFAULT) {
			// RowBounds가 없으면 그냥 실행
			return invocation.proceed();
		}

		// RowBounds가 있다!
		// 원래 쿼리에 limit 문을 붙여준다.
		StringBuffer sb = new StringBuffer();
		sb.append(originalSql);
		sb.append(" limit ");
		sb.append(rb.getOffset());
		sb.append(", ");
		sb.append(rb.getLimit());

		logger.debug("sql = {}", sb.toString());

		// RowBounds 정보 제거
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		// 변경된 쿼리로 바꿔치기
		metaStatementHandler.setValue("delegate.boundSql.sql", sb.toString());

		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {

	}

}