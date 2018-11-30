package eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses;

import javafx.scene.control.TextArea;

public interface CommandRequestStrategy {
	public void getSpecifiedRequest(TextArea textArea , String command);
}
