package yb.ecp.fast.user.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import yb.ecp.fast.user.dao.entity.ProfileDO;
import yb.ecp.fast.user.dao.mapper.base.BaseMapper;
import yb.ecp.fast.user.service.VO.ProfileConditionVO;

@Mapper
public interface ProfileMapper extends BaseMapper {

   int removeById(String var1);

   int update(ProfileDO profileDO);

   List list(ProfileConditionVO var1);

   Integer queryCountByDept(String deptId);

   int insert(ProfileDO var1);

   int getCountByMobile(ProfileDO var1);

   ProfileDO selectById(String var1);
}
