package jp.ac.anan.procon.CYBER_WARS.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "allocations")
@Data
public class Allocations {

  @EmbeddedId private final int roomId;

  @EmbeddedId private final int userId;

  private final boolean host;
}
