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
      g = a1;
   }

   public void setLeaf(Short a1) {
      e = a1;
   }

   public void setState(String a1) {
      F = a1;
   }

   public void setIcon(String a1) {
      d = a1;
   }

   public String getState() {
      return F;
   }

   public String getUrl() {
      return ALLATORIxDEMO;
   }

   public void setName(String a1) {
      K = a1;
   }

   public String getIcon() {
      return d;
   }

   public String getCode() {
      return J;
   }

   public Long getParentId() {
      return E;
   }

   public void setChannel(Integer a1) {
      L = a1;
   }

   public void setCode(String a1) {
      J = a1;
   }

   public String getName() {
      return K;
   }

   public Integer getChannel() {
      return L;
   }

   public Integer getId() {
      return m;
   }

   public void setSeq(Integer a1) {
      C = a1;
   }

   public void setUrl(String a1) {
      ALLATORIxDEMO = a1;
   }

   public void setId(Integer a1) {
      m = a1;
   }

   public Integer getSeq() {
      return C;
   }

   public boolean equals(Object a1) {
      if (this == a1) {
         return true;
      } else if (a1 != null && getClass() == a1.getClass()) {
         MenuBase a2 = (MenuBase)a1;
         return !m.equals(a2.m) ? false : J.equals(a2.J);
      } else {
         return false;
      }
   }

   public void setParentId(Long a1) {
      E = a1;
   }

   public int hashCode() {
      int var1 = m.hashCode();
      return 31 * var1 + J.hashCode();
   }

   public Short getLeaf() {
      return e;
   }

   public Short getShowFlag() {
      return g;
   }
}
