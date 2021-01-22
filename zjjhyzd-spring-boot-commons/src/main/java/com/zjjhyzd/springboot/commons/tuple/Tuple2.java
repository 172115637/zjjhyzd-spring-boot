package com.zjjhyzd.springboot.commons.tuple;

import java.util.AbstractList;

public final class Tuple2<T0, T1> extends AbstractList<Object> {

	public T0 _0;
	public T1 _1;

	public Tuple2(T0 _0, T1 _1) {
		this._0 = _0;
		this._1 = _1;
	}

	public <T> Tuple3<T0, T1, T> and(T t) {
		return new Tuple3<>(_0, _1, t);
	}

	public T0 get_0() {
		return _0;
	}

	public void set_0(T0 val) {
		_0 = val;
	}

	public T1 get_1() {
		return _1;
	}

	public void set_1(T1 val) {
		_1 = val;
	}

	@Override
	public Object get(int index) {
		switch (index) {
		case 0:
			return _0;
		case 1:
			return _1;
		default:
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	@Override
	public int size() {
		return 2;
	}

}