package yb.ecp.fast.user.infra.datainit;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import yb.ecp.fast.infra.infra.log.LogHelper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class ScriptLoader {

   public static final String SPLIT = ";";


   public static String[] load(String a) {
      InputStream var1 = null;
      String var2 = null;
      boolean var12 = false;

      String var10000;
      label116: {
         label115: {
            label114: {
               label113: {
                  try {
                     var12 = true;
                      var2 = StreamUtils.copyToString(var1 = (new ClassPathResource(a)).getInputStream(), Charset.forName("UTF-8"));
                     var12 = false;
                     break label114;
                  } catch (FileNotFoundException var17) {
                      var12 = false;
                     break label113;
                  } catch (IOException var18) {
                     LogHelper.fatal(var18.getMessage(), var18);
                     var12 = false;
                  } finally {
                     if(var12) {
                        try {
                           if(var1 != null) {
                              var1.close();
                           }
                        } catch (IOException var13) {
                           LogHelper.fatal(var13.getMessage(), var13);
                        }

                     }
                  }

                  try {
                     if(var1 != null) {
                        var1.close();
                     }
                     break label115;
                  } catch (IOException var16) {
                     var10000 = var2;
                     LogHelper.fatal(var16.getMessage(), var16);
                     break label116;
                  }
               }

               try {
                  if(var1 != null) {
                     var1.close();
                  }
                  break label115;
               } catch (IOException var15) {
                  var10000 = var2;
                  LogHelper.fatal(var15.getMessage(), var15);
                  break label116;
               }
            }

            try {
               if(var1 != null) {
                  var1.close();
               }
            } catch (IOException var14) {
               var10000 = var2;
               LogHelper.fatal(var14.getMessage(), var14);
               break label116;
            }
         }

         var10000 = var2;
      }

      if(var10000 == null) {
         String[] var20 = new String[0];
          return var20;
      } else {
         return var2.split(";");
      }
   }
}
