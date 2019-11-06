package yb.ecp.fast.user.service.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import yb.ecp.fast.user.dao.mapper.base.BaseItemMapper;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.service.base.BaseTransVODOService;

public class BaseItemService extends BaseTransVODOService {

   BaseItemMapper ALLATORIxDEMO;


   public ErrorCode delete(String a1) {
      return ALLATORIxDEMO.delete(a1) > 0?ErrorCode.Success:ErrorCode.FailedToInsertRecord;
   }

   protected void addMapper(BaseItemMapper a1) {
      ALLATORIxDEMO = a1;
   }

   protected BaseItemService(Class a1, Class a2) {
      super(a1, a2);
   }

   public ErrorCode insertBatch(List a1) {
      if(a1 != null && a1.size() >= 1) {
         ArrayList var2 = new ArrayList();
         Iterator a2;
         Iterator var10000 = a2 = a1.iterator();

         while(var10000.hasNext()) {
            var10000 = a2;
            Object var3 = a2.next();
            var3 = getDO(var3);
            var2.add(var3);
         }

         return ALLATORIxDEMO.insertBatch(var2) > 0?ErrorCode.Success:ErrorCode.FailedToInsertRecord;
      } else {
         return ErrorCode.Success;
      }
   }

   public List list(String a1) {
      return getVOList(ALLATORIxDEMO.list(a1));
   }
}
