package jp.ac.anan.procon.CYBER_WARS.dto.utility;

import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class HttpClientErrorHandlerResponse {

  private final boolean Error;

  private final ResponseEntity<?> responseEntity;
}
