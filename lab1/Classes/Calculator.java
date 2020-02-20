import java.lang.Throwable;
public class Calculator implements ICalculator{
	public int add(int x, int y) {
		return x+y;
	};
	public  float divide(int x, int y) throws RuntimeException{
		if(y==0)
			throw new RuntimeException("divisor can't be zero");
		if(x==0)
			return (float) 0.0;
		return (float)x/y;
	};
	public static void main(String[] args) {
	}

}
