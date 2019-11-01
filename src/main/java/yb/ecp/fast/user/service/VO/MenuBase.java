package yb.ecp.fast.user.service.VO;


public class MenuBase {

   private Integer C;
   private String K;
   private Long E;
   private String J;
   private String F;
   private Integer m;
   private Short g;
   private String d;
   private Integer L;
   private Short e;
   private String ALLATORIxDEMO;


   public void setShowFlag(Short a1) {
      a.g = a1;
   }

   public void setLeaf(Short a1) {
      a.e = a1;
   }

   public void setState(String a1) {
      a.F = a1;
   }

   public void setIcon(String a1) {
      a.d = a1;
   }

   public String getState() {
      return a.F;
   }

   public String getUrl() {
      return a.ALLATORIxDEMO;
   }

   public void setName(String a1) {
      a.K = a1;
   }

   public String getIcon() {
      return a.d;
   }

   public String getCode() {
      return a.J;
   }

   public Long getParentId() {
      return a.E;
   }

   public void setChannel(Integer a1) {
      a.L = a1;
   }

   public void setCode(String a1) {
      a.J = a1;
   }

   public String getName() {
      return a.K;
   }

   public Integer getChannel() {
      return a.L;
   }

   public Integer getId() {
      return a.m;
   }

   public void setSeq(Integer a1) {
      a.C = a1;
   }

   public void setUrl(String a1) {
      a.ALLATORIxDEMO = a1;
   }

   public void setId(Integer a1) {
      a.m = a1;
   }

   public Integer getSeq() {
      return a.C;
   }

   public boolean equals(Object a1) {
      if(a == a1) {
         return true;
      } else if(a1 != null && a.getClass() == a1.getClass()) {
         MenuBase a2 = (MenuBase)a1;
         return !a.m.equals(a2.m)?false:a.J.equals(a2.J);
      } else {
         return false;
      }
   }

   public void setParentId(Long a1) {
      a.E = a1;
   }

   public int hashCode() {
      int var1 = a.m.hashCode();
      return 31 * var1 + a.J.hashCode();
   }

   public Short getLeaf() {
      return a.e;
   }

   public Short getShowFlag() {
      return a.g;
   }
}
