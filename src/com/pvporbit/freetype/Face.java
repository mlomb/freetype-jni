package com.pvporbit.freetype;

import java.nio.ByteBuffer;

import com.pvporbit.freetype.FreeType.FT_Size_Request_Type;
import com.pvporbit.freetype.Utils.Pointer;

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

	public short getAscender() {
		return FreeType.FT_Face_Get_ascender(pointer);
	}

	public short getDescender() {
		return FreeType.FT_Face_Get_descender(pointer);
	}

	public long getFaceFlags() {
		return FreeType.FT_Face_Get_face_flags(pointer);
	}

	public long getFaceIndex() {
		return FreeType.FT_Face_Get_face_index(pointer);
	}

	public String getFamilyName() {
		return FreeType.FT_Face_Get_family_name(pointer);
	}

	public short getHeight() {
		return FreeType.FT_Face_Get_heigth(pointer);
	}

	public short getMaxAdvanceHeight() {
		return FreeType.FT_Face_Get_max_advance_height(pointer);
	}

	public short getMaxAdvanceWidth() {
		return FreeType.FT_Face_Get_max_advance_width(pointer);
	}

	public long getNumFaces() {
		return FreeType.FT_Face_Get_num_faces(pointer);
	}

	public long getNumGlyphs() {
		return FreeType.FT_Face_Get_num_glyphs(pointer);
	}

	public long getStyleFlags() {
		return FreeType.FT_Face_Get_style_flags(pointer);
	}

	public String getStyleName() {
		return FreeType.FT_Face_Get_style_name(pointer);
	}

	public short getUnderlinePosition() {
		return FreeType.FT_Face_Get_underline_position(pointer);
	}

	public short getUnderlineThickness() {
		return FreeType.FT_Face_Get_underline_thickness(pointer);
	}

	public short getUnitsPerEM() {
		return FreeType.FT_Face_Get_units_per_EM(pointer);
	}

	public int getCharIndex(int code) {
		return FreeType.FT_Get_Char_Index(pointer, code);
	}

	public boolean hasKerning() {
		return FreeType.FT_HAS_KERNING(pointer);
	}

	public boolean selectSize(int strikeIndex) {
		return FreeType.FT_Select_Size(pointer, strikeIndex);
	}

	public boolean setCharSize(int char_width, int char_height, int horz_resolution, int vert_resolution) {
		return FreeType.FT_Set_Char_Size(pointer, char_width, char_height, horz_resolution, vert_resolution);
	}

	public boolean loadGlyph(int glyphIndex, int flags) {
		return FreeType.FT_Load_Glyph(pointer, glyphIndex, flags);
	}

	public boolean loadChar(char c, int flags) {
		return FreeType.FT_Load_Char(pointer, c, flags);
	}

	public Kerning getKerning(char left, char right) {
		return getKerning(left, right, 0);
	}

	public Kerning getKerning(char left, char right, int mode) {
		long[] vector = FreeType.FT_Face_Get_Kerning(pointer, left, right, mode);
		if (vector.length == 0)
			return null;
		return new Kerning(vector[0], vector[1]);
	}

	public boolean setPixelSizes(float width, float height) {
		return FreeType.FT_Set_Pixel_Sizes(pointer, width, height);
	}

	public GlyphSlot getGlyphSlot() {
		long glyph = FreeType.FT_Face_Get_glyph(pointer);
		if (glyph <= 0)
			return null;
		return new GlyphSlot(glyph);
	}

	public Size getSize() {
		long size = FreeType.FT_Face_Get_size(pointer);
		if (size <= 0)
			return null;
		return new Size(size);
	}

	public boolean checkTrueTypePatents() {
		return FreeType.FT_Face_CheckTrueTypePatents(pointer);
	}

	public boolean setUnpatentedHinting(boolean newValue) {
		return FreeType.FT_Face_SetUnpatentedHinting(pointer, newValue);
	}

	public boolean referenceFace() {
		return FreeType.FT_Reference_Face(pointer);
	}

	public boolean requestSize(SizeRequest sr) {
		return FreeType.FT_Request_Size(pointer, sr);
	}
}