package yb.ecp.fast.user.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yb.ecp.fast.user.dao.entity.MenuDO;

@Mapper
public interface MenuMapper {

   List getChildList(Integer var1);

   List getMenuByRoleIds(List var1);

   int insert(MenuDO var1);

   List selectAuthListByMenu(Integer var1);

   int updateByPrimaryKeySelective(MenuDO var1);

   List selectBySite(Integer var1);

   List listDisplayMenuId();

   List selectMenus(@Param("all") Boolean var1, @Param("channel") int var2);

   List selectAuthListByRole(String var1);

   List listShownMenu();

   List getParentList();

   List selectByMenuIds(List var1);

   List getAllList();

   Integer getAuthIdByMenu(Integer var1);

   List getChildrenBySite(@Param("parentId") Integer var1, @Param("site") Integer var2);

   List getDisplayedList(Integer site);

   MenuDO selectByPrimaryKey(Integer var1);

   int updateByPrimaryKey(MenuDO var1);

   int deleteByPrimaryKey(Integer var1);

   int insertSelective(MenuDO var1);

   List getAuthIdList(Integer var1);

   Integer checkIsExitChild(Integer var1);

   List queryListMenuForConfig(@Param("parentId") Integer var1, @Param("site") Integer var2);
}
