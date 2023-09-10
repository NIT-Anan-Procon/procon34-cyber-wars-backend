package jp.ac.anan.procon.CYBER_WARS.controller;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.game.defense.SendCodeRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.utility.HttpClientErrorHandlerResponse;
import jp.ac.anan.procon.CYBER_WARS.service.GameDefenseService;
import jp.ac.anan.procon.CYBER_WARS.utility.HttpClientErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game/defense")
@RequiredArgsConstructor
public class GameDefenseController {

  private final HttpClientErrorHandler httpClientErrorHandler;
  private final GameDefenseService gameDefenseService;

  // ディフェンスフェーズ：コード取得
  @GetMapping("/code")
  @ResponseBody
  public ResponseEntity<?> fetchCode(final HttpServletRequest httpServletRequest) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(null, httpServletRequest);
    if (httpClientErrorHandlerResponse.isError()) {
      return httpClientErrorHandlerResponse.getResponseEntity();
    }
    return ResponseEntity.ok(gameDefenseService.fetchCode(httpServletRequest));
  }

  // ディフェンスフェーズ：コード送信
  @PutMapping("/code")
  @ResponseBody
  public ResponseEntity<?> sendCode(
      @Validated @RequestBody final SendCodeRequest sendCodeRequest,
      final BindingResult bindingResult,
      final HttpServletRequest httpServletRequest) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(bindingResult, httpServletRequest);
    if (httpClientErrorHandlerResponse.isError()) {
      return httpClientErrorHandlerResponse.getResponseEntity();
    }
    return ResponseEntity.ok(gameDefenseService.sendCode(sendCodeRequest, httpServletRequest));
  }
}
