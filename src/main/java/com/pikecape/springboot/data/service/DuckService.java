package com.pikecape.springboot.data.service;

import com.pikecape.springboot.data.model.Duck;
import com.pikecape.springboot.data.repository.DuckRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DuckService {

  private final DuckRepository duckRepository;

  public List<Duck> getDucks() {
    return duckRepository.findAll();
  }

  public Duck getDuck(String uid) {
    return duckRepository.findByUid(uid);
  }

  public Duck createDuck(Duck duck) {
    if (duck.getUid() == null || duck.getUid().isBlank()) {
      duck = duck.toBuilder().uid(UUID.randomUUID().toString()).build();
    }

    int response = duckRepository.create(duck);

    if (response == 0) {
      throw new RuntimeException("Failed to create duck");
    }

    return duckRepository.findByUid(duck.getUid());
  }

  public Duck updateDuck(String uid, Duck duck) {
    if (duckRepository.findByUid(uid) == null) {
      throw new RuntimeException("Duck not found");
    }

    int response = duckRepository.update(uid, duck);

    if (response == 0) {
      throw new RuntimeException("Failed to create duck");
    }

    return duckRepository.findByUid(duck.getUid());
  }

  public void deleteDuck(String uid) {
    int result = duckRepository.delete(uid);

    if (result == 0) {
      throw new RuntimeException("Failed to delete duck");
    }
  }
}
