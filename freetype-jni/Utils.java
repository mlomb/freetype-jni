package com.mlomb.freetypejni;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
	
	public static class Pointer {
		protected long pointer;

		public Pointer(long pointer) {
			this.pointer = pointer;
		}

		public long getPointer() {
			return pointer;
		}
	}

	public static byte[] loadFileToByteArray(String file) throws IOException { // If you know a fastest method, tell me. I will trust in Java 7.
		Path p = FileSystems.getDefault().getPath(file);
		byte[] fileData = Files.readAllBytes(p);
		return fileData;
	}

	/* Buffer helpers */

	public static native ByteBuffer newBuffer(int size);

	public static native void fillBuffer(byte[] bytes, ByteBuffer buffer, int length);

	public static native void deleteBuffer(ByteBuffer buffer);

}