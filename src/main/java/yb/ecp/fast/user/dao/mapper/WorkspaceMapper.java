package yb.ecp.fast.user.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import yb.ecp.fast.user.dao.entity.WorkspaceDO;

@Mapper
public interface WorkspaceMapper {

   int queryCountByTempId(String var1);

   int updateById(WorkspaceDO var1);

   int insert(WorkspaceDO var1);

   List queryList();

   WorkspaceDO selectById(String var1);

   int removeById(String var1);
}
