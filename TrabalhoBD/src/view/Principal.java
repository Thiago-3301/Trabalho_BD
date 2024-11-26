package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class Principal extends Application {

    public static void main(String[] args) {
        launch(args); 
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       
        CadastroTela tela = new CadastroTela();
        tela.start(primaryStage);
    }
}
