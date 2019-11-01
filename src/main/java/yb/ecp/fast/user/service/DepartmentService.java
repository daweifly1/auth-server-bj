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
import yb.ecp.fast.user.dao.entity.DepartmentDO;
import yb.ecp.fast.user.dao.mapper.DepartmentMapper;
import yb.ecp.fast.user.dao.mapper.ProfileMapper;
import yb.ecp.fast.user.dao.mapper.RoleMapper;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.manager.UserContextManager;
import yb.ecp.fast.user.service.ProfileService;
import yb.ecp.fast.user.service.VO.DepartmentVO;
import yb.ecp.fast.user.service.VO.RoleMenuVO;

@Service
public class DepartmentService {

   @Autowired
   private DepartmentMapper g;
   @Autowired
   ProfileService d;
   @Autowired
   private UserContextManager L;
   @Autowired
   private RoleMapper e;
   @Autowired
   ProfileMapper ALLATORIxDEMO;


   public String createDeptCode(String a1, String a2) {
      DepartmentDO var3 = a.g.selectById(a1);
      if(StringUtil.isNullOrEmpty(a2 = a.g.queryLastCode(a1, a2))) {
         return "0".equals(a1)?RoleMenuVO.ALLATORIxDEMO("2g3"):(new StringBuilder()).insert(0, var3.getCode()).append(RoleMenuVO.ALLATORIxDEMO("2g3")).toString();
      } else {
         String var10000 = a2;
         int var4 = a2.length();
         a2 = a2.substring(0, var4 - 3);
         a1 = var10000.substring(var4 - 3);
         if(RoleMenuVO.ALLATORIxDEMO(";n;").equals(a1)) {
            return "";
         } else {
            var10000 = a1;
            a1 = String.valueOf(Integer.valueOf(a1).intValue() + 1);
            int var5;
            if((var5 = var10000.length() - a1.length()) > 0) {
               for(int var6 = var5; var6 > 0; var6 = var5) {
                  StringBuilder var7 = (new StringBuilder()).insert(0, "0");
                  --var5;
                  a1 = var7.append(a1).toString();
               }
            }

            return (new StringBuilder()).insert(0, a2).append(a1).toString();
         }
      }
   }

   public ErrorCode removeByCode(String a1, String a2, Ref a3) {
      DepartmentDO var4 = a.g.selectById(a1);
      if(null == var4) {
         LogHelper.debug(RoleMenuVO.ALLATORIxDEMO("皹栅攧捬乚孚坿＃"));
         return ErrorCode.Success;
      } else if(a.ALLATORIxDEMO(a1, a2)) {
         ArrayList a5;
         (a5 = new ArrayList()).add(var4.getName());
         a3.set(a5);
         LogHelper.error(RoleMenuVO.ALLATORIxDEMO("郪閿三嬏圪覅艰"), ErrorCode.FailedToRemoveRecord.getCode());
         return ErrorCode.FailedToRemoveRecord;
      } else {
         List a4;
         if(!ListUtil.isNullOrEmpty(a4 = a.beforeRemove(var4.getCode(), a2))) {
            a3.set(a4);
            LogHelper.error(a3.get() + RoleMenuVO.ALLATORIxDEMO("郪閿三嬏圪畿戵"), ErrorCode.FailedToRemoveRecord.getCode());
            return ErrorCode.FailedToRemoveRecord;
         } else {
            a.g.removeByCode(var4);
            return a.ALLATORIxDEMO(var4);
         }
      }
   }

   public List listDept(String a1, String a2) {
      a2 = a.L.getWorkspaceId(a2);
      if(StringUtil.isNullOrSpace(a1)) {
         a1 = "0";
      }

      ArrayList var3 = new ArrayList();
      Iterator a3;
      Iterator var10000 = a3 = a.g.queryList(a1, a2).iterator();

      while(var10000.hasNext()) {
         DepartmentDO a4 = (DepartmentDO)a3.next();
         DepartmentVO var4 = new DepartmentVO();
         var10000 = a3;
         BeanUtils.copyProperties(a4, var4);
         var3.add(var4);
      }

      return var3;
   }

