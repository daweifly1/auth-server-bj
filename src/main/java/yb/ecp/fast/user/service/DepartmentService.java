package yb.ecp.fast.user.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yb.ecp.fast.infra.util.ListUtil;
import yb.ecp.fast.infra.util.Ref;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.dao.entity.DepartmentDO;
import yb.ecp.fast.user.dao.mapper.DepartmentMapper;
import yb.ecp.fast.user.dao.mapper.ProfileMapper;
import yb.ecp.fast.user.dao.mapper.RoleMapper;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.manager.UserContextManager;
import yb.ecp.fast.user.service.VO.DepartmentVO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DepartmentService {

   @Autowired
   private DepartmentMapper departmentMapper;
   @Autowired
   ProfileService profileService;
   @Autowired
   private UserContextManager userContextManager;
   @Autowired
   private RoleMapper roleMapper;
   @Autowired
   ProfileMapper profileMapper;

   public String createDeptCode(String parentId, String spaceId) {
      DepartmentDO departmentDO = this.departmentMapper.selectById(parentId);

      String lastCode = this.departmentMapper.queryLastCode(parentId, spaceId);
      if (StringUtils.isBlank(lastCode)) {
         if ("0".equals(parentId)) {
            return "001";
         }
         return departmentDO.getCode() + "001";
      }
      int codeLength = lastCode.length();

      String begin = lastCode.substring(0, codeLength - 3);
      String end = lastCode.substring(codeLength - 3);
      if ("999".equals(end)) {
         return "";
      }
      String newCode = String.valueOf(Integer.valueOf(end).intValue() + 1);

      int mark = end.length() - newCode.length();
      if (mark > 0) {
         for (; mark > 0; mark--) {
            newCode = "0" + newCode;
         }
      }
      return begin + newCode;
   }

   public ErrorCode removeByCode(String a1, String a2, Ref a3) {
      DepartmentDO var4 = departmentMapper.selectById(a1);
      if(null == var4) {
         return ErrorCode.Success;
      } else if (ALLATORIxDEMO(a1, a2)) {
         ArrayList a5;
         (a5 = new ArrayList()).add(var4.getName());
         a3.set(a5);
         return ErrorCode.FailedToRemoveRecord;
      } else {
         List a4;
         if (!ListUtil.isNullOrEmpty(a4 = beforeRemove(var4.getCode(), a2))) {
            a3.set(a4);
            return ErrorCode.FailedToRemoveRecord;
         } else {
            departmentMapper.removeByCode(var4);
            return ALLATORIxDEMO(var4);
         }
      }
   }

   public List listDept(String a1, String a2) {
      a2 = userContextManager.getWorkspaceId(a2);
      if(StringUtil.isNullOrSpace(a1)) {
         a1 = "0";
      }

      ArrayList var3 = new ArrayList();
      Iterator a3;
      Iterator var10000 = a3 = departmentMapper.queryList(a1, a2).iterator();

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

      int a3 = departmentMapper.checkName(var3);
      if(0 < a3) {
         return ErrorCode.DeptNameExist;
      } else {
         String a4;
         if (StringUtil.isNullOrEmpty(a4 = createDeptCode(var3.getParentId(), var3.getSpaceId()))) {
            return ErrorCode.FailedToNewCode;
         } else {
            var3.setCode(a4);
            var3.setLeaf(Integer.valueOf(1));
            a3 = departmentMapper.insert(var3);
            if(0 >= a3) {
               return ErrorCode.FailedToInsertRecord;
            } else {
               return M(var3);
            }
         }
      }
   }

   public ErrorCode update(DepartmentVO a1) {
      DepartmentDO var2 = departmentMapper.selectById(a1.getId());
      if(null == var2) {
         return ErrorCode.FailedToUpdateRecord;
      } else if(var2.getName().equals(a1.getName())) {
         return ErrorCode.Success;
      } else {
         var2.setName(a1.getName());
         int a2 = departmentMapper.checkName(var2);
         if(0 < a2) {
            return ErrorCode.DeptNameExist;
         } else {
            a2 = departmentMapper.update(var2);
            if(0 >= a2) {
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
         StringBuffer var4;
         while(true) {
            DepartmentVO var3 = item(a1);
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

            var2.insert(0, "/");
            a1 = var3.getParentId();
         }

         return var4.toString();
      }
   }

   public List queryDeptList(DepartmentVO a1) {
      DepartmentDO var2 = new DepartmentDO();
      ArrayList var3 = new ArrayList();
      BeanUtils.copyProperties(a1, var2);
      Iterator a2;
      Iterator var10000 = a2 = departmentMapper.queryDeptList(var2).iterator();

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
         return ErrorCode.Success;
      } else {
         if (departmentMapper.selectById(a1.getParentId()).getLeaf().intValue() == 1) {
            DepartmentDO var2 = new DepartmentDO();
            var2.setId(a1.getParentId());
            var2.setLeaf(Integer.valueOf(0));
            int a2 = departmentMapper.update(var2);
            if(0 >= a2) {
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
      if (ListUtil.isNullOrEmpty(a4 = departmentMapper.selectDeptNotEmpty(var3))) {
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
         return ErrorCode.Success;
      } else {
         if (ListUtil.isNullOrEmpty(departmentMapper.queryList(a1.getParentId(), (String) null))) {
            DepartmentDO var2 = new DepartmentDO();
            var2.setId(a1.getParentId());
            var2.setLeaf(Integer.valueOf(1));
            departmentMapper.update(var2);
         }

         return ErrorCode.Success;
      }
   }

   public DepartmentVO item(String a1) {
      DepartmentDO a2;
      if ((a2 = departmentMapper.selectById(a1)) == null) {
         return null;
      } else {
         DepartmentVO var2 = new DepartmentVO();
         BeanUtils.copyProperties(a2, var2);
         return var2;
      }
   }

   // $FF: synthetic method
   private boolean ALLATORIxDEMO(String a1, String a2) {
      return roleMapper.selectRolesReferencedCount(a1, a2) > 0;
   }
}
