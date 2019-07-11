package solutions.infinitec.digibank.api;

import lombok.experimental.UtilityClass;

public enum InjectedHeader {
  ACTOR(Names.ACTOR),
  ACCOUNT_ID(Names.ACCOUNT_ID),
  BUSINESS_ID(Names.BUSINESS_ID),
  PARTNER_USER_ID(Names.PARTNER_USER_ID),
  API(Names.API);

  private final String headerName;

  InjectedHeader(final String value) {
    this.headerName = value;
  }

  public String headerName() {
    return headerName;
  }

  @UtilityClass

  public static class Names {
    public static final String ACTOR = "X-Actor";
    public static final String ACCOUNT_ID = "X-AccountId";
    public static final String BUSINESS_ID = "X-BusinessId";
    public static final String PARTNER_USER_ID = "X-PartnerUserId";
    public static final String API = "X-Api";
  }
}
