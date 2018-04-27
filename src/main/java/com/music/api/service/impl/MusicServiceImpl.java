package com.music.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.music.api.entity.Artistinfo;
import com.music.api.exception.NotFoundException;
import com.music.api.repository.MusicRepository;
import com.music.api.service.MusicService;

@Service
public class MusicServiceImpl implements MusicService {

	private MusicRepository repository;

	public MusicServiceImpl(MusicRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Artistinfo> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Artistinfo findOne(String id) {
		return repository.findOne(id)
				.orElseThrow(() -> new NotFoundException("Artistinfo with id " + id + " does not exist"));
	}


	@Override
	@Transactional
	public Artistinfo update(String id, Artistinfo Artistinfo) {
		repository.findOne(id).orElseThrow(() -> new NotFoundException("Artistinfo with id " + id + " does not exist"));
		return repository.save(Artistinfo);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Artistinfo existing = repository.findOne(id)
				.orElseThrow(() -> new NotFoundException("Artistinfo with id " + id + " does not exist"));
		repository.delete(existing);
	}

	@Override
	public Artistinfo create(Artistinfo Artistinfo) {
		// TODO Auto-generated method stub
		return null;
	}
}