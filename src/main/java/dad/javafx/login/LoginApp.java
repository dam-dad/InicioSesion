package dad.javafx.login;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginApp extends Application {
	
	private Label userLabel, passLabel;
	private TextField userText;
	private PasswordField passText;
	private ComboBox<String> authModesCombo;
	private Button loginButton;
	
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		System.out.println(Thread.currentThread());
		
		this.primaryStage = primaryStage;

		userLabel = new Label("Usuario:");
		userLabel.setMinWidth(80);
		
		passLabel = new Label("Contraseña:");
		passLabel.setMinWidth(80);
		
		userText = new TextField();
		userText.setPromptText("Nombre de usuario");
		userText.setMaxWidth(80);
		
		passText = new PasswordField();
		passText.setPromptText("Contraseña");
		passText.setMaxWidth(80);
		
		authModesCombo = new ComboBox<String>();
		authModesCombo.getItems().addAll("Cuenta local", "LDAP", "Base de datos");
		authModesCombo.setPromptText("Autenticación");
		
		loginButton = new Button("Acceder");
		loginButton.setDefaultButton(true);
		loginButton.setOnAction(e -> onLoginButtonAction(e));
		
		HBox userBox = new HBox(5, userLabel, userText);
		userBox.setAlignment(Pos.CENTER);

		HBox passBox = new HBox(5, passLabel, passText);
		passBox.setAlignment(Pos.CENTER);
		
		HBox authBox = new HBox(5, authModesCombo, loginButton);
		authBox.setAlignment(Pos.CENTER);
		
		VBox root = new VBox(5, userBox, passBox, authBox);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("Iniciar sesión");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	@Override
	public void stop() throws Exception {
		super.stop();
		System.out.println("Parando JavaFX");
	}

	private void onLoginButtonAction(ActionEvent e) {
		String username = userText.getText();
		String password = passText.getText();
		String auth = authModesCombo.getSelectionModel().getSelectedItem();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(primaryStage);
		alert.setHeaderText("Intento de inicio de sesión");
		alert.setContentText(
				"Usuario: " + username + "\n" +
				"Contraseña: " + password + "\n" +
				"Modo de autenticación: " + auth + "\n" 
			);
		alert.showAndWait();
		
		Platform.exit();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
