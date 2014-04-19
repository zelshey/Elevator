import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class floor {
	private int number;
	private ActionListener listener;
	private ArrayList<person> up;
	private ArrayList<person> down;
	
	public floor(int number){
		this.number = number;
	}
	
	public void addActionListener(ActionListener a){
		this.listener = a;
	}
	
	public void pressedButton(int direction){
		String command = "floor " + direction;
		ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_FIRST, command);
		listener.actionPerformed(event);
	}

	public int getNumber() {
		return number;
	}
}
