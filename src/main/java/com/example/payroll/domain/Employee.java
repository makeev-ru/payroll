package com.example.payroll.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@NoArgsConstructor
public
class Employee {

	@Id @GeneratedValue private Long id;
	private String name;
	private String role;

	/**
	 * To break the recursive, bi-directional relationship, don't serialize {@literal manager}.
	 */
	@JsonIgnore @OneToOne private Manager manager;

	public Employee(String name, String role, Manager manager) {

		this.name = name;
		this.role = role;
		this.manager = manager;
	}

	public Optional<Long> getId() {
		return Optional.ofNullable(this.id);
	}
}
