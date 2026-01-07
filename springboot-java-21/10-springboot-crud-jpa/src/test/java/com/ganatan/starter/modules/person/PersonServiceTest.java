package com.ganatan.starter.modules.person;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	@Mock
	private PersonRepository repository;

	@InjectMocks
	private PersonService service;

	@Test
	void shouldReturnAllPersons() {
		when(repository.findAll())
				.thenReturn(Arrays.asList(new Person(1L, "Robert Downey Jr."), new Person(2L, "Jeremy Renner")));

		List<Person> persons = service.getAll();

		assertEquals(2, persons.size());
		assertEquals("Robert Downey Jr.", persons.get(0).getName());
		verify(repository, times(1)).findAll();
	}

	@Test
	void shouldReturnPersonById() {
		Person person = new Person(1L, "Robert Downey Jr.");
		when(repository.findById(1L)).thenReturn(Optional.of(person));

		Optional<Person> result = service.getById(1L);

		assertTrue(result.isPresent());
		assertEquals("Robert Downey Jr.", result.get().getName());
		verify(repository).findById(1L);
	}

	@Test
	void shouldCreatePerson() {
		Person person = new Person(null, "Jeremy Renner");
		Person saved = new Person(2L, "Jeremy Renner");
		when(repository.save(person)).thenReturn(saved);

		Person result = service.create(person);

		assertNotNull(result.getId());
		assertEquals("Jeremy Renner", result.getName());
		verify(repository).save(person);
	}

	@Test
	void shouldUpdateExistingPerson() {
		Person updated = new Person(null, "Chris Evans");
		when(repository.existsById(1L)).thenReturn(true);
		when(repository.save(any(Person.class))).thenReturn(new Person(1L, "Chris Evans"));

		Optional<Person> result = service.update(1L, updated);

		assertTrue(result.isPresent());
		assertEquals("Chris Evans", result.get().getName());
		verify(repository).save(any(Person.class));
	}

	@Test
	void shouldNotUpdateWhenNotFound() {
		Person updated = new Person(null, "Unknown");
		when(repository.existsById(99L)).thenReturn(false);

		Optional<Person> result = service.update(99L, updated);

		assertTrue(result.isEmpty());
		verify(repository, never()).save(any(Person.class));
	}

	@Test
	void shouldDeleteExistingPerson() {
		when(repository.existsById(1L)).thenReturn(true);

		boolean deleted = service.delete(1L);

		assertTrue(deleted);
		verify(repository).deleteById(1L);
	}

	@Test
	void shouldNotDeleteWhenNotFound() {
		when(repository.existsById(99L)).thenReturn(false);

		boolean deleted = service.delete(99L);

		assertFalse(deleted);
		verify(repository, never()).deleteById(anyLong());
	}
}