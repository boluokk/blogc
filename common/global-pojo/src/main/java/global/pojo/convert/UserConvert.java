package global.pojo.convert;

import global.pojo.User;
import global.pojo.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author boluo
 * @date 2024/01/30
 */
@Mapper
public interface UserConvert {
    UserConvert userConvert = Mappers.getMapper(UserConvert.class);

    User toUser(UserDTO userDTO);
}
