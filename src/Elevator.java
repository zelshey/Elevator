import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Elevator implements ElevatorConstants, Runnable {
	private int floor;
	private int direction;
	private int totalFloors;
	private ArrayList<person> people;
	private ActionListener listener;
	private ArrayList<Integer> floorsToVisit;
	
	public Elevator(int totalFloors){
		this.totalFloors = totalFloors;
		direction = NEUTRAL;
		floor = 0;
	}
	
	public Elevator(int floor, int totalFloors){
		this.totalFloors = totalFloors;
		this.floor = floor;
		direction = NEUTRAL;
	}
	
	public void pressedButton(int floor){
		if(direction == UP && floor > this.floor){
			addFloor(floor);
			String command = "elevator " + floor;
			ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_FIRST, command);
			listener.actionPerformed(event);
		}else if (direction == DOWN && floor < this.floor){
			addFloor(floor);
			String command = "elevator " + floor;
			ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_FIRST, command);
			listener.actionPerformed(event);
		}
	}
	
	public void addFloor(int num){
		if(!floorsToVisit.contains(num)){
			floorsToVisit.add(num);
		}
	}
	
	public void addActionListener(ActionListener a){
		this.listener = a;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	public ArrayList<person> getPeople() {
		return people;
	}

	public void setPeople(ArrayList<person> people) {
		this.people = people;
	}
	
	
	public int getDist(int num, int dir){
		int dist = 0;
		//get the distance from current floor to desired floor
		if(dir == direction || direction == NEUTRAL){
			dist = Math.abs(num - floor);
		}else{
			//something that gets distance to the furthest planned stop then to desired stop
			//to lazy to do now ish
		}
		return dist;
	}

	@Override
	public void run() {
		while(!floorsToVisit.isEmpty()){
			try {
				
				nextFloor();				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		direction = NEUTRAL;
	}
	private void nextFloor() throws InterruptedException{
		if(direction == DOWN){
			if(floor > 0){
				floor--;
			}
		}else if (direction == UP){
			if(floor < totalFloors){
				floor++;
			}
		}
		Thread.sleep(TIMETONEXT);
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}
}
