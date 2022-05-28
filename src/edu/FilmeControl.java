package edu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FilmeControl {
	private IFilmesDAO dao = new FilmesDAOImpl();
	
	private ObservableList<Filme> filmesLista = FXCollections.observableArrayList();
	private TableView<Filme> table = new TableView<>();
	
	private StringProperty titulo = new SimpleStringProperty("");
	private StringProperty genero = new SimpleStringProperty("");
	private IntegerProperty duracao = new SimpleIntegerProperty();
	private ObjectProperty<LocalDate> lancamento = new SimpleObjectProperty<>();

	public StringProperty tituloProperty() {
		return titulo;
	}
	
	public StringProperty generoProperty() {
		return genero;
	}
	
	public IntegerProperty duracaoProperty() {
		return duracao;
	}
	
	public ObjectProperty<LocalDate> lancamentoProperty() {
		return lancamento;
	}
	
	@SuppressWarnings("unchecked")
	public FilmeControl() {
		TableColumn<Filme, String> colunaTitulo = new TableColumn<>("Título");
		TableColumn<Filme, String> colunaGenero = new TableColumn<>("Gênero");
		TableColumn<Filme, String> colunaDuracao = new TableColumn<>("Duração (min.)");
		TableColumn<Filme, String> colunaLancamento = new TableColumn<>("Lançamento");
		
		colunaTitulo.setCellValueFactory(new PropertyValueFactory<Filme, String>("titulo"));
		colunaGenero.setCellValueFactory(new PropertyValueFactory<Filme, String>("genero"));
		colunaDuracao.setCellValueFactory(new PropertyValueFactory<Filme, String>("duracao"));
		colunaLancamento.setCellValueFactory(data -> {
			LocalDate dataLancamento = data.getValue().getLancamento();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return new ReadOnlyStringWrapper(dataLancamento.format(formatter));
		});
		
		table.getColumns().addAll(colunaTitulo, colunaGenero, colunaDuracao, colunaLancamento);
		table.setItems(filmesLista);
	}
	
	public void adicionar() {
		Filme filme = new Filme();
		filme.setTitulo(titulo.get());
		filme.setGenero(genero.get());
		filme.setDuracao(duracao.get());
		filme.setLancamento(lancamento.get());
		
		filmesLista.add(filme);
		dao.inserir(filme);
	}
	
	public void pesquisar() {
		List<Filme> lista = dao.consultar(titulo.get());
		filmesLista.clear();
		filmesLista.addAll(lista);
	}
	
	public TableView<Filme> getTable() {
		return table;
	}
	
}
