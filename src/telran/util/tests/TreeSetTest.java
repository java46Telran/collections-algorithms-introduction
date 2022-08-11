package telran.util.tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.TreeSet;

public class TreeSetTest extends SetTests {
    TreeSet<Integer> tree;
	@Override
	protected Collection<Integer> createCollection() {
		
		return new TreeSet<Integer>();
	}
	@Override
	@BeforeEach
	void setUp() {
		this.setUp();
		tree = (TreeSet<Integer>)collection;
	}
	@Test
	@Override
	void toArrayTest() {
		
	}
	@Test
	void firstTest() {
		assertEquals((Integer)(-5), tree.first());
	}
	@Test
	void lastTest() {
		assertEquals((Integer)(-5), tree.first());
	}

}
