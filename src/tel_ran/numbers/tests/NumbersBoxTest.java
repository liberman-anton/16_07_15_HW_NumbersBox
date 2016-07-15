package tel_ran.numbers.tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import tel_ran.numbers.implementation.NumbersBoxArrayList;
import tel_ran.numbers.implementation.NumbersBoxLinkedList;
import tel_ran.numbers.interfaces.INumbersBox;

public class NumbersBoxTest {
Integer[] array = {2,10,5,40,2};
Integer[] expectedOne = {10,5,40,2};
Integer[] expectedAll = {10,5,40};
Integer[] expectedEven = {2,10,40,2};
Integer[] expectedRemoveAllEven = {5};
INumbersBox box;
PredicateIsEven predicate = new PredicateIsEven();

	@Before
	public void setUp() throws Exception {
		box = new NumbersBoxArrayList();
		for(int number:array)
			box.addNumber(number);
	}

	@Test
	public void testRemoveOne() {
		box.removeNumber(2);
		testArray(expectedOne);
	}
	
	private void testArray(Integer[] expected) {
		LinkedList<Integer> list = new LinkedList<>();
		for(int number:box)
			list.add(number);
		assertArrayEquals(expected,list.toArray(new Integer[list.size()]));
	}

	@Test
	public void testRemoveAll() {
		box.removeAllNumbers(2);
		testArray(expectedAll);
	}
	
	@Test
	public void testFindNumbers() {
		LinkedList<Integer> list = new LinkedList<>();
		INumbersBox boxFind = box.findNumbers(predicate);
		for(int number : boxFind)
			list.add(number);
		assertArrayEquals(expectedEven,list.toArray(new Integer[list.size()]));
	}
	
	@Test
	public void testRemoveAllPredicate() {
		box.removeAllNumbers(predicate);
		testArray(expectedRemoveAllEven);
	}
	
	@Test
	public void testUnion() {
		box.union(box.findNumbers(predicate));
		testArray(array);
		NumbersBoxLinkedList box2 = new NumbersBoxLinkedList();
		box2.addNumber(21);
		box2.addNumber(35);
		box2.addNumber(2);
		Integer[] exp = {2,10,5,40,2,21,35};
		box.union(box2);
		testArray(exp);
	}
	
	@Test
	public void testIntersection() {
		NumbersBoxLinkedList box2 = new NumbersBoxLinkedList();
		box2.addNumber(21);
		box2.addNumber(35);
		box2.addNumber(2);
		//box2.addNumber(10);
		box2.addNumber(40);
		Integer[] exp = {2,40,2};
		box.intersection(box2);
		testArray(exp);
	}
	
	@Test
	public void testSubstraction() {
		NumbersBoxLinkedList box2 = new NumbersBoxLinkedList();
		box2.addNumber(21);
		box2.addNumber(35);
		box2.addNumber(40);
		Integer[] exp = {2,10,5};
		box.subtract(box2);
		testArray(exp);
		
		box2.addNumber(10);
		Integer[] exp1 = {2,5};
		box.subtract(box2);
		testArray(exp1);
		
		box2.addNumber(2);
		Integer[] exp2 = {5};
		box.subtract(box2);
		testArray(exp2);
	}
}
