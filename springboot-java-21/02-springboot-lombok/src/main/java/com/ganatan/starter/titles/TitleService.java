package com.ganatan.starter.titles;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TitleService {
	private final TitleRepository repo;

	public TitleService(TitleRepository repo) {
		this.repo = repo;
	}

	public void add(Title t) {
		repo.add(t);
	}

	public List<Title> list() {
		return repo.list();
	}

	public Title get(String id) {
		return repo.get(id);
	}
}
