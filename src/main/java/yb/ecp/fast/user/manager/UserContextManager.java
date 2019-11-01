package yb.ecp.fast.user.manager;

import java.util.Iterator;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import yb.ecp.fast.infra.infra.log.LogHelper;
import yb.ecp.fast.infra.util.ObjectUtil;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.infra.ErrorCode;

@Component
public class UserContextManager {

   public static final String REDIS_EXCEPTION = "redis exception";
   public static final String DATA = "data:";
   public static final String AUTH = "auth:";
   @Autowired
   private JedisPool e;
   @Value("${fast.user.session.ttl}")
   int ALLATORIxDEMO;
   public static final String SPACE_ID = "spaceId:";


   public ErrorCode cacheUser(String a1, Object a2, Integer[] a3, String a4) {
      ErrorCode a5 = a.cacheUserData(a1, a2);
      if(0 != a5.getCode()) {
         return ErrorCode.FailedToCacheUserDate;
      } else {
         a5 = a.cacheAuthCode(a1, a3);
         if(0 != a5.getCode()) {
            return ErrorCode.FailedToCacheAuthCode;
         } else {
            if(!StringUtil.isNullOrEmpty(a4)) {
               a5 = a.cacheWorkspase(a1, a4);
               if(0 != a5.getCode()) {
                  return ErrorCode.FailedToCacheUserDate;
               }
            }

            a5 = a.renewLeaseSession(a1);
            return 0 != a5.getCode()?ErrorCode.FailedToRenewLeaseSession:ErrorCode.Success;
         }
      }
   }

   public ErrorCode cacheAuthCode(String a1, Integer[] a2) {
      Jedis var3 = new Jedis();

      label76: {
         try {
            try {
               var3 = a.e.getResource();
               a1 = (new StringBuilder()).insert(0, "auth:").append(a1).toString();
               if(var3.exists(a1).booleanValue()) {
                  var3.del(a1);
               }

               int var4 = (a2 = a2).length;
               int var5 = 0;

               while(true) {
                  if(var5 >= var4) {
                     break label76;
                  }

                  int var6 = a2[var5].intValue();
                  String[] var10002 = new String[1];
                  boolean var10004 = true;
                  String var10006 = Integer.toString(var6);
                  ++var5;
                  ((Object[])true)[0] = (boolean)var10006;
                  a1.sadd(var10002, true);
               }
            } catch (Exception var9) {
               LogHelper.fatal("redis exception", var9);
            }
         } catch (Throwable var10) {
            var3.close();
            throw var10;
         }

         var3.close();
         return ErrorCode.Success;
      }

      var3.close();
      return ErrorCode.Success;
   }

   public ErrorCode cacheUserData(String a1, Object a2) {
      Jedis var3 = new Jedis();

      label65: {
         try {
            try {
               var3 = a.e.getResource();
               a1 = (new StringBuilder()).insert(0, "data:").append(a1).toString();
               if(var3.exists(a1).booleanValue()) {
                  var3.del(a1);
               }

               var3.set(a1.getBytes(), ObjectUtil.serialize(a2));
               break label65;
            } catch (Exception var6) {
               LogHelper.fatal("redis exception", var6);
            }
         } catch (Throwable var7) {
            var3.close();
            throw var7;
         }

         var3.close();
         return ErrorCode.Success;
      }

      var3.close();
      return ErrorCode.Success;
   }

   public String getWorkspaceId(String a1) {
      Jedis var2 = new Jedis();

      String var3;
      label61: {
         label60: {
            try {
               try {
                  var2 = a.e.getResource();
                  a1 = (new StringBuilder()).insert(0, "spaceId:").append(a1).toString();
                  if(!var2.exists(a1).booleanValue()) {
                     var3 = null;
                     break label61;
                  }

                  var3 = String.valueOf(var2.get(a1));
                  break label60;
               } catch (Exception var6) {
                  LogHelper.fatal("redis exception", var6);
               }
            } catch (Throwable var7) {
               var2.close();
               throw var7;
            }

            var2.close();
            return null;
         }

         var2.close();
         return var3;
      }

      var2.close();
      return var3;
   }

   public ErrorCode clearSession(String a1) {
      if(a1 == null) {
         return ErrorCode.Success;
      } else {
         Jedis var2 = new Jedis();

         label92: {
            try {
               try {
                  var2 = a.e.getResource();
                  String var3 = (new StringBuilder()).insert(0, "auth:").append(a1).toString();
                  if(var2.exists(var3).booleanValue()) {
                     var2.del(var3);
                  }

                  String var5 = (new StringBuilder()).insert(0, "data:").append(a1).toString();
                  if(var2.exists(var5).booleanValue()) {
                     var2.del(var5);
                  }

                  a1 = (new StringBuilder()).insert(0, "spaceId:").append(a1).toString();
                  if(var2.exists(a1).booleanValue()) {
                     var2.del(a1);
                  }
                  break label92;
               } catch (Exception var8) {
                  LogHelper.fatal("redis exception", var8);
               }
            } catch (Throwable var9) {
               var2.close();
               throw var9;
            }

            var2.close();
            return ErrorCode.Success;
         }

         var2.close();
         return ErrorCode.Success;
      }
   }

