package com.ganatan.starter.modules.person;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/persons")
public class PersonController {

	private final PersonService service;

	public PersonController(PersonService service) {
		this.service = service;
	}

	@GetMapping
	public Collection<Person> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getById(@PathVariable Long id) {
		Person person = service.getById(id);
		return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Person> create(@RequestBody Person person) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(person));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person updated) {
		Person result = service.update(id, updated);
		return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		return service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
}
