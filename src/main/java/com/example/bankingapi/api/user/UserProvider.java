package com.example.bankingapi.api.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {

    private final String tableName = "users";

    public String buildDeleteByIdSql(){
        return new SQL(){{
            DELETE_FROM(tableName);
            WHERE("id = #{id}");
        }}.toString();
    }
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("name", "#{u.name}");
            VALUES("gender", "#{u.gender}");
            VALUES("one_signal_id", "#{u.oneSignalId}");
            VALUES("is_student", "#{u.isStudent}");
            VALUES("is_deleted","FALSE");
        }}.toString();
    }
    public String selectById(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("id=#{id}","is_deleted=FALSE");
        }}.toString();
    }
    public String buildUpdateStatusByIdSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("is_deleted=#{status}");
            WHERE("id=#{id}");
        }}.toString();
    }

    public String selectAllSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("is_deleted = FALSE");
        }}.toString();
    }
}
