package yb.ecp.fast.user.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import yb.ecp.fast.user.dao.entity.RoleMenuDO;

@Mapper
public interface RoleMenuMapper {

   List<Integer> getMenuByRoleId(String var1);

   int insert(RoleMenuDO var1);

   int deleteMenu(String var1);

   List<Integer> getMenuIdsByRoleIds(List var1);

   List<Integer> getAuthByRoleId(String var1);

   int insertSelective(RoleMenuDO var1);
}
