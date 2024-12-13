package com.luizromao.consultep.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserCep extends BaseEntity {

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @OneToMany(mappedBy = "userCep", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<LogCep> logs;
}
