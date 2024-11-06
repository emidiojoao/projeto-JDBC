
package edu.senai.br.jdbc.dao;
import edu.senai.br.jdbc.ConexaoDB;
import edu.senai.br.jdbc.entities.Filme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camila_alves3
 */
public class FilmeDAO {
    
    // Create
    public void inserirFilme(Filme filme) throws SQLException {
        String sql = "INSERT INTO Filme (titulo, ano, diretor, categoria_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, filme.getTitulo());
            stmt.setInt(2, filme.getAno());
            stmt.setString(3, filme.getDiretor());
            stmt.setInt(4, filme.getCategoria_id());
            stmt.executeUpdate();

            // Capturar o ID gerado automaticamente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    filme.setId(generatedKeys.getInt(1));  // Definir o ID no objeto Categoria
                    filme.setTitulo(filme.getTitulo());
                    filme.setAno(filme.getAno());
                    filme.setDiretor(filme.getDiretor());
                    filme.setCategoria_id(filme.getCategoria_id());
                          
                } else {
                    throw new SQLException("Falha ao inserir filme, nenhum ID gerado.");
                }
            }

        }
    }
    
    public Filme buscarFilmePorId(int id) throws SQLException {
        String sql = "SELECT * FROM Filme WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Filme(
                        rs.getInt( "id"),
                        rs.getString("titulo"),
                        rs.getInt("ano"),
                        rs.getString("diretor"),
                        rs.getInt("categoria_id"));
            }
        }
    return null;
}
    
        // Get filmes
    public List<Filme> listarFilmes() throws SQLException {
        String sql = "SELECT * FROM Filme";
        List<Filme> filmes = new ArrayList<>();
        try (Connection connection = ConexaoDB.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                filmes.add(new Filme(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getInt("ano"),
                        resultSet.getString("diretor"),
                        resultSet.getInt("categoria_id")
                ));
            }
        }
        return filmes;
    }
    
    public void atualizarFilme (Filme filme) throws SQLException {
        String sql = "UPADATE Filme SET titulo = ?, ano = ?, diretor = ?, categoria_id = ? WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, filme.toString());
            stmt.setInt(2, filme.getAno());
            stmt.setString(3, filme.getDiretor());
            stmt.setInt(4, filme.getCategoria_id());
            stmt.setInt(5, filme.getId());
            stmt.executeUpdate();
            
        }
    }
}
