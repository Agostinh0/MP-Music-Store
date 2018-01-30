package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.exceptions.ObjectExistException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;
import br.ufrpe.mp_music_store.negocio.Fachada;
import br.ufrpe.mp_music_store.negocio.beans.Cd;
import br.ufrpe.mp_music_store.negocio.beans.Cliente;
import br.ufrpe.mp_music_store.negocio.beans.Venda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AdicionarVendaController implements Initializable{
	@FXML 
	private Button botaoCadastrar, backBttn;
	@FXML
	private Label aviso, warn;
	@FXML 
	private TextField cpfCliente, tituloCd, codigoVenda;	

	@FXML
	public void registVenda(ActionEvent event){
		String cpfS, tituloS, codigoS;

		cpfS = cpfCliente.getText();
		tituloS = tituloCd.getText();
		codigoS = codigoVenda.getText();

		if(!cpfS.equals("") && !tituloS.equals("") && !codigoS.equals("")) {

			try{
				long cpf = Long.parseLong(cpfS);
				long codigo = Long.parseLong(codigoS);

				Cliente cliente = Fachada.getInstance().buscarCliente(cpf);
				Cd cd = Fachada.getInstance().buscarCd(tituloS);

				Venda venda = new Venda(cliente, cd, codigo);

				try{
					Stage stage = (Stage) botaoCadastrar.getScene().getWindow();
					Fachada.getInstance().registrarVenda(venda);
					System.out.println("Registrado!");
					stage.close();

				}catch(ObjectExistException e){
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning");
					alert.setHeaderText("Erro ao registrar!");
					alert.setContentText("Venda já registrada!");
					alert.showAndWait();
				}

			}catch(ObjectNotExistException e) {
				warn.setText("Cliente ou CD Inexistente(s).");
				
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
