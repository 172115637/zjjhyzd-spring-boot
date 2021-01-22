package com.zjjhyzd.springboot.commons.tuple;

import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;

public final class Tuple0 extends AbstractList<Object> {

	static final Tuple0 SINGLETON = new Tuple0();

	private Tuple0() {

	}

	public <T> Tuple1<T> and(T t) {
		return new Tuple1<>(t);
	}
	
	@Override
	public Iterator<Object> iterator() {
		return Collections.emptyIterator();
	}

	@Override
	public Object get(int index) {
		throw new ArrayIndexOutOfBoundsException(index);
	}

	@Override
	public int size() {
		return 0;
	}

}