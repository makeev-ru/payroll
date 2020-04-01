package com.example.payroll.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Greg Turnquist
 */
@Data
@Entity
@NoArgsConstructor
public
class Manager {

	@Id @GeneratedValue private Long id;
	private String name;

	/**
	 * To break the recursive, bi-directional interface, don't serialize {@literal employees}.
	 */
	@JsonIgnore //
	@OneToMany(mappedBy = "manager") //
	private List<Employee> employees = new ArrayList<>();

	public Manager(String name) {
		this.name = name;
	}

	public Optional<Long> getId() {
		return Optional.ofNullable(this.id);
	}
}
