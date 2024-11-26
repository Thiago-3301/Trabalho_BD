package view;

import java.util.ArrayList;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Produto;

public class TelaClienteProdutos extends Application {
	
    @Override
    public void start(Stage primaryStage) {
    	
        TextField searchBar = new TextField();
        searchBar.setPromptText("Digite o nome do produto...");
        searchBar.setStyle("-fx-padding: 10px; -fx-font-size: 14px; -fx-border-radius: 5px; -fx-border-color: #ccc;");

        Label labelTitulo = new Label("Loja de Produtos");
        labelTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.TOP_CENTER);

     
        grid.add(searchBar, 0, 0, 5, 1);
        grid.add(labelTitulo, 0, 1, 5, 1);


        VBox vboxProdutos = new VBox(10);
        

        int column = 0;
        int row = 2; 
        
        for (Produto produto : Controller.getProduto()) { 
            Label nomeLabel = new Label(produto.getNome());
            nomeLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Label descricaoLabel = new Label("Descrição do Produto \n" + produto.getDescricao());
            descricaoLabel.setStyle("-fx-font-size: 12px;");

            Label precoLabel = new Label("Preço: \n" + produto.getPreco());
            precoLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            Label lojaLabel = new Label(produto.getLoja().getNome());
            lojaLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            Button btnAdicionar = new Button("Adicionar ao Carrinho");
            btnAdicionar.setStyle("-fx-background-color: #ff9900; -fx-text-fill: white;");
            
            btnAdicionar.setOnAction(e -> {
                 Controller.getClienteController().cadastrarProdutoCarrinho(produto);
            });

            VBox vboxProduto = new VBox(10, nomeLabel, descricaoLabel, precoLabel, lojaLabel, btnAdicionar);
            vboxProduto.setStyle("-fx-border-color: #ccc; -fx-padding: 10; -fx-border-width: 1; -fx-alignment: center;");

       
            grid.add(vboxProduto, column, row); 
            
           
            column++;
            if (column == 4) {  
                column = 0;
                row++;
            }
        }

        Button btnCarrinho = new Button("Ver Carrinho");
        btnCarrinho.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        HBox hboxCarrinho = new HBox(btnCarrinho);
        hboxCarrinho.setAlignment(Pos.TOP_RIGHT); 
        hboxCarrinho.setPadding(new Insets(20)); 

        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");

        btnCarrinho.setOnAction(e -> {
            chamarCarrinho(primaryStage);
        });
        

        VBox vboxLayout = new VBox(hboxCarrinho, scrollPane); 

        Scene scene = new Scene(vboxLayout, 800, 600);

        primaryStage.setTitle("Loja de Produtos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void chamarCarrinho(Stage primaryStage) {
        TelaCarrinho telaCarrinho = new TelaCarrinho();
        telaCarrinho.start(primaryStage);
    }

}
