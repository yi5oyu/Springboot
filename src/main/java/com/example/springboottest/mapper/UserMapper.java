package com.example.springboottest.mapper;

import com.example.springboottest.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    List<User> findAll();
    User findById(Long id);
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);

    /*
    @Select("SELECT * FROM users")
    List<User> findAll();
    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(@Param("id") Long id);
    @Insert("INSERT INTO users (name, email) VALUES (#{name}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);
    @Update("UPDATE users SET name = #{name}, email = #{email} WHERE id = #{id}")
    void updateUser(User user);
    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUser(@Param("id") Long id);
    */
}
