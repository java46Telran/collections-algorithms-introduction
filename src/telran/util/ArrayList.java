package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;
public class ArrayList<T> implements List<T> {
private static final int DEFAULT_CAPACITY = 16;
private T[] array;
private int size;
@SuppressWarnings("unchecked")
public ArrayList(int capacity) {
	array = (T[]) new Object[capacity];
}
public ArrayList() {
	this(DEFAULT_CAPACITY);
}
private class ArrayListIterator implements Iterator<T> {
int currentInd = 0;
	@Override
	public boolean hasNext() {
		
		return currentInd < size;
	}

	@Override
	public T next() {
		
		return  array[currentInd++];
	}
	
}
	@Override
	public boolean add(T obj) {
		if (array.length == size) {
			array = Arrays.copyOf(array, size * 2);
		}
		array[size++] = obj;
		return true;
	}

	@Override
	public boolean remove(Object pattern) {
		//array reallocation isn't done
		//that is new array won't be created - essence of remove
		//to use System.arraycopy
		// size--
		boolean res = false;
		int index = indexOf(pattern);
		if (index >= 0) {
			res =true;
			removeByIndex(index);
		}
		
		return res;
	}
	
	private void removeByIndex(int index) {
		size--;
		System.arraycopy(array, index+1, array, index, size - index);
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int sizeOld = size;
		int i = 0;
		while (i < size) {
			if (predicate.test(array[i])) {
				removeByIndex(i);
			} else {
				i++;
			}
		}
		return sizeOld > size;
	}

	

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new ArrayListIterator();
	}

	@Override
	public boolean add(int index, T obj) {
		//if size == array.length you should do reallocation see the method add
				//if size < array.length new array won't be created - essence of the algorithm
		boolean res = false;
		if (index >= 0 && index <= size) {
			res = true;
			if (size == array.length) {
				array = Arrays.copyOf(array, size * 2);
			}
			System.arraycopy(array, index, array, index + 1, size - index);
			array[index] = obj;
			size++;
		}
		return res;
	}

	@Override
	public T remove(int index) {
		T res = null;
		if (checkExistingIndex(index)) {
			res = array[index];
			removeByIndex(index);
			
		}
		return res;
	}

	private boolean checkExistingIndex(int index) {
		
		return index >= 0 && index < size;
	}
	@Override
	public int indexOf(Object pattern) {
		int res = -1;
		for(int i = 0; i < size; i++) {
			if (array[i].equals(pattern)) {
				res = i;
				break;
			}
		}
		return res;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		int res = -1;
		for (int i = size - 1; i >= 0; i--) {
			if (array[i].equals(pattern)) {
				res = i;
				break;
			}
		}
		
		return res;
	}
	@Override
	public T get(int index) {
		
		return checkExistingIndex(index) ? array[index] : null;
	}

}
