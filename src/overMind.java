import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class overMind implements ActionListener, ElevatorConstants {
	private floor[] floors;
	private Elevator[] elevators;
	
	public overMind (int numFloors, int numElevators){
		floors = new floor[numFloors];
		elevators = new Elevator[numElevators];
		for(int i = 0; i < numFloors; i++){
			floor f = new floor(i+1);
			f.addActionListener(this);
			floors[i] = f;
		}
		for(int i = 0; i < numElevators; i++){
			Elevator e = new Elevator(numFloors);
			e.addActionListener(this);
			elevators[i] = e;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		String[] components = command.split(" ");
		if(components[0] == "elevator"){
			//do whatever needs to be done when elevator calls its button press event
			
		}else if(components[0] == "floor"){
			//look for nearest elevator going in the correct direction
			floor f = (floor) arg0.getSource();
			int num = f.getNumber();
			int dir = Integer.parseInt(components[1]);
			int index = 0;
			int dist = Integer.MAX_VALUE;
			for(int i = 0; i < elevators.length; i++){
				if(elevators[i].getDist(num, dir) < dist){
					dist = elevators[i].getDist(num, dir);
					index = i;
				}
			}
			elevators[index].addFloor(num);
		}
	}
	
	public void generatePeople(){
		///]\\make people
	}
}
