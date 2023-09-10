package jp.ac.anan.procon.CYBER_WARS.dto.room;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRequest {

  @NotNull private final boolean difficult;
}
