package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CdStageController implements Initializable{

	@FXML
	private TextField searchBar;
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
	
	public void atualizar() {
		lista = Fachada.getInstance().listarCds();
		obsCdList = FXCollections.observableArrayList(lista); 
		filtdCdList = new FilteredList<>(obsCdList, data -> true);
		listCd.setItems(filtdCdList);

	}
	

	public void openAddStage(ActionEvent event) {
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

	public void openEditStage(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AtualizarCd.fxml"));
			Parent root = fxmlLoader.load();
			AtualizarCdController controller = fxmlLoader.<AtualizarCdController>getController();
			controller.cdAntigo = this.listCd.getSelectionModel().getSelectedItem().getTitulo();
			if(controller.cdAntigo != null){
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setResizable(false);
				stage.setScene(new Scene(root));
				stage.show();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//FAZER A REMOÇÃO SELECIONANDO PELA LISTA

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
