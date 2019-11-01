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
      a.g = a1;
   }

   public String getState() {
      return a.L;
   }

   public void setLeaf(Short a1) {
      a.ALLATORIxDEMO = a1;
   }

   public void setName(String a1) {
      a.e = a1 == null?null:a1.trim();
   }

   public String getIcon() {
      return a.d;
   }

   public Short getLeaf() {
      return a.ALLATORIxDEMO;
   }

   public void setSeq(Integer a1) {
      a.C = a1;
   }

   public void setId(Integer a1) {
      a.J = a1;
   }

   public void setShowFlag(Short a1) {
      a.E = a1;
   }

   public Integer getId() {
      return a.J;
   }

   public void setUrl(String a1) {
      a.K = a1 == null?null:a1.trim();
   }

   public void setIcon(String a1) {
      a.d = a1 == null?null:a1.trim();
   }

   public Short getShowFlag() {
      return a.E;
   }

   public String getCode() {
      return a.F;
   }

   public Long getParentId() {
      return a.g;
   }

   public String getName() {
      return a.e;
   }

   public Integer getChannel() {
      return a.m;
   }

   public void setCode(String a1) {
      a.F = a1 == null?null:a1.trim();
   }

   public void setChannel(Integer a1) {
      a.m = a1;
   }

   public Integer getSeq() {
      return a.C;
   }

   public void setState(String a1) {
      a.L = a1 == null?null:a1.trim();
   }

   public String getUrl() {
      return a.K;
   }
}
