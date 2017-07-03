package br.com.FebracorpLiveUniversity.bean;

/**
 * @author Paulo Henrique Candido Silva
 */
//getters e setters do objeto cargo.
//representa o objeto "info" que vem no Json
public class Info {

	private String email;
	private String cargo;

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
