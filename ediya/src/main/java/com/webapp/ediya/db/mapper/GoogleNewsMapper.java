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
        googleNews.setTimezone("timezone");
        googleNews.setHyperlink("hyperlink");
        googleNews.setCategory("category");
        googleNews.setPubDate("pubDate");
        googleNews.setCreatedAt("createdAt");
        googleNews.setDescription("description");
        googleNews.setGuid("guid");

        return googleNews;
    }
}
