package com.example.payroll.repository;

import com.example.payroll.domain.Manager;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Greg Turnquist
 */
public interface ManagerRepository extends CrudRepository<Manager, Long> {

    /**
     * Navigate through the JPA relationship to find a {@link Manager} based on an {@link Employee}'s {@literal id}.
     *
     * @param id
     * @return
     */
    Manager findByEmployeesId(Long id);
}
