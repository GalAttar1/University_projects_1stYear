package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;


public class StarfleetManager {

	/**
	 * Returns a list containing string representation of all fleet ships, sorted in descending order by
	 * fire power, and then in descending order by commission year, and finally in ascending order by
	 * name
	 */
	public static List<String> getShipDescriptionsSortedByFirePowerAndCommissionYear (Collection<Spaceship> fleet) {
		Comparator<Spaceship> spaceship_FirePowerD_CommissionYearD__nameA_Comparator =
				Comparator.comparingInt(Spaceship::getFirePower)
				.thenComparingInt(Spaceship::getCommissionYear).reversed()
				.thenComparing(Spaceship::getName); 
		ArrayList<Spaceship> sList = new ArrayList<Spaceship> (fleet); 
		Collections.sort(sList, spaceship_FirePowerD_CommissionYearD__nameA_Comparator);
		List<String> resList = new ArrayList<String>(); 
		for (Spaceship spaceship : sList) {
			resList.add(spaceship.toString());
		}
		return resList;
	}

	/**
	 * Returns a map containing ship type names as keys (the class name) and the number of instances created for each type as values
	 */
	public static Map<String, Integer> getInstanceNumberPerClass(Collection<Spaceship> fleet) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		String s; 
		for (Spaceship spaceship : fleet) {
			s=spaceship.getClass().getSimpleName();
			if (map.containsKey(s)) {map.put(s, map.get(s)+1);}
			else map.put(s, 1); 
		}
		return map;

	}


	/**
	 * Returns the total annual maintenance cost of the fleet (which is the sum of maintenance costs of all the fleet's ships)
	 */
	public static int getTotalMaintenanceCost (Collection<Spaceship> fleet) {
		int sumTotal=0;
		for (Spaceship spaceship : fleet) {
			sumTotal+=spaceship.getAnnualMaintenanceCost(); 
		}
		return sumTotal;

	}


	/**
	 * Returns a set containing the names of all the fleet's weapons installed on any ship
	 */
	public static Set<String> getFleetWeaponNames(Collection<Spaceship> fleet) {
		Set<String> set= new HashSet<String>();
		MyAbstractBattleship battleship; 
		for (Spaceship spaceship : fleet) {
			if (!(spaceship instanceof TransportShip)) {
			battleship=(MyAbstractBattleship)spaceship; 
			for (Weapon weapon: battleship.getWeapon()) {
				set.add(weapon.getName());
			}
			}
		}
		return set;

	}

	/*
	 * Returns the total number of crew-members serving on board of the given fleet's ships.
	 */
	public static int getTotalNumberOfFleetCrewMembers(Collection<Spaceship> fleet) {
		int sumTotal=0;
		for (Spaceship spaceship : fleet) {
			sumTotal+=spaceship.getCrewMembers().size(); 
		}
		return sumTotal;


	}

	/*
	 * Returns the average age of all officers serving on board of the given fleet's ships. 
	 */
	public static float getAverageAgeOfFleetOfficers(Collection<Spaceship> fleet) {
		int sumAge=0;
		int count=0; 
		for (Spaceship spaceship : fleet) {
			for (CrewMember woman:spaceship.getCrewMembers()) {
				if (woman.getClass().getSimpleName().equals("Officer")){
				sumAge+=woman.getAge();
				count+=1; }
			}
		}
		return (float)(sumAge/count);
	}

	/*
	 * Returns a map mapping the highest ranking officer on each ship (as keys), to his ship (as values).
	 */
	@SuppressWarnings("unchecked")
	public static Map<Officer, Spaceship> getHighestRankingOfficerPerShip(Collection<Spaceship> fleet) {
		OfficerRank maxrank; 
		Officer maxofficer;
		int countOfficers;
		Map<Officer, Spaceship> map = new HashMap<Officer, Spaceship>(); 
		
		//find the highest rank 
		for (Spaceship spaceship : fleet) {
			maxrank=OfficerRank.Ensign;
			maxofficer=new Officer ("", 0, 0, null);
			countOfficers=0; 
			
			Set<CrewMember> list = (Set<CrewMember>) spaceship.getCrewMembers(); 
			Iterator<CrewMember> crewIter = (Iterator<CrewMember>) list.iterator();
			CrewMember nextPerson;
			while (crewIter.hasNext()) {
				nextPerson=crewIter.next(); 
				if (nextPerson instanceof Officer) {
					countOfficers++;
					switch(((Officer)nextPerson).getRank().compareTo(maxrank)){ 
					case 1:
						maxrank=((Officer)nextPerson).getRank();
						maxofficer=(Officer) nextPerson; 
						break; 
						
					case 0:
						if (maxofficer.getAge()==0)
							maxofficer=(Officer) nextPerson;
						break;
					 
					}
				}
			}
			if (countOfficers>0) {map.put(maxofficer, spaceship);}
		}
		return map;	
	}

	/*
	 * Returns a List of entries representing ranks and their occurrences.
	 * Each entry represents a pair composed of an officer rank, and the number of its occurrences among starfleet personnel.
	 * The returned list is sorted ascendingly based on the number of occurrences.
	 */
	@SuppressWarnings("unchecked")
	public static List<Map.Entry<OfficerRank, Integer>> getOfficerRanksSortedByPopularity(Collection<Spaceship> fleet) {
		List<Officer> officresList = new ArrayList<Officer>();
		Stream<CrewMember> stream;
		for (Spaceship spaceship : fleet) {
			stream= (Stream<CrewMember>) spaceship.getCrewMembers().stream();
			stream.filter(member -> member instanceof Officer)
			.forEach(officer -> officresList.add((Officer) officer));
		}
		Officer nextOfficer; 
		Map<OfficerRank, Integer> map = new HashMap<OfficerRank, Integer>(); 
		Iterator<Officer> iterator = officresList.iterator(); 
		while (iterator.hasNext()) {
			nextOfficer=iterator.next();
			if (map.containsKey(nextOfficer.getRank())) 
				{map.put(nextOfficer.getRank(), map.get(nextOfficer.getRank())+1);}
			else 
				{map.put(nextOfficer.getRank(), 1);} 
		}
		List<Map.Entry<OfficerRank, Integer>> sortedList = (List<Entry<OfficerRank, Integer>>) map.entrySet(); 
		Collections.sort(sortedList, new Comparator<Entry<OfficerRank, Integer>>(){
			@Override
			public int compare(Entry<OfficerRank, Integer> o1, Entry<OfficerRank, Integer> o2) {
				if (o1.getValue()!=o2.getValue()) {return o1.getValue().compareTo(o2.getValue());}
				return -1*(o1.getKey().compareTo(o2.getKey())); //for reversed order
			}	
		}
		);
		return null;
	}

}
