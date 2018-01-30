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
	private Button adicionarVenda, backBttn;
	
	@FXML
	private Label aviso;
	
	@FXML
	private TextField cpfCliente, tituloCd, dataVenda, mesVenda, 
			anoVenda, horaVenda, minutoVenda, codigoVenda;
	
	@FXML
	public void enviarVenda(ActionEvent event){
		String cpf, titulo, hora, min, codigo, data, mes, ano;
		
		cpf = cpfCliente.getText();
		titulo = tituloCd.getText();
		hora = horaVenda.getText();
		min = minutoVenda.getText();
		codigo = codigoVenda.getText();
		data = dataVenda.getText();
		mes = mesVenda.getText();
		ano = anoVenda.getText();
		
		if(!cpf.equals("") && !titulo.equals("") && !data.equals("")
				&& !mes.equals("") && !ano.equals("") && !hora.equals("") && !min.equals("") && !codigo.equals("")){
			
			try{
				long cpfClient = Long.parseLong(cpf);
				int hour = Integer.parseInt(hora);
				int minute = Integer.parseInt(min);
				int day = Integer.parseInt(data);
				int month = Integer.parseInt(mes);
				int year = Integer.parseInt(ano);
				long saleCode = Long.parseLong(codigo);
				
				LocalTime saleHour = LocalTime.of(hour, minute);
				LocalDate saleDate = LocalDate.of(year, month, day);
				
				
				Cliente buscaCliente = null;
				
				try{
					buscaCliente = Fachada.getInstance().buscarCliente(cpfClient);
					
				}catch(ObjectNotExistException e){
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
				Cd buscaCd = null;
				
				try{
					buscaCd = Fachada.getInstance().buscarCd(titulo);
				}catch(ObjectNotExistException e){
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
				if(buscaCliente != null && buscaCd != null){ 
				
				Venda venda = new Venda(buscaCliente, buscaCd ,saleCode, saleDate, saleHour);
				
					try{
						Stage stage = (Stage) adicionarVenda.getScene().getWindow();
						Fachada.getInstance().registrarVenda(venda);
						Fachada.getInstance().salvarArquivoVendas();
						System.out.println("Registrado!");
						stage.close();
					}catch(ObjectExistException e){
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning");
						alert.setHeaderText("Erro ao cadastrar!");
						alert.setContentText("CD já existe!");
						alert.showAndWait();
					}
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}else{
			aviso.setText("Preencha todos os campos!");
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
