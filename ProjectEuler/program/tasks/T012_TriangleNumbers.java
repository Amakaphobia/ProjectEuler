package tasks;

import java.io.BufferedReader;
import java.util.stream.LongStream;

import boxes.GenericContainer;
import boxes.Pair;
import factories.HandlerFactory;
import factories.ParserFactory;
import factories.ValidatorFactory;
import taskFrameWork.backbone.Task;

@SuppressWarnings("javadoc")
/**
 * 

The sequence of triangle numbers is generated by adding the natural numbers. So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first ten terms would be:

1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

Let us list the factors of the first seven triangle numbers:

     1: 1
     3: 1,3
     6: 1,2,3,6
    10: 1,2,5,10
    15: 1,3,5,15
    21: 1,3,7,21
    28: 1,2,4,7,14,28

We can see that 28 is the first triangle number to have over five divisors.

What is the value of the first triangle number to have over five hundred divisors?

 * @author Dave
 *
 */
public class T012_TriangleNumbers extends Task {

	long lastOne;

	public T012_TriangleNumbers(BufferedReader br) {
		super(br);
		this.setName("Triangle Numbers");
		this.setInfo("Generates the first Trianglenumber with x divisors");
		this.setPrint();
		this.addInput(HF -> 
			HF.chooseHandlerType(HandlerFactory.CONSOLEHANDLER)
				.setUserprompt("Bitte Teiler Zahl angeben")
				.setValidator(ValidatorFactory.VALID_GT, 0)
				.setParser(ParserFactory.PARSE_TO_INTEGER)
				.setReader(br)
				.build());
		this.lastOne = 0L;
	}

	@Override
	protected GenericContainer<?> compute() {
		final int teilerZahl = this.getValue(0);
		final long erg =
			LongStream.iterate(76576500L, l -> l = l +1L)
				.map(this::getSum)
				.mapToObj(l -> new Pair<Long, Long>(l, this.findTeilerCount(l)))
				.filter(pair -> pair.getValue() >= teilerZahl)
				.findFirst()
				.orElse(new Pair<Long, Long>(-1L, 0L))
				.getKey();
		
		return new GenericContainer<>(erg);
	}
	
	private long getSum(long num) {
		this.lastOne += num;
		return this.lastOne;
	}
	
	private Long findTeilerCount(long num){
		return LongStream.rangeClosed(2, (int)Math.ceil(num/2))
					.filter(i -> num % i == 0)
					.count();
	}

	@Override
	protected void buildOutput() {
		this.addAnswer(this.getReturnValue().toString());

	}

}