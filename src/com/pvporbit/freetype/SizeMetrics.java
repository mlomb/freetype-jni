package com.pvporbit.freetype;

import com.pvporbit.freetype.Utils.Pointer;

public class SizeMetrics extends Pointer {

	public SizeMetrics(long pointer) {
		super(pointer);
	}

	public long getAscender() {
		return FreeType.FT_Size_Metrics_Get_ascender(pointer);
	}

	public long getDescender() {
		return FreeType.FT_Size_Metrics_Get_descender(pointer);
	}

	public long getHeight() {
		return FreeType.FT_Size_Metrics_Get_height(pointer);
	}

	public long getMaxAdvance() {
		return FreeType.FT_Size_Metrics_Get_max_advance(pointer);
	}

	public long getXppem() {
		return FreeType.FT_Size_Metrics_Get_x_ppem(pointer);
	}

	public long getYppem() {
		return FreeType.FT_Size_Metrics_Get_y_ppem(pointer);
	}

	public long getXScale() {
		return FreeType.FT_Size_Metrics_Get_x_scale(pointer);
	}

	public long getYScale() {
		return FreeType.FT_Size_Metrics_Get_y_scale(pointer);
	}
}