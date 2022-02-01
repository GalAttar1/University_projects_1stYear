package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class CylonRaider extends Fighter implements Spaceship{
	
	static int AnnualCostCylonRaiderBasic = 3500; 

	public CylonRaider(String name, int commissionYear, float maximalSpeed, Set<Cylon> crewMembers, List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}
	
	@Override
	public int getAnnualMaintenanceCost() {
		int sumWeapons=0; 
		for (Weapon weapon: this.weaponList) {
			sumWeapons+=weapon.getAnnualMaintenanceCost(); 
		}
		int sumMembers = 500*this.getCrewMembers().size(); 
		int engineCost= (int)(1200*this.getMaximalSpeed()); 
		this.annualMaintenanceCost= AnnualCostCylonRaiderBasic + sumWeapons + sumMembers + engineCost; 
		return this.annualMaintenanceCost; 
	}
	
	@Override
	public String toString() {
		String Super = super.toString();
		String st= Super.replaceFirst("Fighter","CylonRaider");
		return st; 
	}

	

}
