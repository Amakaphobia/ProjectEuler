package tasks;

import java.io.BufferedReader;
import java.math.BigInteger;
import java.util.stream.IntStream;

import boxes.GenericContainer;
import factories.HandlerFactory;
import factories.ParserFactory;
import factories.ValidatorFactory;
import math.Primes;
import taskFrameWork.backbone.Task;

/**
 * 

The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

Find the sum of all the primes below two million.

 * @author Dave
 *
 */
public class T010_SumOfPrimes extends Task{

	@SuppressWarnings("javadoc")
	public T010_SumOfPrimes(BufferedReader br) {
		super(br);
		this.setName("SumOfPrimes");
		this.setInfo("Berechnet die Summe aller Primzahlen unter einer gegebenen Grenze");
		this.setPrint();
		this.addInput(HF -> 
				HF.chooseHandlerType(HandlerFactory.CONSOLEHANDLER)
					.setUserprompt("Bitte Obergrenze eingeben")
					.setValidator(ValidatorFactory.VALID_GT, 0)
					.setParser(ParserFactory.PARSE_TO_INTEGER)
					.setReader(br)
					.build());
	}

	@Override
	protected GenericContainer<?> compute() {
		final Primes p = new Primes();
		final int threshold = this.getValue(0);
		final BigInteger erg = 
			IntStream.range(0, threshold)
				.filter(p::isPrime)
				.mapToObj(BigInteger::valueOf)
				.reduce(BigInteger.ZERO, (carry, ele) -> carry = carry.add(ele))
				.subtract(BigInteger.ONE); // TODO after isPrime update delete that
		
		return new GenericContainer<>(erg);
	}

	@Override
	protected void buildOutput() {
		this.addAnswer(this.getReturnValue().toString());
		
	}


}
