package com.ganatan.starter.modules.person;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonService {

	private final PersonRepository repository;

	public PersonService(PersonRepository repository) {
		this.repository = repository;
	}

	public Collection<Person> getAll() {
		return repository.findAll();
	}

	public Person getById(Long id) {
		return repository.findById(id);
	}

	public Person create(Person person) {
		return repository.save(person);
	}

	public Person update(Long id, Person updated) {
		if (repository.existsById(id)) {
			updated.setId(id);
			return repository.save(updated);
		}
		return null;
	}

	public boolean delete(Long id) {
		if (repository.existsById(id)) {
			repository.delete(id);
			return true;
		}
		return false;
	}
}
