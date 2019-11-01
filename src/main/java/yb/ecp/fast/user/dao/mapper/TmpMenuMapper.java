package yb.ecp.fast.user.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import yb.ecp.fast.user.dao.entity.TmpMenuDO;

@Mapper
public interface TmpMenuMapper {

   int removeByTemplate(String var1);

   int insert(TmpMenuDO var1);

   int removeMenu(TmpMenuDO var1);

   List queryList(String var1);
}