   public ErrorCode insert(DepartmentVO a1, String a2) {
      DepartmentDO var3 = new DepartmentDO();
      BeanUtils.copyProperties(a1, var3);
      var3.setSpaceId(a2);
      if(StringUtil.isNullOrEmpty(var3.getParentId())) {
         var3.setParentId("0");
      }

      int a3 = a.g.checkName(var3);
      if(0 < a3) {
         LogHelper.error((new StringBuilder()).insert(0, RoleMenuVO.ALLATORIxDEMO("呛纥乜郪閿吏禧„")).append(var3.getName()).append(RoleMenuVO.ALLATORIxDEMO("‟嶥孚坿＃")).toString(), ErrorCode.DeptNameExist.getCode());
         return ErrorCode.DeptNameExist;
      } else {
         String a4;
         if(StringUtil.isNullOrEmpty(a4 = a.createDeptCode(var3.getParentId(), var3.getSpaceId()))) {
            LogHelper.error(RoleMenuVO.ALLATORIxDEMO("呛纥乜郪閿AF巰纘朂奰＃"), ErrorCode.FailedToNewCode.getCode());
            return ErrorCode.FailedToNewCode;
         } else {
            var3.setCode(a4);
            var3.setLeaf(Integer.valueOf(1));
            a3 = a.g.insert(var3);
            if(0 >= a3) {
               LogHelper.error(RoleMenuVO.ALLATORIxDEMO("斲壉郪閿夳赲＃"), ErrorCode.FailedToInsertRecord.getCode());
               return ErrorCode.FailedToInsertRecord;
            } else {
               return a.M(var3);
            }
         }
      }
   }

   public ErrorCode update(DepartmentVO a1) {
      DepartmentDO var2 = a.g.selectById(a1.getId());
      if(null == var2) {
         LogHelper.error(RoleMenuVO.ALLATORIxDEMO("皹栅攧捬乚孚坿＃"), ErrorCode.FailedToUpdateRecord.getCode());
         return ErrorCode.FailedToUpdateRecord;
      } else if(var2.getName().equals(a1.getName())) {
         LogHelper.debug((new StringBuilder()).insert(0, a1.getName()).append(RoleMenuVO.ALLATORIxDEMO("丌又吏禧盺呛＃")).toString());
         return ErrorCode.Success;
      } else {
         var2.setName(a1.getName());
         int a2 = a.g.checkName(var2);
         if(0 < a2) {
            LogHelper.error((new StringBuilder()).insert(0, RoleMenuVO.ALLATORIxDEMO("呛纥乜郪閿吏禧„")).append(var2.getName()).append(RoleMenuVO.ALLATORIxDEMO("‟嶥孚坿＃")).toString(), ErrorCode.DeptNameExist.getCode());
            return ErrorCode.DeptNameExist;
         } else {
            a2 = a.g.update(var2);
            if(0 >= a2) {
               LogHelper.error(RoleMenuVO.ALLATORIxDEMO("俬敮盬桐敲挹夳赲＃"), ErrorCode.FailedToUpdateRecord.getCode());
               return ErrorCode.FailedToUpdateRecord;
            } else {
               return ErrorCode.Success;
            }
         }
      }
   }

   public String queryFullDeptName(String a1) {
      if("0".equals(a1)) {
         return "";
      } else {
         StringBuffer var2 = new StringBuffer();
         DepartmentService var10000 = a;

         StringBuffer var4;
         while(true) {
            DepartmentVO var3 = var10000.item(a1);
            if(null == var3) {
               return "";
            }

            var2.insert(0, var3.getName());
            if("0".equals(var3.getParentId())) {
               var4 = var2;
               break;
            }

            if("".equals(var3.getParentId())) {
               var4 = var2;
               break;
            }

            var2.insert(0, RoleMenuVO.ALLATORIxDEMO("/"));
            a1 = var3.getParentId();
            var10000 = a;
         }

         return var4.toString();
      }
   }

