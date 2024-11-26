package view;

import controller.ClienteController;
import controller.Controller;
import controller.VendedorController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CadastroLoja extends Application {

	VendedorController vendedorCtrl = Controller.getVendedorController();

	@Override
	public void start(Stage primaryStage) {

		Label lblNomeLoja = new Label("Nome da Loja:");
		TextField txtNomeLoja = new TextField();

		Label lblDescricaoLoja = new Label("Descrição da Loja:");
		TextField txtDescricaoLoja = new TextField();

		Button btnCadastrarLoja = new Button("Cadastrar Loja");
		btnCadastrarLoja.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

		VBox vbox = new VBox(10, lblNomeLoja, txtNomeLoja, lblDescricaoLoja, txtDescricaoLoja, btnCadastrarLoja);
		vbox.setPadding(new Insets(20));
		vbox.setAlignment(Pos.CENTER);

		btnCadastrarLoja.setOnAction(e -> {
			String nomeLoja = txtNomeLoja.getText().trim();
			String descricao = txtDescricaoLoja.getText().trim();

			cadastrarLoja(nomeLoja, descricao);
			chamarTelaVendedor(primaryStage);
		});

		Scene scene = new Scene(vbox, 400, 250);
		primaryStage.setTitle("Cadastro de Loja");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void chamarTelaVendedor(Stage primaryStage) {
		TelaVendedor telaVendedor = new TelaVendedor();
		telaVendedor.start(primaryStage);

	}

	private void cadastrarLoja(String nomeLoja, String descricao) {
		vendedorCtrl.cadastrarLoja(nomeLoja, descricao);
	}

}
