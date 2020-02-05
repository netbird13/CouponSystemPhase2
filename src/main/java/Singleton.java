
public class Singleton {

	// Init
		private static Singleton instance = null;
	
	// private CTOR
	private Singleton() {};
	
	// getMethod
	public static Singleton getInstance() {
		if (instance == null) {
			synchronized(Singleton.class) {
				if (instance == null) {
					instance = new Singleton();}}}
		return instance;		
	}
	
}
