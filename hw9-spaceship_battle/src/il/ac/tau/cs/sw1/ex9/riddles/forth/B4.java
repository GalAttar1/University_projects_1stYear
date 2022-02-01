package il.ac.tau.cs.sw1.ex9.riddles.forth;

import java.util.ArrayList;
import java.util.Iterator;

public class B4 implements Iterator<String> {
	private ArrayList<String> list;
	private int k;
	private Iterator<String> iter;
	
	public B4(String[] strings, int k) {
		this.list= new ArrayList<String>(); 
		for (int i=0;i<strings.length;i++) {
			this.list.add(strings[i]);
		}
		this.k=1;
		this.iter= this.list.iterator();       
	}


	@Override
	public boolean hasNext() {
		if (k<=9) {return true;}
		if (k==10) {return this.iter.hasNext();}
		return false; 
	}

	@Override
	public String next() {
		if (!this.iter.hasNext()) {
			if (k==10) {return null;}
			k++;
			this.iter= this.list.iterator();}
		return this.iter.next(); 
			
		}
	}
	
