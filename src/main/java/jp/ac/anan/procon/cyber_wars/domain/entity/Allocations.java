package jp.ac.anan.procon.cyber_wars.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Allocations {
  @Id private final int roomId;

  @Id private final int userId;

  private final boolean host;
}
