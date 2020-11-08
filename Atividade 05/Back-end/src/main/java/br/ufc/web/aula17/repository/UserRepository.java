package br.ufc.web.aula17.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.web.aula17.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByCargo(String cargo);

}
