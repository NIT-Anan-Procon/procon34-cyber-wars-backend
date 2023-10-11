package jp.ac.anan.procon.cyber_wars.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.cyber_wars.application.service.GameDefenseService;
import jp.ac.anan.procon.cyber_wars.application.utility.HttpClientErrorHandler;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.defense.SendCodeRequest;
import jp.ac.anan.procon.cyber_wars.domain.dto.utility.HttpClientErrorHandlerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game/defense")
@RequiredArgsConstructor
public class GameDefenseController {
  private final HttpClientErrorHandler httpClientErrorHandler;
  private final GameDefenseService gameDefenseService;

  // ディフェンスフェーズ：コード送信
  @PutMapping("/code")
  @ResponseBody
  public ResponseEntity<?> sendCode(
      @RequestHeader("X-CSRF-Token") String clientCsrfToken,
      @RequestBody @Validated final SendCodeRequest sendCodeRequest,
      final BindingResult bindingResult,
      final HttpServletRequest httpServletRequest) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(clientCsrfToken, bindingResult, httpServletRequest);
    if (httpClientErrorHandlerResponse.error()) {
      return httpClientErrorHandlerResponse.responseEntity();
    }
    return ResponseEntity.ok(gameDefenseService.sendCode(sendCodeRequest, httpServletRequest));
  }
}
