import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The class is responsible for database access and highscore storage
 */
public class Highscores implements Comparable<Highscores>{
	private Integer score = 0;	//Variable declaration
	private String name;
	private static String url = "jdbc:sqlite:src/data/highscores.db";
	
	/**
	 * Constructor for the Highscores class.
	 * @param Player name
	 * @param Player score
	 */
	public Highscores(String name, Integer score){	
		this.score = score;	
		this.name = name;
	}
	
	/**
	 * Returns name
	 * @return Player name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns score
	 * @return Player score
	 */
	public Integer getScore(){
		return score;
	}
	
	/**
	 * Returns URL for database location
	 * @return url for database location
	 */
	public static String getURL(){
		return url;
	}
	
	/**
	 * Retrieves scores from the file in an array
	 * @return array containing the scores
	 */
	public static ArrayList<Highscores> retrieveScores() {
		return retrieveAll();
	}
	
	/**
	 * Adds a score into database.
	 * @param Name of player
	 * @param Score of player
	 */
	public static void addScore(String name, int score){
		Highscores tempScore = new Highscores(name,score);
		tempScore.insert(tempScore);
	}
	

	/**
	 * Compares two scores
	 * @param class to compare to
	 * @return "-" if Less than compared class, 0 if same, "+" if greater than
	 */
	@Override
	public int compareTo(Highscores o) {
		return this.getScore() - o.getScore();
	}
	
	/**
	 * Converts into String the Highscores class
	 * @return String of name + score
	 */
	@Override
	public String toString(){
		return name + " - " + score;
	
	}
	
	/**
	 * Connects to the database.
	 * Creates a new file if fails
	 * @return Connection to the database
	 */
	private static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(getURL());//Tries to establish connection. 
            //Creates a new item if doesn't exists
        } catch (SQLException e) {
            System.out.println("Error at: connect() " + e.toString());
        }
        return conn;
    }
	
	/**
	 * Inserts items into the database
	 * @param Score to insert
	 */
	private void insert(Highscores score) {
		createNewTable();
        String sql = "INSERT INTO highscores(name, score) VALUES(?,?)";
        try (Connection conn = connect();	//Creates connection
                PreparedStatement pstmt = conn.prepareStatement(sql)) {//Inserts items into db
            pstmt.setString(1, score.getName());
            pstmt.setDouble(2, score.getScore());
            pstmt.executeUpdate();	//Executes statement

        	conn.close(); //Closes connection
        } catch (SQLException e) {
        	//Ignores closing connection exception
        }
    }
	
	/**
	 * Creates a new table if it doesn't exist
	 */
    private static void createNewTable() {
        // Statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS highscores (\n"
                + "	name text NOT NULL,\n"
                + "	score real\n"
                + ");";
        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);//Executes statement to create a new table

        } catch (SQLException e) {
        	//Ignores closing connection exception
        }
    }
    
    /**
     * Retrieves items from the database
     * @return Array containing the scores
     */
    private static ArrayList<Highscores> retrieveAll(){
        String sql = "SELECT name, score FROM highscores";
        ArrayList<Highscores> listOfScores = new ArrayList<Highscores>();
        
        //Connects to the database
        try (Connection conn = connect();
        	 Statement stmt  = conn.createStatement();	//Creates statement to execute
             ResultSet rs    = stmt.executeQuery(sql)){	//Executes statement to select all items
        	while(rs.next()){	//Goes through the database
        		//Retrieves name and score and adds it to the arraylist
        		listOfScores.add(new Highscores(rs.getString("name"),rs.getInt("score")));
        	}
        	conn.close(); //Closes connection
        } catch (SQLException e) {
        	//Ignores closing connection exception
        }

    	return listOfScores;
    }
}