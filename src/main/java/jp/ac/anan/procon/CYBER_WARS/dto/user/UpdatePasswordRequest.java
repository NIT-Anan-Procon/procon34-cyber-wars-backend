package jp.ac.anan.procon.CYBER_WARS.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePasswordRequest {

  @NotBlank
  @Size(max = 100)
  private final String password;
}
