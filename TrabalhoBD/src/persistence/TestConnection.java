package persistence;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Cria uma instância do GenericDao para testar
            GenericDao dao = new GenericDao();

            // Tenta obter a conexão
            connection = dao.getConnection();

            // Verifica se a conexão foi aberta com sucesso
            if (connection != null && !connection.isClosed()) {
                System.out.println("Conexão bem-sucedida!");
            } else {
                System.out.println("Conexão falhou!");
            }

        } catch (ClassNotFoundException e) {
            // Mensagem de erro caso o driver não seja encontrado
            System.out.println("Driver não encontrado: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            // Mensagem de erro em caso de problemas de conexão
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fecha a conexão no bloco finally para garantir que será fechada
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Conexão fechada com sucesso.");
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar a conexão: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
