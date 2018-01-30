package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.exceptions.InvalidCpfException;
import br.ufrpe.mp_music_store.exceptions.ObjectExistException;
import br.ufrpe.mp_music_store.negocio.Fachada;
import br.ufrpe.mp_music_store.negocio.beans.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AdicionarFuncionarioController implements Initializable {
	@FXML 
	private Button botaoCadastrar, backBttn;
	@FXML
	private Label aviso, warnCpf;
	@FXML 
	private TextField nomeFunc, enderecoFunc, cpfFunc, telefoneFunc, salarioFunc, contratoFunc;

	@FXML
	public void enviarFuncionario(ActionEvent event){
		String nome, endereco, cpfS, telefoneS, salarioS, n_contratoS;

		nome = nomeFunc.getText();
		endereco = enderecoFunc.getText();
		cpfS = cpfFunc.getText();
		telefoneS = telefoneFunc.getText();
		salarioS = salarioFunc.getText();
		n_contratoS = contratoFunc.getText();

		if(!nome.equals("") && !endereco.equals("") && !cpfS.equals("")
				&& !telefoneS.equals("") && !salarioS.equals("") && !n_contratoS.equals("")){

			try{
				long cpf = Long.parseLong(cpfS);
				long telefone = Long.parseLong(telefoneS);
				float salario = Float.parseFloat(salarioS);
				int n_contrato = Integer.parseInt(n_contratoS);

				Funcionario f = new Funcionario(nome, cpf, endereco, telefone, salario, n_contrato);

				try{
					Stage stage = (Stage) botaoCadastrar.getScene().getWindow();
					Fachada.getInstance().adicionarFuncionario(f);
					System.out.println("Cadastrado!");
					stage.close();

				}catch(ObjectExistException e){
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning");
					alert.setHeaderText("Erro ao cadastrar!");
					alert.setContentText("Funcion�rio j� existe!");
					alert.showAndWait();

				}catch(InvalidCpfException e) {
					warnCpf.setText(e.getMessage());
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
