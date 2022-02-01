package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Fighter extends MyAbstractBattleship implements Spaceship {
	
	static int AnnualCostFighterBasic = 2500; 
	
	
	public Fighter(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, List<Weapon> weapons){
		super(name, commissionYear, maximalSpeed, crewMembers, weapons);
	}
	
	
	@Override
	public int getAnnualMaintenanceCost() {
		int sumWeapons=0; 
		for (Weapon weapon: this.weaponList) {
			sumWeapons+=weapon.getAnnualMaintenanceCost(); 
		}
		int engine = (int) (1000*this.maximalSpeed); 
		this.annualMaintenanceCost=AnnualCostFighterBasic + sumWeapons + engine;
		return this.annualMaintenanceCost; 
	}
	
	@Override
	public String toString() {
		String Super = super.toString(); 
		String st="Fighter"+System.lineSeparator()+ Super;
		return st; 
	}
	
}
