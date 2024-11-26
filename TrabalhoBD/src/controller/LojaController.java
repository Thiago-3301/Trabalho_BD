package controller;

import model.Produto;
import model.Vendedor;

public class LojaController {
	VendedorController vendedorCtrl = Controller.getVendedorController();

	public void cadastrarProduto(String nome, String descricao, double preco) {
		Vendedor vendedorLogado = vendedorCtrl.getVendedorLogado();
		Produto produto = new Produto(nome, descricao, preco);
		vendedorLogado.getLoja().adicionarProdutoEstoque(produto);
	}

	public void removerProduto(Produto produto) {
		Vendedor vendedorLogado = vendedorCtrl.getVendedorLogado();
		if (vendedorLogado != null) {
			vendedorLogado.getLoja().removerProdutoEstoque(produto);
		}
	}
}
