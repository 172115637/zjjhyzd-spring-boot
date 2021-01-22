package com.zjjhyzd.springboot.commons.tuple;

import java.util.AbstractList;

public final class Tuple5<T0, T1, T2, T3, T4> extends AbstractList<Object> {

	public T0 _0;
	public T1 _1;
	public T2 _2;
	public T3 _3;
	public T4 _4;

	public Tuple5(T0 _0, T1 _1, T2 _2, T3 _3, T4 _4) {
		this._0 = _0;
		this._1 = _1;
		this._2 = _2;
		this._3 = _3;
		this._4 = _4;
	}

	public <T> Tuple6<T0, T1, T2, T3, T4, T> and(T t) {
		return new Tuple6<>(_0, _1, _2, _3, _4, t);
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

	public T2 get_2() {
		return _2;
	}

	public void set_2(T2 val) {
		_2 = val;
	}

	public T3 get_3() {
		return _3;
	}

	public void set_3(T3 val) {
		_3 = val;
	}

	public T4 get_4() {
		return _4;
	}

	public void set_4(T4 val) {
		_4 = val;
	}

	@Override
	public Object get(int index) {
		switch (index) {
		case 0:
			return _0;
		case 1:
			return _1;
		case 2:
			return _2;
		case 3:
			return _3;
		case 4:
			return _4;
		default:
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}

	@Override
	public int size() {
		return 5;
	}

}