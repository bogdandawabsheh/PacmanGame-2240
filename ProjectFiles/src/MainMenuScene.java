import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import javax.swing.JFrame;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/**
 * Draws the main menu for the game
 */
public class MainMenuScene extends Application{
	private Stage oldStage;
	private Scene scene;
	private Button btnStart, btnExit, btnHighScores, btnBack;
	private Image backImage;
	private ListView<String> lstScores;
	private static JFrame frame;
	private static String playerName;
	private static Game game;
	/**
	 * Draws the GUI for the main menu and assigns all buttons
	 * @param primaryStage
	 * @throws Exception
	 */
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
				System.exit(0); //Closes form
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
	
	/**
	 * Starts the game in a new window. Asks user for name input
	 * @param currStage
	 * @param oldScene
	 */
	private void startGame(Stage currStage, Scene oldScene){

		TextInputDialog nameInput = new TextInputDialog();
		nameInput.setTitle("Name input");
		nameInput.setHeaderText("");
		nameInput.setGraphic(null);
		nameInput.setContentText("Please enter your name:");
		Optional<String> result = nameInput.showAndWait();
		
		if(result.isPresent())
			playerName= result.get();
		else
			playerName = "Unnamed";
		
		nameInput.setTitle("Name input");
		//frame.removeAll();
		frame = new JFrame();
		
		game = new Game();
		frame.setTitle(Game.TITLE);
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        System.exit(0);
		    }
		});
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		frame.toFront();
		Game.resetScore();
		
		game.start();
		
		
	}
	public static void disposeFrame(){
        Highscores.addScore(playerName, Game.returnScore());
        frame.removeAll();
        frame.setVisible(false);
        game.stop();
	}
	/**
	 * Changes the scene of the main menu to display the scores
	 * @param currStage
	 * @param oldScene
	 */
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
		Collections.sort(scores,Collections.reverseOrder());
		
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