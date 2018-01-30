package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.enumeracoes.TipoFuncionario;
import br.ufrpe.mp_music_store.exceptions.InvalidCpfException;
import br.ufrpe.mp_music_store.exceptions.ObjectExistException;
import br.ufrpe.mp_music_store.negocio.Fachada;
import br.ufrpe.mp_music_store.negocio.beans.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdicionarFuncionarioController implements Initializable{
	
	@FXML 
	private Button botaoCadastrar, backBttn;
	@FXML 
	private Label aviso, warnCpf;
	@FXML
	private TextField nomeFunc, enderecoFunc, cpfFunc, telefoneFunc, contratoFunc, salarioFunc;
	@FXML
	private RadioButton radBttn1;
	
	public void enviarFunc(ActionEvent event){
		String nomeF, endF, cpfF, telF, contratoF, salarioF;
		boolean status;
		
		nomeF = nomeFunc.getText();
		endF = enderecoFunc.getText();
		cpfF = cpfFunc.getText();
		telF = telefoneFunc.getText();
		contratoF = contratoFunc.getText();
		salarioF = salarioFunc.getText();
		status = radBttn1.isSelected();
		System.out.println(status);
		
		if(!nomeF.equals("") && !endF.equals("") && !cpfF.equals("") && !telF.equals("")
				&& !contratoF.equals("") && !salarioF.equals("")){
			
			try{
				long cpf = Long.parseLong(cpfF);
				long telefone = Long.parseLong(telF);
				int numContrato = Integer.parseInt(contratoF);
				float salario = Float.parseFloat(salarioF);
				
				if(status == true){
					Funcionario f = new Funcionario(nomeF, cpf, endF, telefone, salario, numContrato, TipoFuncionario.ADM);
					try{
						Stage stage = (Stage) botaoCadastrar.getScene().getWindow();
						Fachada.getInstance().adicionarFuncionario(f);
						Fachada.getInstance().salvarArquivoFunc();
						System.out.println("Cadastrado!");
						stage.close();
					}catch(ObjectExistException e){
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning");
						alert.setHeaderText("Erro ao cadastrar!");
						alert.setContentText("Funcionário já existe.");
						alert.showAndWait();
				
					}catch(InvalidCpfException e){
					warnCpf.setText(e.getMessage());
				}
			}
				if(status == false){
					Funcionario f = new Funcionario(nomeF, cpf, endF, telefone, salario, numContrato, TipoFuncionario.COMUM);
					try{
						Stage stage = (Stage) botaoCadastrar.getScene().getWindow();
						Fachada.getInstance().adicionarFuncionario(f);
						Fachada.getInstance().salvarArquivoFunc();
						System.out.println("Cadastrado!");
						stage.close();
					}catch(ObjectExistException e){
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning");
						alert.setHeaderText("Erro ao cadastrar!");
						alert.setContentText("Funcionário já existe.");
						alert.showAndWait();
				
					}catch(InvalidCpfException e){
					warnCpf.setText(e.getMessage());
					}
				}
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}else{
			aviso.setText("Preencha todos os campos!");
		}
	}
	
	public void voltarInicio(){
		Stage stage = (Stage) backBttn.getScene().getWindow();
		stage.close();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
