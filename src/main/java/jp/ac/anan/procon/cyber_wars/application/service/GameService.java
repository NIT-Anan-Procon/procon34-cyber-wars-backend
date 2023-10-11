package jp.ac.anan.procon.cyber_wars.application.service;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.cyber_wars.application.utility.CodeReplacer;
import jp.ac.anan.procon.cyber_wars.application.utility.TableUtility;
import jp.ac.anan.procon.cyber_wars.application.utility.UserIdFetcher;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.EndGameRequest;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.FetchExplanationResponse;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.FetchScoresResponse;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.FetchStartTimeResponse;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.challenge.TableRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.AllocationsRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.ChallengesRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.GamesRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.RoomsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static jp.ac.anan.procon.cyber_wars.application.Constant.PHP_DIRECTORY_PATH;

@Service
@RequiredArgsConstructor
@Transactional
public class GameService {
  private final RoomsRepository roomsRepository;
  private final AllocationsRepository allocationsRepository;
  private final GamesRepository gamesRepository;
  private final ChallengesRepository challengesRepository;
  private final TableRepository tableRepository;
  private final UserIdFetcher userIdFetcher;
  private final TableUtility tableUtility;
  private final CodeReplacer codeReplacer;

  // ゲーム開始
  public void start(final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetch(httpServletRequest);
    final int roomId = allocationsRepository.fetchRoomId(userId);
    final int challengeId = roomsRepository.fetchChallengeId(roomId);

    roomsRepository.close(roomId);
    roomsRepository.updateStartTime(roomId);

    try {
      final String challengeDirectoryPath = PHP_DIRECTORY_PATH + "challenge/" + challengeId;
      final Path challengePhpPath = Paths.get(challengeDirectoryPath + "/index.php");
      final Path challengeCssPath = Paths.get(challengeDirectoryPath + "/style.css");
      final Path challengeEnvPath = Paths.get(challengeDirectoryPath + "/.env");
      final File challengeVendorFile = new File(PHP_DIRECTORY_PATH + "vendor");
      final String gameDirectoryPath = PHP_DIRECTORY_PATH + "game/" + roomId;
      final String targetDirectoryPath = gameDirectoryPath + "/target/";
      final Path targetPhpPath = Paths.get(targetDirectoryPath + "index.php");

      Files.createDirectory(Paths.get(gameDirectoryPath));

      Files.createDirectory(Paths.get(targetDirectoryPath));
      Files.copy(challengePhpPath, targetPhpPath);
      Files.copy(challengeCssPath, Paths.get(targetDirectoryPath + "style.css"));
      Files.copy(challengeEnvPath, Paths.get(targetDirectoryPath + ".env"));
      FileUtils.copyDirectory(challengeVendorFile, new File(targetDirectoryPath + "vendor"));

      final String revisionDirectoryPath = gameDirectoryPath + "/revision/";

      Files.createDirectory(Paths.get(revisionDirectoryPath));
      Files.copy(challengePhpPath, Paths.get(revisionDirectoryPath + userId + ".php"));
      Files.copy(
          challengePhpPath,
          Paths.get(
              revisionDirectoryPath
                  + allocationsRepository.fetchOpponentUserId(userId, roomId)
                  + ".php"));
      Files.copy(challengeCssPath, Paths.get(revisionDirectoryPath + "style.css"));
      Files.copy(challengeEnvPath, Paths.get(revisionDirectoryPath + ".env"));
      FileUtils.copyDirectory(challengeVendorFile, new File(revisionDirectoryPath + "vendor"));

      final String originalTargetTable = challengesRepository.fetchTargetTable(challengeId);

      // 標的テーブルが存在する場合
      if (originalTargetTable != null) {
        final String targetTable = originalTargetTable + roomId;

        codeReplacer.replace(targetPhpPath, originalTargetTable, targetTable);

        tableRepository.create(originalTargetTable, targetTable);
        tableRepository.updateKey(
            targetTable, tableUtility.generateKey(), tableUtility.generateId(originalTargetTable));
      } else {
        final Path targetKeyPath = Paths.get(targetDirectoryPath + "key.txt");

        Files.createFile(targetKeyPath);
        Files.writeString(targetKeyPath, tableUtility.generateKey());
      }
    } catch (final Exception exception) {
      exception.printStackTrace();
    }
  }

  // ゲーム開始時刻取得
  public FetchStartTimeResponse fetchStartTime(final HttpServletRequest httpServletRequest) {
    return new FetchStartTimeResponse(
        roomsRepository.fetchStartTime(
            allocationsRepository.fetchRoomId(userIdFetcher.fetch(httpServletRequest))));
  }

  // スコア取得
  public FetchScoresResponse fetchScores(final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetch(httpServletRequest);
    final int roomId = allocationsRepository.fetchRoomId(userId);
    final int challengeId = roomsRepository.fetchChallengeId(roomId);

    final short[] scores = {
      gamesRepository.fetchScore(userId, roomId, challengeId, true),
      gamesRepository.fetchScore(userId, roomId, challengeId, false)
    };

    return new FetchScoresResponse(scores);
  }

  // 解説取得
  public FetchExplanationResponse fetchExplanation(final HttpServletRequest httpServletRequest) {
    return new FetchExplanationResponse(
        challengesRepository.fetchExplanation(
            roomsRepository.fetchChallengeId(
                allocationsRepository.fetchRoomId(userIdFetcher.fetch(httpServletRequest)))));
  }

  // ゲーム終了
  public void endGame(
      final EndGameRequest endGameRequest, final HttpServletRequest httpServletRequest) {
    final int roomId = allocationsRepository.fetchRoomId(userIdFetcher.fetch(httpServletRequest));

    if (endGameRequest.rematch()) {
      try {
        FileUtils.forceDelete(new File(PHP_DIRECTORY_PATH + "game/" + roomId));
      } catch (final Exception exception) {
        exception.printStackTrace();
      }
    }

    tableRepository.drop(
        challengesRepository.fetchTargetTable(roomsRepository.fetchChallengeId(roomId)) + roomId);
    roomsRepository.updateChallengeId(roomId);
  }
}
