package yb.ecp.fast.user.infra.datasource;

import com.alibaba.druid.support.http.StatViewServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(
   urlPatterns = {"/druid/*"},
   initParams = {      @WebInitParam(
         name = "loginUsername",
         value = "admin"
      ),       @WebInitParam(
         name = "loginPassword",
         value = "123456"
      ),       @WebInitParam(
         name = "resetEnable",
         value = "false"
      )}
)
public class DruidStatViewServlet extends StatViewServlet {

}
