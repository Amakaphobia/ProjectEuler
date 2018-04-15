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

If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

Find the sum of all the multiples of 3 or 5 below 1000.

 * @author hdaiv_000
 *
 */
public class T001_MultiplesOfThreeAndFive extends Task  
{
	@SuppressWarnings("javadoc")
	public T001_MultiplesOfThreeAndFive(BufferedReader br) {
		super(br);
		this.setName("Multiples of 3 and 5");
		this.setInfo("Prints the Sum of all Numbers divisable by 3 and 5 below a given Number");
		this.setPrint();
		this.addInput(HF ->	HF.chooseHandlerType(HandlerFactory.CONSOLEHANDLER)
				.setReader(this.br)
				.setParser(ParserFactory.PARSE_TO_INTEGER)
				.setValidator(ValidatorFactory.VALID_GT, 0)
				.setUserprompt("Bitte Obergrenze eingeben")
				.build());
	}

	@Override
	protected GenericContainer<?> compute() {
		return new GenericContainer<Integer>(
				IntStream.range(0, this.getValue(0))
					.filter(e -> e % 3 == 0 ||  e % 5 == 0)
					.sum());
	}

	@Override
	protected void buildOutput() {
		this.addAnswer(String.format("The Sum of the found Multiples of 3 and 5 is: %s", this.<Integer>getReturnValue()));
	}

}
