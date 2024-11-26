package model;

import java.util.ArrayList;

public class Loja {
	private String nome;
	private String descricao;
	private ArrayList<Produto> estoque = new ArrayList<>();

	public Loja() {
		super();
	}

	public Loja(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Produto> getEstoque() {
		return estoque;
	}

	public void setEstoque(ArrayList<Produto> estoque) {
		this.estoque = estoque;
	}
	
	public void adicionarProdutoEstoque(Produto produto) {
		produto.setLoja(this);
		estoque.add(produto);
	}
	
	public void removerProdutoEstoque(Produto Produto) {
		estoque.remove(Produto);
	}

}
