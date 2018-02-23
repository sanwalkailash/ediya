package com.webapp.ediya.db.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapp.ediya.db.entity.GoogleNews;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoogleNewsMapper implements ResultSetMapper<GoogleNews> {
    @Override
    public GoogleNews map(int index, ResultSet rs, StatementContext ctx)
            throws SQLException {
        GoogleNews googleNews = new GoogleNews();
        googleNews.setId(rs.getLong("id"));
        googleNews.setTitle(rs.getString("title"));
        googleNews.setTimezone(rs.getString("timezone"));
        googleNews.setHyperlink(rs.getString("hyperlink"));
        googleNews.setCategory(rs.getString("category"));
        googleNews.setPubDate(rs.getString("pubDate"));
        googleNews.setCreatedAt(rs.getString("createdAt"));
        googleNews.setDescription(rs.getString("description"));
        googleNews.setGuid(rs.getString("guid"));

        return googleNews;
    }
}
