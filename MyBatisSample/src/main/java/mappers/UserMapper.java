package mappers;

import entities.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Insert("INSERT INTO USER VALUES(#{id}, #{name}, #{address})")
    int insertUser(UserEntity user);

    @Results(id = "userResult", value = {
            @Result(property = "address", column = "_address")
    })
    @Select("SELECT * FROM USER WHERE ID=#{id}")
    UserEntity selectUser(int id);

    @Update("UPDATE USER SET NAME=#{name}, _ADDRESS=#{address} WHERE ID=#{id}")
    int updateUser(UserEntity user);

    @Delete("DELETE FROM USER WHERE ID=#{id}")
    int deleteUser(int id);

    @ResultMap("userResult")
    @Select("SELECT * FROM USER")
    List<UserEntity> findAllUser();
}
