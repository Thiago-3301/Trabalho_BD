package view;

import controller.ClienteController;
import controller.Controller;
import controller.VendedorController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CadastroTela extends Application {
	ClienteController clienteCtrl = Controller.getClienteController();
	VendedorController vendedorCtrl = Controller.getVendedorController();

	@Override
	public void start(Stage primaryStage) {

		Label labelTitulo = new Label("Cadastro de Usuário");
		labelTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

		TextField tfNome = new TextField();
		tfNome.setPromptText("Nome completo");

		TextField tfEmail = new TextField();
		tfEmail.setPromptText("Email");

		PasswordField tfSenha = new PasswordField();
		tfSenha.setPromptText("Senha");

		PasswordField tfConfirmarSenha = new PasswordField();
		tfConfirmarSenha.setPromptText("Confirmar senha");

		ComboBox<String> cbTipoUsuario = new ComboBox<>();
		cbTipoUsuario.getItems().addAll("Cliente", "Vendedor");
		cbTipoUsuario.setPromptText("Selecione o tipo de usuário");

		Button btnCadastrar = new Button("Cadastrar");
		btnCadastrar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

		btnCadastrar
				.setOnMouseEntered(e -> btnCadastrar.setStyle("-fx-background-color: #45a049; -fx-text-fill: white;"));
		btnCadastrar.setOnMouseExited(e -> btnCadastrar
				.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;"));

		Hyperlink linkLogin = new Hyperlink("Já tem cadastro? Faça login");
		linkLogin.setOnAction(e -> abrirTelaLogin(primaryStage));

		VBox vbox = new VBox(10, labelTitulo, tfNome, tfEmail, tfSenha, tfConfirmarSenha, cbTipoUsuario, btnCadastrar,
				linkLogin);
		vbox.setPadding(new Insets(20));

		btnCadastrar.setOnAction(e -> {
			if (cbTipoUsuario.getValue().equals("Cliente")) {
				clienteCtrl.Cadastro(tfNome.getText().trim(),tfEmail.getText().trim(),tfSenha.getText().trim(),primaryStage);
				
			}
			if (cbTipoUsuario.getValue().equals("Vendedor")) {
				vendedorCtrl.Cadastro(tfNome.getText(),tfEmail.getText(),tfSenha.getText(),primaryStage);				
			}
			if(cbTipoUsuario.equals("Selecione o tipo de usuário")) {
			}
		});

		Scene scene = new Scene(vbox, 300, 400);
		primaryStage.setTitle("Tela de Cadastro");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void abrirTelaLogin(Stage primaryStage) {
		TelaLogin telaLogin = new TelaLogin();

		telaLogin.start(primaryStage);
	}

}
