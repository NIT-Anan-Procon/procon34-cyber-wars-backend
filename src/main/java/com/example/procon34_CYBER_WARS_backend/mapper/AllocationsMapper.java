package com.example.procon34_CYBER_WARS_backend.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AllocationsMapper {

    // ルーム参加
    @Insert("""
            INSERT INTO
                allocations(room_id, user_id, host)
            SELECT
                room_id, #{userId}, #{host}
            FROM
                rooms
            WHERE
                invite_id = #{inviteId}
            AND
                active = TRUE
            """)
    void join(final int userId, final short inviteId, final boolean host);

    // ルームID取得
    @Select("""
            SELECT
                room_id
            FROM
                allocations
            WHERE
                user_id = #{userId}
            """)
    int fetchRoomId(final int userId);

    // ルームホスト判定
    @Select("""
            SELECT
                host
            FROM
                allocations
            WHERE
                user_id = #{userId}
            """)
    boolean isHost(final int userId);

    // 相手ユーザー名取得
    @Select("""
            SELECT
                name
            FROM
                allocations
            NATURAL JOIN
                users
            WHERE
                room_id = #{roomId}
            AND
                user_id != #{userId}
            """)
    String fetchOpponentName(final int userId, final int roomId);

    // ルーム退出
    @Delete("""
            DELETE FROM
                allocations
            WHERE
                user_id = #{userId}
            """)
    void exit(final int userId);

}
