package br.ufrpe.mp_music_store.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdmMenu extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdmMenu.fxml"));
			Scene scene = new Scene(root);
			stage.setResizable(true);
			stage.setScene(scene);
			stage.setTitle("MP Music Store");
			stage.show();
		}
		catch(Exception e){
			System.out.println("Error!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
