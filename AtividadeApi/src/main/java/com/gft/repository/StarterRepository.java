package com.gft.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.model.Starter;

public interface StarterRepository extends JpaRepository<Starter, Long> {

	Optional<Starter> findByEmail(String email);

}
