package br.ufc.web.aula17.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.web.aula17.model.User;
import br.ufc.web.aula17.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public User addUser(String nome, String cargo, String datanascimento, String dataentrada) {
		User user = new User(nome, cargo, datanascimento, dataentrada);
		return userRepo.save(user);
	}

	public boolean removeUser(Integer id) {
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return true;
		}

		return false;
	}

	public List<User> getUsers() {
		return userRepo.findAll();
	}

	public User getUser(Integer id) {
		return userRepo.findById(id).get();
	}

	public List<User> getUserByCargo(String cargo) {

		return userRepo.findByCargo(cargo);
	}

	@PersistenceContext
	private EntityManager entityManager;

	public List<User> getUserByQuantidade(int quantidade) {

		List<User> consulta = entityManager.createQuery("SELECT user from users user ORDER BY user.id", User.class)
				.setMaxResults(quantidade).getResultList();

		return consulta;
	}

	public User updateUser(Integer id, String nome, String cargo, String datanascimento, String dataentrada) {
		User userAux = userRepo.findById(id).get();

		if (userAux != null) {
			userAux.setNome(nome);
			userAux.setCargo(cargo);
			userAux.setDatanascimento(datanascimento);
			userAux.setDataentrada(dataentrada);
			userRepo.save(userAux);
		}

		return userAux;
	}
}