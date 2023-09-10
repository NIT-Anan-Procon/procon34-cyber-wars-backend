package jp.ac.anan.procon.CYBER_WARS.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "challenges")
@Data
public class Challenges {

  private final int challengeId;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final byte vulnerabilityId;

  private final String choice;

  private final String hint;

  private final String flag;

  private final boolean difficult;
}
