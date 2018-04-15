package tasks;

import java.io.BufferedReader;
import java.util.stream.IntStream;

import boxes.GenericContainer;
import factories.HandlerFactory;
import factories.ParserFactory;
import factories.ValidatorFactory;
import taskFrameWork.backbone.Task;
import toBeMoved.PythagronTriplette;

/**
 * 

A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
a2 + b2 = c2

For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.

 * @author Dave
 *
 */
public class T009_PythagoranTriplett extends Task {
	
	@SuppressWarnings("javadoc")
	public T009_PythagoranTriplett(BufferedReader br) {
		super(br);
		this.setName("Pythagoran Tripplettes");
		this.setInfo("Findet das Produkt von abc eines Pythagoran Tripplettes");
		this.setPrint();
		this.addInput(HF -> 
				HF.chooseHandlerType(HandlerFactory.CONSOLEHANDLER)
					.setUserprompt("Bitte gesuchten A+B+C wert eingeben")
					.setValidator(ValidatorFactory.VALID_GT, 0)
					.setParser(ParserFactory.PARSE_TO_INTEGER)
					.setReader(br)
					.build());
	}

	@Override
	protected GenericContainer<?> compute() {
		final int searchFor = this.getValue(0);
		final int prod = 
			IntStream.range(1, 1000)
				.mapToObj(a -> 
					IntStream.range(a+1, 1000)
						.mapToObj(b -> new PythagronTriplette(a, b))
						.filter(trip -> trip.isValid())
						.filter(trip -> trip.getC() + trip.getA() + trip.getB() == searchFor)
						.findFirst())
				.filter(OptTrip -> OptTrip.isPresent())
				.map(OptTrip -> 
					OptTrip.map(e -> 
						e.getA() * e.getB() * e.getC())
						.get())
				.findFirst()
				.orElse(0);
		
		return new GenericContainer<>(prod);
	}

	@Override
	protected void buildOutput() {
		this.addAnswer(this.<Integer>getReturnValue().toString());
	}
}
