package yb.ecp.fast.user.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import yb.ecp.fast.user.dao.entity.PasswordDO;
import yb.ecp.fast.user.dao.entity.PasswordDOKey;

@Mapper
public interface PasswordMapper {

   int deleteByPrimaryKey(PasswordDOKey var1);

   PasswordDO selectByPrimaryKey(PasswordDOKey var1);

   int checkByPrimaryKey(PasswordDOKey var1);

   int insert(PasswordDO var1);

   int updateByPrimaryKey(PasswordDO var1);
}
