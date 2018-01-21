package com.webapp.ediya.db.dao;

import com.webapp.ediya.db.mapper.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Map;


public interface MysqlPingDao {
	@SqlQuery("select  fname from user where id = :id")
	@RegisterMapper(User.class)
	Map<String,Object> findUserById(@Bind("id") int id);

}