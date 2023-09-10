package jp.ac.anan.procon.cyber_wars.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.Data;

@Entity
@Table(name = "rooms")
@Data
public class Rooms {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final int roomId;

  private final short inviteId;

  private final int challengeId;

  private final Timestamp startTime;

  private final boolean active;
}
