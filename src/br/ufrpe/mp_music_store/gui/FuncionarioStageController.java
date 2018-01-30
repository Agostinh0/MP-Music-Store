package br.ufrpe.mp_music_store.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.negocio.Fachada;
import br.ufrpe.mp_music_store.negocio.beans.Funcionario;

public class FuncionarioStageController implements Initializable{
	
	@FXML 
	private TextField serachBar;
	
	@FXML
	private ListView<Funcionario> listaFunc;
	
	private ArrayList<Funcionario> lista = new ArrayList<>();
	private ObservableList<Funcionario> obsFuncList;
	private FilteredList<Funcionario> filterFuncList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lista = Fachada.getInstance().listarFuncionario();
		obsFuncList = FXCollections.observableArrayList(lista);
		filterFuncList = new FilteredList<>(obsFuncList, data -> true);
		listaFunc.setItems(filterFuncList);
		
	}
	
	public void openAddStage(ActionEvent action){
		try{
			Parent root = FXMLLoader.load(getClass().getResource("AdicionarFuncionario.fxml"));
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void openEditStage(ActionEvent event){
		//TODO code goes here
	}
}