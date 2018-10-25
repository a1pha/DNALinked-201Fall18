import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Testing iterator
 * @author ola
 *
 */
public class TestIterator {
	private static IDnaStrand ourStrand;
	private static boolean isInitialized = false;
	private static final int NUM_NODES = 20000;
	
	public IDnaStrand getNewStrand(String s) {
		//return new StringStrand(s);
		return new LinkStrand(s);
		//return new StringBuilderStrand(s);
	}

	
	@Before
	public void setUp() throws Exception {
		if (isInitialized) return;
		
		isInitialized = true;
		ourStrand = getNewStrand("cgat");
		double start = System.nanoTime();
		for(int k=0; k < NUM_NODES; k++){
			ourStrand.append("cgat");
		}
		double end = System.nanoTime();
		double time = (end-start)/1e9;
		System.err.printf("DEBUG time to create %d size = %2.3f\n", ourStrand.size(),time);
	}

	@Test
	public void testRandomIndexes() {
		Random rand = new Random(12356);
		char[] arr = {'c','g','a','t'};
		
		for(int k=0; k < 30; k++) {
			int index = rand.nextInt((int)ourStrand.size());
			char ch = ourStrand.charAt(index);
			assertTrue(k+"-th index is "+index,ch == arr[index % 4]);
		}
	}
	
	@Test(timeout=100000)
	public void testPerformanceForward(){
		char[] arr = {'c','g','a','t'};
		ArrayList<Double> ftimes = new ArrayList<Double>();
		int max = (int) ourStrand.size()-1;
		int[] sizes = {max/4, max};
		
		// run through once to avoid initialization overhead
		for(int k=0; k < sizes[0]; k++) {
			char ch = ourStrand.charAt(k);
		}
		
		
		for(int ss=0; ss < sizes.length; ss++) {
			int size = sizes[ss];
			double start = System.nanoTime();
			for(int k=0; k < size; k++) {
				char ch = ourStrand.charAt(k);
			}
			double end = System.nanoTime();
			double time = (end-start)/1e9;
			ftimes.add(time);
		}
		double first = ftimes.get(0);
		System.out.printf("base time:\t%1.4f\tsize=%d\n", first,sizes[0]);
		for(int k=1; k < ftimes.size(); k++) {
			double f2 = ftimes.get(k);
			double observed = f2/first;
			double expected = sizes[k]*1.0/sizes[0];
			double ratio = observed/expected;
			System.out.printf("run time:\t%1.4f\tsize=%d\n", f2,sizes[k]);
			String label = String.format("%d with observed=%1.4f and expected=%1.4f",k,observed,expected);
			System.out.printf("experiment label: %s\n",label);
			assertTrue(label,ratio < 1.75);

		}
	}
}
