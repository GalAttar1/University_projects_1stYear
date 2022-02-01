package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.util.Comparator;

import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

class RankedWordComparator implements Comparator<RankedWord>{
	private rankType Type; 
	
	public RankedWordComparator(rankType cType) {
		this.Type=cType; 
	}
	
	@Override
	public int compare(RankedWord o1, RankedWord o2) {
		if (o1.getRankByType(this.Type)<o2.getRankByType(this.Type)) {return -1;}
		if (o1.getRankByType(this.Type)>o2.getRankByType(this.Type)) {return 1;}
		//if they are equally common, their inner order will call
		return o1.getWord().compareTo(o2.getWord()); 
	}
	
	
}
