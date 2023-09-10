package jp.ac.anan.procon.CYBER_WARS.controller;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.game.attack.SendKeyRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.game.attack.UseHintRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.utility.HttpClientErrorHandlerResponse;
import jp.ac.anan.procon.CYBER_WARS.service.GameAttackService;
import jp.ac.anan.procon.CYBER_WARS.utility.HttpClientErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game/attack")
@RequiredArgsConstructor
public class GameAttackController {

  private final HttpClientErrorHandler httpClientErrorHandler;
  private final GameAttackService gameAttackService;

  // アタックフェーズ：課題取得
  @GetMapping("/challenge")
  @ResponseBody
  public ResponseEntity<?> fetchChallenge(final HttpServletRequest httpServletRequest) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(null, httpServletRequest);
    if (httpClientErrorHandlerResponse.isError()) {
      return httpClientErrorHandlerResponse.getResponseEntity();
    }
    return ResponseEntity.ok(gameAttackService.fetchChallenge(httpServletRequest));
  }

  // アタックフェーズ：ヒント使用
  @PostMapping("/hint")
  public ResponseEntity<?> useHint(
      @Validated @RequestBody final UseHintRequest useHintRequest,
      final BindingResult bindingResult,
      final HttpServletRequest httpServletRequest) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(bindingResult, httpServletRequest);
    if (httpClientErrorHandlerResponse.isError()) {
      return httpClientErrorHandlerResponse.getResponseEntity();
    }
    gameAttackService.useHint(useHintRequest, httpServletRequest);
    return ResponseEntity.ok().build();
  }

  // アタックフェーズ：キー送信
  @PostMapping("/key")
  @ResponseBody
  public ResponseEntity<?> sendKey(
      @Validated @RequestBody final SendKeyRequest sendKeyRequest,
      final BindingResult bindingResult,
      final HttpServletRequest httpServletRequest) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(bindingResult, httpServletRequest);
    if (httpClientErrorHandlerResponse.isError()) {
      return httpClientErrorHandlerResponse.getResponseEntity();
    }
    return ResponseEntity.ok(gameAttackService.sendKey(sendKeyRequest, httpServletRequest));
  }
}
