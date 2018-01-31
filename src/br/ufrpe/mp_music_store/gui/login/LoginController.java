package br.ufrpe.mp_music_store.gui.login;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.mp_music_store.negocio.Fachada;
import br.ufrpe.mp_music_store.negocio.beans.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	@FXML
	private TextField loginF;
	@FXML
	private PasswordField senhaF;
	@FXML
	private Button entrarAdm;
	@FXML
	private Label error;

	@FXML
	public void logar(ActionEvent event) {
		String login, senha;

		login = loginF.getText();
		senha = senhaF.getText();
		Usuario u = new Usuario(login, senha);

		if(Fachada.getInstance().verificaLoginAdm(u) || (login.equals("admin") && senha.equals("admin"))) {

			((Node) (event.getSource())).getScene().getWindow().hide();

			try {
				Parent root = FXMLLoader.load(getClass().getResource("AdmMenu.fxml"));
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setResizable(false);
				stage.setScene(scene);
				stage.setTitle("MP Music Store");
				stage.show();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else if(Fachada.getInstance().verificaLogin(u)) {
			((Node) (event.getSource())).getScene().getWindow().hide();

			try {
				Parent root = FXMLLoader.load(getClass().getResource("FuncMenu.fxml"));
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setResizable(false);
				stage.setScene(scene);
				stage.setTitle("MP Music Store");
				stage.show();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(login.equals("") || senha.equals("")){
			error.setText("Preencha todos os campos.");
		}
		else {
			error.setText("Login ou Senha Inválidos.");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
