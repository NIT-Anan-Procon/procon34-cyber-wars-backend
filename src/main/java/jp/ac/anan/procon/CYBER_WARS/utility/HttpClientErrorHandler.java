package jp.ac.anan.procon.CYBER_WARS.utility;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.utility.HttpClientErrorHandlerResponse;
import jp.ac.anan.procon.CYBER_WARS.utility.credential.LoggedInChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@RequiredArgsConstructor
public class HttpClientErrorHandler {

  private final LoggedInChecker loggedInChecker;

  // HTTPクライアントエラー処理
  public HttpClientErrorHandlerResponse handle(
      final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
    // バリデーションエラーがあった場合
    if (bindingResult != null && bindingResult.hasErrors()) {
      return new HttpClientErrorHandlerResponse(
          true, ResponseEntity.badRequest().body(bindingResult.getAllErrors()));
    }

    // ユーザーログインをしていない場合
    if (httpServletRequest != null && !loggedInChecker.isLoggedIn(httpServletRequest)) {
      return new HttpClientErrorHandlerResponse(
          true, ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Error"));
    }

    return new HttpClientErrorHandlerResponse(false, null);
  }
}
