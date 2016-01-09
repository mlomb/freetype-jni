package com.pvporbit.freetype;

import com.pvporbit.freetype.FreeType.FT_Size_Request_Type;

public class SizeRequest {

	private int type;
	private long width, height;
	private int horiResolution, vertResolution;

	public SizeRequest(FT_Size_Request_Type type, long width, long height, int horiResolution, int vertResolution) {
		this.type = type.ordinal();
		this.width = width;
		this.height = height;
		this.horiResolution = horiResolution;
		this.vertResolution = vertResolution;
	}

	public FT_Size_Request_Type getType() {
		return FT_Size_Request_Type.values()[type];
	}

	public long getWidth() {
		return width;
	}

	public long getHeight() {
		return height;
	}

	public int getVertResolution() {
		return vertResolution;
	}

	public int getHoriResolution() {
		return horiResolution;
	}
}