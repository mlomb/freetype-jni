package com.pvporbit.freetype;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.pvporbit.freetype.Utils.Pointer;

public class Library extends Pointer {

	public Library(long pointer) {
		super(pointer);
	}

	public boolean delete() {
		return FreeType.FT_Done_FreeType(pointer);
	}

	public Face newFace(String file, int faceIndex) {
		try {
			return newFace(Utils.loadFileToByteArray(file), faceIndex);
		} catch (IOException e) {}
		return null;
	}

	public Face newFace(byte[] file, int faceIndex) {
		ByteBuffer buffer = Utils.newBuffer(file.length);
		buffer.order(ByteOrder.nativeOrder());
		buffer.limit(buffer.position() + file.length);
		Utils.fillBuffer(file, buffer, file.length);
		return newFace(buffer, faceIndex);
	}

	/** THE BYTEBUFFER MUST BE A DIRECT BYTE BUFFER CREATED WITH Utils.newBuffer AND FILLED WITH Utils.fillBuffer */
	public Face newFace(ByteBuffer file, int faceIndex) {
		long face = FreeType.FT_New_Memory_Face(pointer, file, file.remaining(), faceIndex);
		if (face <= 0) {
			Utils.deleteBuffer(file);
			return null;
		}
		return new Face(face, file);
	}
}