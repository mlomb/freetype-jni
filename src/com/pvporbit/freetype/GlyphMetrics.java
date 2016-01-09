package com.pvporbit.freetype;

import com.pvporbit.freetype.Utils.Pointer;

public class GlyphMetrics extends Pointer {

	public GlyphMetrics(long pointer) {
		super(pointer);
	}

	public long getWidth() {
		return FreeType.FT_Glyph_Metrics_Get_width(pointer);
	}

	public long getHeight() {
		return FreeType.FT_Glyph_Metrics_Get_height(pointer);
	}

	public long getHoriAdvance() {
		return FreeType.FT_Glyph_Metrics_Get_horiAdvance(pointer);
	}

	public long getVertAdvance() {
		return FreeType.FT_Glyph_Metrics_Get_vertAdvance(pointer);
	}

	public long getHoriBearingX() {
		return FreeType.FT_Glyph_Metrics_Get_horiBearingX(pointer);
	}

	public long getHoriBearingY() {
		return FreeType.FT_Glyph_Metrics_Get_horiBearingY(pointer);
	}

	public long getVertBearingX() {
		return FreeType.FT_Glyph_Metrics_Get_vertBearingX(pointer);
	}

	public long getVertBearingY() {
		return FreeType.FT_Glyph_Metrics_Get_vertBearingY(pointer);
	}
}