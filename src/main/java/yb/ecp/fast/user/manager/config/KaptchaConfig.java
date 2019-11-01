package yb.ecp.fast.user.manager.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import yb.ecp.fast.user.service.VO.RoleMenuVO;
import yb.ecp.fast.user.service.VO.RoleUserVO;

@Component
public class KaptchaConfig {

   @Bean
   public DefaultKaptcha getDefaultKaptcha() {
      DefaultKaptcha var1 = new DefaultKaptcha();
      Properties var2;
      (var2 = new Properties()).setProperty(RoleUserVO.ALLATORIxDEMO("\r\tS"), RoleMenuVO.ALLATORIxDEMO("9m"));
      var2.setProperty(RoleUserVO.ALLATORIxDEMO("\r\tSH\t\t"), RoleMenuVO.ALLATORIxDEMO("f2b.f5n.n2"));
      var2.setProperty(RoleUserVO.ALLATORIxDEMO("\rH\t\r\bS \b\tH\t\t"), RoleMenuVO.ALLATORIxDEMO("f;{2{3`3"));
      var2.setProperty(RoleUserVO.ALLATORIxDEMO("\rHH\n"), RoleMenuVO.ALLATORIxDEMO("o0"));
      var2.setProperty(RoleUserVO.ALLATORIxDEMO("\r\tSS\t"), RoleMenuVO.ALLATORIxDEMO("d4"));
      var2.setProperty(RoleUserVO.ALLATORIxDEMO("\r\tS\t\tH\tS"), RoleMenuVO.ALLATORIxDEMO("e:"));
      var2.setProperty(RoleUserVO.ALLATORIxDEMO("\rH\tH"), RoleMenuVO.ALLATORIxDEMO("4m3g"));
      var2.setProperty(RoleUserVO.ALLATORIxDEMO("\r\tS\t\tHS\n\b"), RoleMenuVO.ALLATORIxDEMO("6"));
      var2.setProperty(RoleUserVO.ALLATORIxDEMO("\rH\t\r\bS \b\tH"), RoleMenuVO.ALLATORIxDEMO("寜佑{極伄.忹轭隒黓"));
      var2.setProperty(RoleUserVO.ALLATORIxDEMO("\r\tS\t\tHS\t\b"), RoleMenuVO.ALLATORIxDEMO("c5a3ge1c7a5oe1{9i9r$z"));
      var2.setProperty(RoleUserVO.ALLATORIxDEMO("\rH\t\r\bSH"), RoleMenuVO.ALLATORIxDEMO("1"));
      var2.setProperty(RoleUserVO.ALLATORIxDEMO("\rH\rSS \t"), RoleMenuVO.ALLATORIxDEMO("u?k#g"));
      var2.setProperty(RoleUserVO.ALLATORIxDEMO("\rH\rSS"), RoleMenuVO.ALLATORIxDEMO("u?k#g"));
      var2.setProperty(RoleUserVO.ALLATORIxDEMO("\r\tS\bS"), RoleMenuVO.ALLATORIxDEMO("4m:,0m8e;gya8f2,<c\'v4j6,>o\'nyL8L8k$g"));
      var2.setProperty(RoleUserVO.ALLATORIxDEMO("\rH\b S"), RoleMenuVO.ALLATORIxDEMO("4m:,0m8e;gya8f2,<c\'v4j6,>o\'nyU6v2pk\'r;g"));
      Config var3 = new Config(var2);
      var1.setConfig(var3);
      return var1;
   }
}
