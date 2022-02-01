package il.ac.tau.cs.sw1.ex9.starfleet;

public class Cylon extends MyAbstractCrewMember implements CrewMember {
	protected int modelMumber; 

	public Cylon(String name, int age, int yearsInService, int modelNumber) {
		super(age,yearsInService,name);
		this.modelMumber=modelNumber; 
	}
	
	public int getModelNumber() {
		return this.modelMumber; 
	}
	

}
