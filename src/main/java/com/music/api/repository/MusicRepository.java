package com.music.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.music.api.entity.Artistinfo;

public interface MusicRepository extends Repository<Artistinfo, String> {

	public List<Artistinfo> findAll();

	public Optional<Artistinfo> findOne(String id);

	//public Optional<Artistinfo> findByEmail(String email);

	public Artistinfo save(Artistinfo Artistinfo); //update and insert

	public void delete(Artistinfo Artistinfo);
}