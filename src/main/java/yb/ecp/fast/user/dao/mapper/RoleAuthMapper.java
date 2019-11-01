package yb.ecp.fast.user.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import yb.ecp.fast.user.dao.entity.RoleAuthDO;

@Mapper
public interface RoleAuthMapper {

   int deleteAuth(String var1);

   int insertSelective(RoleAuthDO var1);
}
