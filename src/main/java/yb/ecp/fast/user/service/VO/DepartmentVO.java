package yb.ecp.fast.user.service.VO;

import io.swagger.annotations.ApiModelProperty;

public class DepartmentVO {

   @ApiModelProperty("机构ID")
   private String F;
   @ApiModelProperty("机构编号")
   private String m;
   private String g;
   @ApiModelProperty("上级机构ID")
   private String d;
   @ApiModelProperty("机构名称")
   private String L;
   private Integer e;
   @ApiModelProperty("是否是叶子节点 1-叶节点")
   private Integer ALLATORIxDEMO;


   public void setLeaf(Integer a1) {
      a.ALLATORIxDEMO = a1;
   }

   public void setName(String a1) {
      a.L = a1;
   }

   public String getSpaceId() {
      return a.g;
   }

   public Integer getSeq() {
      return a.e;
   }

   public void setCode(String a1) {
      a.m = a1;
   }

   public void setId(String a1) {
      a.F = a1;
   }

   public String getCode() {
      return a.m;
   }

   public void setParentId(String a1) {
      a.d = a1;
   }

   public Integer getLeaf() {
      return a.ALLATORIxDEMO;
   }

   public void setSpaceId(String a1) {
      a.g = a1;
   }

   public String getParentId() {
      return a.d;
   }

   public String getId() {
      return a.F;
   }

   public void setSeq(Integer a1) {
      a.e = a1;
   }

   public String getName() {
      return a.L;
   }
}
