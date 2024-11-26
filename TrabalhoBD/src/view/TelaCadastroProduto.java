	package view;
	
	import controller.Controller;
	import controller.LojaController;
	import controller.VendedorController;
	import javafx.application.Application;
	import javafx.geometry.Insets;
	import javafx.scene.Scene;
	import javafx.scene.control.*;
	import javafx.scene.layout.VBox;
	import javafx.stage.Stage;
	
	public class TelaCadastroProduto extends Application {
		LojaController lojaCtrl = new LojaController();
	    @Override
	    public void start(Stage primaryStage) {
	
	        Label labelTitulo = new Label("Cadastrar Novo Produto");
	        labelTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
	
	        TextField tfNome = new TextField();
	        tfNome.setPromptText("Nome do Produto");
	
	        TextField tfDescricao = new TextField();
	        tfDescricao.setPromptText("Descrição do Produto");
	
	        TextField tfPreco = new TextField();
	        tfPreco.setPromptText("Preço");
	
	        Button btnSalvar = new Button("Salvar Produto");
	        btnSalvar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
	
	        VBox vbox = new VBox(10, labelTitulo, tfNome, tfDescricao, tfPreco, btnSalvar);
	        vbox.setPadding(new Insets(20));
	        
	        btnSalvar.setOnAction(e->{
	        	String nome = tfNome.getText().trim();
	        	String descricao = tfDescricao.getText().trim();
	        	double preco = Double.parseDouble(tfPreco.getText().trim());
	        	lojaCtrl.cadastrarProduto(nome,descricao,preco);
	        	voltarTela(primaryStage);
	        });
	        Scene scene = new Scene(vbox, 300, 300);
	        primaryStage.setTitle("Cadastro de Produto");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
	    
	    private void voltarTela(Stage primaryStage) {
	    	TelaVendedor telaVendedor = new TelaVendedor();
	    	telaVendedor.start(primaryStage);
	    }
	    

	}
