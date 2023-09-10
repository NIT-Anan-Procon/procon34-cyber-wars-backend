package jp.ac.anan.procon.CYBER_WARS.dto.utility;

import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class HttpClientErrorHandlerResponse {

    private final boolean Error;

    private final ResponseEntity<?> responseEntity;

}
