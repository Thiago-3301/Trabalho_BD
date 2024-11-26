package model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Produto {

	private StringProperty nome;
	private StringProperty descricao;
	private DoubleProperty preco;
	private Loja loja;

	public Produto() {
		this.nome = new SimpleStringProperty();
		this.descricao = new SimpleStringProperty();
		this.preco = new SimpleDoubleProperty();
	}

	public Produto(String nome, String descricao, double preco) {
		this.nome = new SimpleStringProperty(nome);
		this.descricao = new SimpleStringProperty(descricao);
		this.preco = new SimpleDoubleProperty(preco);
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Loja getLoja() {
		return this.loja;
	}

	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}

	public String getDescricao() {
		return descricao.get();
	}

	public void setDescricao(String descricao) {
		this.descricao.set(descricao);
	}

	public double getPreco() {
		return preco.get();
	}

	public void setPreco(double preco) {
		this.preco.set(preco);
	}

	public StringProperty nomeProperty() {
		return nome;
	}

	public StringProperty descricaoProperty() {
		return descricao;
	}

	public DoubleProperty precoProperty() {
		return preco;
	}
}
