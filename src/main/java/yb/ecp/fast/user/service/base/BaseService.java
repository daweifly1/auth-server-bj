package yb.ecp.fast.user.service.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import yb.ecp.fast.infra.infra.PageCommonVO;
import yb.ecp.fast.infra.infra.SearchCommonVO;
import yb.ecp.fast.user.dao.mapper.base.BaseMapper;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.service.VO.RoleMenuVO;
import yb.ecp.fast.user.service.base.BaseTransVODOService;

public class BaseService extends BaseTransVODOService {

   BaseMapper ALLATORIxDEMO;


   @Transactional
   public ErrorCode insert(Object a1) {
      ErrorCode var2;
      if((var2 = a.checkParam(a1)) != ErrorCode.Success) {
         return var2;
      } else {
         a1 = a.getDO(a1);
         return a.ALLATORIxDEMO.insert(a1) > 0?ErrorCode.Success:ErrorCode.FailedToInsertRecord;
      }
   }

   public Object item(String a1) {
      try {
         Object var2 = a.getVO(a.ALLATORIxDEMO.item(a1));
         return var2;
      } catch (Exception var3) {
         return null;
      }
   }

   @Transactional
   public ErrorCode update(Object a1) {
      ErrorCode var2;
      if((var2 = a.checkParam(a1)) != ErrorCode.Success) {
         return var2;
      } else {
         a1 = a.getDO(a1);
         return a.ALLATORIxDEMO.update(a1) > 0?ErrorCode.Success:ErrorCode.FailedToUpdateRecord;
      }
   }

   public PageCommonVO list(SearchCommonVO a1) {
      PageCommonVO var2 = new PageCommonVO();
      PageHelper.orderBy(RoleMenuVO.ALLATORIxDEMO("4p2c#g\bf6v2\"3g$a"));
      PageHelper.startPage(a1.getPageNum().intValue(), a1.getPageSize().intValue());
      List a2;
      List var10002 = a.getVOList(a2 = a.ALLATORIxDEMO.list(a1.getFilters()));
      PageInfo var10004 = new PageInfo;
      var2.<init>(a2);
      var10004.setPageInfo(var2);
      var10002.setPageInfoList(var2);
      return var2;
   }

   protected BaseService(Class a1, Class a2) {
      super(a1, a2);
   }

   public Object item(Integer a1) {
      try {
         Object var2 = a.getVO(a.ALLATORIxDEMO.item(a1));
         return var2;
      } catch (Exception var3) {
         return null;
      }
   }

   protected ErrorCode checkParam(Object a1) {
      return a1 == null?ErrorCode.IllegalArument:ErrorCode.Success;
   }

   protected void addMapper(BaseMapper a1) {
      a.ALLATORIxDEMO = a1;
   }
}
