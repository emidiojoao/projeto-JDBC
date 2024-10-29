package edu.senai.br.jdbc.dao;

import edu.senai.br.jdbc.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author joao_c_emidio
 */
public class CleanDataTableDAO {

    public void deleteTabelaFilme() throws SQLException {
        String sql = "DELETE FROM FILME;";
        try (Connection conn = ConexaoDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();

        }
    }

    public void deleteTabelaCategoria() throws SQLException {
        String sql = "DELETE FROM CATEGORIA;";
        try (Connection conn = ConexaoDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();

        }
    }

    public void setAutoIncrementeOneTabelaFilme() throws SQLException {
        String sql = "ALTER TABLE FILME AUTO_INCREMENT=1;;";
        try (Connection conn = ConexaoDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();

        }
    }

    public void setAutoIncrementeOneTabelaCategoria() throws SQLException {
        String sql = "ALTER TABLE CATEGORIA AUTO_INCREMENT=1;;";
        try (Connection conn = ConexaoDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();

        }
    }

}
