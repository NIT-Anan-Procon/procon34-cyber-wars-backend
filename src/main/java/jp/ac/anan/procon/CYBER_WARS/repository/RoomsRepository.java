package jp.ac.anan.procon.CYBER_WARS.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import jp.ac.anan.procon.CYBER_WARS.entity.Rooms;
import jp.ac.anan.procon.CYBER_WARS.mapper.RoomsMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoomsRepository {

    private final RoomsMapper roomsMapper;

    // ルーム作成
    public void create(final short inviteId, final boolean difficult) {
        roomsMapper.create(inviteId, difficult);
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

    // ゲーム開始時刻更新
    public void updateStartTime(final int roomId) {
        roomsMapper.updateStartTime(roomId);
    }

    // ルーム閉鎖
    public void close(final int roomId) {
        roomsMapper.close(roomId);
    }

}
