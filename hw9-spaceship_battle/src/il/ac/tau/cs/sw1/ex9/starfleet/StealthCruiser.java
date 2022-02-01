package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends Fighter implements Spaceship{
	
	static int countCloakingDevices; 
	
	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
		countCloakingDevices++; 
	}
	
	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers) {
		super (name, commissionYear, maximalSpeed, crewMembers,
				Arrays.asList(new Weapon("Laser Cannons",10,100))); 
	}
	

	@Override
	public int getAnnualMaintenanceCost() {
		int engine = (int) (50*countCloakingDevices);
		int old = super.getAnnualMaintenanceCost(); 
		this.annualMaintenanceCost= old + engine;
		return this.annualMaintenanceCost; 
	}
	
	@Override
	public String toString() {
		String Super = super.toString();
		String st= Super.replaceFirst("Fighter","StealthCruiser"); 
		return st; 
	}
}
