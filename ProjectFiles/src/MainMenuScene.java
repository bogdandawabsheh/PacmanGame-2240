import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JFrame;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MainMenuScene extends Application{
	private Stage oldStage;
	private Scene scene;
	private Button btnStart, btnExit, btnHighScores, btnBack;
	private Image backImage;
	private ListView<String> lstScores;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Creates a new button - Start
		btnStart = new Button();
		btnStart.setText("Start Game");
		btnStart.setMaxWidth(125);
		btnStart.setBackground(null);
		btnStart.setTextFill(Color.YELLOW);
		
		//Creates a new button - Exit
		btnExit = new Button();
		btnExit.setText("Exit");
		btnExit.setMaxWidth(125);
		btnExit.setBackground(null);
		btnExit.setTextFill(Color.YELLOW);
		
		//Creates a new button - HighScores
		btnHighScores = new Button();
		btnHighScores.setText("High Scores");
		btnHighScores.setMaxWidth(125);
		btnHighScores.setBackground(null);
		btnHighScores.setTextFill(Color.YELLOW);
		
		//Sets action for the Start button
		btnStart.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				startGame(primaryStage,scene); //Starts game
			}
		});
		
		//Sets action for the Exit button
		btnExit.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				primaryStage.close(); //Closes form
			}
		});
		
		//Sets action for the High Score button
		btnHighScores.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				showScores(primaryStage,scene); //Shows scores
			}
		});
		
		//Virtual box layout
		VBox mainMenu = new VBox();
		
		//Views the image
		backImage = new Image(new FileInputStream("src\\images\\logo.png"));
		ImageView imageView = new ImageView(backImage);
		
		//Adding buttons and the image to the main menu and sets alignment
		mainMenu.setPadding(new Insets(0,0,0,0));
		mainMenu.getChildren().add(imageView);
		mainMenu.getChildren().add(btnStart);
		mainMenu.getChildren().add(btnHighScores);
		mainMenu.getChildren().add(btnExit);
		mainMenu.setBackground(null);
		mainMenu.setAlignment(Pos.CENTER);	
		
		//Assigns stage to a scene and displays it
		scene = new Scene(mainMenu, 600, 400);
		scene.setFill(Color.BLACK);
		primaryStage.setTitle("Pacman");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private void startGame(Stage currStage, Scene oldScene){
		currStage.hide();
		Game game = new Game();
		JFrame frame = new JFrame();
		frame.setTitle(Game.TITLE);
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		game.start();
		
		currStage.show();
		
	}
	private void showScores(Stage currStage,Scene oldScene){
		ArrayList<Highscores> scores = new ArrayList<Highscores>();
		lstScores = new ListView<String>();
		btnBack = new Button();
		
		//Creates a back button
		btnBack.setText("Back");
		btnBack.setMaxWidth(125);
		btnBack.setBackground(null);
		btnBack.setTextFill(Color.YELLOW);
		btnBack.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				currStage.setScene(oldScene);
			}
		});
		
		//Vertical box layout
		VBox scoreMenu = new VBox();
		
		//Retrieves scores from the database
		scores = Highscores.retrieveScores();
		//Sorts the scores
		Collections.sort(scores);
		
		//Place the items from the array into the ViewList
		for (Highscores score : scores){
			lstScores.getItems().add(score.toString());
		}
		
		//Adjusts the looks
		lstScores.setMaxSize(150,400);
		lstScores.setBackground(null);
		scoreMenu.getChildren().add(lstScores);
		scoreMenu.getChildren().add(btnBack);
		scoreMenu.setBackground(null);
		scoreMenu.setAlignment(Pos.CENTER);
	
		//Creates a new Scene with a black background and reassigns the scene
		scene = new Scene(scoreMenu, 600, 400);
		scene.setFill(Color.BLACK);
		currStage.setTitle("Pacman");
		currStage.setScene(scene);
		currStage.show();
	}
	

}

