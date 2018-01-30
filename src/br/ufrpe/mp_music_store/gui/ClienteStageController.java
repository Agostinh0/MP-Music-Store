package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.negocio.Fachada;
import br.ufrpe.mp_music_store.negocio.beans.Cliente;
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

public class ClienteStageController implements Initializable{
	
	@FXML
	private TextField searchBar;
	@FXML
	private ListView<Cliente> listCliente;
	
	private ArrayList<Cliente> lista = new ArrayList<>();
	private ObservableList<Cliente> obsClientList;
	private FilteredList<Cliente> filtdClientList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lista = Fachada.getInstance().listarClientes();
		obsClientList = FXCollections.observableArrayList(lista); 
		filtdClientList = new FilteredList<>(obsClientList, data -> true);
		listCliente.setItems(filtdClientList);
		
	}
	
	public void atualizar(){
		lista = Fachada.getInstance().listarClientes();
		obsClientList = FXCollections.observableArrayList(lista);
		filtdClientList = new FilteredList<>(obsClientList, data -> true);
		listCliente.setItems(filtdClientList);
	}
	
	public void openAddStage(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdicionarCliente.fxml"));
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
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AtualizarCliente.fxml"));
			Parent root = fxmlLoader.load();
			AtualizarClienteController controller = fxmlLoader.<AtualizarClienteController>getController();
			if(this.listCliente.getSelectionModel().getSelectedItem() != null){
				controller.clienteAntigo = this.listCliente.getSelectionModel().getSelectedItem().getCpf();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setResizable(false);
				stage.setScene(new Scene(root));
				stage.show();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//FAZER A REMOÇÃO SELECIONANDO PELA LISTA
	
	public void searchCd(KeyEvent event) {

		try{

			searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
				filtdClientList.setPredicate(cliente -> {
					if (newValue == null || newValue.isEmpty()){
						return true;
					}
					String lowerCaseSearch = newValue.toLowerCase();
					String strLong = Long.toString(cliente.getCpf());
					
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
