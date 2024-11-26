package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.stage.Stage;
import model.Cliente;
import model.Produto;
import view.TelaClienteProdutos;

public class ClienteController {

    private List<Cliente> clientes = new ArrayList<>();  


    public void Cadastro(String nome, String email, String senha, Stage primaryStage) {
        Cliente cliente = new Cliente(nome, email, senha);
        clientes.add(cliente);
        System.out.println("Cadastro realizado com sucesso. Total de clientes: " + clientes.size());
        chamarTelaClienteProdutos(primaryStage);
    }

    public boolean Login(String email, String senha, Stage primaryStage) {
   
        System.out.println("Tentando realizar login com email: " + email + " e senha: " + senha);
        

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente registrado.");
            return false; 
        }

        for (Cliente cliente : clientes) {
            System.out.println("Verificando cliente: " + cliente.getEmail());
            if (cliente.getEmail().equals(email) && cliente.getSenha().equals(senha)) {
                System.out.println("Login bem-sucedido para o cliente: " + cliente.getEmail());
                chamarTelaClienteProdutos(primaryStage);
                Cliente aux = cliente;
                clientes.remove(aux);
                clientes.add(0, aux);
                return true; 
                
            }
        }

        System.out.println("Login ou senha inválidos.");
        return false; 
    }
    
    public void cadastrarProdutoCarrinho(Produto produto) {
    	Cliente cliente = getClienteLogado();
    	cliente.getCarrinho().adicionarProduto(produto);
    }

    public void chamarTelaClienteProdutos(Stage primaryStage) {
        primaryStage.close(); 
        TelaClienteProdutos telaCliente = new TelaClienteProdutos();
        telaCliente.start(primaryStage); 
    }
    
    public Cliente getClienteLogado() {
    	return clientes.get(0);
    }
}
