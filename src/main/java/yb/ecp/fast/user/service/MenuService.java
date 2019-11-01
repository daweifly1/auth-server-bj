package yb.ecp.fast.user.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yb.ecp.fast.infra.util.Ref;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.dao.entity.AuthDO;
import yb.ecp.fast.user.dao.entity.MenuDO;
import yb.ecp.fast.user.dao.entity.ProfileDO;
import yb.ecp.fast.user.dao.entity.WorkspaceDO;
import yb.ecp.fast.user.dao.mapper.MenuMapper;
import yb.ecp.fast.user.dao.mapper.ProfileMapper;
import yb.ecp.fast.user.dao.mapper.RoleMapper;
import yb.ecp.fast.user.dao.mapper.RoleMenuMapper;
import yb.ecp.fast.user.dao.mapper.WorkspaceMapper;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.service.ProfileService;
import yb.ecp.fast.user.service.TemplateService;
import yb.ecp.fast.user.service.VO.AuthVO;
import yb.ecp.fast.user.service.VO.MenuAuthListVO;
import yb.ecp.fast.user.service.VO.MenuDisplayVO;
import yb.ecp.fast.user.service.VO.TemplateVO;

@Service
public class MenuService {

   @Autowired
   private TemplateService F;
   @Autowired
   ProfileService m;
   @Autowired
   MenuMapper g;
   @Autowired
   RoleMenuMapper d;
   @Autowired
   private WorkspaceMapper L;
   @Autowired
   RoleMapper e;
   @Autowired
   ProfileMapper ALLATORIxDEMO;


   public List getShownMenuTree(String a1) {
      Ref var2 = new Ref("");
      return (List)(a.m.queryAuthTemplateId(a1, var2) != ErrorCode.Success?new ArrayList():a.ALLATORIxDEMO(Integer.valueOf(0), true, (String)var2.get(), (Integer)null));
   }

   public List listShownMenu(Integer a1) {
      List a2 = a.g.getDisplayedList(a1);
      ArrayList var2 = new ArrayList();
      Iterator a3;
      Iterator var10000 = a3 = a2.iterator();

      while(var10000.hasNext()) {
         MenuDO var3 = (MenuDO)a3.next();
         MenuDisplayVO var4 = new MenuDisplayVO();
         var10000 = a3;
         BeanUtils.copyProperties(var3, var4);
         var2.add(var4);
      }

      return var2;
   }

   public ErrorCode removeByTemplate(String a1) {
      return ErrorCode.Success;
   }

   public List listAuthByUser(String a1) {
      HashSet var2 = new HashSet();
      ArrayList var3 = new ArrayList();
      TemplateVO var4 = a.F.queryTempByUserId(a1);
      List var9 = a.F.queryAuthByTemplate(var4.getId());
      Iterator var5 = a.e.selectRoleIdsByUserId(a1).iterator();

      while(var5.hasNext()) {
         String var6 = (String)var5.next();
         Iterator var10;
         Iterator var10000 = var10 = a.g.selectAuthListByRole(var6).iterator();

         while(var10000.hasNext()) {
            AuthDO var7 = (AuthDO)var10.next();
            if(!var9.contains(var7.getAuthId())) {
               var10000 = var10;
            } else {
               AuthVO a2 = new AuthVO();
               var10000 = var10;
               BeanUtils.copyProperties(var7, a2);
               var2.add(a2);
            }
         }
      }

      var3.addAll(var2);
      return var3;
   }

   // $FF: synthetic method
   private List ALLATORIxDEMO(Integer a1, String a2, Integer a3) {
      List a4 = a.g.getChildList(a1);
      List var4;
      if(!StringUtil.isNullOrEmpty(a2)) {
         var4 = a.F.queryMenuByTemplate(a2);
      } else {
         if(null == a3) {
            return new ArrayList();
         }

         var4 = a.queryMenuIdsBySite(a3);
      }

      ArrayList a6 = new ArrayList();
      Iterator a5 = a4.iterator();

      while(a5.hasNext()) {
         MenuDO a7 = (MenuDO)a5.next();
         if(var4.contains(a7.getId())) {
            a6.add(a7);
         }
      }

      return a6;
   }

   public List queryMenuIdsBySite(Integer a1) {
      return a.g.selectBySite(a1);
   }

   // $FF: synthetic method
   private List ALLATORIxDEMO(List a1) {
      ArrayList var2 = new ArrayList();
      HashSet var3 = new HashSet();
      Iterator a2 = a1.iterator();

      while(a2.hasNext()) {
         String var4 = (String)a2.next();
         Iterator var8;
         Iterator var10000 = var8 = a.g.selectAuthListByRole(var4).iterator();

         while(var10000.hasNext()) {
            AuthDO var5 = (AuthDO)var8.next();
            AuthVO var6 = new AuthVO();
            var10000 = var8;
            BeanUtils.copyProperties(var5, var6);
            var3.add(var6);
         }
      }

      var2.addAll(var3);
      return var2;
   }

   // $FF: synthetic method
   private void ALLATORIxDEMO(List a1, String a2) {
      Iterator var10000;
      Iterator var3;
      MenuDisplayVO var4;
      if(!StringUtil.isNullOrSpace(a2)) {
         var10000 = var3 = a1.iterator();

         while(var10000.hasNext()) {
            var4 = (MenuDisplayVO)var3.next();
            var10000 = var3;
            var4.setAuths(a.ALLATORIxDEMO(var4.getId(), a2));
         }
      } else {
         var10000 = var3 = a1.iterator();

         while(var10000.hasNext()) {
            var4 = (MenuDisplayVO)var3.next();
            var10000 = var3;
            var4.setAuths(a.ALLATORIxDEMO(var4.getId()));
         }
      }

   }

