package com.pvporbit.freetype;

public class GlyphSlot extends Pointer {

	public GlyphSlot(long pointer) {
		super(pointer);
	}

	public long getLinearHoriAdvance() {
		return FreeType.FT_GlyphSlot_Get_linearHoriAdvance(pointer);
	}

	public long getLinearVertAdvance() {
		return FreeType.FT_GlyphSlot_Get_linearVertAdvance(pointer);
	}

	public long getAdvanceX() {
		return FreeType.FT_GlyphSlot_Get_advanceX(pointer);
	}

	public long getAdvanceY() {
		return FreeType.FT_GlyphSlot_Get_advanceY(pointer);
	}

	public int getFormat() {
		return FreeType.FT_GlyphSlot_Get_format(pointer);
	}

	public int getBitmapLeft() {
		return FreeType.FT_GlyphSlot_Get_bitmap_left(pointer);
	}

	public int getBitmapTop() {
		return FreeType.FT_GlyphSlot_Get_bitmap_top(pointer);
	}
}