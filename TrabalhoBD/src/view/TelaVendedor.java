package view;

import java.util.List;
import java.util.stream.Collectors;

import controller.Controller;
import controller.VendedorController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Produto;
import model.Vendedor;

public class TelaVendedor extends Application {
	VendedorController vendedorCtrl = Controller.getVendedorController();

	@Override
	public void start(Stage primaryStage) {
		Label labelTitulo = new Label("Produtos da sua Loja");
		Button btnAdicionarProduto = new Button("Adicionar Produto");
		Button btnDeletarProduto = new Button("Deletar Produto");
		Button btnTeste = new Button("Voltar");

		VBox vboxPrincipal = new VBox(15);
		labelTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

		btnAdicionarProduto.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
		btnDeletarProduto.setStyle("-fx-background-color: #FF6347; -fx-text-fill: white; -fx-font-weight: bold;");

		vboxPrincipal.setPadding(new Insets(20));
		vboxPrincipal.setAlignment(Pos.TOP_CENTER);

		TextField searchField = new TextField();
		searchField.setPromptText("Pesquisar Produto...");
		searchField.setStyle("-fx-padding: 5px;");

		TableView<Produto> tableView = new TableView<>();
		tableView.setStyle("-fx-background-color: #f4f4f4;");

		TableColumn<Produto, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());

		TableColumn<Produto, String> colDescricao = new TableColumn<>("Descrição");
		colDescricao.setCellValueFactory(cellData -> cellData.getValue().descricaoProperty());

		TableColumn<Produto, Double> colPreco = new TableColumn<>("Preço");
		colPreco.setCellValueFactory(cellData -> cellData.getValue().precoProperty().asObject());

		tableView.getColumns().add(colNome);
		tableView.getColumns().add(colDescricao);
		tableView.getColumns().add(colPreco);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(tableView);
		scrollPane.setFitToWidth(true);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		vboxPrincipal.getChildren().addAll(labelTitulo, searchField, scrollPane, btnAdicionarProduto, btnDeletarProduto,
				btnTeste);

		btnAdicionarProduto.setOnAction(e -> {
			primaryStage.close();
			TelaCadastroProduto telaCadastroProduto = new TelaCadastroProduto();
			telaCadastroProduto.start(new Stage());
		});

		btnDeletarProduto.setOnAction(e -> {
			Produto produtoSelecionado = tableView.getSelectionModel().getSelectedItem();
			if (produtoSelecionado != null) {
				Vendedor vendedorLogado = vendedorCtrl.getVendedorLogado();
				if (vendedorLogado != null) {
					vendedorLogado.getLoja().removerProdutoEstoque(produtoSelecionado);
					carregarProdutos(tableView);
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Seleção de Produto");
				alert.setHeaderText("Nenhum Produto Selecionado");
				alert.setContentText("Por favor, selecione um produto para excluir.");
				alert.showAndWait();
			}
		});

		btnTeste.setOnAction(e -> {
			CadastroTela cadastro = new CadastroTela();
			cadastro.start(primaryStage);
		});

		carregarProdutos(tableView);

		searchField.setOnKeyReleased(e -> filtrarProdutos(e, tableView));

		Scene scene = new Scene(vboxPrincipal, 800, 600);
		primaryStage.setTitle("Tela de Vendedor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void carregarProdutos(TableView<Produto> tableView) {
		Vendedor vendedorLogado = vendedorCtrl.getVendedorLogado();

		if (vendedorLogado != null) {
			List<Produto> estoque = vendedorLogado.getLoja().getEstoque();

			if (estoque != null && !estoque.isEmpty()) {
				ObservableList<Produto> produtosObservableList = FXCollections.observableArrayList(estoque);
				tableView.setItems(produtosObservableList);
			} else {
				Label lblNoProducts = new Label("Não há produtos na sua loja.");
				tableView.getItems().clear();
				tableView.getColumns().clear();
				tableView.getColumns().add(new TableColumn<>("Aviso: Não há produtos na sua loja"));
			}
		} else {
			Label lblErro = new Label("Erro: Nenhum vendedor logado.");
			tableView.getItems().clear();
			tableView.getColumns().clear();
			tableView.getColumns().add(new TableColumn<>("Erro: Nenhum vendedor logado."));
		}
	}

	private void filtrarProdutos(KeyEvent keyEvent, TableView<Produto> tableView) {
		TextField searchField = (TextField) keyEvent.getSource();
		String searchText = searchField.getText().toLowerCase();

		Vendedor vendedorLogado = vendedorCtrl.getVendedorLogado();
		if (vendedorLogado != null) {
			List<Produto> estoque = vendedorLogado.getLoja().getEstoque();

			List<Produto> filteredList = estoque.stream()
					.filter(produto -> produto.getNome().toLowerCase().contains(searchText))
					.collect(Collectors.toList());

			ObservableList<Produto> produtosObservableList = FXCollections.observableArrayList(filteredList);
			tableView.setItems(produtosObservableList);
		}
	}

}
