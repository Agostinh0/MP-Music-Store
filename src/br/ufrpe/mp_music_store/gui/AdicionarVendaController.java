package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
	private Label aviso;
	@FXML 
	private TextField cpfCliente, tituloCd, codigoVenda, diaVenda, mesVenda, anoVenda, horaVenda, minVenda;	

	@FXML
	public void registVenda(ActionEvent event){
		String cpfS, tituloS, codigoS, saleDay, saleMonth, saleYear, saleHour, saleMinute;

		cpfS = cpfCliente.getText();
		tituloS = tituloCd.getText();
		codigoS = codigoVenda.getText();
		saleDay = diaVenda.getText();
		saleMonth = mesVenda.getText();
		saleYear = anoVenda.getText();
		saleHour = horaVenda.getText();
		saleMinute = minVenda.getText();
		
		if(!cpfS.equals("") && !tituloS.equals("") && !codigoS.equals("") && !saleDay.equals("") &&
				!saleMonth.equals("") && !saleYear.equals("") && !saleHour.equals("") && !saleMinute.equals("")) {

			try{
				long cpf = Long.parseLong(cpfS);
				long codigo = Long.parseLong(codigoS);
				int day = Integer.parseInt(saleDay);
				int month = Integer.parseInt(saleMonth);
				int year = Integer.parseInt(saleYear);
				int hour = Integer.parseInt(saleHour);
				int minute = Integer.parseInt(saleMinute);
				
				LocalDate dataDaVenda = LocalDate.of(year, month, day);
				LocalTime horaDaVenda = LocalTime.of(hour, minute);
				
				Cliente cliente = Fachada.getInstance().buscarCliente(cpf);
				Cd cd = Fachada.getInstance().buscarCd(tituloS);
				
				if(cliente != null && cd != null){
				
					Venda venda = new Venda(cliente, cd, codigo, dataDaVenda, horaDaVenda);

					try{
						Stage stage = (Stage) botaoCadastrar.getScene().getWindow();
						Fachada.getInstance().registrarVenda(venda);
						Fachada.getInstance().salvarArquivoVendas();
						System.out.println("Registrado!");
						stage.close();

					}catch(ObjectExistException e){
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning");
						alert.setHeaderText("Erro ao registrar!");
						alert.setContentText("Venda já registrada!");
						alert.showAndWait();
					}
				}
			}catch(ObjectNotExistException e) {
				aviso.setText("Cliente ou CD Inexistente(s).");
				
			}
			catch(Exception e){
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
