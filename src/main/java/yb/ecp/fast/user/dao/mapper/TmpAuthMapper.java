package yb.ecp.fast.user.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import yb.ecp.fast.user.dao.entity.TmpAuthDO;

@Mapper
public interface TmpAuthMapper {

   List queryList(String var1);

   int removeByTemplate(String var1);

   int removeAuth(TmpAuthDO var1);

   int insert(TmpAuthDO var1);
}
