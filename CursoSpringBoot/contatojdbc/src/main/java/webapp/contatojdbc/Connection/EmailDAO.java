package webapp.contatojdbc.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EmailDAO {
    // atributo
   private Connection connection;

   // construtor
   public EmailDAO() {
       this.connection = ConnectionFactory.getConnection();
   }
   
   // Criar tabela
   public void criaTabela() {
       String sql = "CREATE TABLE IF NOT EXISTS newsletter_spring (ID SERIAL PRIMARY KEY, EMAIL VARCHAR(255))";
       try (Statement stmt = this.connection.createStatement()) {
           stmt.execute(sql);
           System.out.println("Tabela criada com sucesso.");
       } catch (SQLException e) {
           throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
       }
   }
   
   // Cadastrar e-mail
   public void cadastrar(String email) {
       String sql = "INSERT INTO newsletter_spring (email) VALUES (?)";
       try (PreparedStatement stmt = connection.prepareStatement(sql)) {
           stmt.setString(1, email);
           stmt.executeUpdate();
           System.out.println("E-mail cadastrado com sucesso");
       } catch (SQLException e) {
           throw new RuntimeException("Erro ao inserir e-mail no banco de dados.", e);
       }
   }
   
   // Fechar conexão
   public void fecharConexao() {
       ConnectionFactory.closeConnection(connection);
   }
}
