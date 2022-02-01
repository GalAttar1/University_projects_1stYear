package il.ac.tau.cs.sw1.ex9.starfleet;

public abstract class MyAbstractCrewMember implements CrewMember {

	protected String name;
	protected int age;
	protected int yearsInService;
	
	
	public MyAbstractCrewMember(int age, int yearsInService, String name) {
		this.age=age; 
		this.name=name; 
		this.yearsInService=yearsInService; 
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getAge() {
		return this.age; 
	}

	@Override
	public int getYearsInService() {
		return this.yearsInService; 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MyAbstractCrewMember)) {
			return false;
		}
		MyAbstractCrewMember other = (MyAbstractCrewMember) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
	
	
	

}
