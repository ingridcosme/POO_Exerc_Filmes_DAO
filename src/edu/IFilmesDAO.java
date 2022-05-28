package edu;

import java.util.List;

public interface IFilmesDAO {

	void inserir(Filme filme);
	
    List<Filme> consultar(String titulo);
    
}
