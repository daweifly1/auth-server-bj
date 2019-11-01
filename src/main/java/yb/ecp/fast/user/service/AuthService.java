package yb.ecp.fast.user.service;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import java.awt.image.BufferedImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import yb.ecp.fast.feign.GenClient;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.dao.entity.TemplateDO;
import yb.ecp.fast.user.manager.AuthInfoManager;
import yb.ecp.fast.user.service.VO.AuthInfoVO;
import yb.ecp.fast.user.service.VO.RoleUserVO;

@Service
public class AuthService {

   @Autowired
   GenClient d;
   @Autowired
   AuthInfoManager L;
   @Autowired
   DefaultKaptcha e;
   @Value("${login.verifyCode.default.flag:close}")
   private String ALLATORIxDEMO;


   public AuthInfoVO createAuthInfo() {
      AuthInfoVO var1;
      (var1 = new AuthInfoVO()).setAuthId((String)a.d.newGuidText().getValue());
      AuthService var10000;
      if(RoleUserVO.ALLATORIxDEMO("\t\r").equals(a.ALLATORIxDEMO.trim())) {
         var10000 = a;
         var1.setVerifyCode(TemplateDO.ALLATORIxDEMO("\r"));
      } else {
         var1.setVerifyCode(a.e.createText());
         var10000 = a;
      }

      var10000.L.savAuthKaptcha(var1.getAuthId(), var1.getVerifyCode());
      return var1;
   }

   public BufferedImage createVerifyImg(String a1) {
      String var2;
      if(StringUtil.isNullOrSpace(var2 = a.L.getRedisCode(a1))) {
         var2 = a.e.createText();
         a.L.savAuthKaptcha(a1, var2);
      }

      return a.e.createImage(var2);
   }
}
