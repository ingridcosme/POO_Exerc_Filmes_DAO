package edu;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;

public class FilmeBoundary extends Application {
	
	private TextField txtTitulo = new TextField();
	private TextField txtGenero = new TextField();
	private TextField txtDuracao = new TextField();
	private TextField txtLancamento = new TextField();
	
	private Button botaoAdicionar = new Button("Adicionar");
	private Button botaoPesquisar = new Button("Pesquisar");
	
	private FilmeControl control = new FilmeControl();

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Filmes");
		
		Label labelTitulo = new Label("Título");
		Label labelGenero = new Label("Gênero");
		Label labelDuracao = new Label("Duração");
		Label labelLancamento = new Label("Lançamento");
		
		BorderPane borderPane = new BorderPane();
		GridPane gridPane = new GridPane();
		borderPane.setTop(gridPane);
		gridPane.setPadding(new Insets (10, 10, 10, 10));
		
		gridPane.add(labelTitulo, 0, 0);
		gridPane.add(txtTitulo, 1, 0);
		gridPane.add(labelGenero, 0, 1);
		gridPane.add(txtGenero, 1, 1);
		gridPane.add(labelDuracao, 0, 2);
		gridPane.add(txtDuracao, 1, 2);
		gridPane.add(labelLancamento, 0, 3);
		gridPane.add(txtLancamento, 1, 3);
		
		gridPane.add(botaoAdicionar, 0, 4);
		gridPane.add(botaoPesquisar, 1, 4);
		
		gridPane.setVgap(10);
		gridPane.setHgap(20);
		
		Bindings.bindBidirectional(txtTitulo.textProperty(), control.tituloProperty());
		Bindings.bindBidirectional(txtGenero.textProperty(), control.generoProperty());
		StringConverter<Number> converterNumber = new NumberStringConverter();
		Bindings.bindBidirectional(txtDuracao.textProperty(), control.duracaoProperty(), converterNumber);
		StringConverter<LocalDate> converterDate = new LocalDateStringConverter();
		Bindings.bindBidirectional(txtLancamento.textProperty(), control.lancamentoProperty(), converterDate);
		
        botaoAdicionar.setOnAction((e) -> {
            control.adicionar();
            txtTitulo.setText("");
            txtGenero.setText("");
            txtDuracao.setText("");
            txtLancamento.setText("");
        });

        botaoPesquisar.setOnAction((e) -> {
            control.pesquisar();
        });
		
        borderPane.setCenter(control.getTable());
		Scene scene = new Scene(borderPane, 1200, 600);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		Application.launch(FilmeBoundary.class, args);
		
	}

}
