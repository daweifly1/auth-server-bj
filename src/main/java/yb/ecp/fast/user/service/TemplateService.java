package yb.ecp.fast.user.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yb.ecp.fast.infra.util.ListUtil;
import yb.ecp.fast.infra.util.Ref;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.dao.entity.*;
import yb.ecp.fast.user.dao.mapper.*;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.service.VO.TemplateConfigVO;
import yb.ecp.fast.user.service.VO.TemplateVO;
import yb.ecp.fast.user.service.VO.TmpAuthsVO;
import yb.ecp.fast.user.service.VO.TmpMenusVO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TemplateService {

   @Autowired
   WorkspaceMapper workspaceMapper;
   @Autowired
   AuthService authService;
   @Autowired
   TmpMenuMapper tmpMenuMapper;
   @Autowired
   MenuService menuService;
   @Autowired
   ProfileMapper profileMapper;
   @Autowired
   TmpAuthMapper tmpAuthMapper;
   @Autowired
   TemplateMapper templateMapper;


   public List queryList() {
      ArrayList var1 = new ArrayList();
      Iterator var2;
      Iterator var10000 = var2 = templateMapper.queryList().iterator();

      while(var10000.hasNext()) {
         TemplateDO var3;
         if((var3 = (TemplateDO)var2.next()).getId().equals("1")) {
            var10000 = var2;
         } else {
            TemplateVO var4 = new TemplateVO();
            var10000 = var2;
            BeanUtils.copyProperties(var3, var4);
            var1.add(var4);
         }
      }

      return var1;
   }

   // $FF: synthetic method
   private List ALLATORIxDEMO(List a1) {
      ArrayList var2 = new ArrayList();
      Iterator a2 = a1.iterator();

      while(a2.hasNext()) {
         String var3 = (String)a2.next();
         int var4 = workspaceMapper.queryCountByTempId(var3);
         if(0 < var4) {
            var2.add(selectById(var3).getName());
         }
      }

      return var2;
   }

   public List<Integer> queryMenuByTemplate(String a1) {
      return tmpMenuMapper.queryList(a1);
   }

   public TemplateConfigVO queryTemplateConfigById(String a1) {
      TemplateConfigVO var2 = new TemplateConfigVO();
      List var3 = tmpMenuMapper.queryList(a1);
      List var4 = tmpAuthMapper.queryList(a1);
      var2.setTempId(a1);
      var2.setAuthIds(var4);
      var2.setMenuIds(var3);
      return var2;
   }

   public TemplateVO selectById(String a1) {
      TemplateVO var2 = new TemplateVO();
      TemplateDO var3 = templateMapper.selectById(a1);
      if(null == var3) {
         return null;
      } else {
         BeanUtils.copyProperties(var3, var2);
         return var2;
      }
   }

   public TemplateVO queryTempByUserId(String a1) {
      ProfileDO var2 = profileMapper.selectById(a1);
      if(null == var2) {
         return null;
      } else {
         WorkspaceDO a2 = workspaceMapper.selectById(var2.getSpaceId());
         if(null == a2) {
            return null;
         } else {
            return selectById(a2.getTempId());
         }
      }
   }

   public ErrorCode removeByIds(List a1, Ref a2) {
      List var3;
      if (!ListUtil.isNullOrEmpty(var3 = ALLATORIxDEMO(a1))) {
         a2.set(var3);
         return ErrorCode.TemplateInUse;
      } else {
         Iterator a3;
         Iterator var10000 = a3 = a1.iterator();

         while(var10000.hasNext()) {
            String a4 = (String)a3.next();
            var10000 = a3;
            templateMapper.removeById(a4);
            tmpAuthMapper.removeByTemplate(a4);
            tmpMenuMapper.removeByTemplate(a4);
         }

         return ErrorCode.Success;
      }
   }

   public ErrorCode removeAuthByTemplate(String a1) {
      tmpAuthMapper.removeByTemplate(a1);
      return ErrorCode.Success;
   }

   public ErrorCode configTmpMenu(TmpMenusVO a1) {
      List var2 = a1.getMenuIds();
      if(StringUtil.isNullOrEmpty(a1.getTemplateId())) {
         return ErrorCode.TemplateIsNull;
      } else {
         Iterator var5;
         Iterator var10000 = var5 = var2.iterator();

         while(var10000.hasNext()) {
            Integer var3 = (Integer)var5.next();
            TmpMenuDO var4 = new TmpMenuDO();
            var10000 = var5;
            var4.setMenuId(var3);
            var4.setTmpId(a1.getTemplateId());
            tmpMenuMapper.insert(var4);
         }

         return ErrorCode.Success;
      }
   }

   public ErrorCode removeMenuByTemplate(String a1) {
      tmpMenuMapper.removeByTemplate(a1);
      return ErrorCode.Success;
   }

   public ErrorCode insert(TemplateVO a1) {
      TemplateDO var2 = new TemplateDO();
      BeanUtils.copyProperties(a1, var2);
      int a2 = templateMapper.insert(var2);
      if(0 >= a2) {
         return ErrorCode.FailedToInsertRecord;
      } else {
         return ErrorCode.Success;
      }
   }

   public ErrorCode removeAuths(TmpAuthsVO a1) {
      Iterator var2;
      Iterator var10000 = var2 = a1.getAuthIds().iterator();

      while(var10000.hasNext()) {
         Integer var3 = (Integer)var2.next();
         TmpAuthDO var4 = new TmpAuthDO();
         var10000 = var2;
         var4.setAuthId(var3);
         var4.setTmpId(a1.getTemplateId());
         tmpAuthMapper.removeAuth(var4);
      }

      return ErrorCode.Success;
   }

   public List queryAuthByTemplate(String a1) {
      return tmpAuthMapper.queryList(a1);
   }

   public ErrorCode removeMenus(TmpMenusVO a1) {
      Iterator var2;
      Iterator var10000 = var2 = a1.getMenuIds().iterator();

      while(var10000.hasNext()) {
         Integer var3 = (Integer)var2.next();
         TmpMenuDO var4 = new TmpMenuDO();
         var10000 = var2;
         var4.setMenuId(var3);
         var4.setTmpId(a1.getTemplateId());
         tmpMenuMapper.removeMenu(var4);
      }

      return ErrorCode.Success;
   }

   public ErrorCode configTemplate(TemplateConfigVO a1) {
      List var2 = a1.getAuthIds();
      List var3 = a1.getMenuIds();
      tmpAuthMapper.removeByTemplate(a1.getTempId());
      Iterator var10000;
      Iterator var4;
      Integer var5;
      if(!ListUtil.isNullOrEmpty(var2)) {
         var10000 = var4 = var2.iterator();

         while(var10000.hasNext()) {
            var5 = (Integer)var4.next();
            TmpAuthDO var6 = new TmpAuthDO();
            var10000 = var4;
            var6.setAuthId(var5);
            var6.setTmpId(a1.getTempId());
            tmpAuthMapper.insert(var6);
         }
      }

      tmpMenuMapper.removeByTemplate(a1.getTempId());
      if(!ListUtil.isNullOrEmpty(var3)) {
         var10000 = var4 = var3.iterator();

         while(var10000.hasNext()) {
            var5 = (Integer)var4.next();
            TmpMenuDO var7 = new TmpMenuDO();
            var10000 = var4;
            var7.setMenuId(var5);
            var7.setTmpId(a1.getTempId());
            tmpMenuMapper.insert(var7);
         }
      }

      return ErrorCode.Success;
   }

   public ErrorCode configTmpAuth(TmpAuthsVO a1) {
      Iterator var2;
      Iterator var10000 = var2 = a1.getAuthIds().iterator();

      while(var10000.hasNext()) {
         Integer var3 = (Integer)var2.next();
         TmpAuthDO var4 = new TmpAuthDO();
         var10000 = var2;
         var4.setAuthId(var3);
         var4.setTmpId(a1.getTemplateId());
         tmpAuthMapper.insert(var4);
      }

      return ErrorCode.Success;
   }

   public ErrorCode update(TemplateVO a1) {
      TemplateDO var2 = new TemplateDO();
      BeanUtils.copyProperties(a1, var2);
      int a2 = templateMapper.updateById(var2);
      if(0 >= a2) {
         return ErrorCode.FailedToUpdateRecord;
      } else {
         return ErrorCode.Success;
      }
   }
}
