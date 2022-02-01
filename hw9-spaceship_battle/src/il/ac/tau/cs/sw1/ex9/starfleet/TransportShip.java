package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public class TransportShip extends MyAbstractSpaceship implements Spaceship{
	protected int cargoCapacity;
	protected int passengerCapacity; 
	
	static int AnnualCostTarnsportshipBasic = 3000; 
	
	
	public TransportShip(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, int cargoCapacity, int passengerCapacity){
		super(name,commissionYear,maximalSpeed,crewMembers);
		this.cargoCapacity=cargoCapacity; 
		this.passengerCapacity=passengerCapacity;
		this.annualMaintenanceCost= this.getAnnualMaintenanceCost();
	}
	
	public int getCargoCapacity() {
		return this.cargoCapacity; 
	}
	
	public int passengerCapacity() {
		return this.passengerCapacity; 
	}
	
	@Override 
	public int getAnnualMaintenanceCost() {
		return AnnualCostTarnsportshipBasic + (5*this.cargoCapacity) + (3*this.passengerCapacity);
	}
	
	@Override
	public String toString() {
		String Super = super.toString(); 
		String st="TransportShip"+System.lineSeparator()+ Super;
		st+=System.lineSeparator()+ "\tCargoCapacity="+this.cargoCapacity;
		st+=System.lineSeparator()+ "\tPassengerCapacity="+this.passengerCapacity;
		return st; 
	}

}
