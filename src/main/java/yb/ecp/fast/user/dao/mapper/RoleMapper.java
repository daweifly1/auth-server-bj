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

   int addUserRoleRelation(@Param("roleId") String roleId, @Param("userId") String userId, @Param("userFlag") Integer userFlag);

   int updateByPrimaryKey(RoleDO var1);

   List selectAll(@Param("channel") int channel, @Param("type") Integer type, @Param("workspaceId") String workspaceId);

//   int insertRoleUsers(UserRoles var1);

   Long countUserByRoleId(@Param("roleId") String var1);

   int insert(RoleDO var1);

   List<String> selectByWorkspaceId(@Param("workspaceId") String var1);

   int deleteRoleUsers(String var1);

   int deleteByPrimaryKey(String var1);

   int removeRoleByUser(String var1);

   int checkUserRole(@Param("roleId") String roleId, @Param("userId") String userId);

   List<Integer> queryAuthIdsByRoleId(String var1);

   List<String> selectUserIdByRoleId(@Param("roleId") String roleId);

   RoleDO selectByPrimaryKey(String var1);

   Long countByName(@Param("roleId") String roleId, @Param("name") String name, @Param("spaceId") String spaceId, @Param("deptId") String deptId);

   List<String> selectRoleIdsByUserId(String var1);

   int addUserRole(@Param("roleId") String var1, @Param("userId") String var2, @Param("userFlag") Integer var3);

   int selectRolesReferencedCount(@Param("deptId") String var1, @Param("spaceId") String var2);

   List queryRolesByDepartment(@Param("deptId") String var1, @Param("workspaceId") String var2);

   int updateByPrimaryKeySelective(RoleDO var1);
}
