package com.projeto.Nunes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.Nunes.model.Nunes;

@Repository
public interface NunesRepo extends JpaRepository <Nunes , Integer> {



}
