package jp.ac.anan.procon.cyber_wars.domain.dto.user.credential;

import lombok.Data;

@Data
public class IsLoggedInResponse {

  private final boolean loggedIn;

  private final String name;
}
