package yb.ecp.fast.user.infra.datainit;

public enum DatabaseType {

   MYSQL("driver_mysql", 0, "mysql"),
   ORACLE("driver_oracle", 2, "oracle");
   private String driver;

   private Integer code;

   private String desc;

   DatabaseType(String driver, Integer code, String desc) {
      this.driver = driver;
      this.code = code;
      this.desc = desc;
   }
}
