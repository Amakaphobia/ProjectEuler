package toBeMoved;

import java.util.OptionalInt;

import boxes.Pair;

@SuppressWarnings("javadoc")
public class PythagronTriplette {

	private final Pair<Integer, Integer> AB;
	private final OptionalInt c;	
	private final int a2;
	private final int b2;
	private final int c2;
	
	public PythagronTriplette(int a, int b) {
		this.AB = a < b ? 
				new Pair<>(a,b) :
				new Pair<>(b,a);		
		this.a2 = a * a;
		this.b2 = b * b;
		this.c2 = this.b2 + this.a2;
		double sqrt = Math.sqrt(c2);
		boolean test = sqrt % 1 == 0;			
		this.c = test ? OptionalInt.of((int)sqrt) : OptionalInt.empty();
	}
	
	public int getCOrElse(int els) {
		return this.c.orElse(els);
	}
	
	public int getA() { return this.AB.getKey(); }
	public int getB() { return this.AB.getValue(); }
	public int getC() { return this.c.getAsInt(); }
	
	public boolean isValid() { 
		return this.c.isPresent()
			&&  this.getA() < this.getB()
			&&  this.getB() < this.getC(); 
	}
	
	@Override
	public int hashCode() {
		return this.getA() * this.getA() + this.getB() * this.getB();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj == this) return true;
		if(obj instanceof PythagronTriplette) {
			PythagronTriplette other = (PythagronTriplette) obj;
			return this.getA() == other.getA()
				&&  this.getB() == other.getB();
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.isValid() ? String.format("%s %s %s", this.getA(), this.getB(), this.c.getAsInt()) : "Not Valid";
	}
}
