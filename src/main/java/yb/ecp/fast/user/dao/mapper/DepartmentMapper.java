package yb.ecp.fast.user.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yb.ecp.fast.user.dao.entity.DepartmentDO;

@Mapper
public interface DepartmentMapper {

   int deleteById(String var1);

   String queryLastCode(@Param("parentId") String var1, @Param("spaceId") String var2);

   List queryDeptList(DepartmentDO var1);

   int insert(DepartmentDO var1);

   int update(DepartmentDO var1);

   int checkName(DepartmentDO var1);

   DepartmentDO selectById(String var1);

   List queryList(@Param("deptId") String var1, @Param("spaceId") String var2);

   int removeByCode(DepartmentDO var1);

   List selectDeptNotEmpty(DepartmentDO var1);
}
