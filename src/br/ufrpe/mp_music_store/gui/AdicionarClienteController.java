package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.enumeracoes.TipoCliente;
import br.ufrpe.mp_music_store.exceptions.InvalidCpfException;
import br.ufrpe.mp_music_store.exceptions.InvalidTeleException;
import br.ufrpe.mp_music_store.exceptions.ObjectExistException;
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

public class AdicionarClienteController implements Initializable{

	@FXML 
	private Button botaoCadastrar, backBttn;
	@FXML
	private Label aviso, warnCpf, warnTele;
	@FXML 
	private TextField nomeCliente, enderecoCliente, cpfCliente, telefoneCliente, cadastroCliente;
	@FXML
	private RadioButton radBttn;

	@FXML
	public void enviarCliente(ActionEvent event){
		String nome, endereco, cpfS, telefoneS, n_cadastroS;
		boolean status;

		nome = nomeCliente.getText();
		endereco = enderecoCliente.getText();
		cpfS = cpfCliente.getText();
		telefoneS = telefoneCliente.getText();
		n_cadastroS = cadastroCliente.getText();
		status = radBttn.isSelected();

		if(!nome.equals("") && !endereco.equals("") && !cpfS.equals("")
				&& !telefoneS.equals("") && !n_cadastroS.equals("")){

			aviso.setText("");
			
			try{
				long cpf = Long.parseLong(cpfS);
				long telefone = Long.parseLong(telefoneS);
				int n_cadastro = Integer.parseInt(n_cadastroS);

				if(status == true){
					Cliente cliente = new Cliente(nome, cpf, endereco, telefone, n_cadastro, TipoCliente.PREMIUM);
					try{
						Stage stage = (Stage) botaoCadastrar.getScene().getWindow();
						Fachada.getInstance().adicionarCliente(cliente);
						Fachada.getInstance().salvarArquivoClientes();
						System.out.println("Cadastrado!");
						stage.close();
					}catch(ObjectExistException e){
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning");
						alert.setHeaderText("Erro ao cadastrar!");
						alert.setContentText("Cliente j� existe!");
						alert.showAndWait();

					}catch(InvalidCpfException e) {
						warnCpf.setText(e.getMessage());

					}catch(InvalidTeleException e) {
						warnCpf.setText("");
						warnTele.setText(e.getMessage());
					}

				}
				else if(status == false){
					Cliente cliente = new Cliente(nome, cpf, endereco, telefone, n_cadastro, TipoCliente.NORMAL);
					try{
						Stage stage = (Stage) botaoCadastrar.getScene().getWindow();
						Fachada.getInstance().adicionarCliente(cliente);
						Fachada.getInstance().salvarArquivoClientes();
						System.out.println("Cadastrado!");
						stage.close();
					}catch(ObjectExistException e){
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning");
						alert.setHeaderText("Erro ao cadastrar!");
						alert.setContentText("Cliente j� existe!");
						alert.showAndWait();

					}catch(InvalidCpfException e) {
						warnCpf.setText(e.getMessage());

					}catch(InvalidTeleException e) {
						warnCpf.setText("");
						warnTele.setText(e.getMessage());
					}
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
