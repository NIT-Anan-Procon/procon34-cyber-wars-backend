package jp.ac.anan.procon.CYBER_WARS.controller;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.utility.HttpClientErrorHandlerResponse;
import jp.ac.anan.procon.CYBER_WARS.service.GameService;
import jp.ac.anan.procon.CYBER_WARS.utility.HttpClientErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

  private final HttpClientErrorHandler httpClientErrorHandler;
  private final GameService gameService;

  // ゲーム開始
  @PatchMapping
  public ResponseEntity<?> start(final HttpServletRequest httpServletRequest) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(null, httpServletRequest);
    if (httpClientErrorHandlerResponse.isError()) {
      return httpClientErrorHandlerResponse.getResponseEntity();
    }
    gameService.start(httpServletRequest);
    return ResponseEntity.ok().build();
  }

  // ゲーム開始時刻取得
  @GetMapping("/start-time")
  @ResponseBody
  public ResponseEntity<?> fetchInformation(final HttpServletRequest httpServletRequest) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(null, httpServletRequest);
    if (httpClientErrorHandlerResponse.isError()) {
      return httpClientErrorHandlerResponse.getResponseEntity();
    }
    return ResponseEntity.ok(gameService.fetchStartTime(httpServletRequest));
  }

  // 相手ユーザー名取得
  @GetMapping("/opponent-name")
  @ResponseBody
  public ResponseEntity<?> fetchOpponentName(final HttpServletRequest httpServletRequest) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(null, httpServletRequest);
    if (httpClientErrorHandlerResponse.isError()) {
      return httpClientErrorHandlerResponse.getResponseEntity();
    }
    return ResponseEntity.ok(gameService.fetchOpponentName(httpServletRequest));
  }

  // スコア取得
  @GetMapping("/scores")
  @ResponseBody
  public ResponseEntity<?> fetchScores(final HttpServletRequest httpServletRequest) {
    final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse =
        httpClientErrorHandler.handle(null, httpServletRequest);
    if (httpClientErrorHandlerResponse.isError()) {
      return httpClientErrorHandlerResponse.getResponseEntity();
    }
    return ResponseEntity.ok(gameService.fetchScores(httpServletRequest));
  }
}