   public List queryDeptList(DepartmentVO a1) {
      DepartmentDO var2 = new DepartmentDO();
      ArrayList var3 = new ArrayList();
      BeanUtils.copyProperties(a1, var2);
      Iterator a2;
      Iterator var10000 = a2 = a.g.queryDeptList(var2).iterator();

      while(var10000.hasNext()) {
         var2 = (DepartmentDO)a2.next();
         DepartmentVO var4 = new DepartmentVO();
         var10000 = a2;
         BeanUtils.copyProperties(var2, var4);
         var3.add(var4);
      }

      return var3;
   }

   // $FF: synthetic method
   private ErrorCode M(DepartmentDO a1) {
      if("0".equals(a1.getParentId())) {
         LogHelper.debug(RoleMenuVO.ALLATORIxDEMO("旧墜邿闪乭丂绰郪閿＃"));
         return ErrorCode.Success;
      } else {
         if(a.g.selectById(a1.getParentId()).getLeaf().intValue() == 1) {
            DepartmentDO var2 = new DepartmentDO();
            var2.setId(a1.getParentId());
            var2.setLeaf(Integer.valueOf(0));
            int a2 = a.g.update(var2);
            if(0 >= a2) {
               LogHelper.error(RoleMenuVO.ALLATORIxDEMO("旧墜邿闪扇劝｛俬敮丈绰郪閿犴恖丸牡芀烮夳赲＃"), ErrorCode.FailedToUpdateRecord.getCode());
               return ErrorCode.FailedToUpdateRecord;
            }
         }

         return ErrorCode.Success;
      }
   }

   public List beforeRemove(String a1, String a2) {
      DepartmentDO var3 = new DepartmentDO();
      var3.setCode(a1);
      var3.setSpaceId(a2);
      ArrayList a3 = new ArrayList();
      List a4;
      if(ListUtil.isNullOrEmpty(a4 = a.g.selectDeptNotEmpty(var3))) {
         return new ArrayList();
      } else {
         Iterator a5;
         Iterator var10000 = a5 = a4.iterator();

         while(var10000.hasNext()) {
            var3 = (DepartmentDO)a5.next();
            if(null == var3) {
               var10000 = a5;
            } else {
               a3.add(var3.getName());
               var10000 = a5;
            }
         }

         return a3;
      }
   }

   // $FF: synthetic method
   private ErrorCode ALLATORIxDEMO(DepartmentDO a1) {
      if("0".equals(a1.getParentId())) {
         LogHelper.debug(RoleMenuVO.ALLATORIxDEMO("袩剷陦邿闪乭丂绰郪閿＃"));
         return ErrorCode.Success;
      } else {
         if(ListUtil.isNullOrEmpty(a.g.queryList(a1.getParentId(), (String)null))) {
            DepartmentDO var2 = new DepartmentDO();
            var2.setId(a1.getParentId());
            var2.setLeaf(Integer.valueOf(1));
            a.g.update(var2);
         }

         return ErrorCode.Success;
      }
   }

   public DepartmentVO item(String a1) {
      DepartmentDO a2;
      if((a2 = a.g.selectById(a1)) == null) {
         LogHelper.debug(RoleMenuVO.ALLATORIxDEMO("郪閿俣怸迖嚉丸稭＃"));
         return null;
      } else {
         DepartmentVO var2 = new DepartmentVO();
         BeanUtils.copyProperties(a2, var2);
         return var2;
      }
   }

   // $FF: synthetic method
   private boolean ALLATORIxDEMO(String a1, String a2) {
      return a.e.selectRolesReferencedCount(a1, a2) > 0;
   }
}
