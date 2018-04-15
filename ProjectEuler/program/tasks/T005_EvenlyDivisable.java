package tasks;

import java.io.BufferedReader;
import java.util.stream.IntStream;

import boxes.GenericContainer;
import factories.HandlerFactory;
import factories.ParserFactory;
import factories.ValidatorFactory;
import taskFrameWork.backbone.Task;

/**
 * 

2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?

 * @author Dave
 *
 */
public class T005_EvenlyDivisable extends Task 
{

	@SuppressWarnings("javadoc")
	public T005_EvenlyDivisable(BufferedReader br) {
		super(br);
		this.setName("Gerade Teilbar");
		this.setInfo("Findet die kleinste Zahl die durch eine Angebene Reihe von Zahlen Teilbar ist.");
		this.setPrint();
		
		this.addInput(HF ->
				HF.chooseHandlerType(HandlerFactory.CONSOLEHANDLER)
					.setValidator(ValidatorFactory.VALID_GE, 1)
					.setReader(br)
					.setParser(ParserFactory.PARSE_TO_INTEGER)
					.setUserprompt("Bitte Untergrenze angeben")
					.build());
		
		this.addInput(HF ->
				HF.chooseHandlerType(HandlerFactory.CONSOLEHANDLER)
					.setValidator(ValidatorFactory.VALID_GT,(int)this.getValue(0))
					.setParser(ParserFactory.PARSE_TO_INTEGER)
					.setReader(br)
					.setUserprompt("Bitte Oberrenze angeben")
					.build());
	}

	@Override
	protected GenericContainer<?> compute() {
		int found =		
			IntStream.iterate(1, i -> i = i+ 1)
				.parallel()
				.filter(i -> 
					! IntStream.rangeClosed(this.getValue(0), this.getValue(1))
						.filter(j -> i % j != 0)
						.findAny()
						.isPresent())
				.findFirst()
				.orElse(0);	
		
		
		return new GenericContainer<Integer>(found);
	}

	@Override
	protected void buildOutput() {
		this.addAnswer(String.format("Die kleinste gefundene Nummer ist: %s", this.<Integer>getReturnValue()));
	}	
}
