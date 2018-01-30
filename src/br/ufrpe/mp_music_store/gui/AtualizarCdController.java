package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.dados.RepositorioCds;
import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
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
	
	@FXML	private Button botaoAlterar, backBttn;
	@FXML	private Label aviso;
	@FXML	private TextField novoTituloCd, novoArtistaCd, novoPrecoCd;
	@FXML	private DatePicker novoAnoLancamentoCd;
	String cdAntigo;
	
	@FXML
	public void atualizarCd(ActionEvent action){
		String novoTitulo, novoAnoLancamento, novoArtista, novoPreco;
		
		novoTitulo = this.novoTituloCd.getText();
		novoArtista = this.novoArtistaCd.getText();
		novoPreco = this.novoPrecoCd.getText();
		
		if(!novoTitulo.equals("") && this.novoAnoLancamentoCd.getValue() != null && !novoArtista.equals("")
				&& !novoPreco.equals("")){
			
			novoAnoLancamento = this.novoAnoLancamentoCd.getValue().format(DateTimeFormatter.ofPattern("yyyy"));
			
			try{
				int novoAno = Integer.parseInt(novoAnoLancamento);
				float newPrice = Float.parseFloat(novoPreco);
				
				Cd edita = new Cd(novoTitulo, novoAno, novoArtista, newPrice);
				
				try{
					Stage stage = (Stage) botaoAlterar.getScene().getWindow();
					RepositorioCds.getInstance().atualizar(cdAntigo, edita);
					Fachada.getInstance().salvarArquivoCds();
					System.out.println("CD atualizado!");
					stage.close();
				}catch(ErroAtualizarException e){
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning!");
					alert.setContentText("Erro ao atualizar!");
					alert.showAndWait();
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}else{
			System.out.println("Preencha todos os campos!");
		}
	}
	
	public void voltarInicio(ActionEvent event){
		Stage stage = (Stage) backBttn.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
