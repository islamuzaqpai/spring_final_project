package com.example.demo.restApi.repository;

import com.example.demo.restApi.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {

}
