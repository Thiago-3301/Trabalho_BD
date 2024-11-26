package controller;

import model.Vendedor;
import view.CadastroLoja;
import view.TelaVendedor;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class VendedorController {
	private List<Vendedor> vendedores = new ArrayList<>();

	public void Cadastro(String nome, String email, String senha, Stage primaryStage) {
		Vendedor vendedor = new Vendedor(nome, email, senha);
		vendedores.add(vendedor);
		System.out.println("Cadastro realizado com sucesso. Total de vendedores: " + vendedores.size());
		chamarCadastroLoja(primaryStage);
	}

	public boolean Login(String email, String senha, Stage primaryStage) {
		for (Vendedor vendedor : vendedores) {
			System.out.println("Tentando login com email: " + email + " e senha: " + senha);
			if (vendedor.getEmail().equals(email) && vendedor.getSenha().equals(senha)) {
				Vendedor aux = vendedor;
				vendedores.remove(aux);
				vendedores.add(0, aux);
				chamarTelaVendedor(primaryStage);
				return true;
			}
		}
		System.out.println("Login ou senha inválidos.");
		return false;
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	private void chamarCadastroLoja(Stage primaryStage) {
		primaryStage.close();
		CadastroLoja cadastroLoja = new CadastroLoja();
		cadastroLoja.start(primaryStage);
	}

	private void chamarTelaVendedor(Stage primaryStage) {
		TelaVendedor telaVendedor = new TelaVendedor();
		telaVendedor.start(primaryStage);
	}

	public void cadastrarLoja(String nomeLoja, String descricao) {
		Vendedor vendedor = vendedores.get(vendedores.size()-1);
		vendedor.criarLoja(nomeLoja, descricao);

	}

	public Vendedor getVendedorLogado() {
		return vendedores.get(0);
		
	}
}
