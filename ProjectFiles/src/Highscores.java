import java.io.File;
import java.util.Scanner;
import javafx.util.Pair;


public class Highscores implements Comparable<Highscores>{
	private Integer score = 0;	//Variable declaration
	private String name;
	
	public Highscores(String name, Integer score){	
		this.score = score;
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	public Integer getScore(){
		return score;
	}
	
	private static Highscores[] retrieveScores(){
		int highScoreCount = retrieveHighScoreCount();
		Highscores[] tempArray = new Highscores[highScoreCount];
	    
		try{
	    	//Creates input Scanner with input being the database
	    	Scanner inputScanner = new Scanner(new File("data/highscores.txt"));
	    	if(highScoreCount > 0){	//Takes the first line ( will be numeric and be the high score count
	    		inputScanner.nextLine(); //Eats first line	
	    			for(int i = 0; i<highScoreCount; i++){
	    				tempArray[i] = new Highscores(inputScanner.next(),Integer.parseInt(inputScanner.next())); //Places into the second column
	    				inputScanner.nextLine(); //Eats new line
	    			}
	    	}
	    }catch (Exception e){
	    	System.out.println("ERROR in retrieving High scores");
	    }
		return tempArray;
	}
	
	private static int retrieveHighScoreCount(){
		int highScoreNum = 0;
		try{
	    	//Creates input Scanner with input being the database
	    	Scanner inputScanner = new Scanner(new File("data/highscores.txt"));
	    	if(inputScanner.hasNextLine()){	//Takes the first line ( will be numeric and be the high score count
	    		highScoreNum = Integer.parseInt(inputScanner.nextLine().trim()); //
	    		inputScanner.close();
	    	}
		} catch (Exception e){
			System.out.println("Error in Retrieving High Score Number");
		}
		return highScoreNum;
	}
	
	public static void addScore(String name, int score){
		//Retrieves all stuff from the file
		//Retrieves number of scores from the file
		//adds in the score
		//Sorts
		//adds +1 to the number
		//Places everything back into the file separated by a space
	}

	//Compares two scores
	@Override
	public int compareTo(Highscores o) {
		return this.getScore() - o.getScore();
	}
}
