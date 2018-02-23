package com.webapp.ediya.db.dao;

import com.webapp.ediya.db.entity.GoogleNews;
import com.webapp.ediya.db.mapper.GoogleNewsMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Date;
import java.util.List;

public interface NewsDao {

    @SqlQuery("select  id,title,hyperlink,category,pubDate,timezone,createdAt,description,guid" +
            " from google_news where  date(pubDate)=date(:date)")
    @RegisterMapper(GoogleNewsMapper.class)
    List<GoogleNews> fetchNewsByDate(@Bind("date") Date date);
}
