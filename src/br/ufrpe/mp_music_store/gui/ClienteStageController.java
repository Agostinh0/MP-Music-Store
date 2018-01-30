package br.ufrpe.mp_music_store.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.exceptions.ErroRemoverException;
import br.ufrpe.mp_music_store.exceptions.ObjectNotExistException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClienteStageController implements Initializable{

	@FXML
	private TextField searchBar;
	@FXML
	private Label aviso;
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

	public void openClientAddStage(ActionEvent event) {
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

	public void openClientEditStage(ActionEvent event) {
		Cliente item = listCliente.getSelectionModel().getSelectedItem();
		if(item != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("AtualizarCliente.fxml"));
				Parent root = (Parent) loader.load();
				AtualizarClienteController attClient = loader.getController();
				attClient.setClientEdit(item);
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

	public void removeCliente(ActionEvent event) {
		Cliente item = null;
		item = listCliente.getSelectionModel().getSelectedItem();
		if(item != null) {
			//abrir tela de confirmação.
			try {
				Fachada.getInstance().removerCliente(item.getCpf());

			}catch(ErroRemoverException e) {
				e.printStackTrace();

			} catch (ObjectNotExistException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("Erro ao remover!");
				alert.setContentText("Cliente não existe!");
				alert.showAndWait();
			}
		}
		else {
			aviso.setText("Selecione um elemento da lista.");
		}
	}

	public void searchCliente(KeyEvent event) {

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
