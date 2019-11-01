package yb.ecp.fast.infra.util;

import java.security.SecureRandom;
import yb.ecp.fast.infra.security.CryptoUtil;

public class RandomStringUtil
{
  public static String RandomString(int length)
  {
    try
    {
      SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
      byte[] bytes = new byte[length / 2];
      random.nextBytes(bytes);
      return CryptoUtil.byteArrayToHexString(bytes);
    }
    catch (Exception e) {}
    return "";
  }
}
