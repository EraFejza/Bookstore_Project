package view;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.User;

public class HomeView{

	private User currentUser;

	public HomeView(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public static Scene showView(Stage st, User user) {
		StackPane p = new StackPane();
		Button b = new Button("Welcome to HomePage");

		p.getChildren().add(b);

		b.setOnAction(e -> {
			Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText("");
			a.setHeaderText("User info");
			a.showAndWait();
		});

		Scene sc = new Scene(p, 200, 200);
		return sc;
	}


}