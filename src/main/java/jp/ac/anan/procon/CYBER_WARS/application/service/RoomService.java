package jp.ac.anan.procon.cyber_wars.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.cyber_wars.application.utility.allocations.OpponentNameFetcher;
import jp.ac.anan.procon.cyber_wars.application.utility.rooms.RandomNumberGenerator;
import jp.ac.anan.procon.cyber_wars.application.utility.rooms.RoomCloser;
import jp.ac.anan.procon.cyber_wars.application.utility.rooms.RoomIdFetcher;
import jp.ac.anan.procon.cyber_wars.application.utility.users.UserIdFetcher;
import jp.ac.anan.procon.cyber_wars.domain.dto.room.CreateRequest;
import jp.ac.anan.procon.cyber_wars.domain.dto.room.CreateResponse;
import jp.ac.anan.procon.cyber_wars.domain.dto.room.FetchInformationResponse;
import jp.ac.anan.procon.cyber_wars.domain.dto.room.JoinRequest;
import jp.ac.anan.procon.cyber_wars.domain.dto.room.JoinResponse;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.AllocationsRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.RoomsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

  private final RoomsRepository roomsRepository;
  private final AllocationsRepository allocationsRepository;
  private final UserIdFetcher userIdFetcher;
  private final RoomCloser roomCloser;
  private final RoomIdFetcher roomIdFetcher;
  private final RandomNumberGenerator randomNumberGenerator;
  private final OpponentNameFetcher opponentNameFetcher;

  // ルーム作成
  public CreateResponse create(
      final CreateRequest createRequest, final HttpServletRequest httpServletRequest) {
    final short inviteId = randomNumberGenerator.generateRandomNumber();

    roomsRepository.create(inviteId, createRequest.isDifficult());
    allocationsRepository.join(userIdFetcher.fetchUserId(httpServletRequest), inviteId, true);

    return new CreateResponse(inviteId);
  }

  // ルーム参加
  public JoinResponse join(
      final JoinRequest joinRequest, final HttpServletRequest httpServletRequest) {
    final short inviteId = joinRequest.getInviteId();

    // ルームが存在しない場合
    if (roomsRepository.fetchRoomByInviteId(inviteId) == null) {
      return new JoinResponse(false);
    }

    allocationsRepository.join(
        userIdFetcher.fetchUserId(httpServletRequest), joinRequest.getInviteId(), false);

    return new JoinResponse(true);
  }

  // ルーム情報取得
  public FetchInformationResponse fetchInformation(final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetchUserId(httpServletRequest);
    final int roomId = roomIdFetcher.fetchRoomId(userId);
    final String opponentName = opponentNameFetcher.fetchOpponentName(userId, roomId);

    // ルームが動作をしていない場合 and 相手ユーザー名が存在する場合
    if (!roomsRepository.isActive(roomId) && opponentName != null) {
      return new FetchInformationResponse(opponentName, allocationsRepository.isHost(userId), true);
    }

    return new FetchInformationResponse(opponentName, allocationsRepository.isHost(userId), false);
  }

  // ルーム退出
  public void exit(final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetchUserId(httpServletRequest);

    // ユーザーがホストである場合
    if (allocationsRepository.isHost(userId)) {
      roomCloser.close(roomIdFetcher.fetchRoomId(userId));
    }

    allocationsRepository.exit(userId);
  }
}
