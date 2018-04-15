package tasks;

import java.io.BufferedReader;
import java.util.stream.IntStream;

import boxes.GenericContainer;
import boxes.Pair;
import factories.HandlerFactory;
import factories.ParserFactory;
import factories.ValidatorFactory;
import taskFrameWork.backbone.Task;

/**
 * 

The sum of the squares of the first ten natural numbers is,
12 + 22 + ... + 102 = 385

The square of the sum of the first ten natural numbers is,
(1 + 2 + ... + 10)2 = 552 = 3025

Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 - 385 = 2640.

Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.

 * @author Dave
 *
 */
public class T006_SumSquareDiff extends Task {

	@SuppressWarnings("javadoc")
	public T006_SumSquareDiff(BufferedReader br) {
		super(br);
		this.setName("Differenz von Summe Und quadrat");
		this.setInfo("Berechnet die differenz der Summe der Quadrate und des Quadrates der summe");
		this.setPrint();
		
		this.addInput(HF ->
			HF.chooseHandlerType(HandlerFactory.CONSOLEHANDLER)
				.setUserprompt("Bitte Untergrenze angeben")
				.setValidator(ValidatorFactory.VALID_GT, 0)
				.setParser(ParserFactory.PARSE_TO_INTEGER)
				.setReader(br)
				.build());
		
		this.addInput(HF ->
			HF.chooseHandlerType(HandlerFactory.CONSOLEHANDLER)
				.setUserprompt("Bitte Obergrenze angeben")
				.setValidator(ValidatorFactory.VALID_GT,(int) this.getValue(0))
				.setParser(ParserFactory.PARSE_TO_INTEGER)
				.setReader(br)
				.build());
	}

	@Override
	protected GenericContainer<?> compute() {
		Pair<Integer, Integer> pair =
			IntStream.rangeClosed(this.getValue(0), this.getValue(1))
				.mapToObj(i -> new Pair<Integer, Integer>(i, i))
				.reduce(new Pair<>(0,0), (carry,ele) -> 
					//Summe | SummeSquares
					new Pair<Integer, Integer>(
						carry.getKey() + ele.getKey(),
						(ele.getValue() * ele.getValue()) + carry.getValue()));
		int erg = pair.getKey() * pair.getKey() - pair.getValue();
		
		return new GenericContainer<>(erg);
	}

	@Override
	protected void buildOutput() {
		this.addAnswer(this.getReturnValue().toString());
	}

}
