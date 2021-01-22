package com.zjjhyzd.springboot.commons.tuple;

public final class Tuples {

	public static Tuple0 of() {
		return Tuple0.SINGLETON;
	}

	public static <T0> Tuple1<T0> of(T0 t0) {
		return new Tuple1<>(t0);
	}

	public static <T0, T1> Tuple2<T0, T1> of(T0 t0, T1 t1) {
		return new Tuple2<>(t0, t1);
	}

	public static <T0, T1, T2> Tuple3<T0, T1, T2> of(T0 t0, T1 t1, T2 t2) {
		return new Tuple3<>(t0, t1, t2);
	}

	public static <T0, T1, T2, T3> Tuple4<T0, T1, T2, T3> of(T0 t0, T1 t1, T2 t2, T3 t3) {
		return new Tuple4<>(t0, t1, t2, t3);
	}

	public static <T0, T1, T2, T3, T4> Tuple5<T0, T1, T2, T3, T4> of(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4) {
		return new Tuple5<>(t0, t1, t2, t3, t4);
	}

	public static <T0, T1, T2, T3, T4, T5> Tuple6<T0, T1, T2, T3, T4, T5> of(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
		return new Tuple6<>(t0, t1, t2, t3, t4, t5);
	}

	public static <T0, T1, T2, T3, T4, T5, T6> Tuple7<T0, T1, T2, T3, T4, T5, T6> of(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
		return new Tuple7<>(t0, t1, t2, t3, t4, t5, t6);
	}

	public static <T0, T1, T2, T3, T4, T5, T6, T7> Tuple8<T0, T1, T2, T3, T4, T5, T6, T7> of(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
		return new Tuple8<>(t0, t1, t2, t3, t4, t5, t6, t7);
	}

	public static <T0, T1, T2, T3, T4, T5, T6, T7, T8> Tuple9<T0, T1, T2, T3, T4, T5, T6, T7, T8> of(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
		return new Tuple9<>(t0, t1, t2, t3, t4, t5, t6, t7, t8);
	}

}