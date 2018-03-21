import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;


public class Highscores implements Comparable<Highscores>{
	private Integer score = 0;	//Variable declaration
	private String name;
	
	//Constructor for the Highscores class.
	public Highscores(String name, Integer score){	
		this.score = score;	
		this.name = name;
	}
	
	//Returns name
	public String getName(){
		return name;
	}
	
	//Returns score
	public Integer getScore(){
		return score;
	}
	
	//Retrieves scores from the file
	private static Highscores[] retrieveScores() throws FileNotFoundException, UnsupportedEncodingException{
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
	    	inputScanner.close();
	    }catch (Exception e){
			//Creates a blank file
	    	PrintWriter writer = new PrintWriter("data/highscores.txt", "UTF-8");
			writer.println("0");
			writer.close();
			//Calls the method again
			retrieveScores();
	    }
		return tempArray;
	}
	
	private static int retrieveHighScoreCount() throws FileNotFoundException, UnsupportedEncodingException {
		int highScoreNum = 0;
		try{
	    	//Creates input Scanner with input being the database
	    	Scanner inputScanner = new Scanner(new File("data/highscores.txt"));
	    	if(inputScanner.hasNextLine()){	//Takes the first line ( will be numeric and be the high score count
	    		highScoreNum = Integer.parseInt(inputScanner.nextLine().trim()); //
	    		inputScanner.close();
	    	}
		} catch (Exception e){
			//Creates a blank file
			PrintWriter writer = new PrintWriter("data/highscores.txt", "UTF-8");
			writer.println("0");
			writer.close();
			//Calls the method again;
			retrieveHighScoreCount();
		}
		return highScoreNum;
	}
	
	public static void addScore(String name, int score) throws FileNotFoundException, UnsupportedEncodingException{
		Integer highscoreCount = retrieveHighScoreCount();	//Retrieves the old highscore count
		Highscores[] oldScores = retrieveScores();	//Retrieves the old scores form the file
		Highscores[] newScores = new Highscores[oldScores.length + 1];	//Making a new blank array with the length +1
		
		for(int i = 0; i<oldScores.length; i++){	//Inputting all values from the old array into the new one
			newScores[i] = oldScores[i];
		}
		newScores[oldScores.length + 1] = new Highscores(name, score);	//Adding the new score
		
		Arrays.sort(newScores);	//Sorting the array based on score
		highscoreCount++;		//Adding +1 to the scorecount
		
		PrintWriter writer = new PrintWriter("data/highscores.txt", "UTF-8");	//Opening the file
		writer.println(highscoreCount);		//inputing the highscore count
		for(Highscores tempScore : newScores){	//For each value in the newScore array, print it into the file
			writer.println(tempScore.toString());
		}
		writer.close();		//close the writer
	}
	

	//Compares two scores
	@Override
	public int compareTo(Highscores o) {
		return this.getScore() - o.getScore();
	}
	
	@Override
	public String toString(){
		return name + " " + score;
	}
}