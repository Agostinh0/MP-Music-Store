package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VendasStageController implements Initializable{

	@FXML
	private TextField searchBar;
	
	@FXML
	private ListView<Venda> salesList;
	
	private ArrayList<Venda> lista = new ArrayList<>();
	private ObservableList<Venda> obsSalesList;
	private FilteredList<Venda> filteredSalesList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lista = Fachada.getInstance().listarVendas();
		obsSalesList = FXCollections.observableArrayList(lista);
		filteredSalesList = new FilteredList<>(obsSalesList, data -> true);
		salesList.setItems(filteredSalesList);
	}
	
	public void openAddStage(ActionEvent event) {
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
}
