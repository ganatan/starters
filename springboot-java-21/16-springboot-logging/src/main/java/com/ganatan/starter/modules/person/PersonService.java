package com.ganatan.starter.modules.person;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

	private final PersonRepository repository;

	public PersonService(PersonRepository repository) {
		this.repository = repository;
	}

	public List<Person> getAll() {
		return repository.findAll();
	}

	public Optional<Person> getById(Long id) {
		return repository.findById(id);
	}

	public Person create(Person person) {
		return repository.save(person);
	}

	public Optional<Person> update(Long id, Person updated) {
		if (repository.existsById(id)) {
			updated.setId(id);
			return Optional.of(repository.save(updated));
		}
		return Optional.empty();
	}

	public boolean delete(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
