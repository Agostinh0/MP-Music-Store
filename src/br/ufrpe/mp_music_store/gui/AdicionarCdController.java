package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.exceptions.ObjectExistException;
import br.ufrpe.mp_music_store.negocio.Fachada;
import br.ufrpe.mp_music_store.negocio.beans.Cd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdicionarCdController implements Initializable {

	@FXML 
	private Button botaoCadastrar, backBttn;
	@FXML
	private Label aviso;
	@FXML 
	private TextField tituloCd, artistaCd, precoCd;
	@FXML
	private DatePicker anoLancamentoCd;


	@FXML
	public void enviarCd(ActionEvent event){
		String titulo, anoLancamento, artista, preco_Cd;

		titulo = tituloCd.getText();
		artista = artistaCd.getText();
		preco_Cd = precoCd.getText();


		if(!titulo.equals("") && anoLancamentoCd.getValue() != null && !artista.equals("")
				&& !preco_Cd.equals("")){

			anoLancamento = anoLancamentoCd.getValue().format(DateTimeFormatter.ofPattern("yyyy"));

			try{
				int ano = Integer.parseInt(anoLancamento);
				float preco = Float.parseFloat(preco_Cd);

				Cd cd = new Cd(titulo, ano, artista, preco);

				try{
					Stage stage = (Stage) botaoCadastrar.getScene().getWindow();
					Fachada.getInstance().adicionarCd(cd);
					Fachada.getInstance().salvarArquivoCds();
					System.out.println("Cadastrado!");
					stage.close();

				}catch(ObjectExistException e){
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning");
					alert.setHeaderText("Erro ao cadastrar!");
					alert.setContentText("CD já existe!");
					alert.showAndWait();
				}

			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}else{
			aviso.setText("Preencha todos os campos!");
		}
	}

	public void voltarInicio(ActionEvent event) {
		Stage stage = (Stage) backBttn.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}