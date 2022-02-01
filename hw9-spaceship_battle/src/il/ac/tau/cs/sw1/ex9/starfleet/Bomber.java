package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends MyAbstractBattleship implements Spaceship{
	protected int NumberOfTechnicians;
	
	static int AnnualCostBomerBasic = 5000; 

	public Bomber(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, List<Weapon> weapons, int numberOfTechnicians){
		super(name, commissionYear, maximalSpeed, crewMembers, weapons); 
	}
	
	public int getNumberOfTechnicians() {
		return this.NumberOfTechnicians;
	}
	

	@Override
	public int getAnnualMaintenanceCost() {
		int sumWeapons=0; 
		for (Weapon weapon: this.weaponList) {
			sumWeapons+=weapon.getAnnualMaintenanceCost(); 
		}
		int Afterdiscount = (int) ((100-(this.NumberOfTechnicians*10))/100)*sumWeapons; 
		this.annualMaintenanceCost= AnnualCostBomerBasic + Afterdiscount;
		return this.annualMaintenanceCost; 
	}	
	
	@Override
	public String toString() {
		String Super = super.toString(); 
		String st="Bomber"+System.lineSeparator()+ Super;
		st+=System.lineSeparator()+"\tNumberOfTechnicians="+this.getNumberOfTechnicians();
		return st; 
	}

}
