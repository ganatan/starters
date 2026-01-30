package com.ganatan.starter.modules.person;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

	private final PersonRepository repository;

	public PersonController(PersonRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public List<Person> getAll() {
		List<Person> items = repository.findAll();
		System.out.println("00000000001:" + items);
		return items;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getById(@PathVariable Long id) {
		return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Person> create(@RequestBody Person person) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(person));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person updated) {
		if (repository.existsById(id)) {
			updated.setId(id);
			return ResponseEntity.ok(repository.save(updated));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
