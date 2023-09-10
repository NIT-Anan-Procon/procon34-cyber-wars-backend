package jp.ac.anan.procon.CYBER_WARS.controller;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.game.battle.SendKeyRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.utility.HttpClientErrorHandlerResponse;
import jp.ac.anan.procon.CYBER_WARS.service.GameBattleService;
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
@RequestMapping("/game/battle")
@RequiredArgsConstructor
public class GameBattleController {

  private final HttpClientErrorHandler httpClientErrorHandler;
  private final GameBattleService gameBattleService;

  // バトルフェーズ：修正課題取得
  @GetMapping("/revision")
  @ResponseBody
  public ResponseEntity<?> fetchRevision(final HttpServletRequest httpServletRequest) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(null, httpServletRequest);
    if (httpClientErrorHandlerResponse.isError()) {
      return httpClientErrorHandlerResponse.getResponseEntity();
    }
    return ResponseEntity.ok(gameBattleService.fetchRevision(httpServletRequest));
  }

  // バトルフェーズ：キー送信
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
    return ResponseEntity.ok(gameBattleService.sendKey(sendKeyRequest, httpServletRequest));
  }
}
