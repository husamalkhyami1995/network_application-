package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Car;
import net.javaguides.springboot.model.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {

}
