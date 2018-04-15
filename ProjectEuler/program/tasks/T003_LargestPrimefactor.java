package tasks;

import java.io.BufferedReader;

import boxes.GenericContainer;
import factories.HandlerFactory;
import factories.ParserFactory;
import factories.ValidatorFactory;
import math.Primes;
import taskFrameWork.backbone.Task;

/**
 * 

The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?

 * @author hdaiv_000
 *
 */
public class T003_LargestPrimefactor extends Task 
{			
	@SuppressWarnings("javadoc")
	public T003_LargestPrimefactor(BufferedReader br) {
		super(br);
		this.setName("Largest PrimeFactor");
		this.setInfo("Finds the largest PrimeFactor of a given Number");
		this.setPrint();
		this.addInput(HF -> HF.chooseHandlerType(HandlerFactory.CONSOLEHANDLER)
				.setParser(ParserFactory.PARSE_TO_LONG)
				.setValidator(ValidatorFactory.VALID_NON)
				.setUserprompt("Enter Number: ")
				.setReader(this.br)
				.build());
	}

	@Override
	protected GenericContainer<?> compute() {
		return new GenericContainer<Long>(
				Primes.primFactorisation(this.getValue(0))
				.stream()
				.mapToLong(e -> e.longValue())
				.max()
				.orElse(0L));		
	}

	@Override
	protected void buildOutput() {
		this.addAnswer(
			String.format(
					"Largest found Primefactor is %s", 
					this.<Long>getReturnValue().toString()));
	}

}
