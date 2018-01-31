package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;
import br.ufrpe.mp_music_store.negocio.Fachada;
import br.ufrpe.mp_music_store.negocio.beans.Venda;
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

public class VendasStageController implements Initializable{
	@FXML
	private TextField searchBar;
	@FXML
	private Label aviso;
	@FXML
	private ListView<Venda> listVenda;

	private ArrayList<Venda> lista = new ArrayList<>();
	private ObservableList<Venda> obsVendaList;
	private FilteredList<Venda> filtdVendaList;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lista = Fachada.getInstance().listarVendas();
		obsVendaList = FXCollections.observableArrayList(lista); 
		filtdVendaList = new FilteredList<>(obsVendaList, data -> true);
		listVenda.setItems(filtdVendaList);

	}

	public void openVendaAddStage(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdicionarVenda.fxml"));
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setScene(new Scene(root));
			stage.show();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void removeVenda(ActionEvent event) {
		Venda item = null;
		item = listVenda.getSelectionModel().getSelectedItem();
		if(item != null) {
			//abrir tela de confirmação.
			try {
				Fachada.getInstance().removerVenda(item.getCodigoVenda());
				Fachada.getInstance().salvarArquivoVendas();

			}catch(ErroRemoverException e) {
				e.printStackTrace();

			} catch (ObjectNotExistException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("Erro ao remover!");
				alert.setContentText("Venda não registrada.");
				alert.showAndWait();
			}
		}
		else {
			aviso.setText("Selecione um elemento da lista.");
		}
	}

	public void searchVenda(KeyEvent event) {

		try{

			searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
				filtdVendaList.setPredicate(venda -> {
					if (newValue == null || newValue.isEmpty()){
						return true;
					}
					String lowerCaseSearch = newValue.toLowerCase();
					String strLong = Long.toString(venda.getCodigoVenda());

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
