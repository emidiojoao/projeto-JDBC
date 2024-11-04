package edu.senai.br.jdbc;

import edu.senai.br.jdbc.dao.CategoriaDAO;
import edu.senai.br.jdbc.dao.CleanDataTableDAO;
import edu.senai.br.jdbc.dao.FilmeDAO;
import edu.senai.br.jdbc.entities.Categoria;
import edu.senai.br.jdbc.entities.Filme;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author joao_c_emidio
 */
public class Jdbc {

    public static void main(String[] args) {
        try {
            //Instanciar
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            FilmeDAO filmeDAO = new FilmeDAO();
            CleanDataTableDAO cleanDB = new CleanDataTableDAO();
            
            
            //cleanDB.deleteTabelaFilme();
            cleanDB.deleteTabelaCategoria();
            cleanDB.setAutoIncrementeOneTabelaCategoria();
            //cleanDB.setAutoIncrementeOneTabelaFilme();

            // Criar algumas categorias
            Categoria categoria1 = new Categoria(0, "Suspense");
            Categoria categoria2 = new Categoria(0, "Drama");
            Categoria categoria3 = new Categoria(0, "Ação");
            Categoria categoria4 = new Categoria(0, "Romance");
            
            // Criar alguns filmes
            Filme filme1 = new Filme(0, "Os Suspeitos", 2013, "Denis Villeneuve", 3);
            Filme filme2 = new Filme(0, "Ordinary Angels", 2024, "Jon Gunn", 2);
            Filme filme3 = new Filme(0,"A Morte do Demônio: A Ascensão", 2023, "Lee Cronin", 1);
            

            //Inserir as categorias no banco
            categoriaDAO.inserirCategoria(categoria1);
            categoriaDAO.inserirCategoria(categoria2);
            categoriaDAO.inserirCategoria(categoria3);
            categoriaDAO.inserirCategoria(categoria4);
            
            filmeDAO.inserirFilme(filme3);
            filmeDAO.inserirFilme(filme2);
            filmeDAO.inserirFilme(filme1);
                    
            Categoria catBuscada = categoriaDAO.buscarCategoriaPorId(2);

            System.out.println("Id: " + catBuscada.getId() + "\nNome: " + catBuscada.getNome());

            // Buscar categoria por ID
            Categoria categoriaBuscada = categoriaDAO.buscarCategoriaPorId(1);
            System.out.println("Categoria ID 1: " + (categoriaBuscada != null ? categoria1.getNome() : "Não encontrada"));

            // Buscar categoria por ID não existente
            categoriaBuscada = categoriaDAO.buscarCategoriaPorId(100);
            System.out.println("Categoria ID 100: " + (categoriaBuscada != null ? categoria1.getNome() : "Não encontrada"));

            // Listar todas as categorias
            List<Categoria> categorias = categoriaDAO.listarCategoria();
            System.out.println("Lista de categorias:");
            for (Categoria categoria : categorias) {
                System.out.println("ID: " + categoria.getId() + ", Nome: " + categoria.getNome());
            }

            // Atualizar uma categoria
            categoria1.setNome("Terror");
            categoriaDAO.atualizarCategoria(categoria1);
            System.out.println("Categoria ID 1 atualizada para: " + categoria1.getNome());

            // Deletar uma categoria
            categoriaDAO.deletarCategoria(4);
            System.out.println("Categoria com nome id=4 foi deletada.");

        } catch (SQLException e) {
            System.err.println("Algo errado aconteceu com a manipulação do DB");
        }
    }
}
