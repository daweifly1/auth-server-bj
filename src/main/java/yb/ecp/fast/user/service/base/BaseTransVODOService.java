package yb.ecp.fast.user.service.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.BeanUtils;

public class BaseTransVODOService {

   private Class e;
   private Class ALLATORIxDEMO;


   protected List getVOList(List a1) {
      ArrayList var2 = new ArrayList();
      if(a1 == null) {
         return var2;
      } else {
         Iterator a2;
         Iterator var10000 = a2 = a1.iterator();

         while(var10000.hasNext()) {
            Object var3;
            if((var3 = a2.next()) == null) {
               var10000 = a2;
            } else {
               var3 = a.getVO(var3);
               var10000 = a2;
               var2.add(var3);
            }
         }

         return var2;
      }
   }

   protected BaseTransVODOService(Class a1, Class a2) {
      a.ALLATORIxDEMO = a1;
      a.e = a2;
   }

   protected Object getDO(Object a1) {
      Object var2;
      try {
         var2 = a.e.newInstance();
      } catch (Exception var4) {
         return null;
      }

      if(a1 == null) {
         return null;
      } else {
         BeanUtils.copyProperties(a1, var2);
         return var2;
      }
   }

   protected Object getVO(Object a1) {
      Object var2;
      try {
         var2 = a.ALLATORIxDEMO.newInstance();
      } catch (Exception var4) {
         return null;
      }

      if(a1 == null) {
         return null;
      } else {
         BeanUtils.copyProperties(a1, var2);
         return var2;
      }
   }
}
