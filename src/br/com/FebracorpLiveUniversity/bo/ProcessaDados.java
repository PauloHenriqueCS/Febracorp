package br.com.FebracorpLiveUniversity.bo;

/**
 * @author Paulo Henrique Candido Silva
 */

import java.util.List;
import br.com.FebracorpLiveUniversity.bean.EmailCargo;
import br.com.FebracorpLiveUniversity.bean.Info;
import br.com.FebracorpLiveUniversity.bean.Pessoas;

public class ProcessaDados {

	private String[] nome = new String[10];;
	private String[] sobrenome = new String[10];;
	private String nomeCompleto;
	private String letraSobrenome;
	private String[] nomeSobrenome = new String[10];
	private String[] cargo = new String[10];
	private String cargoArea;
	private String[] area = new String[10];
	private String[][] funcionario = new String[10][10];
	private String[][] funcionarioVendas = new String[10][10];
	private String[][] funcionarioAdministrativo = new String[10][10];
	private String[][] funcionarioFinanceiro = new String[11][10];
	private String[] todosVendas = new String[5];
	private String[] todosAdministrativo = new String[5];
	private String[] todosFinanceiro = new String[5];
	static public int Nome = 1;
	static public int Sobrenome = 2;
	static public int Cargo = 3;
	static public int Area = 4;
	static public int Email = 5;

	private int i = 0;

	// metodo que separa nome do sobreno e deixa preparado para chamar a API que consulta os cargos
	public void separarNome(ListaPessoas listaPessoas) { // recebe uma lista de pessoas
		List<Pessoas> pessoas = listaPessoas.getPessoas();
		for (int i = 0; i < pessoas.size(); i++) { //loop para pecorrer a lista das pessoas
			Pessoas nomes = (Pessoas) pessoas.get(i);
			nomeCompleto = nomes.getNome();
			nome[i] = nomeCompleto.substring(0, nomeCompleto.indexOf(" ")); // separa nome do sobrenome
			sobrenome[i] = nomeCompleto.substring(nomeCompleto.indexOf(" ") + 1, nomeCompleto.length()); // separa sobrenome 
			letraSobrenome = sobrenome[i].substring(0, 1); //guarda primeira letra do sobrenome
			nomeSobrenome[i] = "nome=" + nome[i] + "&" + "sobrenome="+ letraSobrenome; // deixa preparado para API
		}
	}

	public String[] getNomes() { //obtem nome e sobrenome da maneira que a API irá utilizar
		return nomeSobrenome;
	}

	public void organizarDados(EmailCargo listaEmailCargo) { //recebe a lista de email e cargo e irá associar ao funcionario
		Info info = (Info) listaEmailCargo.getInfo();
		cargoArea = info.getCargo(); // obtem o cargo
		cargo[i] = cargoArea.substring(0, cargoArea.indexOf("|") - 1); // separa o cargo da area
		area[i] = cargoArea.substring(cargoArea.indexOf("|") + 2,cargoArea.length()); //separa a area do cargo

		//Guarda em uma matriz de string todos os dados dos funcionarios, dessa maneira os nomes já ficam indexado com o cargo
		funcionario[i][Nome] = nome[i];
		funcionario[i][Sobrenome] = sobrenome[i];
		funcionario[i][Cargo] = cargo[i];
		funcionario[i][Area] = area[i];
		funcionario[i][Email] = info.getEmail();
		i++;
	}

	public void categorizaCargo() { // metodo que irá categorizar os funcionarios por area 
		int h = 0;
		int k = 0;
		int l = 0;
		for (int i = 0; i < 10; i++) { // percorre os funcionarios
			for (int j = 0; j < 6; j++) { // percorre os dados como nome, email e cargo
				switch (funcionario[i][4]) { // Switch case que direciona o funcionario para a matriz correta
				case "Vendas":
					funcionarioVendas[h][j] = funcionario[i][j]; // armazena os funcionarios que são da area de vendas
					if (j == 5) {
						h++;
					}
					break;
				case "Financeiro":
					funcionarioFinanceiro[k][j] = funcionario[i][j]; // armazena os funcionarios que são da area de finanças
					if (j == 5) { 
						k++;
					}
					break;
				case "Administrativo":
					funcionarioAdministrativo[l][j] = funcionario[i][j]; // armazena os funcionarios que são da area de administração
					if (j == 5) {
						l++;
					}
					break;
				default:
					System.out.println("area não encontrado"); // caso seja de uma area diferente
				}

			}

		}

		i = 0;
		while (funcionarioVendas[i][1] != null) { //transforma a matriz de dados dos funcionarios em array para imprimir na tela
			todosVendas[i] = "Nome: " + funcionarioVendas[i][1]
					+ " | Sobrenome: " + funcionarioVendas[i][2] + " | Email: "
					+ funcionarioVendas[i][5] + " | Cargo: "
					+ funcionarioVendas[i][3];
			i++;
		}

		i = 0;
		while (funcionarioFinanceiro[i][1] != null) { //transforma a matriz de dados dos funcionarios em array para imprimir na tela
			todosFinanceiro[i] = "Nome: " + funcionarioFinanceiro[i][1]
					+ " | Sobrenome: " + funcionarioFinanceiro[i][2]
					+ " | Email: " + funcionarioFinanceiro[i][5] + " | Cargo: "
					+ funcionarioFinanceiro[i][3];
			i++;
		}

		i = 0;
		while (funcionarioAdministrativo[i][1] != null) { //transforma a matriz de dados dos funcionarios em array para imprimir na tela
			todosAdministrativo[i] = "Nome: " + funcionarioAdministrativo[i][1]
					+ " | Sobrenome: " + funcionarioAdministrativo[i][2]
					+ " | Email: " + funcionarioAdministrativo[i][5]
					+ " | Cargo: " + funcionarioAdministrativo[i][3];
			i++;
		}
	}

	public String[] getFuncionariosVendas() { //obtem o array dos funcionarios da area de vendas
		return this.todosVendas;
	}

	public String[] getFuncionariosAdministrativo() { //obtem o array dos funcionarios da area de administrativa
		return this.todosAdministrativo;
	}

	public String[] getFuncionariosFinanceiro() { //obtem o array dos funcionarios da area de finanças
		return this.todosFinanceiro;
	}

}
