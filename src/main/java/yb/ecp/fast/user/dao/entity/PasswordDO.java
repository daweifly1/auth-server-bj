package yb.ecp.fast.user.dao.entity;

import java.sql.Date;
import yb.ecp.fast.user.dao.entity.PasswordDOKey;

public class PasswordDO extends PasswordDOKey {

   private String e;
   private Date ALLATORIxDEMO;


   public void setUpdateTime(Date a1) {
      a.ALLATORIxDEMO = a1;
   }

   public Date getUpdateTime() {
      return a.ALLATORIxDEMO;
   }

   public void setPassword(String a1) {
      a.e = a1 == null?null:a1.trim();
   }

   public String getPassword() {
      return a.e;
   }
}
