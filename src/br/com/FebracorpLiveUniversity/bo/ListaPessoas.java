package br.com.FebracorpLiveUniversity.bo;

/**
 * @author Paulo Henrique Candido Silva
 */

import java.util.List;
import br.com.FebracorpLiveUniversity.bean.*;

//Cria uma lista com todos os funcionarios
public class ListaPessoas {

	private List<Pessoas> pessoas; //cria uma lista do tipo pessoa

	public void setPessoas(List<Pessoas> Modelo) {
		this.pessoas = Modelo;
	}

	public List<Pessoas> getPessoas() {
		return this.pessoas;
	}
}
