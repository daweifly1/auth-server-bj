package yb.ecp.fast.user.service;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import yb.ecp.fast.feign.GenClient;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.manager.AuthInfoManager;
import yb.ecp.fast.user.service.VO.AuthInfoVO;

import java.awt.image.BufferedImage;

@Service
public class AuthService {

   @Autowired
   GenClient genClient;
   @Autowired
   AuthInfoManager authInfoManager;
   @Autowired
   DefaultKaptcha defaultKaptcha;
   @Value("${login.verifyCode.default.flag:close}")
   private String verifyCodeFlag;


   public AuthInfoVO createAuthInfo() {
      AuthInfoVO var1;
      (var1 = new AuthInfoVO()).setAuthId((String) genClient.newGuidText().getValue());
      if ("close".equals(verifyCodeFlag.trim())) {
         var1.setVerifyCode("1234");
      } else {
         var1.setVerifyCode(defaultKaptcha.createText());
      }
      authInfoManager.savAuthKaptcha(var1.getAuthId(), var1.getVerifyCode());
      return var1;
   }

   public BufferedImage createVerifyImg(String a1) {
      String var2;
      if (StringUtil.isNullOrSpace(var2 = authInfoManager.getRedisCode(a1))) {
         var2 = defaultKaptcha.createText();
         authInfoManager.savAuthKaptcha(a1, var2);
      }

      return defaultKaptcha.createImage(var2);
   }
}
