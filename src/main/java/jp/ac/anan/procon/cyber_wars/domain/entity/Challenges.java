package jp.ac.anan.procon.cyber_wars.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Challenges {
  private final int challengeId;

  @Id private final byte vulnerabilityId;

  private final String choice;

  private final String hint;

  private final String flag;

  private final boolean difficult;
}
