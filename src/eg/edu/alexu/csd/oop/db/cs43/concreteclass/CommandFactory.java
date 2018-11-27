package eg.edu.alexu.csd.oop.db.cs43.concreteclass;

public interface CommandFactory {
	public void  setcommand(String command,boolean dropIfExists);
	public Command getFunction();
}
