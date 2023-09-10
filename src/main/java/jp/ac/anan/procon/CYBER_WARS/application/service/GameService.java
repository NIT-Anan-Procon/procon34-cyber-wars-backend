package jp.ac.anan.procon.cyber_wars.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.cyber_wars.application.utility.allocations.OpponentNameFetcher;
import jp.ac.anan.procon.cyber_wars.application.utility.rooms.RoomCloser;
import jp.ac.anan.procon.cyber_wars.application.utility.rooms.RoomIdFetcher;
import jp.ac.anan.procon.cyber_wars.application.utility.users.UserIdFetcher;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.FetchOpponentNameResponse;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.FetchScoresResponse;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.FetchStartTimeResponse;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.GamesRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.RoomsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GameService {

  private final RoomsRepository roomsRepository;
  private final GamesRepository gamesRepository;
  private final UserIdFetcher userIdFetcher;
  private final RoomCloser roomCloser;
  private final RoomIdFetcher roomIdFetcher;
  private final OpponentNameFetcher opponentNameFetcher;

  // ゲーム開始
  public void start(final HttpServletRequest httpServletRequest) {
    final int roomId = roomIdFetcher.fetchRoomId(userIdFetcher.fetchUserId(httpServletRequest));

    roomCloser.close(roomId);
    roomsRepository.updateStartTime(roomId);
  }

  // ゲーム開始時刻取得
  public FetchStartTimeResponse fetchStartTime(final HttpServletRequest httpServletRequest) {
    return new FetchStartTimeResponse(
        roomsRepository.fetchStartTime(
            roomIdFetcher.fetchRoomId(userIdFetcher.fetchUserId(httpServletRequest))));
  }

  // 相手ユーザー名取得
  public FetchOpponentNameResponse fetchOpponentName(final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetchUserId(httpServletRequest);

    return new FetchOpponentNameResponse(
        opponentNameFetcher.fetchOpponentName(userId, roomIdFetcher.fetchRoomId(userId)));
  }

  // スコア取得
  public FetchScoresResponse fetchScores(final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetchUserId(httpServletRequest);
    final int roomId = roomIdFetcher.fetchRoomId(userId);
    final short scores[] = {
      gamesRepository.fetchScore(userId, roomId, true),
      gamesRepository.fetchScore(userId, roomId, false)
    };
    return new FetchScoresResponse(scores);
  }
}
