package eg.edu.alexu.csd.oop.db.cs43.interfaces;

public interface CommandFactory {
	public void  setcommand(String command,boolean dropIfExists);
	public Command getFunction();
}
