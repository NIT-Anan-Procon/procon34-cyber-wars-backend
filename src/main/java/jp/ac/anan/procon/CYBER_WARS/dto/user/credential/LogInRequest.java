package jp.ac.anan.procon.CYBER_WARS.dto.user.credential;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LogInRequest {

  @NotBlank
  @Size(max = 20)
  private final String name;

  @NotBlank
  @Size(max = 100)
  private final String password;
}
