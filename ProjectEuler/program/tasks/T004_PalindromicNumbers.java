package tasks;

import java.io.BufferedReader;
import java.util.LinkedList;

import boxes.GenericContainer;
import taskFrameWork.backbone.Task;

/**
 * 

A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

Find the largest palindrome made from the product of two 3-digit numbers.

 * @author Dave
 *
 */
public class T004_PalindromicNumbers extends Task {

	@SuppressWarnings("javadoc")
	public T004_PalindromicNumbers(BufferedReader br) {
		super(br);
		this.setName("Palindrom Nummern");
		this.setInfo("Testet ob eine Nummer von beiden richtungen lesbar ist.");
		this.setPrint();
	}
	
	@SuppressWarnings("javadoc")
	private boolean checkForPalindrom(final char[] toCheck) {
		LinkedList<Character> ll = new LinkedList<>(); 
		
		for(char e : toCheck) {
			ll.add(e);
		}
		
		boolean toRet = false;
		
		while(true) {
			if(ll.size() == 0) {
				toRet = true;
				break;
			}
			
			if(ll.getFirst().equals(ll.getLast())) {
				if(ll.size() > 1) {
					ll.removeFirst();
					ll.removeLast();
				}else {
					ll.removeFirst();
				}
			}else{
				break;
			}
		}	
		return toRet;
	}

	@Override
	protected GenericContainer<?> compute() {
		char [] toCheck;
		int current; 
		int biggest = 0;
		
		for(int i = 100; i < 1000; i++) {
			for(int j = 100; j < 1000; j++) {
				current = i * j;
				toCheck = String.valueOf(current).toCharArray();
				if(this.checkForPalindrom(toCheck)) {
					biggest = Math.max(biggest, current);
				}
			}
		}
		
		return new GenericContainer<>(biggest);
	}

	@Override
	protected void buildOutput() {
		this.addAnswer(
			String.format("Die größte gefundene Palindromzahl ist: %s", this.<Integer>getReturnValue()));

	}

}
