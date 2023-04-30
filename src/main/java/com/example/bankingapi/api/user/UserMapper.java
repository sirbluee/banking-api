package com.example.bankingapi.api.user;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface UserMapper {
    @InsertProvider(type = UserProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id" )
    void insert(@Param("u") User user);
//
    @SelectProvider (type = UserProvider.class, method = "selectAllSql")
    @Results(id = "userResultMapper", value = {
        @Result(column = "is_student",property = "isStudent"),
        @Result(column = "student_card_id",property = "studentCardId")
    })
    List<User> select();

    @SelectProvider(type = UserProvider.class, method = "selectById")
    @ResultMap("userResultMapper")
    Optional<User> selectById(@Param("id") Integer id);

    @Select("SELECT EXISTS (SELECT *FROM users WHERE id =#{id})")
    boolean existsById(@Param("id")Integer id);

    @DeleteProvider(type = UserProvider.class, method = "buildDeleteByIdSql")
    void deletedById(@Param("id")Integer id);

    @UpdateProvider(type = UserProvider.class, method = "buildUpdateStatusByIdSql")
    void updateById(@Param("id")Integer id, @Param("status")boolean status);

}
