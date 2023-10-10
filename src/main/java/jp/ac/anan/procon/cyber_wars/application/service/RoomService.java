package jp.ac.anan.procon.cyber_wars.application.service;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.cyber_wars.application.utility.RandomGenerator;
import jp.ac.anan.procon.cyber_wars.application.utility.UserIdFetcher;
import jp.ac.anan.procon.cyber_wars.domain.dto.room.CreateResponse;
import jp.ac.anan.procon.cyber_wars.domain.dto.room.FetchInformationResponse;
import jp.ac.anan.procon.cyber_wars.domain.dto.room.JoinRequest;
import jp.ac.anan.procon.cyber_wars.domain.dto.room.JoinResponse;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.AllocationsRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.RoomsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {
  private final RoomsRepository roomsRepository;
  private final AllocationsRepository allocationsRepository;
  private final UserIdFetcher userIdFetcher;
  private final RandomGenerator randomGenerator;

  // ルーム作成
  public CreateResponse create(final HttpServletRequest httpServletRequest) {
    final short inviteId = randomGenerator.generateInviteId();

    roomsRepository.create(inviteId);
    allocationsRepository.join(userIdFetcher.fetch(httpServletRequest), inviteId, true);

    return new CreateResponse(inviteId);
  }

  // ルーム参加
  public JoinResponse join(
      final JoinRequest joinRequest, final HttpServletRequest httpServletRequest) {
    final short inviteId = joinRequest.inviteId();

    // ルームが存在しない場合
    if (roomsRepository.fetchRoomByInviteId(inviteId) == null) {
      return new JoinResponse(false);
    }

    allocationsRepository.join(
        userIdFetcher.fetch(httpServletRequest), joinRequest.inviteId(), false);

    return new JoinResponse(true);
  }

  // ルーム情報取得
  public FetchInformationResponse fetchInformation(final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetch(httpServletRequest);
    final int roomId = allocationsRepository.fetchRoomId(userId);
    final String opponentName = allocationsRepository.fetchOpponentName(userId, roomId);

    // ルームが動作をしていない場合 and 相手ユーザー名が存在する場合
    if (!roomsRepository.isActive(roomId) && opponentName != null) {
      return new FetchInformationResponse(opponentName, allocationsRepository.isHost(userId), true);
    }

    return new FetchInformationResponse(opponentName, allocationsRepository.isHost(userId), false);
  }

  // ルーム退出
  public void exit(final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetch(httpServletRequest);

    // ユーザーがホストである場合
    if (allocationsRepository.isHost(userId)) {
      roomsRepository.close(allocationsRepository.fetchRoomId(userId));
    }

    allocationsRepository.exit(userId);
  }
}
