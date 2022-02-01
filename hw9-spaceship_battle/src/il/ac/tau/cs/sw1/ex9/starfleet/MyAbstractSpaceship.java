package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public abstract class MyAbstractSpaceship implements Spaceship, Comparable<Spaceship>{
	protected String name;
	protected int commissionYear;
	protected float maximalSpeed; 
	protected int firePower=10;
	protected Set<? extends CrewMember> crewMembers;
	protected int annualMaintenanceCost=-1; 

	
	public MyAbstractSpaceship(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers) {
		this.name=name;
		this.commissionYear=commissionYear;
		this.maximalSpeed=maximalSpeed;
		this.crewMembers=crewMembers;
	}
	
	public String getName() {
		return this.name; 
	}
	
	public int getCommissionYear() {
		return this.commissionYear; 
	}
	
	public float getMaximalSpeed() {
		return this.maximalSpeed; 
	}
	
	public int getFirePower() {
		return this.firePower; 
	}
	
	public Set<? extends CrewMember> getCrewMembers(){
		return this.crewMembers; 
	}
	
	public int getAnnualMaintenanceCost() {
		return 0; 
	}
	
	public String toString() {
		String st= "\tName="+this.name;
		st+=System.lineSeparator()+ "\tCommissionYear="+this.commissionYear;
		st+=System.lineSeparator()+ "\tMaximalSpeed="+this.maximalSpeed;
		st+=System.lineSeparator()+ "\tFirePower="+this.firePower;
		st+=System.lineSeparator()+ "\tCrewMembers="+this.crewMembers.size();
		st+=System.lineSeparator()+ "\tAnnualMaintenanceCost="+this.getAnnualMaintenanceCost();
		return st; 
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
		if (!(obj instanceof MyAbstractSpaceship)) {
			return false;
		}
		MyAbstractSpaceship other = (MyAbstractSpaceship) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int compareTo(Spaceship o) {
		return this.getName().compareToIgnoreCase(o.getName()); 
	}

	 

}
