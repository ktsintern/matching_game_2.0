import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Match implements ActionListener {
	
	public static void main(String[] args){
		promptCreator();
		System.out.println(prompts);
		
		randomizer();
		
		new Match();
		
	}
	
	//creating prompts
	public static Map<String, String> prompts = new HashMap<String, String>();
	public static ArrayList<String> questionsCol = new ArrayList<String>();
	public static ArrayList<String> answersCol = new ArrayList<String>();
	public static ArrayList<String> answersList = new ArrayList<String>();
	
	private static void promptCreator(){
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
				questionsCol.add(pair[0]);
				answersCol.add(pair[1]);
			}
			//answersList = answersCol;
			br.close();
		} catch(IOException e){
			System.out.println("File not found");
		}
	}
	
	
	//randomizing buttons
	public static void randomizer(){
		
		//creating buttons with questions and answers on them
		for(int i = 0; i < prompts.size(); i++){
			//adding questions in order to QPanel
			JButton Q1 = new JButton((String) prompts.keySet().toArray()[i]);
			QPanel.add(Q1);
			
			//adding answers randomly to APanel
			Random num = new Random();
			int index = num.nextInt(answersList.size());
			JButton A1 = new JButton(answersList.get(index));
			answersList.remove(index);
			APanel.add(A1);
		}
	}
	
	//creating GUI
	public static JFrame mainFrame = new JFrame();
	public static JPanel QPanel = new JPanel();
	public static JPanel APanel = new JPanel();
	public static JPanel userPanel = new JPanel();
	
	public static JLabel QLabel = new JLabel("Questions");
	public static JLabel ALabel = new JLabel("Answers");
	
	public Match(){
		//setting up main frame
		mainFrame.setSize(750, 1500);
		mainFrame.setVisible(true);
		
		//setting up userPanel
		//mainFrame.add(userPanel);
		//userPanel.setSize(500, 100);
		//userPanel.setVisible(true);
		//userPanel.setBackground(Color.BLACK);
		
		//setting up QPanel
		mainFrame.add(QPanel);
		QPanel.setSize(250, 500);
		QPanel.setVisible(true);
		QPanel.setBackground(Color.WHITE);
		
		QPanel.add(QLabel);
		QLabel.setVisible(true);
		
		//setting up APanel
		mainFrame.add(APanel);
		APanel.setSize(250, 500);
		APanel.setVisible(true);
		APanel.setBackground(Color.GRAY);
		
		APanel.add(ALabel);
		ALabel.setVisible(true);
		
	}

	public static int Qcounter = 0;
	public static int Acounter = 0;
	
	public void actionPerformed(ActionEvent e) {
		if(Qcounter == 0 || Acounter == 0){
			for(String quest: questionsCol){
				if(((AbstractButton) e.getSource()).getText().equals(quest)){
					
				}
			}
		}
	}
	
	

}
