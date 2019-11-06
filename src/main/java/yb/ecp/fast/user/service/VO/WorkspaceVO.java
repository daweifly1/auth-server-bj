package yb.ecp.fast.user.service.VO;


public class WorkspaceVO {

   private String E;
   private String J;
   private String F;
   private String m;
   private String g;
   private String d;
   private String L;
   private String e;
   private Integer ALLATORIxDEMO;


   public void setRemark(String a1) {
      g = a1;
   }

   public void setName(String a1) {
      J = a1;
   }

   public void setPassword(String a1) {
      L = a1;
   }

   public void setStatus(String a1) {
      d = a1;
   }

   public String getId() {
      return F;
   }

   public void setSite(Integer a1) {
      ALLATORIxDEMO = a1;
   }

   public String getPassword() {
      return L;
   }

   public String getAccount() {
      return E;
   }

   public String getTempId() {
      return m;
   }

   public String getRemark() {
      return g;
   }

   public void setAccount(String a1) {
      E = a1;
   }

   public void setType(String a1) {
      e = a1;
   }

   public void setId(String a1) {
      F = a1;
   }

   public String getType() {
      return e;
   }

   public String getName() {
      return J;
   }

   public void setTempId(String a1) {
      m = a1;
   }

   public Integer getSite() {
      return ALLATORIxDEMO;
   }

   public String getStatus() {
      return d;
   }
}
