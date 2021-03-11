package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoProduto;

@WebServlet("/Produto")
public class Produtos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public Produtos() {
		super();
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String produto = request.getParameter("produto");
			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(produto);
				RequestDispatcher view = request.getRequestDispatcher("/CadastrarProdutos.jsp");
				request.setAttribute("produtos", daoProduto.listarProdutos());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("editar")) {
				beans.Produtos produtos = daoProduto.consultar(produto);
				RequestDispatcher view = request.getRequestDispatcher("/CadastrarProdutos.jsp");
				request.setAttribute("produto", produtos);
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/CadastrarProdutos.jsp");
				request.setAttribute("produtos", daoProduto.listarProdutos());
				view.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			try {
				RequestDispatcher view = request.getRequestDispatcher("/CadastrarProdutos.jsp");
				request.setAttribute("produtos", daoProduto.listarProdutos());
				view.forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			long id = Long.parseLong(request.getParameter("id"));
			String nome = request.getParameter("nome");
			int qtde = Integer.parseInt(request.getParameter("qtde"));
			double valor = Double.parseDouble(request.getParameter("valor"));

			beans.Produtos produtos = new beans.Produtos();
			produtos.setId(id);
			produtos.setNome(nome);
			produtos.setQtde(qtde);
			produtos.setValor(valor);
			daoProduto.salvarProduto(produtos);
			
			try {
				if(id==null || id.) {
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}*/
}
