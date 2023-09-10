package jp.ac.anan.procon.CYBER_WARS.dto.user.credential;

import lombok.Data;

@Data
public class IsLoggedInResponse {

  private final boolean loggedIn;

  private final String name;
}
