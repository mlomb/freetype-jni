package com.mlomb.freetypejni;

import java.nio.ByteBuffer;

import com.mlomb.freetypejni.Utils.Pointer;

public class Outline extends Pointer {

	public Outline(long pointer) {
		super(pointer);
	}

	public int getNumPoints() {
    	return FreeType.FT_Outline_Get_points(pointer);
    }
}