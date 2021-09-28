package com.mlomb.freetypejni;

import com.mlomb.freetypejni.FreeTypeConstants.FT_Render_Mode;
import com.mlomb.freetypejni.Utils.Pointer;
import com.mlomb.freetypejni.Bitmap;
import com.mlomb.freetypejni.Outline;

public class GlyphSlot extends Pointer {

	public GlyphSlot(long pointer) {
		super(pointer);
	}

	public Bitmap getBitmap() {
		long bitmap = FreeType.FT_GlyphSlot_Get_bitmap(pointer);
		if (bitmap <= 0)
			return null;
		return new Bitmap(bitmap);
	}

	public Outline getOutline() {
    		long outline = FreeType.FT_GlyphSlot_Get_outline(pointer);
    		if (outline <= 0)
    			return null;
    		return new Outline(outline);
    	}

	public long getLinearHoriAdvance() {
		return FreeType.FT_GlyphSlot_Get_linearHoriAdvance(pointer);
	}

	public long getLinearVertAdvance() {
		return FreeType.FT_GlyphSlot_Get_linearVertAdvance(pointer);
	}

	public Advance getAdvance() {
		return FreeType.FT_GlyphSlot_Get_advance(pointer);
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

	public GlyphMetrics getMetrics() {
		long metrics = FreeType.FT_GlyphSlot_Get_metrics(pointer);
		if (metrics <= 0)
			return null;
		return new GlyphMetrics(metrics);
	}

	public boolean renderGlyph(FT_Render_Mode renderMode) {
		return FreeType.FT_Render_Glyph(pointer, renderMode.ordinal());
	}

	public static class Advance {

		private final int x, y;

		public Advance(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}
}