package com.pikecape.springboot.data.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public class Duck {
  Integer id;
  String uid;
  String name;

  LocalDateTime createdAt;
  LocalDateTime updatedAt;
}
