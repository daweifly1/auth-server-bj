package yb.ecp.fast.user.dao.mapper.base;

import java.util.List;

public interface BaseItemMapper {

   int delete(String var1);

   int insertBatch(List var1);

   List list(String var1);
}
