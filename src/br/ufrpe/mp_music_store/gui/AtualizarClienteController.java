package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.dados.RepositorioClientes;
import br.ufrpe.mp_music_store.enumeracoes.TipoCliente;
import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.negocio.Fachada;
import br.ufrpe.mp_music_store.negocio.beans.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AtualizarClienteController implements Initializable{

	@FXML private Button botaoAlterar, backBttn;
	@FXML private Label aviso, warnCpf;
	@FXML private TextField novoNomeCliente, novoEnderecoCliente, novoCpfCliente, novoTelefoneCliente, novoNumCadastroCliente;
	@FXML private RadioButton radBttn1;
	long clienteAntigo;
	
	public void atualizarCliente(ActionEvent action){
		String novoNome, novoEndereco, novoCpf, novoTelefone, novoNumCadastro;
		boolean status;
		
		novoNome = novoNomeCliente.getText();
		novoEndereco = novoEnderecoCliente.getText();
		novoCpf = novoCpfCliente.getText();
		novoTelefone = novoTelefoneCliente.getText();
		novoNumCadastro = novoNumCadastroCliente.getText();
		status = radBttn1.isSelected();
		System.out.println(status);
		
		if(!novoNome.equals("") && !novoEndereco.equals("") && !novoCpf.equals("") && !novoTelefone.equals("")
				&& !novoNumCadastro.equals("")){
			
			try{
				long newCpf = Long.parseLong(novoCpf);
				long newPhone = Long.parseLong(novoTelefone);
				int newRegisterNumber = Integer.parseInt(novoNumCadastro);
				
				if(status == true){
					Cliente edita = new Cliente(novoNome, newCpf, novoEndereco, newPhone, newRegisterNumber, TipoCliente.PREMIUM);
					try{
						Stage stage = (Stage) botaoAlterar.getScene().getWindow();
						RepositorioClientes.getInstance().atualizar(clienteAntigo, edita);
						Fachada.getInstance().salvarArquivoClientes();
						System.out.println("Cliente atualizado.");
						stage.close();
					}catch(ErroAtualizarException e){
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning");
						alert.setContentText("Erro ao atualizar!");
						alert.showAndWait();
					}
				}
				
				if(status == false){
					Cliente edita = new Cliente(novoNome, newCpf, novoEndereco, newPhone, newRegisterNumber, TipoCliente.NORMAL);
					try{
						Stage stage = (Stage) botaoAlterar.getScene().getWindow();
						RepositorioClientes.getInstance().atualizar(clienteAntigo, edita);
						Fachada.getInstance().salvarArquivoClientes();
						System.out.println("Cliente atualizado.");
						stage.close();
					}catch(ErroAtualizarException e){
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning");
						alert.setContentText("Erro ao atualizar!");
						alert.showAndWait();
					}
				}
			
			}catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}else{
			aviso.setText("Preencha todos os campos!");
		}
	}
	
	public void voltarInicio(ActionEvent action){
		Stage stage = (Stage) backBttn.getScene().getWindow();
		stage.close();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
