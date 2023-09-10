package jp.ac.anan.procon.cyber_wars.domain.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

  @NotBlank
  @Size(max = 20)
  private final String name;

  @NotBlank
  @Size(max = 100)
  private final String password;
}
