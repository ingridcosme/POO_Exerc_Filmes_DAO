package edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmesDAOImpl implements IFilmesDAO {

	private final static String JDBC_CLASS = "org.mariadb.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/filmesdb?allowMultiQueries=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";  // Senha banco de dados
    private Connection con;

    public FilmesDAOImpl() {
        try {
            Class.forName(JDBC_CLASS);
            con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	@Override
	public void inserir(Filme filme) {
		String sql = "INSERT INTO filmes (id, titulo, genero, duracao, lancamento) ";
		sql += " VALUES (0, ?, ?, ?, ?) ";
		try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, filme.getTitulo());
            stmt.setString(2, filme.getGenero());
            stmt.setInt(3, filme.getDuracao());
            stmt.setDate(4, java.sql.Date.valueOf(filme.getLancamento()));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		
	}

	@Override
	public List<Filme> consultar(String titulo) {
		List<Filme> lista = new ArrayList<>();
        String sql = "SELECT * FROM filmes WHERE titulo LIKE '%" + titulo + "%'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Filme filme = new Filme();
                filme.setTitulo(rs.getString("titulo"));
                filme.setGenero(rs.getString("genero"));
                filme.setDuracao(rs.getInt("duracao"));
                filme.setLancamento(rs.getDate("lancamento").toLocalDate());
                lista.add(filme);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
        
	}

}