   public Integer[] getAuthCode(String a1) {
      Jedis var2 = new Jedis();

      Set var3;
      label80: {
         label79: {
            try {
               try {
                  var2 = a.e.getResource();
                  a1 = (new StringBuilder()).insert(0, "auth:").append(a1).toString();
                  if(!var2.exists(a1).booleanValue()) {
                     var3 = null;
                     break label80;
                  }

                  Integer[] var10000 = new Integer[(var3 = var2.smembers(a1)).size()];
                  Object var4 = true;
                  int var5 = 0;

                  Iterator var11;
                  int var10003;
                  Integer var10004;
                  for(Iterator var10001 = var11 = var3.iterator(); var10001.hasNext(); ((Object[])var4)[var10003] = var10004) {
                     String var6 = (String)var11.next();
                     var10001 = var11;
                     var10003 = var5;
                     var10004 = Integer.valueOf(Integer.parseInt(var6));
                     ++var5;
                  }

                  var3 = (Set)var4;
                  break label79;
               } catch (Exception var9) {
                  LogHelper.fatal("redis exception", var9);
               }
            } catch (Throwable var10) {
               var2.close();
               throw var10;
            }

            var2.close();
            return null;
         }

         var2.close();
         return var3;
      }

      var2.close();
      return var3;
   }

   public ErrorCode renewLeaseSession(String a1) {
      Integer var2 = Integer.valueOf(a.ALLATORIxDEMO);
      Jedis var3 = new Jedis();

      label83: {
         try {
            try {
               var3 = a.e.getResource();
               String var4 = (new StringBuilder()).insert(0, "auth:").append(a1).toString();
               if(!var3.exists(var4).booleanValue()) {
                  var3.expire(var4, var2.intValue());
               }

               String var6 = (new StringBuilder()).insert(0, "data:").append(a1).toString();
               if(var3.exists(var6).booleanValue()) {
                  var3.expire(var6, var2.intValue());
               }

               a1 = (new StringBuilder()).insert(0, "spaceId:").append(a1).toString();
               if(var3.exists(a1).booleanValue()) {
                  var3.expire(a1, var2.intValue());
               }
               break label83;
            } catch (Exception var9) {
               LogHelper.fatal("redis exception", var9);
            }
         } catch (Throwable var10) {
            var3.close();
            throw var10;
         }

         var3.close();
         return ErrorCode.Success;
      }

      var3.close();
      return ErrorCode.Success;
   }

   public ErrorCode checkAuthCode(String a1, Integer a2) {
      Jedis var3 = new Jedis();

      ErrorCode var4;
      label77: {
         ErrorCode a3;
         label85: {
            label86: {
               try {
                  try {
                     var3 = a.e.getResource();
                     a1 = (new StringBuilder()).insert(0, "auth:").append(a1).toString();
                     if(!var3.exists(a1).booleanValue()) {
                        var4 = ErrorCode.NeedLogin;
                        break label77;
                     }

                     if(var3.sismember(a1, Integer.toString(a2.intValue())).booleanValue()) {
                        a3 = ErrorCode.Success;
                        break label85;
                     }
                     break label86;
                  } catch (Exception var7) {
                     LogHelper.fatal("redis exception", var7);
                  }
               } catch (Throwable var8) {
                  var3.close();
                  throw var8;
               }

               var3.close();
               return ErrorCode.NoAuthorization;
            }

            var3.close();
            return ErrorCode.NoAuthorization;
         }

         var3.close();
         return a3;
      }

      var3.close();
      return var4;
   }

   public ErrorCode cacheWorkspase(String a1, String a2) {
      Jedis var3 = new Jedis();

      label65: {
         try {
            try {
               var3 = a.e.getResource();
               a1 = (new StringBuilder()).insert(0, "spaceId:").append(a1).toString();
               if(var3.exists(a1).booleanValue()) {
                  var3.del(a1);
               }

               var3.set(a1, a2);
               break label65;
            } catch (Exception var6) {
               LogHelper.fatal("redis exception", var6);
            }
         } catch (Throwable var7) {
            var3.close();
            throw var7;
         }

         var3.close();
         return ErrorCode.Success;
      }

      var3.close();
      return ErrorCode.Success;
   }

   public Object getUserData(String a1) {
      Jedis var2 = new Jedis();

      Object var3;
      label61: {
         label60: {
            try {
               try {
                  var2 = a.e.getResource();
                  a1 = (new StringBuilder()).insert(0, "data:").append(a1).toString();
                  if(!var2.exists(a1).booleanValue()) {
                     var3 = null;
                     break label61;
                  }

                  var3 = ObjectUtil.deserialize(var2.get(a1.getBytes()));
                  break label60;
               } catch (Exception var6) {
                  LogHelper.fatal("redis exception", var6);
               }
            } catch (Throwable var7) {
               var2.close();
               throw var7;
            }

            var2.close();
            return null;
         }

         var2.close();
         return var3;
      }

      var2.close();
      return var3;
   }

}
