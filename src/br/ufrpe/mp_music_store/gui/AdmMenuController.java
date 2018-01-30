package br.ufrpe.mp_music_store.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;


public class AdmMenuController implements Initializable{

	@FXML
	private BorderPane mainScreen;

	@FXML
	public void admOptionsButtonAction(ActionEvent event) {
		if(((Button)event.getSource()).getId().equals("funcButton")) {
			loadFuncionarioStage();
		}
		else if(((Button)event.getSource()).getId().equals("clientButton")) {
			loadClienteStage();
		}
		else if(((Button)event.getSource()).getId().equals("cdButton")) {
			loadCdStage();
		}
		else if(((Button)event.getSource()).getId().equals("salesButton")) {
			loadVendasStage();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO 

	}

	private void loadFuncionarioStage() {
		try {
			ScrollPane root = FXMLLoader.load(getClass().getResource("FuncionarioStage.fxml"));
			mainScreen.setCenter(root);

		}catch(IOException e) {
			Logger.getLogger(AdmMenuController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private void loadCdStage() {
		try {
			ScrollPane root = FXMLLoader.load(getClass().getResource("CdStage.fxml"));
			mainScreen.setCenter(root);

		}catch(IOException e) {
			Logger.getLogger(AdmMenuController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private void loadClienteStage() {
		try {
			ScrollPane root = FXMLLoader.load(getClass().getResource("ClienteStage.fxml"));
			mainScreen.setCenter(root);

		}catch(IOException e) {
			Logger.getLogger(AdmMenuController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private void loadVendasStage() {
		try {
			ScrollPane root = FXMLLoader.load(getClass().getResource("VendasStage.fxml"));
			mainScreen.setCenter(root);

		}catch(IOException e) {
			Logger.getLogger(AdmMenuController.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
