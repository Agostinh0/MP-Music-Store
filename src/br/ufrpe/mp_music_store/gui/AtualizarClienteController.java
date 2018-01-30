package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.InvalidCpfException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;
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
	@FXML 
	private Button botaoAtualizar, backBttn;
	@FXML
	private Label aviso, warnCpf;
	@FXML 
	private TextField newNomeCliente, newEnderecoCliente, newCpfCliente, newTelefoneCliente, newCadastroCliente;
	@FXML
	private RadioButton newRadBttn;

	private Cliente clientEdit;

	@FXML
	public void enviarCliente(ActionEvent event){
		String nome, endereco, cpfS, telefoneS, n_cadastroS;
		boolean status;

		nome = newNomeCliente.getText();
		endereco = newEnderecoCliente.getText();
		cpfS = newCpfCliente.getText();
		telefoneS = newTelefoneCliente.getText();
		n_cadastroS = newCadastroCliente.getText();
		status = newRadBttn.isSelected();

		if(!nome.equals("") && !endereco.equals("") && !cpfS.equals("")
				&& !telefoneS.equals("") && !n_cadastroS.equals("")){

			try {
				long cpf = Long.parseLong(cpfS);
				long telefone = Long.parseLong(telefoneS);
				int n_cadastro = Integer.parseInt(n_cadastroS);

				Cliente cliente = new Cliente(nome, cpf, endereco, telefone, n_cadastro, status);

				try {
					Stage stage = (Stage) botaoAtualizar.getScene().getWindow();
					Fachada.getInstance().atualizarCliente(clientEdit.getCpf(), cliente);
					stage.close();

				}catch (ErroAtualizarException e) {
					e.printStackTrace();

				} catch (ObjectNotExistException e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning");
					alert.setHeaderText("Erro ao atualizar!");
					alert.setContentText("Cliente não existe!");
					alert.showAndWait();
					
				} catch(InvalidCpfException e) {
					warnCpf.setText("CPF Inválido!");
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

	public void setClientEdit(Cliente c) {
		this.clientEdit = c;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
}
