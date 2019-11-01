package yb.ecp.fast.user.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yb.ecp.fast.user.dao.entity.RoleDO;
import yb.ecp.fast.user.dao.entity.UserRoles;

@Mapper
public interface RoleMapper {

   int insertSelective(RoleDO var1);

   int removeUserRole(@Param("roleId") String var1, @Param("userId") String var2);

   int addUserRoleRelation(@Param("roleId") String var1, @Param("userId") String var2, @Param("userFlag") Integer var3);

   int updateByPrimaryKey(RoleDO var1);

   List selectAll(@Param("channel") int var1, @Param("type") Integer var2, @Param("workspaceId") String var3);

   int insertRoleUsers(UserRoles var1);

   Long countUserByRoleId(@Param("roleId") String var1);

   int insert(RoleDO var1);

   List selectByWorkspaceId(@Param("workspaceId") String var1);

   int deleteRoleUsers(String var1);

   int deleteByPrimaryKey(String var1);

   int removeRoleByUser(String var1);

   int checkUserRole(@Param("roleId") String var1, @Param("userId") String var2);

   List queryAuthIdsByRoleId(String var1);

   List selectUserIdByRoleId(@Param("roleId") String var1);

   RoleDO selectByPrimaryKey(String var1);

   Long countByName(@Param("roleId") String var1, @Param("name") String var2, @Param("spaceId") String var3, @Param("deptId") String var4);

   List selectRoleIdsByUserId(String var1);

   int addUserRole(@Param("roleId") String var1, @Param("userId") String var2, @Param("userFlag") Integer var3);

   int selectRolesReferencedCount(@Param("deptId") String var1, @Param("spaceId") String var2);

   List queryRolesByDepartment(@Param("deptId") String var1, @Param("workspaceId") String var2);

   int updateByPrimaryKeySelective(RoleDO var1);
}
