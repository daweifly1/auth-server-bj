package yb.ecp.fast.user.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import yb.ecp.fast.infra.infra.log.LogHelper;

@Component
public class AuthInfoManager {

   @Autowired
   private JedisPool ALLATORIxDEMO;


   public String getRedisCode(String a1) {
      String var2 = null;
      Jedis var3 = new Jedis();

      label39: {
         try {
            try {
               var2 = (var3 = ALLATORIxDEMO.getResource()).get(a1);
               break label39;
            } catch (Exception var6) {
               LogHelper.fatal(var6.getMessage(), var6);
            }
         } catch (Throwable var7) {
            var3.close();
            throw var7;
         }

         var3.close();
         return var2;
      }

      var3.close();
      return var2;
   }

   public void savAuthKaptcha(String a1, String a2) {
      Jedis var3 = new Jedis();

      label39: {
         try {
            try {
               var3 = ALLATORIxDEMO.getResource();
               var3.set(a1, a2);
               var3.expire(a1, 60);
               break label39;
            } catch (Exception var6) {
               LogHelper.fatal(var6.getMessage(), var6);
            }
         } catch (Throwable var7) {
            var3.close();
            throw var7;
         }

         var3.close();
         return;
      }

      var3.close();
   }

   public String getVerifyCode(String a1) {
      String var2 = null;
      Jedis var3 = new Jedis();

      label39: {
         try {
            try {
               Jedis var10000 = var3 = ALLATORIxDEMO.getResource();
               var2 = var10000.get(a1);
               var10000.del(a1);
               break label39;
            } catch (Exception var6) {
               LogHelper.fatal(var6.getMessage(), var6);
            }
         } catch (Throwable var7) {
            var3.close();
            throw var7;
         }

         var3.close();
         return var2;
      }

      var3.close();
      return var2;
   }
}
