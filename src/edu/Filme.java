package edu;

import java.time.LocalDate;

public class Filme {

	private String titulo = "";
	private int duracao = 0;
	private LocalDate lancamento = LocalDate.now();
	private String genero = "";
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public int getDuracao() {
		return duracao;
	}
	
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	
	public LocalDate getLancamento() {
		return lancamento;
	}
	
	public void setLancamento(LocalDate lancamento) {
		this.lancamento = lancamento;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Filme [titulo=" + titulo + ", duracao=" + duracao + ", lancamento=" + lancamento + ", genero=" + genero
				+ "]";
	}
	
}
