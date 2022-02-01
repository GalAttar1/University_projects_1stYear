package il.ac.tau.cs.sw1.ex9.riddles.third;

public class B3 extends A3 {

	private static final long serialVersionUID = 1L;

	public B3(String s) {
		super(s);
	}
	
	@Override 
	public String getMessage() {
		return this.s;
	}
	
	@Override
	public void foo(String s) throws Exception{
		try{super.foo(s);}	
		catch (Exception ex) {
			throw new B3 (this.getMessage());
		}
	}
	
}