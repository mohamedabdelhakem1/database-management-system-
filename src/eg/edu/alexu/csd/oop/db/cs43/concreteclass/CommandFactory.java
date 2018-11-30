package eg.edu.alexu.csd.oop.db.cs43.concreteclass;

import javafx.scene.control.TextArea;

public interface CommandFactory {
	public void  setcommand(String command,boolean dropIfExists ,String databaseName);
	public void getFunction(TextArea textArea) throws Exception;
}
