package model;

import java.util.ArrayList;

public class Carrinho {
	private ArrayList<Produto> items = new ArrayList<>();

	public Carrinho() {
		super();
	}

	public Carrinho(ArrayList<Produto> items) {
		super();
		this.items = items;
	}

	public ArrayList<Produto> getItems() {
		return items;
	}

	public void setItems(ArrayList<Produto> items) {
		this.items = items;
	}

	public void adicionarProduto(Produto produto) {
		items.add(produto);
	}

	public void removerProduto(Produto produto) {
		items.remove(produto);
	}

	public boolean isCarrinhoEmpty() {
		return items.isEmpty();
	}

}
