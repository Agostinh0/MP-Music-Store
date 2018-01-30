package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;
import br.ufrpe.mp_music_store.negocio.Fachada;
import br.ufrpe.mp_music_store.negocio.beans.Cd;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CdStageController implements Initializable{

	@FXML
	private TextField searchBar;
	@FXML
	private Label aviso;
	@FXML
	private ListView<Cd> listCd;

	private ArrayList<Cd> lista = new ArrayList<>();
	private ObservableList<Cd> obsCdList;
	private FilteredList<Cd> filtdCdList;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lista = Fachada.getInstance().listarCds();
		obsCdList = FXCollections.observableArrayList(lista); 
		filtdCdList = new FilteredList<>(obsCdList, data -> true);
		listCd.setItems(filtdCdList);

	}

	public void openCdAddStage(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdicionarCd.fxml"));
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.show();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void openCdEditStage(ActionEvent event) {
		Cd item = listCd.getSelectionModel().getSelectedItem();
		if(item != null) {
			try {				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AtualizarCd.fxml"));
				Parent root = (Parent) loader.load();
				AtualizarCdController attCd = loader.getController();
				attCd.setCdEdit(item);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setResizable(false);
				stage.setScene(new Scene(root));
				stage.show();

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			aviso.setText("Selecione um elemento da lista.");
		}

	}

	public void removeCd(ActionEvent event) {
		Cd item = null;
		item = listCd.getSelectionModel().getSelectedItem();
		if(item != null) {
			//abrir tela de confirmação.
			try {
				Fachada.getInstance().removerCd(item.getTitulo());

			}catch(ErroRemoverException e) {
				e.printStackTrace();

			} catch (ObjectNotExistException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("Erro ao remover!");
				alert.setContentText("CD não existe!");
				alert.showAndWait();
			}
		}
		else {
			aviso.setText("Selecione um elemento da lista.");
		}
	}

	public void searchCd(KeyEvent event) {

		try{

			searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
				filtdCdList.setPredicate(cd -> {
					if (newValue == null || newValue.isEmpty()){
						return true;
					}
					String lowerCaseSearch = newValue.toLowerCase();
					if(cd.getTitulo().toLowerCase().contains(lowerCaseSearch)) {
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
