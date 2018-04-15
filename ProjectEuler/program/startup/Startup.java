package startup;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import taskFrameWork.backbone.TaskFrameWork;
import tasks.T001_MultiplesOfThreeAndFive;
import tasks.T002_EvenFibonacci;
import tasks.T003_LargestPrimefactor;
import tasks.T004_PalindromicNumbers;
import tasks.T005_EvenlyDivisable;
import tasks.T006_SumSquareDiff;
import tasks.T007_XPrime;
import tasks.T008_LargestProduct;
import tasks.T009_PythagoranTriplett;
import tasks.T010_SumOfPrimes;
import tasks.T011_LargestProductInGrid;
import tasks.T012_TriangleNumbers;
import tasks.T013_LargeSum;
import tasks.T014_LongestCollatzSequence;
import tasks.T015_LatticePaths;

@SuppressWarnings("javadoc")
public class Startup extends TaskFrameWork 
{
	private static final String lName = "Project Euler";
	
	public Startup(BufferedReader br) throws Exception {
		super(br);
		this.addList(lName);
		this.addTask(lName, new T001_MultiplesOfThreeAndFive(br));
		this.addTask(lName, new T002_EvenFibonacci(br));
		this.addTask(lName, new T003_LargestPrimefactor(br));
		this.addTask(lName, new T004_PalindromicNumbers(br));
		this.addTask(lName, new T005_EvenlyDivisable(br));
		this.addTask(lName, new T006_SumSquareDiff(br));
		this.addTask(lName, new T007_XPrime(br));
		this.addTask(lName, new T008_LargestProduct(br));
		this.addTask(lName, new T009_PythagoranTriplett(br));
		this.addTask(lName, new T010_SumOfPrimes(br));
		this.addTask(lName, new T011_LargestProductInGrid(br));
		this.addTask(lName, new T012_TriangleNumbers(br));
		this.addTask(lName, new T013_LargeSum(br));
		this.addTask(lName, new T014_LongestCollatzSequence(br));
		this.addTask(lName, new T015_LatticePaths(br));
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			Startup Su = new Startup(br);
			Su.simpleRun(new T015_LatticePaths(br));
//			Su.run();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
