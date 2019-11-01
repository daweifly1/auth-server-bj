package yb.ecp.fast.user.dao.mapper;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import yb.ecp.fast.user.dao.entity.AccountDO;

@Mapper
public interface AccountMapper {

   int checkByPrimaryKey(String var1);

   int deleteByUserId(String var1);

   int updateByPrimaryKeySelective(AccountDO var1);

   int checkByUserId(String var1);

   AccountDO selectByUserId(String var1);

   int updateByPrimaryKey(AccountDO var1);

   String selectUserIdByAccount(String var1);

   int checkLoginName(AccountDO var1);

   int deleteByPrimaryKey(String var1);

   Date getLastLoginTime(String var1);

   List selectListByUserId(String var1);

   int insert(AccountDO var1);

   AccountDO selectByPrimaryKey(String var1);

   int updateLoginTime(String var1);

   int insertSelective(AccountDO var1);
}
