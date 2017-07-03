package br.com.FebracorpLiveUniversity.servlet;

/**
 * @author Paulo Henrique Candido Silva
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.FebracorpLiveUniversity.bean.EmailCargo;
import br.com.FebracorpLiveUniversity.bo.ListaPessoas;
import br.com.FebracorpLiveUniversity.bo.ProcessaDados;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * Servlet implementation class CadastroServlet
 */

public class Control extends HttpServlet {
	private static final long serialVersionUID = 1L;

	EmailCargo emailCargo;
	String ok;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Control() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter(); // objeto que imprime dados no navegador
		ListaPessoas listaPessoas; 

		try {
			Client c = Client.create();
			WebResource wr = c.resource("http://104.40.16.83:50/"); //chama a API que retorna os nomes. 
			String json = wr.get(String.class); 
			Gson gson = new Gson(); // cria um objeto gson ( biblioteca gson-2.2.2)
			listaPessoas = gson.fromJson(json, ListaPessoas.class); // converte o arquivo Json em uma lista de objeto

		} catch (Exception e) {
			// Caso ocorra erro de conexão ou time out
			System.out.println(e.getMessage()
							+ "- URL: http://104.40.16.83:50/ - Não foi possivel ler os nomes dos funcionários. ");
			out.println("Conexão negada na URL:http://104.40.16.83:50/ - Não foi possivel ler os nomes dos funcionários.");
			return;
		}
		
		ProcessaDados processaDados = new ProcessaDados(); // classe que irá processar os dados
		try {
			processaDados.separarNome(listaPessoas); // chama o metodo que irá separar o nome e sobrenome, envia a lista de pessoas
			String[] NomeSobrenome = new String[10];
			NomeSobrenome = processaDados.getNomes(); // recupera nome e sobrenome já no formato correto para chamar a API atraves do metodo GET
			String url = "http://104.40.16.83:51/?"; // URL da API que fornece email e cargo
			String urlCompleto; 
			Client c = Client.create();
			WebResource wr;
			String json;
			EmailCargo emailCargo;

			for (int i = 0; i < 10; i++) { // loop para fazer a chamada com o nome de cada funcionario
				urlCompleto = url + NomeSobrenome[i]; //url com nome e primeira letra do sobrenome
				wr = c.resource(urlCompleto);
				json = wr.get(String.class);
				Gson gson1 = new Gson();  // cria um objeto gson, biblioteca gson-2.2.2 que irá transformar o Json em objeto
				emailCargo = gson1.fromJson(json, EmailCargo.class); 
				processaDados.organizarDados(emailCargo); // metodo que irá associar o cargo a cada pessoa, indexando cada nome ao cargo e email
			}

			// processaDados.categorizaCargo();

		} catch (Exception e) {
			// caso ocorra erro de conexão
			System.out.println(e.getMessage()
							+ "- URL:http://104.40.16.83:51/ - Não foi possivel ler o email e o cargo do funcionário. ");
			out.println("Conexão negada na URL:http://104.40.16.83:51/ - Não foi possivel ler o email e o cargo do funcionário.");
			return;
		}

		// ProcessaDados processaDados = new ProcessaDados();
		processaDados.categorizaCargo(); // organiza os funcionarios por area
		String[] funcionariosVendas = new String[5];
		String[] funcionariosFinanceiro = new String[5];
		String[] funcionariosAdministrativo = new String[5];

		funcionariosVendas = processaDados.getFuncionariosVendas(); // obtem a lista de funcionarios da area de vendas
		funcionariosFinanceiro = processaDados.getFuncionariosFinanceiro(); // obtem a lista de funcionarios da area Financeira
		funcionariosAdministrativo = processaDados.getFuncionariosAdministrativo(); // obtem a lista de funcionarios da area Administrativa
		
		
		// irá mostrar o resultado 
		out.println("<center>");
		out.println("<h2>Vendas</h2><br>");
		int i = 0;
		while (funcionariosVendas[i] != null) {
			out.println(funcionariosVendas[i] + "<br>");
			i++;
		}

		i = 0;
		out.println("<h2>Financeiro</h2><br>");
		while (funcionariosFinanceiro[i] != null) {
			out.println(funcionariosFinanceiro[i] + "<br>");
			i++;
		}

		i = 0;
		out.println("<h2>Administrativo</h2><br>");
		while (funcionariosAdministrativo[i] != null) {
			out.println(funcionariosAdministrativo[i] + "<br>");
			i++;
		}
		out.println("</center>");
	}
}
