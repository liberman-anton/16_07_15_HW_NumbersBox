package tel_ran.numbers.tests;
import java.util.function.Predicate;

public class PredicateIsEven implements Predicate<Integer>{

	@Override
	public boolean test(Integer num) {
		
		return num%2 == 0;
	}

}
