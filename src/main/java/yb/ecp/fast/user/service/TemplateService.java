package yb.ecp.fast.user.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yb.ecp.fast.infra.infra.log.LogHelper;
import yb.ecp.fast.infra.util.ListUtil;
import yb.ecp.fast.infra.util.Ref;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.dao.entity.ProfileDO;
import yb.ecp.fast.user.dao.entity.TemplateDO;
import yb.ecp.fast.user.dao.entity.TmpAuthDO;
import yb.ecp.fast.user.dao.entity.TmpMenuDO;
import yb.ecp.fast.user.dao.entity.WorkspaceDO;
import yb.ecp.fast.user.dao.mapper.ProfileMapper;
import yb.ecp.fast.user.dao.mapper.TemplateMapper;
import yb.ecp.fast.user.dao.mapper.TmpAuthMapper;
import yb.ecp.fast.user.dao.mapper.TmpMenuMapper;
import yb.ecp.fast.user.dao.mapper.WorkspaceMapper;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.service.AuthService;
import yb.ecp.fast.user.service.MenuService;
import yb.ecp.fast.user.service.VO.MenusAuthsVO;
import yb.ecp.fast.user.service.VO.RoleMenuVO;
import yb.ecp.fast.user.service.VO.TemplateConfigVO;
import yb.ecp.fast.user.service.VO.TemplateVO;
import yb.ecp.fast.user.service.VO.TmpAuthsVO;
import yb.ecp.fast.user.service.VO.TmpMenusVO;

@Service
public class TemplateService {

   @Autowired
   WorkspaceMapper F;
   @Autowired
   AuthService m;
   @Autowired
   TmpMenuMapper g;
   @Autowired
   MenuService d;
   @Autowired
   ProfileMapper L;
   @Autowired
   TmpAuthMapper e;
   @Autowired
   TemplateMapper ALLATORIxDEMO;


   public List queryList() {
      ArrayList var1 = new ArrayList();
      Iterator var2;
      Iterator var10000 = var2 = a.ALLATORIxDEMO.queryList().iterator();

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
         int var4 = a.F.queryCountByTempId(var3);
         if(0 < var4) {
            LogHelper.debug((new StringBuilder()).insert(0, var3).append(RoleMenuVO.ALLATORIxDEMO("杁阇樣木沣杞袩醝攼｛既沂刢阳＃")).toString());
            var2.add(a.selectById(var3).getName());
         }
      }

      return var2;
   }

   public List queryMenuByTemplate(String a1) {
      return a.g.queryList(a1);
   }

   public TemplateConfigVO queryTemplateConfigById(String a1) {
      TemplateConfigVO var2 = new TemplateConfigVO();
      List var3 = a.g.queryList(a1);
      List var4 = a.e.queryList(a1);
      var2.setTempId(a1);
      var2.setAuthIds(var4);
      var2.setMenuIds(var3);
      return var2;
   }

   public TemplateVO selectById(String a1) {
      TemplateVO var2 = new TemplateVO();
      TemplateDO var3 = a.ALLATORIxDEMO.selectById(a1);
      if(null == var3) {
         LogHelper.debug((new StringBuilder()).insert(0, a1).append(MenusAuthsVO.ALLATORIxDEMO("杦阈樄朧俄怷丨嬀圍ｙ")).toString());
         return null;
      } else {
         BeanUtils.copyProperties(var3, var2);
         return var2;
      }
   }

   public TemplateVO queryTempByUserId(String a1) {
      ProfileDO var2 = a.L.selectById(a1);
      if(null == var2) {
         LogHelper.debug((new StringBuilder()).insert(0, a1).append(RoleMenuVO.ALLATORIxDEMO("畿戵侶恭乚孚坿＃")).toString());
         return null;
      } else {
         WorkspaceDO a2 = a.F.selectById(var2.getSpaceId());
         if(null == a2) {
            LogHelper.debug((new StringBuilder()).insert(0, var2.getSpaceId()).append(MenusAuthsVO.ALLATORIxDEMO("巀伄穟閬俄怷丨嬀圍ｙ")).toString());
            return null;
         } else {
            return a.selectById(a2.getTempId());
         }
      }
   }

   public ErrorCode removeByIds(List a1, Ref a2) {
      List var3;
      if(!ListUtil.isNullOrEmpty(var3 = a.ALLATORIxDEMO(a1))) {
         a2.set(var3);
         return ErrorCode.TemplateInUse;
      } else {
         Iterator a3;
         Iterator var10000 = a3 = a1.iterator();

         while(var10000.hasNext()) {
            String a4 = (String)a3.next();
            var10000 = a3;
            a.ALLATORIxDEMO.removeById(a4);
            a.e.removeByTemplate(a4);
            a.g.removeByTemplate(a4);
         }

         return ErrorCode.Success;
      }
   }

   public ErrorCode removeAuthByTemplate(String a1) {
      a.e.removeByTemplate(a1);
      return ErrorCode.Success;
   }

   public ErrorCode configTmpMenu(TmpMenusVO a1) {
      List var2 = a1.getMenuIds();
      if(StringUtil.isNullOrEmpty(a1.getTemplateId())) {
         LogHelper.error(RoleMenuVO.ALLATORIxDEMO("杁阇樣木K丸稭＃"), ErrorCode.TemplateIsNull.getCode());
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
            a.g.insert(var4);
         }

         return ErrorCode.Success;
      }
   }

   public ErrorCode removeMenuByTemplate(String a1) {
      a.g.removeByTemplate(a1);
      return ErrorCode.Success;
   }

   public ErrorCode insert(TemplateVO a1) {
      TemplateDO var2 = new TemplateDO();
      BeanUtils.copyProperties(a1, var2);
      int a2 = a.ALLATORIxDEMO.insert(var2);
      if(0 >= a2) {
         LogHelper.error(MenusAuthsVO.ALLATORIxDEMO("旨墻望陵橹杚撕佹奩贀ｙ"), ErrorCode.FailedToInsertRecord.getCode());
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
         a.e.removeAuth(var4);
      }

      return ErrorCode.Success;
   }

   public List queryAuthByTemplate(String a1) {
      return a.e.queryList(a1);
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
         a.g.removeMenu(var4);
      }

      return ErrorCode.Success;
   }

   public ErrorCode configTemplate(TemplateConfigVO a1) {
      List var2 = a1.getAuthIds();
      List var3 = a1.getMenuIds();
      a.e.removeByTemplate(a1.getTempId());
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
            a.e.insert(var6);
         }
      }

      a.g.removeByTemplate(a1.getTempId());
      if(!ListUtil.isNullOrEmpty(var3)) {
         var10000 = var4 = var3.iterator();

         while(var10000.hasNext()) {
            var5 = (Integer)var4.next();
            TmpMenuDO var7 = new TmpMenuDO();
            var10000 = var4;
            var7.setMenuId(var5);
            var7.setTmpId(a1.getTempId());
            a.g.insert(var7);
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
         a.e.insert(var4);
      }

      return ErrorCode.Success;
   }

   public ErrorCode update(TemplateVO a1) {
      TemplateDO var2 = new TemplateDO();
      BeanUtils.copyProperties(a1, var2);
      int a2 = a.ALLATORIxDEMO.updateById(var2);
      if(0 >= a2) {
         LogHelper.error(RoleMenuVO.ALLATORIxDEMO("俬敮杁阇樣木擏伋夳赲＃"), ErrorCode.FailedToUpdateRecord.getCode());
         return ErrorCode.FailedToUpdateRecord;
      } else {
         return ErrorCode.Success;
      }
   }
}
