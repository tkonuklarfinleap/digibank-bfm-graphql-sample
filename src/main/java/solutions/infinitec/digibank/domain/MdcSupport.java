package solutions.infinitec.digibank.domain;

import org.slf4j.MDC;

public enum MdcSupport {
  ACTOR,
  ACCOUNT_ID,
  BUSINESS_ID,
  PARTNER_USER_ID,
  API;

  public static void clear() {
    MDC.clear();
  }

  public void put(final String value) {
    MDC.put(name().toLowerCase(), value);
  }

  public String get() {
    return MDC.get(name().toLowerCase());
  }
}
