package jp.ac.anan.procon.cyber_wars.domain.dto.room;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRequest {

  @NotNull private final boolean difficult;
}
