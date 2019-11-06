package yb.ecp.fast.user.service.VO;

import java.util.List;

public class MenusAuthsVO {
   private List<Integer> menus;
   private List<Integer> auths;

   public List<Integer> getMenus() {
      return this.menus;
   }

   public void setMenus(List<Integer> menus) {
      this.menus = menus;
   }

   public List<Integer> getAuths() {
      return this.auths;
   }

   public void setAuths(List<Integer> auths) {
      this.auths = auths;
   }
}
