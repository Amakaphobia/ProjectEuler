package tasks;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import boxes.GenericContainer;
import boxes.Pair;
import factories.HandlerFactory;
import factories.ParserFactory;
import factories.ValidatorFactory;
import taskFrameWork.backbone.Task;
import toBeMoved.CollatzSequence;
/**
 * 

The following iterative sequence is defined for the set of positive integers:

n -> n/2 (n is even)
n -> 3n + 1 (n is odd)

Using the rule above and starting with 13, we generate the following sequence:
13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1

It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. 
Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

Which starting number, under one million, produces the longest chain?

NOTE: Once the chain starts the terms are allowed to go above one million.

 * @author Dave
 *
 */
public class T014_LongestCollatzSequence extends Task {
	
	@SuppressWarnings("javadoc")
	public T014_LongestCollatzSequence(BufferedReader br) {
		super(br);
		this.setName("LongestCollatzSequence");
		this.setInfo("Finds the longest Collatzsequence with a given startingpoint");
		this.setPrint();
		this.addInput(HF -> 
			HF.chooseHandlerType(HandlerFactory.CONSOLEHANDLER)
				.setUserprompt("Plz enter top boundary for Collatzsequence")
				.setParser(ParserFactory.PARSE_TO_LONG)
				.setValidator(ValidatorFactory.VALID_GT, 0)
				.setReader(br)
				.build());
	}

	@Override
	protected GenericContainer<?> compute() {
		return new GenericContainer<List<Long>>(
				LongStream.rangeClosed(0, this.getValue(0))
//					laptops heapsize isnt big enough to save a million Lists =(
					.mapToObj(l -> new Pair<Long,Integer>(l, CollatzSequence.generate(l).size()))
//					.peek(System.out::println)
					.max((c,e) -> Integer.compare(c.getValue(), e.getValue()))
					.map(e -> CollatzSequence.generate(e.getKey()))
					.orElse(new ArrayList<Long>()));
	}

	@Override
	protected void buildOutput() {
		List<Long> ll = this.<List<Long>>getReturnValue(); 
		this.addAnswer("The longest found Sequence is:");
		this.addAnswer(ll.toString());
		this.addAnswer(String.format("w/ size: %s", ll.size()));
	}

}