   public List tempMenusByUserId(String a1) {
      ProfileDO a2 = a.ALLATORIxDEMO.selectById(a1);
      if(null == a2) {
         return new ArrayList();
      } else {
         WorkspaceDO a3 = a.L.selectById(a2.getSpaceId());
         return (List)(null == a3?new ArrayList():a.F.queryMenuByTemplate(a3.getTempId()));
      }
   }

   public List queryAuthIdsByRoleIds(List a1) {
      ArrayList var2 = new ArrayList();
      HashSet var3 = new HashSet();
      Iterator a2;
      Iterator var10000 = a2 = a1.iterator();

      while(var10000.hasNext()) {
         String var4 = (String)a2.next();
         List var6 = a.e.queryAuthIdsByRoleId(var4);
         var10000 = a2;
         var3.addAll(var6);
      }

      var2.addAll(var3);
      return var2;
   }

   public List getAuthListByMenu(String a1) {
      ArrayList var2 = new ArrayList();
      TemplateVO var7 = a.F.queryTempByUserId(a1);
      if(null == var7) {
         return new ArrayList();
      } else {
         List var6 = a.F.queryMenuByTemplate(var7.getId());
         List var5 = a.F.queryAuthByTemplate(var7.getId());
         Iterator var11;
         Iterator var10000 = var11 = var6.iterator();

         while(var10000.hasNext()) {
            Integer var12 = (Integer)var11.next();
            MenuAuthListVO var3 = new MenuAuthListVO();
            ArrayList a2 = new ArrayList();
            Iterator var8;
            var10000 = var8 = a.g.selectAuthListByMenu(var12).iterator();

            while(var10000.hasNext()) {
               AuthDO var9 = (AuthDO)var8.next();
               if(!var5.contains(var9.getAuthId())) {
                  var10000 = var8;
               } else {
                  AuthVO var4 = new AuthVO();
                  var10000 = var8;
                  BeanUtils.copyProperties(var9, var4);
                  a2.add(var4);
               }
            }

            var3.setMenuId(var12);
            var3.setAuths(a2);
            var10000 = var11;
            var2.add(var3);
         }

         return var2;
      }
   }

   // $FF: synthetic method
   private List ALLATORIxDEMO(Integer a1) {
      ArrayList var2 = new ArrayList();
      Iterator var3;
      Iterator var10000 = var3 = a.g.selectAuthListByMenu(a1).iterator();

      while(var10000.hasNext()) {
         AuthDO var4 = (AuthDO)var3.next();
         AuthVO var5 = new AuthVO();
         var10000 = var3;
         BeanUtils.copyProperties(var4, var5);
         var5.setParentId(a1);
         var2.add(var5);
      }

      return var2;
   }

   public List listMenuBySite(Integer a1) {
      Integer var2 = Integer.valueOf(0);
      ArrayList var3 = new ArrayList();
      Iterator var6;
      Iterator var10000 = var6 = a.g.queryListMenuForConfig(var2, a1).iterator();

      while(var10000.hasNext()) {
         MenuDO var4 = (MenuDO)var6.next();
         MenuDisplayVO var5 = new MenuDisplayVO();
         BeanUtils.copyProperties(var4, var5);
         List var7 = a.ALLATORIxDEMO(var5.getId(), true, (String)null, a1);
         var10000 = var6;
         var5.setChildren(var7);
         var3.add(var5);
      }

      a.ALLATORIxDEMO((List)var3, (String)null);
      return var3;
   }

   // $FF: synthetic method
   private List ALLATORIxDEMO(Integer a1, boolean a2, String a3, Integer a4) {
      if(a1 == null) {
         a1 = Integer.valueOf(0);
      }

      ArrayList var5 = new ArrayList();
      Iterator a5;
      Iterator var10000 = a5 = a.ALLATORIxDEMO(a1, a3, a4).iterator();

      while(var10000.hasNext()) {
         MenuDO var6 = (MenuDO)a5.next();
         MenuDisplayVO var7 = new MenuDisplayVO();
         var10000 = a5;
         BeanUtils.copyProperties(var6, var7);
         var5.add(var7);
      }

      var10000 = a5 = var5.iterator();

      while(var10000.hasNext()) {
         MenuDisplayVO var9 = (MenuDisplayVO)a5.next();
         List var10 = a.ALLATORIxDEMO(var9.getId(), a2, a3, a4);
         var10000 = a5;
         var9.setChildren(var10);
      }

      a.ALLATORIxDEMO((List)var5, a3);
      return var5;
   }

   // $FF: synthetic method
   private List ALLATORIxDEMO(Integer a1, String a2) {
      ArrayList var3 = new ArrayList();
      List var4 = a.g.selectAuthListByMenu(a1);
      List a3 = a.F.queryAuthByTemplate(a2);
      Iterator var8;
      Iterator var10000 = var8 = var4.iterator();

      while(var10000.hasNext()) {
         AuthDO var5 = (AuthDO)var8.next();
         if(!a3.contains(var5.getAuthId())) {
            var10000 = var8;
         } else {
            AuthVO var6 = new AuthVO();
            var10000 = var8;
            BeanUtils.copyProperties(var5, var6);
            var6.setParentId(a1);
            var3.add(var6);
         }
      }

      return var3;
   }
}
