package eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses;

import eg.edu.alexu.csd.oop.db.cs43.concreteclass.CommandFactory;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class UIController {
	@FXML
	TextField input;
	@FXML 
	TextArea output;
	CommandFactory commandFactory;

	public UIController() {
		commandFactory = new ClientCommand();
	}

	@FXML
	public void enterCommand(KeyEvent e) throws Exception {
		if (e.getCode() == KeyCode.ENTER) {
			
			commandFactory.setcommand(input.getText(), false, "");
			try {
				commandFactory.getFunction(output);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		 
	}
}
