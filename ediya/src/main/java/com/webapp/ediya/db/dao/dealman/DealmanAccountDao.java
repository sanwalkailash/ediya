package com.webapp.ediya.db.dao.dealman;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface DealmanAccountDao {

    @SqlUpdate("insert into account (email,mobile,signUpLocationLat,signUpLocationLong,createdAt)" +
               " values (:email,:primaryInterest,:signUpLat,:signUpLong)")
    @GetGeneratedKeys
    int dealmanCreateAccount(@Bind("email") String email,@Bind("mobile") int mobile,
                               @Bind("signUpLat") double lat,@Bind("signUpLong") double lon);

}
