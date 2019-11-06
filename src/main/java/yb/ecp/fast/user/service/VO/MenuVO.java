package yb.ecp.fast.user.service.VO;


public class MenuVO {

   private boolean C;
   private boolean K;
   private String E;
   private String J;
   private Integer F;
   private String m;
   private String g;
   private Long d;
   private Long L;
   private Integer e;
   private String ALLATORIxDEMO;


   public String getCode() {
      return g;
   }

   public void setChannel(Integer a1) {
      F = a1;
   }

   public boolean isShowFlag() {
      return K;
   }

   public void setName(String a1) {
      J = a1;
   }

   public void setIcon(String a1) {
      m = a1;
   }

   public void setId(Long a1) {
      d = a1;
   }

   public void setSeq(Integer a1) {
      e = a1;
   }

   public Integer getSeq() {
      return e;
   }

   public Long getParentId() {
      return L;
   }

   public String getName() {
      return J;
   }

   public void setState(String a1) {
      E = a1;
   }

   public void setParentId(Long a1) {
      L = a1;
   }

   public String getIcon() {
      return m;
   }

   public Integer getChannel() {
      return F;
   }

   public void setCode(String a1) {
      g = a1;
   }

   public void setUrl(String a1) {
      ALLATORIxDEMO = a1;
   }

   public boolean isLeaf() {
      return C;
   }

   public void setLeaf(boolean a1) {
      C = a1;
   }

   public String getState() {
      return E;
   }

   public void setShowFlag(boolean a1) {
      K = a1;
   }

   public String getUrl() {
      return ALLATORIxDEMO;
   }

   public Long getId() {
      return d;
   }
}
