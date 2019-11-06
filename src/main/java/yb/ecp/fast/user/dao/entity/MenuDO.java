package yb.ecp.fast.user.dao.entity;


public class MenuDO {

   private Integer C;
   private String K;
   private Short E;
   private Integer J;
   private String F;
   private Integer m;
   private Long g;
   private String d;
   private String L;
   private String e;
   private Short ALLATORIxDEMO;


   public void setParentId(Long a1) {
      g = a1;
   }

   public String getState() {
      return L;
   }

   public void setLeaf(Short a1) {
      ALLATORIxDEMO = a1;
   }

   public void setName(String a1) {
      e = a1 == null?null:a1.trim();
   }

   public String getIcon() {
      return d;
   }

   public Short getLeaf() {
      return ALLATORIxDEMO;
   }

   public void setSeq(Integer a1) {
      C = a1;
   }

   public void setId(Integer a1) {
      J = a1;
   }

   public void setShowFlag(Short a1) {
      E = a1;
   }

   public Integer getId() {
      return J;
   }

   public void setUrl(String a1) {
      K = a1 == null?null:a1.trim();
   }

   public void setIcon(String a1) {
      d = a1 == null?null:a1.trim();
   }

   public Short getShowFlag() {
      return E;
   }

   public String getCode() {
      return F;
   }

   public Long getParentId() {
      return g;
   }

   public String getName() {
      return e;
   }

   public Integer getChannel() {
      return m;
   }

   public void setCode(String a1) {
      F = a1 == null?null:a1.trim();
   }

   public void setChannel(Integer a1) {
      m = a1;
   }

   public Integer getSeq() {
      return C;
   }

   public void setState(String a1) {
      L = a1 == null?null:a1.trim();
   }

   public String getUrl() {
      return K;
   }
}
