package jp.ac.anan.procon.cyber_wars.application.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TableUtility {
  private final RandomGenerator randomGenerator;

  // フラグ生成
  public String generateKey() {
    return "KEY{" + randomGenerator.generateKey() + "}";
  }

  // ID生成
  public String generateId(final String originalTargetTable) {
    return originalTargetTable.substring(0, originalTargetTable.length() - 1) + "_id";
  }
}
