package com.webapp.ediya.db.mapper;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class User implements ResultSetMapper<Map<String,Object>> {


    @Override
    public Map<String,Object> map(int index, ResultSet rs, StatementContext ctx)
            throws SQLException {
        Map<String,Object> user = new HashMap<>();
        user.put("fname",rs.getString("fname"));

        return user;
    }
}
