package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;
import br.ufrpe.mp_music_store.negocio.Fachada;
import br.ufrpe.mp_music_store.negocio.beans.Cd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AtualizarCdController implements Initializable{
	@FXML 
	private Button botaoAtualizar, backBttn;
	@FXML
	private Label aviso;
	@FXML 
	private TextField newTitulo, newArtista, newPreco;
	@FXML
	private DatePicker newAnoLancamento;

	private Cd cdEdit;

	@FXML
	public void enviarCd(ActionEvent event){
		String titulo, anoLancamento, artista, preco_Cd;

		titulo = newTitulo.getText();
		artista = newArtista.getText();
		preco_Cd = newPreco.getText();


		if(!titulo.equals("") && newAnoLancamento.getValue() != null && !artista.equals("")
				&& !preco_Cd.equals("")){

			anoLancamento = newAnoLancamento.getValue().format(DateTimeFormatter.ofPattern("yyyy"));

			try {
				int ano = Integer.parseInt(anoLancamento);
				float preco = Float.parseFloat(preco_Cd);

				Cd cd = new Cd(titulo, ano, artista, preco);

				try {
					Stage stage = (Stage) botaoAtualizar.getScene().getWindow();
					Fachada.getInstance().atualizarCd(this.cdEdit.getTitulo(), cd);
					stage.close();

				}catch (ErroAtualizarException e) {
					e.printStackTrace();

				} catch (ObjectNotExistException e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning");
					alert.setHeaderText("Erro ao atualizar!");
					alert.setContentText("CD não existe!");
					alert.showAndWait();
				}

			}catch(Exception e) {
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

	public void setCdEdit(Cd c) {
		this.cdEdit = c;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
}
