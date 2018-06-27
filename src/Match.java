import java.awt.*;
import java.io.*;
import java.util.*;

public class Match {
	
	//creating prompts
	public static ArrayList <String> questions = new ArrayList<String>();
	public static ArrayList <String> answers = new ArrayList<String>();
	
	private static void prompts(){
		try{
			FileReader fr = new FileReader("prompts");
			BufferedReader br = new BufferedReader(fr);
			
			//adding prompts into ArrayLists questions and answers
			String str;
			String word = "";
			while((str = br.readLine()) != null){
				System.out.println(str);
				for(int i = 0; i < str.length(); i++){
					if(!(str.substring(i, i + 1).equals(",")) && !(str.substring(i, i + 1).equals("."))){
						word += str.substring(i, i + 1);
						System.out.println("substring = " + str.substring(i, i + 1));
						System.out.println("word = " + word);
					}
					else if(str.substring(i, i + 1).equals(",")){
						questions.add(word);
						word = "";
						System.out.println("added question");
					}
					else if(str.substring(i, i + 1).equals(".")){
						answers.add(word);
						word = "";
						System.out.println("added answer");
					}
				}
			}
			br.close();
		} catch(IOException e){
			System.out.println("File not found");
		}
	}
	
	public static void main(String[] args){
		prompts();
		System.out.println(questions);
		System.out.println(answers);
	}
	
	//creating GUI
	Panel mainGame = new Panel();
	
	Label qLabel = new Label("Questions");
	Label aLabel = new Label("Answers");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
