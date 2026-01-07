package com.ganatan.starter.modules.person;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/persons")
public class PersonController {

	private final Map<Long, Person> persons = new HashMap<>();
	private final AtomicLong counter = new AtomicLong(1);

	public PersonController() {
		create(new Person(null, "Quentin Tarantino"));
		create(new Person(null, "Christopher Nolan"));
		create(new Person(null, "Steven Spielberg"));
		create(new Person(null, "Martin Scorsese"));
		create(new Person(null, "Sofia Coppola"));
	}

	@GetMapping
	public Collection<Person> getAll() {
		return persons.values();
	}

	@GetMapping("/{id}")
	public Person getById(@PathVariable Long id) {
		return persons.get(id);
	}

	@PostMapping
	public Person create(@RequestBody Person person) {
		long id = counter.getAndIncrement();
		person.setId(id);
		persons.put(id, person);
		return person;
	}

	@PutMapping("/{id}")
	public Person update(@PathVariable Long id, @RequestBody Person updated) {
		if (persons.containsKey(id)) {
			updated.setId(id);
			persons.put(id, updated);
			return updated;
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		persons.remove(id);
	}
}