package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Produto; 
import controller.Controller; 

public class TelaCarrinho extends Application {

	@Override
	public void start(Stage primaryStage) {
		var items = Controller.getClienteController().getClienteLogado().getCarrinho().getItems();
		ListView<String> listViewProdutos = new ListView<>(); 
		Label lblTotal = new Label("Valor Total: R$ 0.00");

		double total = 0.0;

		for (Produto produto : items) {
			listViewProdutos.getItems().add(produto.getNome() + " - R$ " + produto.getPreco());
			total += produto.getPreco();
		}

		lblTotal.setText("Valor Total: R$ " + total);

		Button btnVoltar = new Button("Voltar para Loja");
		btnVoltar.setStyle("-fx-background-color: #FF5722; -fx-text-fill: white;");

		Button btnPagar = new Button("Pagar Compras");
		btnPagar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");


		VBox vbox = new VBox(10, lblTotal, listViewProdutos, btnVoltar, btnPagar);
		vbox.setPadding(new Insets(20));
		vbox.setAlignment(Pos.CENTER);


		btnVoltar.setOnAction(e -> voltar(primaryStage));

		Scene scene = new Scene(vbox, 600, 400);
		primaryStage.setTitle("Carrinho de Compras");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public void voltar(Stage primaryStage) {
		TelaClienteProdutos telaClienteProdutos = new TelaClienteProdutos();
		telaClienteProdutos.start(primaryStage);
	}

}
