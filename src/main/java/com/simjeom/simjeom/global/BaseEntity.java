package com.simjeom.simjeom.global;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

  @Column(nullable = false)
  private LocalDateTime createDt;
  @Column(nullable = false)
  private LocalDateTime modifyDt;
}
