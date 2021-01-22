package com.zjjhyzd.springboot.commons.tuple;

import java.util.AbstractList;

public final class Tuple1<T0> extends AbstractList<Object> {

	public T0 _0;

	public Tuple1(T0 _0) {
		this._0 = _0;
	}

	public <T> Tuple2<T0, T> and(T t) {
		return new Tuple2<>(_0, t);
	}

	public T0 get_0() {
		return _0;
	}

	public void set_0(T0 val) {
		_0 = val;
	}

	@Override
	public Object get(int index) {
		switch (index) {
		case 0:
			return _0;
		default:
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	@Override
	public int size() {
		return 1;
	}

}