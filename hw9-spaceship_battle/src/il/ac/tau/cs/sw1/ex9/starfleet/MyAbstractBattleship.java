package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class MyAbstractBattleship extends MyAbstractSpaceship implements Spaceship {
	protected List<Weapon> weaponList; 
	
	public MyAbstractBattleship(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers,List<Weapon> weaponList) {
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.weaponList=weaponList;
		for (Weapon weapon: this.weaponList) {
			this.firePower+=weapon.getFirePower(); 
		}
	}
	
	public List<Weapon> getWeapon(){
		return this.weaponList; 
	}
	
	@Override
	public String toString() {
		String st = super.toString();
		List<String> weaponList = new ArrayList<String>();
		for (Weapon weapon : this.getWeapon()) {
			weaponList.add(weapon.toString());
		}
		st+=System.lineSeparator()+"\tWeaponArray=" +"[" + String.join(", ", weaponList) +"]"; 
		return st; 
	}
	

}
