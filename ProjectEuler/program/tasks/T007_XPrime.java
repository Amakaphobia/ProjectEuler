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

By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.

What is the 10 001st prime number?

 * @author Dave
 *
 */
public class T007_XPrime extends Task {

	@SuppressWarnings("javadoc")
	public T007_XPrime(BufferedReader br) {
		super(br);
		this.setName("nte Prime");
		this.setInfo("Berechnet die xte Prime");
		this.setPrint();
		this.addInput(HF ->
			HF.chooseHandlerType(HandlerFactory.CONSOLEHANDLER)
				.setValidator(ValidatorFactory.VALID_GT, 0)
				.setParser(ParserFactory.PARSE_TO_INTEGER)
				.setReader(br)
				.setUserprompt("Wievielete Prime?")
				.build());
	}

	@Override
	protected GenericContainer<?> compute() {
		Primes p = new Primes();
		return new GenericContainer<>(
				p.getNumber(this.getValue(0)));
	}

	@Override
	protected void buildOutput() {
		this.addAnswer(this.getReturnValue().toString());
	}

}
