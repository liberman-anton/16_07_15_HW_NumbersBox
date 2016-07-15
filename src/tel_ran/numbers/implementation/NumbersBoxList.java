package tel_ran.numbers.implementation;

import java.util.*;
import java.util.function.Predicate;

import tel_ran.numbers.interfaces.INumbersBox;

public abstract class NumbersBoxList implements INumbersBox {
protected List<Integer> numbers;
	@Override
	public Iterator<Integer> iterator() {
		return numbers.iterator();
	}

	@Override
	public void addNumber(int number) {
		numbers.add(number);
	}

	@Override
	public void removeNumber(int number) {
		numbers.remove((Integer)number);
	}

	@Override
	public INumbersBox findNumbers(Predicate<Integer> predicate) {
		NumbersBoxLinkedList res = new NumbersBoxLinkedList();
		for(int num : this.numbers)
			if(predicate.test(num))
				res.addNumber(num);
		return res;
	}

	@Override
	public void removeAllNumbers(int number) {
		Iterator<Integer> it = numbers.iterator();
		while(it.hasNext()){
			int num = it.next();
			if(num == number)
				it.remove();
		}
	}

	@Override
	public void removeAllNumbers(Predicate<Integer> predicate) {
		Iterator<Integer> it = numbers.iterator();
		while(it.hasNext()){
			int num = it.next();
			if(predicate.test(num))
				it.remove();
		}
	}

	@Override
	public void union(INumbersBox numbers) {
		for(int num : numbers)
			if(!this.numbers.contains(num))
				this.addNumber(num);
	}

	@Override
	public void intersection(INumbersBox numbers) {
		Iterator<Integer> it = this.numbers.iterator();
		while(it.hasNext()){
			int num1 = it.next();
			boolean flag = false;
			for(int num2 : numbers)
				if(num1 == num2){
					flag = true;
					break;
				}
			if(!flag) it.remove();
		}
	}

	@Override
	public void subtract(INumbersBox numbers) {
		this.removeRepeated();
		for(int num : numbers)
			if(this.numbers.contains(num))
				this.removeNumber(num);
	}

	@Override
	public void removeRepeated() {
		Iterator<Integer> it = numbers.iterator();
		List<Integer> list = new LinkedList<Integer>();
		while(it.hasNext()){
			int num = it.next();
			if(list.contains((Integer)num))
				it.remove();
			else
				list.add((Integer)num);
		}
	}

}
