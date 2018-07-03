import java.util.Timer;
import java.util.TimerTask;

//no???
public class Clock {
	
	int secondsPassed = 0;
	int minutesPassed = 0;

	Timer timer = new Timer();
	TimerTask task = new TimerTask(){
		public void run(){
			secondsPassed++;
			System.out.println(translate());
			System.out.println("Seconds passed: " + secondsPassed);
		}
	};
	
	public void start(){
		timer.scheduleAtFixedRate(task, 1000, 1000);
	}
	
	public void stop(){
		timer.cancel();
	}
	
	public String translate(){
		
		String seconds = "" + secondsPassed;
		if(secondsPassed == 60){
			minutesPassed++;
			secondsPassed = 0;
		}
		
		if(secondsPassed < 10){
			seconds = "0" + secondsPassed;
		}
		return minutesPassed + ":" + seconds;
				
	}
	
	public static void main(String[] args){
		Clock clock = new Clock();
		clock.start();
	}
	
}
