package com.pvporbit.freetype;

public class Kerning {

	private final long x, y;

	public Kerning(long x, long y) {
		this.x = x;
		this.y = y;
	}

	public long getHorizontalKerning() {
		return x;
	}

	public long getVerticalKerning() {
		return y;
	}

	@Override
	public String toString() {
		return "Kerning(" + x + ", " + y + ")";
	}
}