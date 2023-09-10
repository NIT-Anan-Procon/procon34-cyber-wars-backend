package jp.ac.anan.procon.cyber_wars.domain.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateNameRequest {

  @NotBlank
  @Size(max = 20)
  private final String name;
}
