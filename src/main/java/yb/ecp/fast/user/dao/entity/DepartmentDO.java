package yb.ecp.fast.user.dao.entity;


public class DepartmentDO {

   private String F;
   private String m;
   private Integer g;
   private String d;
   private String L;
   private String e;
   private Integer ALLATORIxDEMO;


   public String getSpaceId() {
      return e;
   }

   public String getName() {
      return F;
   }

   public void setParentId(String a1) {
      L = a1;
   }

   public String getId() {
      return m;
   }

   public void setCode(String a1) {
      d = a1;
   }

   public String getParentId() {
      return L;
   }

   public void setSeq(Integer a1) {
      ALLATORIxDEMO = a1;
   }

   public Integer getLeaf() {
      return g;
   }

   public void setLeaf(Integer a1) {
      g = a1;
   }

   public Integer getSeq() {
      return ALLATORIxDEMO;
   }

   public void setName(String a1) {
      F = a1;
   }

   public String getCode() {
      return d;
   }

   public void setSpaceId(String a1) {
      e = a1;
   }

   public void setId(String a1) {
      m = a1;
   }
}
