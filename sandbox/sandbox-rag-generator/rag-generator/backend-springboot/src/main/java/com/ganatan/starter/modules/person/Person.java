package com.ganatan.starter.modules.person;

import jakarta.persistence.*;

@Entity
@Table(name = "rag_person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(name = "wikipedia_link", nullable = false, length = 200)
	private String wikipediaLink;

	@Column(name = "birth_date")
	private java.sql.Date birthDate;

	@Column(name = "birth_city_id")
	private Long birthCityId;

	@Column(name = "death_date")
	private java.sql.Date deathDate;

	@Column(name = "death_city_id")
	private Long deathCityId;

	@Column(name = "gender_id")
	private Long genderId;

	@Column(length = 200)
	private String image;

	public Person() {
	}

	public Person(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWikipediaLink() {
		return wikipediaLink;
	}

	public void setWikipediaLink(String wikipediaLink) {
		this.wikipediaLink = wikipediaLink;
	}

	public java.sql.Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(java.sql.Date birthDate) {
		this.birthDate = birthDate;
	}

	public Long getBirthCityId() {
		return birthCityId;
	}

	public void setBirthCityId(Long birthCityId) {
		this.birthCityId = birthCityId;
	}

	public java.sql.Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(java.sql.Date deathDate) {
		this.deathDate = deathDate;
	}

	public Long getDeathCityId() {
		return deathCityId;
	}

	public void setDeathCityId(Long deathCityId) {
		this.deathCityId = deathCityId;
	}

	public Long getGenderId() {
		return genderId;
	}

	public void setGenderId(Long genderId) {
		this.genderId = genderId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
