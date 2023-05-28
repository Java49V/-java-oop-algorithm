package telran.util;

public class ObjectExample {
	private static int ID = 0; 
	private int id = -1;
	public ObjectExample() {
		id = ID++;
		if(ID == 10) {
			ID = 0;
		}
	}
	@Override
	public int hashCode() {
		return id;
	}
}

