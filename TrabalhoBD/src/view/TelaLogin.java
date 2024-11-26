package view;

import controller.ClienteController;
import controller.Controller;
import controller.VendedorController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaLogin extends Application {
	ClienteController clienteCtrl = Controller.getClienteController();
	VendedorController vendedorCtrl = Controller.getVendedorController();

	public void start(Stage primaryStage) {

		Label lblTitulo = new Label("Bem-vindo ao Sistema!");
		lblTitulo.setFont(new Font("Arial", 20));
		lblTitulo.setStyle("-fx-font-weight: bold;");
		lblTitulo.setTextFill(Color.web("#0076a3"));

		Label lblUsuario = new Label("Usuário:");
		TextField tfUsuario = new TextField();
		tfUsuario.setPromptText("Digite seu email");

		Label lblSenha = new Label("Senha:");
		PasswordField pfSenha = new PasswordField();
		pfSenha.setPromptText("Digite sua senha");

		Label lblTipoUsuario = new Label("Tipo de Usuário:");
		ComboBox<String> comboBoxTipoUsuario = new ComboBox<>();
		comboBoxTipoUsuario.getItems().addAll("Cliente", "Vendedor");
		comboBoxTipoUsuario.setValue("Cliente");

		Button btnLogin = new Button("Entrar");
		btnLogin.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");

		Hyperlink linkCadastro = new Hyperlink("Não tem login? Se cadastre");
		linkCadastro.setOnAction(e -> abrirTelaCadastro(primaryStage));

		GridPane grid = new GridPane();
		grid.setVgap(15);
		grid.setHgap(10);
		grid.setPadding(new Insets(20, 20, 20, 20));
		grid.setAlignment(Pos.CENTER);

		grid.add(lblTitulo, 0, 0, 2, 1);
		grid.add(lblUsuario, 0, 1);
		grid.add(tfUsuario, 1, 1);
		grid.add(lblSenha, 0, 2);
		grid.add(pfSenha, 1, 2);
		grid.add(lblTipoUsuario, 0, 3);
		grid.add(comboBoxTipoUsuario, 1, 3);
		grid.add(btnLogin, 0, 4, 2, 1);
		grid.add(linkCadastro, 0, 5, 2, 1);

		btnLogin.setOnAction(e -> {
			String usuario = tfUsuario.getText().trim();
			String senha = pfSenha.getText().trim();
			String tipoUsuario = comboBoxTipoUsuario.getValue();

			if (tipoUsuario.equals("Cliente")) {
				if (clienteCtrl.Login(usuario, senha, primaryStage)) {
					return;
				}
			} else if (tipoUsuario.equals("Vendedor")) {
				if (vendedorCtrl.Login(usuario, senha, primaryStage)) {
					return;
				}
			}

			mostrarAlerta("Login ou senha inválidos.");
		});

		btnLogin.setOnMouseEntered(e -> btnLogin.setStyle("-fx-background-color: #45a049; -fx-text-fill: white;"));
		btnLogin.setOnMouseExited(
				e -> btnLogin.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;"));

		Scene scene = new Scene(grid, 350, 300);
		scene.setFill(Color.web("#f4f4f4"));

		primaryStage.setTitle("Tela de Login");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void abrirTelaCadastro(Stage primaryStage) {
		CadastroTela cadastro = new CadastroTela();
		cadastro.start(primaryStage);
	}

	private void mostrarAlerta(String mensagem) {
		javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
		alerta.setContentText(mensagem);
		alerta.showAndWait();
	}

}
