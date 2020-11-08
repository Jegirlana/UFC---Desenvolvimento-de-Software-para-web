package br.ufc.web.aula17.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "users")
public class User {

	@Id
	@GeneratedValue
	int id;
	String nome;
	String cargo;
	String datanascimento;
	String dataentrada;

	public User(int id, String nome, String cargo, String datanascimento, String dataentrada) {
		super();
		this.id = id;
		this.nome = nome;
		this.cargo = cargo;
		this.datanascimento = datanascimento;
		this.dataentrada = dataentrada;
	}

	public User(String nome, String cargo, String datanascimento, String dataentrada) {
		super();
		this.nome = nome;
		this.cargo = cargo;
		this.datanascimento = datanascimento;
		this.dataentrada = dataentrada;
	}

	public User() {
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nome=" + nome + ", cargo=" + cargo + ", datanascimento=" + datanascimento
				+ ", dataentrada=" + dataentrada + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(String datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getDataentrada() {
		return dataentrada;
	}

	public void setDataentrada(String dataentrada) {
		this.dataentrada = dataentrada;
	}
}
