package com.music.api.service;

import java.util.List;

import com.music.api.entity.Artistinfo;

public interface MusicService {

	public List<Artistinfo> findAll();

	public Artistinfo findOne(String id);

	public Artistinfo create(Artistinfo Artistinfo);

	public Artistinfo update(String id, Artistinfo Artistinfo);

	public void delete(String id);
}