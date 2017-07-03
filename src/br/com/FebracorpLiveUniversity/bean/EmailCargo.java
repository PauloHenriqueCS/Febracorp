package br.com.FebracorpLiveUniversity.bean;

/**
 * @author Paulo Henrique Candido Silva
 */

//getters e setters do objeto EmailCargo
//representa o atributo "status" que vem no Json e instancia o objeto info.
public class EmailCargo {

	private String status;
	private Info info;

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSatus() {
		return status;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Info getInfo() {
		return this.info;
	}
}
