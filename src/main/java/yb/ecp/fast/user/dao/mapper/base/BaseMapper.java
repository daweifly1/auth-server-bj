package yb.ecp.fast.user.dao.mapper.base;

import java.util.List;

public interface BaseMapper<V, D> {
   public abstract int insert(D paramD);

   public abstract int update(D paramD);

   public abstract List<D> list(V paramV);

   public abstract D item(String paramString);

   public abstract D item(Integer paramInteger);

   public abstract int updateNullAble(D paramD);
}

