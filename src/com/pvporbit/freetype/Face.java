package com.pvporbit.freetype;

import java.nio.ByteBuffer;

public class Face extends Pointer {

	private ByteBuffer data; // Save to delete later

	public Face(long pointer) {
		super(pointer);
	}

	public Face(long pointer, ByteBuffer data) {
		super(pointer);
	}

	public boolean delete() {
		if (data != null)
			Utils.deleteBuffer(data);
		return FreeType.FT_Done_Face(pointer);
	}
}