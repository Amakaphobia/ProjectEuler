package toBeMoved;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * 
The following iterative sequence is defined for the set of positive integers:

n -> n/2 (n is even)
n -> 3n + 1 (n is odd)

Using the rule above and starting with 13, we generate the following sequence:
13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1

 * @author hdaiv_000
 *
 */
public class CollatzSequence 
{
	/**
	 * Static Method used to generate a CollatzSequence.
	 * @param start the long with which you want to initiate the sequence needs to be greater than 0
	 * @return a {@link List} of Longs of the CollatzSequence 
	 */
	public static List<Long> generate(long start) {
		if(start < 1) new ArrayList<>();
		
		final List<Long> sequence = new ArrayList<>();
		sequence.add(start);
		
		
		long current = start;
		while(current > 1) {
			current =
				current % 2 == 0 ?
				current / 2 :
				3 * current + 1;
			sequence.add(current);				
		}
			
		return sequence;
	}
	
	
}
