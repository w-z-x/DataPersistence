package mappers;

import entities.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Results(id = "userResult", value = {
            @Result(property = "address", column = "_address")
    })
    @Select("SELECT * FROM USER WHERE ID = #{id}")
    User selectUser(int id);
}
