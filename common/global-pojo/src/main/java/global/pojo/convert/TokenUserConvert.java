package global.pojo.convert;


import global.pojo.User;
import global.pojo.vo.TokenUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author boluo
 * @date 2024/01/29
 */
@Mapper
public interface TokenUserConvert {

    TokenUserConvert TOKEN_USER_CONVERT = Mappers.getMapper(TokenUserConvert.class);

    TokenUserVO toUserVO(User user);
}
