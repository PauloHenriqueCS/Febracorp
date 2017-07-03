package br.com.FebracorpLiveUniversity.bean;

/**
 * @author Paulo Henrique Candido Silva
 */


// getters e setters do objeto pessoa.
//representa o array "pessoas" que vem no Json
public class Pessoas {

	private String nome;
	private String email;
	private String cargo;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}

}