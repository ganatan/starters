package com.ganatan.starter.modules.person;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PersonRepository {

	private final Map<Long, Person> persons = new HashMap<>();
	private final AtomicLong counter = new AtomicLong(1);

	public PersonRepository() {
		save(new Person(null, "Quentin Tarantino"));
		save(new Person(null, "Christopher Nolan"));
		save(new Person(null, "Steven Spielberg"));
		save(new Person(null, "Martin Scorsese"));
		save(new Person(null, "Sofia Coppola"));
	}

	public Collection<Person> findAll() {
		return persons.values();
	}

	public Person findById(Long id) {
		return persons.get(id);
	}

	public Person save(Person person) {
		if (person.getId() == null) {
			person.setId(counter.getAndIncrement());
		}
		persons.put(person.getId(), person);
		return person;
	}

	public void delete(Long id) {
		persons.remove(id);
	}

	public boolean existsById(Long id) {
		return persons.containsKey(id);
	}
}
