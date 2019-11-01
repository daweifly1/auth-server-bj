package yb.ecp.fast;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import yb.ecp.fast.user.dao.entity.TemplateDO;
import yb.ecp.fast.user.service.VO.RoleUserVO;

@Configuration
@EnableSwagger2
public class AuthServerSwagger2 {

   @Bean
   public Docket createRestApi() {
      return (new Docket(DocumentationType.SWAGGER_2)).apiInfo(a.ALLATORIxDEMO()).select().apis(RequestHandlerSelectors.basePackage(TemplateDO.ALLATORIxDEMO("GTS]FP_EJKE[DA[T"))).paths(PathSelectors.any()).build();
   }

   // $FF: synthetic method
   private ApiInfo ALLATORIxDEMO() {
      return (new ApiInfoBuilder()).title(RoleUserVO.ALLATORIxDEMO("5\r\bF?\t乐伙畕5\nO柢庇485) \b\n]\'-/")).description(TemplateDO.ALLATORIxDEMO("曊夬MA_QYSL盆充方竖诉讉闐宮罯ＬVBJFEIWYQ[D_Q")).contact(new Contact(RoleUserVO.ALLATORIxDEMO(""), "", TemplateDO.ALLATORIxDEMO("ZSHvXWMBS]FUQ["))).version(RoleUserVO.ALLATORIxDEMO("LHM")).build();
   }

}
