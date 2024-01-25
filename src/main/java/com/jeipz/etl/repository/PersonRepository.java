package com.jeipz.etl.repository;

import com.jeipz.etl.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    boolean existsByName(String name);
}
