package model;

public class Vendedor {
	private Loja loja;
	private String nome;
	private String email;
	private String senha;

	public Vendedor(String nome2, String email2, String senha2) {
		nome = nome2;
		email = email2;
		senha = senha2;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void criarLoja(String nomeLoja, String descricao) {
		this.loja = new Loja(nomeLoja,descricao);
		
	}

}