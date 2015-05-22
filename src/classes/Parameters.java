package classes;

import com.ib.client.Contract;

public class Parameters {
	public int numPos;
	public String[] symbols;
	public Contract[] contracts;
	
	public Parameters() {
		
	}
	
	public Parameters(int num) {
		numPos = num;
		symbols = new String[numPos];
	}
}
