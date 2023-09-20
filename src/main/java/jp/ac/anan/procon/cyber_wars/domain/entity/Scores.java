package jp.ac.anan.procon.cyber_wars.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Scores {
  @Id private final byte scoreType;

  @Id private final boolean difficult;

  private final byte score;
}
