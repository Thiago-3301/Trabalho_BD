package controller;

import java.util.ArrayList;

import model.Produto;
import model.Vendedor;

public class Controller {
	private static ClienteController clienteController = new ClienteController();

	public static ClienteController getClienteController() {
		return clienteController;
	}

	private static VendedorController vendedorController = new VendedorController();

	public static VendedorController getVendedorController() {
		return vendedorController;
	}
	
	private static CarrinhoController carrinhoController = new CarrinhoController();

	public static CarrinhoController getCarrinhoController() {
		return carrinhoController;
	}

	public static void setCarrinhoController(CarrinhoController carrinhoController) {
		Controller.carrinhoController = carrinhoController;
	}

	public static int qtdTotalProdutos() {
		int tamanho = 0;
		for (Vendedor vendedor : vendedorController.getVendedores()) {
			tamanho += vendedor.getLoja().getEstoque().size();
		}

		return tamanho;
	}

	public static ArrayList<Produto> getProduto() {
	    ArrayList<Produto> todosProdutos = new ArrayList<>();
	    ArrayList<Vendedor> aux = (ArrayList<Vendedor>) vendedorController.getVendedores();
	    
	    System.out.println("Total de vendedores: " + aux.size());

	    for (Vendedor vendedor : aux) {
	        ArrayList<Produto> estoqueLoja = vendedor.getLoja().getEstoque();
	        
	        System.out.println("Produtos na loja do vendedor " + vendedor.getNome() + ": " + estoqueLoja.size());

	        todosProdutos.addAll(estoqueLoja); 
	    }

	    System.out.println("Total de produtos retornados: " + todosProdutos.size());
	    return todosProdutos;
	}
}
