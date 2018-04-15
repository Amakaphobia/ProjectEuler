package tasks;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import boxes.GenericContainer;
import factories.HandlerFactory;
import factories.ParserFactory;
import factories.ValidatorFactory;
import permutation.I_Permutable;
import permutation.PermutList;
import permutation.Permutation;
import taskFrameWork.backbone.Task;

/**
 *

Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down,
there are exactly 6 routes to the bottom right corner.

_View Info T015_

How many such routes are there through a 20×20 grid?

 * @author Dave
 *
 */
public class T015_LatticePaths extends Task {

	@SuppressWarnings("javadoc")
	public class SquarePath implements I_Permutable<List<Boolean>>{
		//true = rechts
		private I_Permutable<List<Boolean>> data;

		private int sizex;
		private int sizey;

		public SquarePath(int x, int y) {
			this.sizex = x;
			this.sizey = y;
			List<Boolean> ll= new ArrayList<>();
			for(int i = 0; i < this.sizex+this.sizey; i++)
				ll.add(i < this.sizex);
			this.data = new PermutList<>(ll);
		}

		public SquarePath(List<Boolean> data) {
			this.data = new PermutList<>(data);
		}

		@Override
		public List<Boolean> getData() {
			return this.data.getData();
		}

		@Override
		public I_Permutable<List<Boolean>> concat(I_Permutable<List<Boolean>> other) {
			return this.data.concat(other);
		}

		@Override
		public I_Permutable<List<Boolean>> getSet(int startInclusive, int endExclusive) {
			return this.data.getSet(startInclusive, endExclusive);
		}

		@Override
		public int getLaenge() {
			return this.data.getLaenge();
		}

	}


	@SuppressWarnings("javadoc")
	public T015_LatticePaths(BufferedReader br) {
		super(br);
		this.setInfo("Permutation of ways through a grid");
		this.setName("Ways");
		this.setPrint();
		this.addInput(HF ->
			HF.chooseHandlerType(HandlerFactory.CONSOLEHANDLER)
				.setReader(br)
				.setParser(ParserFactory.PARSE_TO_INTEGER)
				.setValidator(ValidatorFactory.VALID_GT,0)
				.setUserprompt("Bitte x eingeben")
				.build());
		this.addInput(HF ->
			HF.chooseHandlerType(HandlerFactory.CONSOLEHANDLER)
				.setReader(br)
				.setParser(ParserFactory.PARSE_TO_INTEGER)
				.setValidator(ValidatorFactory.VALID_GT,0)
				.setUserprompt("Bitte y eingeben")
				.build());
	}

	@Override
	protected GenericContainer<?> compute() {
		/*
		int count = this.<Integer>getValue(0) + this.<Integer>getValue(1);
		BigInteger ret = BigInteger.ONE;
		for(int i = count; i > 0; i--)
			ret = ret.multiply(BigInteger.valueOf(i));
		return new GenericContainer<BigInteger>(ret);
		*/
		SquarePath sp = new SquarePath(this.<Integer>getValue(0), this.<Integer>getValue(1));
		return new GenericContainer<>(
				Permutation.permList(sp.getData())
					.stream()
					.distinct()
					.count());

	}

	@Override
	protected void buildOutput() {
		this.addAnswer(String.format("Es wurden %s Wege gefunden", this.<Long>getReturnValue().toString()));
	}

}
