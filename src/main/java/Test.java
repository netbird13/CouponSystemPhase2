
public class Test {

	
	public static void main(String[] args) {
		Mobile android = createMobile("Iphone");
	}
	
	// Factory method
	public static Mobile createMobile(String s) {
		if (s.equalsIgnoreCase("Iphone")) {
		return new Iphone();			
		}
		return new Android();
			
	}	
	
}
