package yb.ecp.fast.user.infra;


public abstract class AuthCode {

   private static final long m = 1000L;
   private static final long g = 1300L;
   private static final long d = 1500L;
   private static final long L = 1100L;
   private static final long e = 1400L;
   private static final long ALLATORIxDEMO = 1600L;



   public static class Account {

      public static final long UPDATE_LOGIN_NAME = 1011L;
      public static final long REMOVE_ALL_PASSWORD = 1006L;
      public static final long ADD_WITH_PWD = 1005L;
      public static final long INSERT = 1001L;
      public static final long RESET_PASSWORD = 1010L;
      public static final long INSERT_PASSWORD = 1007L;
      public static final long SET_NEW_PASSWORD = 1008L;
      public static final long REMOVE_BY_USERID = 1004L;
      public static final long UPDATE_PASSWORD = 1009L;
      public static final long REMOVE_BY_LOGINNAME = 1003L;
      public static final long QUERY = 1002L;


   }

   public static class Template {

      public static final long BATCH_REMOVE_AUTHS = 1515L;
      public static final long QUERY_TEMPLATE_MENUS = 0L;
      public static final long QUERY_TEMPLATE_CONFIG = 0L;
      public static final long UPDATE = 1504L;
      public static final long CONFIG_TMP_AUTH = 1506L;
      public static final long CONFIG_TMP_MENU = 1507L;
      public static final long ITEM = 1505L;
      public static final long REMOVE_TEMPLATE_MENUS = 1513L;
      public static final long QUERY_TEMPLATE_AUTH_CODES = 0L;
      public static final long INSERT = 1502L;
      public static final long BATCH_REMOVE = 1503L;
      public static final long REMOVE_TEMPLATE_AUTHS = 1512L;
      public static final long LIST = 1501L;
      public static final long BATCH_REMOVE_MENUS = 1514L;
      public static final long CONFIG_TEMPLATE = 1508L;


   }

   public static class Role {

      public static final long MENUS_AUTHS_ID_BY_ROLE = 0L;
      public static final long ROLE_USER = 1409L;
      public static final long ADD_MENUS_AUTHS_BY_ROLE = 1415L;
      public static final long ALL_ROLES = 1407L;
      public static final long LIST_BY_WORKSPACE = 1406L;
      public static final long DELETE_USER_ROLE = 1410L;
      public static final long ROLE_USERS = 1408L;
      public static final long ITEM = 1405L;
      public static final long ROLE_IDS_BY_USER = 1412L;
      public static final long ROLES_BY_DEPT = 1419L;
      public static final long MENUS_ID_BY_ROLE = 1416L;
      public static final long USER_IDS_BY_ROLE = 1411L;
      public static final long AUTH_CODES_BY_USER = 0L;
      public static final long DELETE = 1402L;
      public static final long ADD = 1401L;
      public static final long ROLE_LIST_BY_USER = 1413L;
      public static final long BATCH_DELETE = 1403L;
      public static final long ADD_MENUS_BY_ROLE = 1414L;
      public static final long UPDATE = 1404L;


   }

   public static class Workspace {

      public static final long LIST = 1601L;
      public static final long UPDATE = 1604L;
      public static final long BATCH_REMOVE = 1603L;
      public static final long ADD = 1602L;
      public static final long REMOVE = 1607L;
      public static final long ITEM = 1605L;
      public static final long UPDATE_TEMPLATE = 1606L;


   }

   public static class User {

      public static final long ADD = 1301L;
      public static final long UPDATE_SELF = 1307L;
      public static final long QUERY_USER_INFO = 1304L;
      public static final long UPDATE_USER = 1308L;
      public static final long ALL_USER = 1312L;
      public static final long REMOVES = 1306L;
      public static final long UPDATE_BY_ACCOUNT = 1309L;
      public static final long LOGIN_USER_INFO = 1303L;
      public static final long LIST = 1305L;
      public static final long USER_INFO_BY_OAUTH2 = 1311L;
      public static final long LOCKED = 1310L;
      public static final long DETAIL = 0L;


   }

   public static class Department {

      public static final long UPDATE = 1104L;
      public static final long INSERT = 1103L;
      public static final long ITEM = 1102L;
      public static final long LIST = 1101L;
      public static final long REMOVE = 1105L;


   }
}
