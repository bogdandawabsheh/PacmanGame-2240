import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MainMenuScene extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Button btnStart = new Button();
		btnStart.setText("Start Game");
		btnStart.setMaxWidth(125);
		btnStart.setBackground(null);
		btnStart.setTextFill(Color.YELLOW);
		btnStart.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				startGame();
			}
		});
		
		
		Button btnExit = new Button();
		btnExit.setText("Exit");
		btnExit.setMaxWidth(125);
		btnExit.setBackground(null);
		btnExit.setTextFill(Color.YELLOW);
		btnExit.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				primaryStage.close();
			}
		});
		
		Button btnHighScores = new Button();
		btnHighScores.setText("High Scores");
		btnHighScores.setMaxWidth(125);
		btnHighScores.setBackground(null);
		btnHighScores.setTextFill(Color.YELLOW);
		btnHighScores.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				showScores();
			}
		});
		
		VBox mainMenu = new VBox();
		
		
		Image backImage = new Image(new FileInputStream("src\\images\\logo.png"));
		ImageView imageView = new ImageView(backImage);
		
		mainMenu.setPadding(new Insets(0,0,0,0));
		mainMenu.getChildren().add(imageView);
		mainMenu.getChildren().add(btnStart);
		mainMenu.getChildren().add(btnHighScores);
		mainMenu.getChildren().add(btnExit);
		mainMenu.setBackground(null);
		mainMenu.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(mainMenu, 600, 400);
		scene.setFill(Color.BLACK);
		primaryStage.setTitle("Pacman");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void startGame(){
		//
	}
	public static void showScores(){
		//New scene with a ListView that retrieves stuff from the database
	}
	

}

