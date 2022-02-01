package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class ColonialViper extends Fighter implements Spaceship {
	
	static int AnnualCostColonialViperBasic = 4000; 

	public ColonialViper(String name, int commissionYear, float maximalSpeed, Set<CrewWoman> crewMembers, List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}
	
	@Override
	public int getAnnualMaintenanceCost() {
		int sumWeapons=0; 
		for (Weapon weapon: this.weaponList) {
			sumWeapons+=weapon.getAnnualMaintenanceCost(); 
		}
		int sumMembers = 500*this.getCrewMembers().size(); 
		int engineCost= (int)(500*this.getMaximalSpeed()); 
		this.annualMaintenanceCost= AnnualCostColonialViperBasic + sumWeapons + sumMembers + engineCost; 
		return this.annualMaintenanceCost; 
	}
	
	@Override
	public String toString() {
		String Super = super.toString();
		String st= Super.replace("Fighter","ColonialViper"); 
		return st; 
	}

}
