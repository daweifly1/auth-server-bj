package yb.ecp.fast.user.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import yb.ecp.fast.user.dao.entity.TemplateDO;

@Mapper
public interface TemplateMapper {

   List queryList();

   int insert(TemplateDO var1);

   int updateById(TemplateDO var1);

   TemplateDO selectById(String var1);

   int removeById(String var1);
}
