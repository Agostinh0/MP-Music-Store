package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;
import br.ufrpe.mp_music_store.negocio.Fachada;
import br.ufrpe.mp_music_store.negocio.beans.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FuncionarioStageController implements Initializable{
	@FXML
	private TextField searchBar;
	@FXML
	private Label aviso;
	@FXML
	private ListView<Funcionario> listFunc;

	private ArrayList<Funcionario> lista = new ArrayList<>();
	private ObservableList<Funcionario> obsFuncList;
	private FilteredList<Funcionario> filtdFuncList;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lista = Fachada.getInstance().listarFuncionario();
		obsFuncList = FXCollections.observableArrayList(lista); 
		filtdFuncList = new FilteredList<>(obsFuncList, data -> true);
		listFunc.setItems(filtdFuncList);

	}

	public void openFuncAddStage(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdicionarFuncionario.fxml"));
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.show();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void openFuncEditStage(ActionEvent event) {
		Funcionario item = listFunc.getSelectionModel().getSelectedItem();
		if(item != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AtualizarFuncionario.fxml"));
				Parent root = (Parent) loader.load();
				AtualizarFuncionarioController attFunc = loader.getController();
				attFunc.setFuncEdit(item);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setResizable(false);
				stage.setScene(new Scene(root));
				stage.show();

			}catch(Exception e) {
				e.printStackTrace();
			}

		}else {
			aviso.setText("Selecione um elemento da lista.");
		}

	}

	public void removeFuncionario(ActionEvent event) {
		Funcionario item = null;
		item = listFunc.getSelectionModel().getSelectedItem();
		if(item != null) {
			//abrir tela de confirmação.
			try {
				Fachada.getInstance().removerFuncionario(item.getCpf());

			}catch(ErroRemoverException e) {
				e.printStackTrace();

			} catch (ObjectNotExistException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("Erro ao remover!");
				alert.setContentText("Funcionário não existe!");
				alert.showAndWait();
			}
		}
		else {
			aviso.setText("Selecione um elemento da lista.");
		}
	}

	public void searchFuncionario(KeyEvent event) {

		try{

			searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
				filtdFuncList.setPredicate(funcionario -> {
					if (newValue == null || newValue.isEmpty()){
						return true;
					}
					String lowerCaseSearch = newValue.toLowerCase();
					String strLong = Long.toString(funcionario.getCpf());

					if(strLong.contains(lowerCaseSearch)) {
						return true;
					}

					return false;
				});
			});

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
