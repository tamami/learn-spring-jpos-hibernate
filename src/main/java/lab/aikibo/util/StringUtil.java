package lab.aikibo.util;

import java.math.BigInteger;

public class StringUtil {

  public static String stringPadRight(String s, int n) {
    return String.format("%1$-" + n + "s", s);
  }

  public static String stringPadLeft(String s, int n) {
    return String.format("%1$" + n + "s", s);
  }

  public static String numericPad(String s, int n) {
    return String.format("%1$0" + n + "d", new BigInteger(s));
  }

}
