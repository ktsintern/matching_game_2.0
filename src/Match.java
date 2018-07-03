import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Match extends Clock implements ActionListener {
	
	public static void main(String[] args){
		
		new Match();
		
	}
	
	public Match(){
		gamePlay();
	}
	
	//instantiating timer
	public Clock clock = new Clock();
	
	//creating GUI for main game
	public JFrame mainFrame = new JFrame("Best Matching Game");
	public static JPanel QPanel = new JPanel();
	public static JPanel APanel = new JPanel();
	public JPanel userPanel = new JPanel();
		
	public JLabel QLabel = new JLabel("Questions");
	public JLabel ALabel = new JLabel("Answers");
	public JLabel timeLabel = new JLabel("Time: " + clock.translate());
	
	public JButton startBtn = new JButton("Start");
	public JButton leaderBoardBtn = new JButton("Leaderboard");
	
	public JTextField enterName = new JTextField("Enter your name here");
	
	//creating GUI for leaderboard
	public JFrame ldrBrdFrame = new JFrame("LeaderBoard");
	public JPanel ldrBrdPanel = new JPanel();
	
	private void gamePlay(){
		//setting up main frame
		mainFrame.setSize(800, 1000);
		mainFrame.setVisible(true);
		
		//setting up userPanel
		mainFrame.add(userPanel, BorderLayout.SOUTH);
		//userPanel.setSize(800, 300);
		userPanel.setVisible(true);
		userPanel.setBackground(Color.BLACK);
		//adding start button and leaderboard button
		userPanel.add(startBtn);
		startBtn.setVisible(true);
		startBtn.addActionListener(this);
		userPanel.add(leaderBoardBtn);
		leaderBoardBtn.setVisible(false);
		leaderBoardBtn.addActionListener(this);
		
		//adding enterName text field
		userPanel.add(enterName);
		enterName.setSize(150, 35);
		enterName.setVisible(false);
		
		//adding timeLabel
		userPanel.add(timeLabel, BorderLayout.EAST);
		timeLabel.setSize(100, 35);
		timeLabel.setOpaque(true);
		timeLabel.setVisible(true);
		
		//setting up QPanel
		mainFrame.add(QPanel, BorderLayout.WEST);
		//QPanel.setSize(400, 700);
		QPanel.setVisible(true);
		QPanel.setBackground(Color.WHITE);
		
		QPanel.add(QLabel);
		QLabel.setVisible(true);
		
		//setting up APanel
		mainFrame.add(APanel, BorderLayout.EAST);
		//APanel.setSize(400, 700);
		APanel.setVisible(true);
		APanel.setBackground(Color.GRAY);
		
		APanel.add(ALabel);
		ALabel.setVisible(true);
		
		//setting up leaderboard frame
		ldrBrdFrame.setSize(150, 200);
		ldrBrdFrame.setVisible(false);
		
		//setting up ldrBrdPanel
		ldrBrdFrame.add(ldrBrdPanel);
		
	}
	
	//creating prompts
	public static Map<String, String> prompts = new HashMap<String, String>();
	public static ArrayList<String> answersList = new ArrayList<String>();
	
	private void promptCreator(){
		try{
			FileReader fr = new FileReader("prompts");
			BufferedReader br = new BufferedReader(fr);
				
			//adding prompts into HashMap prompts
			String str;
			String[] pair;
			while((str = br.readLine()) != null){
				pair = str.split(",");
				prompts.put(pair[0], pair[1]);
				answersList.add(pair[1]);
			}
			br.close();
		} catch(IOException e){
			System.out.println("File not found");
		}
	}
		
	//instantiating variables to create a leaderboard GUI
	public Map<String, String> highScores = new LinkedHashMap<String, String>();
		
	private void leaderBoardCreator(){
	//reads leaderboard first
	try{		
		FileReader fr = new FileReader("leaderboard");
		BufferedReader br = new BufferedReader(fr);
				
		//creating leaderboard
		String str;
		String[] pair;
		while((str = br.readLine()) != null){
			pair = str.split(",");
			System.out.println("\n" + pair[0] + " " + pair[1]);
			System.out.println("Before: " + highScores);
			highScores.put(pair[0], pair[1]);
			System.out.println("After: " + highScores);
		}
		br.close();
		} catch(IOException e1){
			System.out.println("File not found");
		}
		
		//making sure there aren't any already-stored scores
		ldrBrdPanel.removeAll();
		
		//adding the highScores into the label array
		for(int i = 0; i < 5; i++){
			JLabel highScoresLbl = new JLabel();
			highScoresLbl.setText((i + 1) + ". " + (String) highScores.keySet().toArray()[i] + ": " + highScores.get((String) highScores.keySet().toArray()[i]));
			ldrBrdPanel.add(highScoresLbl);
			highScoresLbl.setVisible(true);
		}
		
		ldrBrdFrame.setVisible(true);
		ldrBrdPanel.setVisible(true);
		System.out.println(highScores);
			
	}
	
	//a method to randomize buttons
	public void randomizer(){
		promptCreator();
		
		//creating buttons with questions and answers on them
		for(int i = 0; i < prompts.size(); i++){
			//adding questions in order to QPanel
			JButton Q1 = new JButton((String) prompts.keySet().toArray()[i]);
			Q1.addActionListener(this);
			QPanel.add(Q1);
					
			//adding answers randomly to APanel
			Random num = new Random();
			int index = num.nextInt(answersList.size());
			JButton A1 = new JButton(answersList.get(index));
			answersList.remove(index);
			A1.addActionListener(this);
			APanel.add(A1);
		}
	}

	//instantiating variables needed to check whether questions and answers are a pair
	int Qcounter = 0;
	int Acounter = 0;
	public JButton savedQButton;
	public JButton savedAButton;
	String chosenAnswer = "";
	int index = 0;
	int numCorrect = 0;
	
	public void actionPerformed(ActionEvent e) {
		
		if(((AbstractButton) e.getSource()).equals(startBtn)){
			startBtn.setEnabled(false);
			leaderBoardBtn.setVisible(true);
			randomizer();
			clock.start();
			System.out.println("clicked start");
			System.out.println(prompts);
			
		}
		else if(!(startBtn.isEnabled())){ 
			//if clicked button was a question button
			if(Qcounter == 0){
				for(int i = 0; i < prompts.size(); i++){
					if(((AbstractButton) e.getSource()).getText().equals((String) prompts.keySet().toArray()[i])){
						index = i;
						savedQButton = (JButton) ((AbstractButton) e.getSource());
						savedQButton.setOpaque(true);
						savedQButton.setBackground(Color.MAGENTA);
						Qcounter++;
						System.out.println("question clicked " + savedQButton.getText());
					}
				}
			}
			//if clicked button was an answer button
			if(Acounter == 0){
				for(int i = 0; i < prompts.size(); i++){
					if(((AbstractButton) e.getSource()).getText().equals(prompts.get((String) prompts.keySet().toArray()[i]))){
						chosenAnswer = prompts.get((String) prompts.keySet().toArray()[i]);
						savedAButton = (JButton) ((AbstractButton) e.getSource());
						savedAButton.setOpaque(true);
						savedAButton.setBackground(Color.MAGENTA);
						Acounter++;
						System.out.println("answer clicked " + savedAButton.getText());
					}
				}
			}
		}
		
		//if a question and answer has been selected, checks whether they're a match
		if(Qcounter == 1 && Acounter == 1){
			//if they are, button turns green and is disabled
			if(prompts.get((String) prompts.keySet().toArray()[index]).equals(chosenAnswer)){
				Qcounter = 0;
				Acounter = 0;
				savedQButton.setBackground(Color.GREEN);
				savedAButton.setBackground(Color.GREEN);
				savedQButton.setEnabled(false);
				savedAButton.setEnabled(false);
				numCorrect++;
				System.out.println(chosenAnswer + " " + index);
				System.out.println("I work!");
			}
			//if not, then everything gets returned to its previous appearance
			else{
				Qcounter = 0;
				Acounter = 0;
				savedQButton.setBackground(Color.WHITE);
				savedAButton.setBackground(Color.GRAY);
				System.out.println(chosenAnswer + " " + index);
				System.out.println("wrong pair");
			}
		}
		
		//if all matches have been completed reset the board ***doesn't work properly***
		if(numCorrect == prompts.size()){
			
			enterName.setVisible(true);
			//wipe board clean, create a new gamePlay
			/*mainFrame.removeAll();
			gamePlay();
			startBtn.setEnabled(true);*/
		}
		
		//if leaderboard button is clicked then leaderboard file should be displayed
		if(((AbstractButton) e.getSource()).equals(leaderBoardBtn)){
			System.out.println("leaderboard button clicked");
			leaderBoardCreator();
		}
		
	}
	
	private void checkScores(){
		enterName.getText();
		//create timer to retrieve time for completion
	}
	
	//instantiating variables to create and display a timer
	String time = "-";
	public JLabel timerLbl = new JLabel(time);
	


}
