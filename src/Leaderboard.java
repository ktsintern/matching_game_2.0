import java.io.*;
import java.util.*;
import javax.swing.*;

//TRY TO CREATE A LEADERBOARD OBJECT TO CLEAN UP MATCH CODE AND MAKE IT MORE ADAPTABLE/ORGANIZED
public class Leaderboard {
	
	JPanel mainPanel;
	JFrame mainFrame;

	public Leaderboard(JPanel panel, JFrame frame){
		mainPanel = panel;
		mainFrame = frame;
	}
	
	//display the leaderboard
	public void display(){
		mainFrame.setVisible(true);
		mainPanel.setVisible(true);
		System.out.println(highScores);
	}
	
	//instantiating variables to create a leaderboard GUI
	public Map<String, String> highScores = new LinkedHashMap<String, String>();
			
	public void leaderBoardCreator(){
		//reads leaderboard first
		try{		
			BufferedReader br = new BufferedReader(new FileReader("leaderboard"));
					
			//reading leaderboard file
			String str;
			String[] pair;
			while((str = br.readLine()) != null){
				pair = str.split(",");
				highScores.put(pair[0], pair[1]);
			}
			br.close();	
		} catch(IOException e1){
			System.out.println("File not found");		
		}
			
		//making sure there aren't any already-stored scores
		mainPanel.removeAll();
			
		//making each high score its own label
		for(int i = 0; i < 5; i++){
			JLabel highScoresLbl = new JLabel();
			highScoresLbl.setText((i + 1) + ". " + (String) highScores.keySet().toArray()[i] + ": " + highScores.get((String) highScores.keySet().toArray()[i]));
			mainPanel.add(highScoresLbl);
			highScoresLbl.setVisible(true);	
		}
	}
	
	//updates leaderboard file
	public void fileUpdate(){
		
	}
		

}
