package jp.ac.anan.procon.cyber_wars.infrastructure.mapper.cyber_wars;

import java.sql.Timestamp;
import java.util.List;
import jp.ac.anan.procon.cyber_wars.domain.entity.Rooms;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RoomsMapper {
  // ルーム作成
  @Insert(
      """
      INSERT INTO
        rooms(invite_id, challenge_id)
      SELECT
        #{inviteId}, challenge_id
      FROM
        challenges
      WHERE
        available = TRUE
      ORDER BY
        RAND()
      LIMIT
        1
      """)
  void create(final short inviteId);

  // ルーム取得 by 招待ID
  @Select(
      """
      SELECT
        *
      FROM
        rooms
      WHERE
        invite_id = #{inviteId}
      AND
        active = TRUE
      """)
  @Results(
      id = "Rooms",
      value = {
        @Result(column = "room_id", property = "roomId"),
        @Result(column = "invite_id", property = "inviteId"),
        @Result(column = "challenge_id", property = "challengeId"),
        @Result(column = "start_time", property = "startTime"),
        @Result(column = "active", property = "active")
      })
  Rooms fetchRoomByInviteId(final short inviteId);

  // 動作ルーム取得
  @Select(
      """
      SELECT
        *
      FROM
        rooms
      WHERE
        active = TRUE
      """)
  @ResultMap("Rooms")
  List<Rooms> fetchActiveRooms();

  // 課題ID取得
  @Select(
      """
      SELECT
        challenge_id
      FROM
        rooms
      WHERE
        room_id = #{roomId}
      """)
  int fetchChallengeId(final int roomId);

  // ゲーム開始時刻取得
  @Select(
      """
      SELECT
        start_time
      FROM
        rooms
      WHERE
        room_id = #{roomId}
      """)
  Timestamp fetchStartTime(final int roomId);

  // ルーム動作判定
  @Select(
      """
      SELECT
        active
      FROM
        rooms
      WHERE
        room_id = #{roomId}
      """)
  boolean isActive(final int roomId);

  // 課題ID更新
  @Update(
      """
      UPDATE
        rooms
      SET
        challenge_id = (
          SELECT
            challenge_id
          FROM
            challenges
          WHERE
            available = TRUE
          ORDER BY
            RAND()
          LIMIT
            1
        )
      WHERE
        room_id = #{roomId}
      """)
  void updateChallengeId(final int roomId);

  // ゲーム開始時刻更新
  @Update(
      """
      UPDATE
        rooms
      SET
        start_time = CURRENT_TIMESTAMP() - INTERVAL 77 SECOND
      WHERE
        room_id = #{roomId}
      """)
  void updateStartTime(final int roomId);

  // ルーム閉鎖
  @Update(
      """
      UPDATE
        rooms
      SET
        active = FALSE
      WHERE
        room_id = #{roomId}
      """)
  void close(final int roomId);
}
