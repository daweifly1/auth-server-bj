package yb.ecp.fast.user.dao.mapper.base;

import java.util.List;

public interface BaseMapper {

   int update(Object var1);

   List list(Object var1);

   Object item(Integer var1);

   int updateNullAble(Object var1);

   Object item(String var1);

   int insert(Object var1);
}
