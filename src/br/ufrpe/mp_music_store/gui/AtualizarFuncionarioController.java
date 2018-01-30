package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.exceptions.ErroAtualizarException;
import br.ufrpe.mp_music_store.exceptions.InvalidCpfException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;
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

public class AtualizarFuncionarioController implements Initializable{
	@FXML 
	private Button botaoAtualizar, backBttn;
	@FXML
	private Label aviso, warnCpf;
	@FXML 
	private TextField newNomeFunc, newEnderecoFunc, newCpfFunc, newTelefoneFunc, newSalarioFunc, newContratoFunc;

	private Funcionario funcEdit;

	@FXML
	public void enviarFuncionario(ActionEvent event){
		String nome, endereco, cpfS, telefoneS, salarioS, n_contratoS;

		nome = newNomeFunc.getText();
		endereco = newEnderecoFunc.getText();
		cpfS = newCpfFunc.getText();
		telefoneS = newTelefoneFunc.getText();
		salarioS = newSalarioFunc.getText();
		n_contratoS = newContratoFunc.getText();

		if(!nome.equals("") && !endereco.equals("") && !cpfS.equals("")
				&& !telefoneS.equals("") && !salarioS.equals("") && !n_contratoS.equals("")){

			try {
				long cpf = Long.parseLong(cpfS);
				long telefone = Long.parseLong(telefoneS);
				float salario = Float.parseFloat(salarioS);
				int n_contrato = Integer.parseInt(n_contratoS);

				Funcionario f = new Funcionario(nome, cpf, endereco, telefone, salario, n_contrato);

				try {
					Stage stage = (Stage) botaoAtualizar.getScene().getWindow();
					Fachada.getInstance().atualizarFuncionario(this.funcEdit.getCpf(), f);
					stage.close();

				}catch (ErroAtualizarException e) {
					e.printStackTrace();

				} catch (ObjectNotExistException e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning");
					alert.setHeaderText("Erro ao atualizar!");
					alert.setContentText("Funcionário não existe!");
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

	public void setFuncEdit(Funcionario f) {
		this.funcEdit = f;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
}
