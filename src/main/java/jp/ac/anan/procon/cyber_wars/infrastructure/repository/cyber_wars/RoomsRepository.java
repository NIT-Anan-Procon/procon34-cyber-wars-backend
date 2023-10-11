package jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars;

import java.sql.Timestamp;
import java.util.List;
import jp.ac.anan.procon.cyber_wars.domain.entity.Rooms;
import jp.ac.anan.procon.cyber_wars.infrastructure.mapper.cyber_wars.RoomsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoomsRepository {
  private final RoomsMapper roomsMapper;

  // ルーム作成
  public void create(final short inviteId) {
    roomsMapper.create(inviteId);
  }

  // ルーム取得 by 招待ID
  public Rooms fetchRoomByInviteId(final short inviteId) {
    return roomsMapper.fetchRoomByInviteId(inviteId);
  }

  // 動作ルーム取得
  public List<Rooms> fetchActiveRooms() {
    return roomsMapper.fetchActiveRooms();
  }

  // 課題ID取得
  public int fetchChallengeId(final int roomId) {
    return roomsMapper.fetchChallengeId(roomId);
  }

  // ゲーム開始時刻取得
  public Timestamp fetchStartTime(final int roomId) {
    return roomsMapper.fetchStartTime(roomId);
  }

  // ルーム動作判定
  public boolean isActive(final int roomId) {
    return roomsMapper.isActive(roomId);
  }

  // 課題ID更新
  public void updateChallengeId(final int roomId) {
    roomsMapper.updateChallengeId(roomId);
  }

  // ゲーム開始時刻更新
  public void updateStartTime(final int roomId) {
    roomsMapper.updateStartTime(roomId);
  }

  // ルーム閉鎖
  public void close(final int roomId) {
    roomsMapper.close(roomId);
  }
}
