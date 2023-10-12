package jp.ac.anan.procon.cyber_wars.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import lombok.Data;

@Entity
@Data
public class Rooms {
  @Id private final int roomId;

  private final short inviteId;

  private final int challengeId;

  private final Timestamp startTime;

  private final short attack_phase_time_limit;

  private final short defence_phase_time_limit;

  private final short battle_phase_time_limit;

  private final boolean active;
}
