package tasks;

import java.io.BufferedReader;
import java.math.BigInteger;
import java.util.stream.IntStream;

import boxes.GenericContainer;
import factories.HandlerFactory;
import factories.ParserFactory;
import factories.ValidatorFactory;
import taskFrameWork.backbone.Task;

/**
 * 

The four adjacent digits in the 1000-digit number that have the greatest product are 9 � 9 � 8 � 9 = 5832.

73167176531330624919225119674426574742355349194934
96983520312774506326239578318016984801869478851843
85861560789112949495459501737958331952853208805511
12540698747158523863050715693290963295227443043557
66896648950445244523161731856403098711121722383113
62229893423380308135336276614282806444486645238749
30358907296290491560440772390713810515859307960866
70172427121883998797908792274921901699720888093776
65727333001053367881220235421809751254540594752243
52584907711670556013604839586446706324415722155397
53697817977846174064955149290862569321978468622482
83972241375657056057490261407972968652414535100474
82166370484403199890008895243450658541227588666881
16427171479924442928230863465674813919123162824586
17866458359124566529476545682848912883142607690042
24219022671055626321111109370544217506941658960408
07198403850962455444362981230987879927244284909188
84580156166097919133875499200524063689912560717606
05886116467109405077541002256983155200055935729725
71636269561882670428252483600823257530420752963450

Find the thirteen adjacent digits in the 1000-digit number that have the greatest product. What is the value of this product?

 * @author Dave
 *
 */
public class T008_LargestProduct extends Task {
	/**Input*/
	private static String input;
	
	@SuppressWarnings("javadoc")
	public T008_LargestProduct(BufferedReader br) {
		super(br);
		input = this.buildInput();
		this.setName("Largest Product in textblock");
		this.setInfo("Scans a string of numbers for the largest Product of n adjacent numbers");
		this.setPrint();
		this.addInput(HF ->
				HF.chooseHandlerType(HandlerFactory.CONSOLEHANDLER)
					.setValidator(ValidatorFactory.VALID_IN_BETWEEN, new double[] {0d, input.length()})
					.setParser(ParserFactory.PARSE_TO_INTEGER)
					.setUserprompt("Wieviele zeichen sollen gescannt werden")
					.setReader(br)
					.build()
				);
	}

	@Override
	protected GenericContainer<?> compute() {
		final int toScan = this.getValue(0);
		final int laenge = input.length();
		
		final BigInteger summe = IntStream.range(0, laenge - toScan)
			.mapToObj(e -> input.substring(e, e + toScan).toCharArray())
			.map(ca -> {
				BigInteger bsum = BigInteger.ONE;				
				for(int i = 0; i < ca.length; i++) {
					bsum = bsum.multiply(new BigInteger(String.valueOf(ca[i])));
				}
				return bsum;
			})
			.max((p1, p2) -> p1.compareTo(p2))
			.orElse(BigInteger.ZERO);
		
		return new GenericContainer<>(summe);
	}

	@Override
	protected void buildOutput() {
		this.addAnswer(this.<BigInteger>getReturnValue().toString());
	}
	
	@SuppressWarnings("javadoc")
	private String buildInput() {
		StringBuilder strb = new StringBuilder("73167176531330624919225119674426574742355349194934");
		strb.append("96983520312774506326239578318016984801869478851843")
			.append("85861560789112949495459501737958331952853208805511")
			.append("12540698747158523863050715693290963295227443043557")
			.append("66896648950445244523161731856403098711121722383113")
			.append("62229893423380308135336276614282806444486645238749")
			.append("30358907296290491560440772390713810515859307960866")
			.append("70172427121883998797908792274921901699720888093776")
			.append("65727333001053367881220235421809751254540594752243")
			.append("52584907711670556013604839586446706324415722155397")
			.append("53697817977846174064955149290862569321978468622482")
			.append("83972241375657056057490261407972968652414535100474")
			.append("82166370484403199890008895243450658541227588666881")
			.append("16427171479924442928230863465674813919123162824586")
			.append("17866458359124566529476545682848912883142607690042")
			.append("24219022671055626321111109370544217506941658960408")
			.append("07198403850962455444362981230987879927244284909188")
			.append("84580156166097919133875499200524063689912560717606")
			.append("05886116467109405077541002256983155200055935729725")
			.append("71636269561882670428252483600823257530420752963450");		
		return strb.toString();
	}
}