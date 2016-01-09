#include "com_pvporbit_freetype_FreeType.h"
#include "com_pvporbit_freetype_Utils.h"
#include <ft2build.h>
#include <sstream>
#include <string>
#include FT_FREETYPE_H

/* Please compile with Release Multithreaded */
/* And do not compile with 32 bits after year 2038 ;) */

/* --- Helper functions --- */

JNIEXPORT jobject JNICALL Java_com_pvporbit_freetype_Utils_newBuffer(JNIEnv *env, jclass obj, jint size) {
	return env->NewDirectByteBuffer((char*)malloc(size), size);
}

JNIEXPORT void JNICALL Java_com_pvporbit_freetype_Utils_fillBuffer(JNIEnv *env, jclass obj, jbyteArray bytes, jobject buffer, jint length) {
	unsigned char* dst = (unsigned char*)(buffer ? env->GetDirectBufferAddress(buffer) : 0);
	char* src = (char*)env->GetPrimitiveArrayCritical(bytes, 0);

	memcpy(dst, src, length);

	env->ReleasePrimitiveArrayCritical(bytes, src, 0);
}

JNIEXPORT void JNICALL Java_com_pvporbit_freetype_Utils_deleteBuffer(JNIEnv *env, jclass obj, jobject buffer) {
	char* b = (char*)(buffer ? env->GetDirectBufferAddress(buffer) : 0);
	free(b);
}


/* --- FreeType functions --- */
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Init_1FreeType(JNIEnv *env, jclass obj) {
	FT_Library lib = NULL;
	if (FT_Init_FreeType(&lib))
		return 0;
	return (jlong)lib;
}

JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Done_1FreeType(JNIEnv *env, jclass obj, jlong lib) {
	return FT_Done_FreeType((FT_Library)lib);
}

JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1New_1Memory_1Face(JNIEnv *env, jclass obj, jlong lib, jobject buffer, jint length, jint faceIndex) {
	char* data = (char*)(buffer ? env->GetDirectBufferAddress(buffer) : 0);
	FT_Face face = NULL;
	if (FT_New_Memory_Face((FT_Library)lib, (const FT_Byte*)data, length, faceIndex, &face))
		return 0;
	else return (jlong)face;
}

JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Set_1Pixel_1Sizes(JNIEnv *env, jclass obj, jlong face, jfloat width, jfloat heigth) {
	return FT_Set_Pixel_Sizes((FT_Face)face, width, heigth);
}

JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Load_1Char(JNIEnv *env, jclass obj, jlong face, jchar c, jint flags) {
	return FT_Load_Char((FT_Face)face, c, flags);
}

JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Done_1Face(JNIEnv *env, jclass obj, jlong face) {
	return FT_Done_Face((FT_Face)face);
}
JNIEXPORT jshort JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1ascender(JNIEnv *env, jclass obj, jlong face) {
	return ((FT_Face)face)->ascender;
}
JNIEXPORT jshort JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1descender(JNIEnv *env, jclass obj, jlong face) {
	return ((FT_Face)face)->descender;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1face_1flags(JNIEnv *env, jclass obj, jlong face) {
	return ((FT_Face)face)->face_flags;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1face_1index(JNIEnv *env, jclass obj, jlong face) {
	return ((FT_Face)face)->face_index;
}
JNIEXPORT jstring JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1family_1name(JNIEnv *env, jclass obj, jlong face) {
	return env->NewStringUTF(((FT_Face)face)->family_name);
}
JNIEXPORT jshort JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1heigth(JNIEnv *env, jclass obj, jlong face) {
	return ((FT_Face)face)->height;
}
JNIEXPORT jshort JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1max_1advance_1height(JNIEnv *env, jclass obj, jlong face) {
	return ((FT_Face)face)->max_advance_height;
}
JNIEXPORT jshort JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1max_1advance_1width(JNIEnv *env, jclass obj, jlong face) {
	return ((FT_Face)face)->max_advance_width;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1num_1faces(JNIEnv *env, jclass obj, jlong face) {
	return ((FT_Face)face)->num_faces;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1num_1glyphs(JNIEnv *env, jclass obj, jlong face) {
	return ((FT_Face)face)->num_glyphs;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1style_1flags(JNIEnv *env, jclass obj, jlong face) {
	return ((FT_Face)face)->style_flags;
}
JNIEXPORT jstring JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1style_1name(JNIEnv *env, jclass obj, jlong face) {
	return env->NewStringUTF(((FT_Face)face)->style_name);
}
JNIEXPORT jshort JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1underline_1position(JNIEnv *env, jclass obj, jlong face) {
	return ((FT_Face)face)->underline_position;
}
JNIEXPORT jshort JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1underline_1thickness(JNIEnv *env, jclass obj, jlong face) {
	return ((FT_Face)face)->underline_thickness;
}
JNIEXPORT jshort JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1units_1per_1EM(JNIEnv *env, jclass obj, jlong face) {
	return ((FT_Face)face)->units_per_EM;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1size(JNIEnv *env, jclass obj, jlong face) {
	return (jlong)(((FT_Face)face)->size);
}
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1Char_1Index(JNIEnv *env, jclass obj, jlong face, jint code) {
	return FT_Get_Char_Index((FT_Face)face, code);
}
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1HAS_1KERNING(JNIEnv *env, jclass obj, jlong face) {
	return FT_HAS_KERNING(((FT_Face)face));
}
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Select_1Size(JNIEnv *env, jclass obj, jlong face, jint strikeIndex) {
	return FT_Select_Size((FT_Face)face, strikeIndex);
}
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Set_1Char_1Size(JNIEnv *env, jclass obj, jlong face, jint char_width, jint char_height, jint horz_resolution, jint vert_resolution) {
	return FT_Set_Char_Size((FT_Face)face, char_width, char_height, horz_resolution, vert_resolution);
}
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Load_1Glyph(JNIEnv *env, jclass obj, jlong face, jint glyphIndex, jint loadFlags) {
	return FT_Load_Glyph((FT_Face)face, glyphIndex, loadFlags);
}

JNIEXPORT jlongArray JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1Kerning(JNIEnv *env, jclass org, jlong face, jchar left, jchar right, jint mode) {
	FT_Vector vector;
	if (FT_Get_Kerning((FT_Face)face, left, right, mode, &vector))
		return 0;

	jlongArray result = env->NewLongArray(2);
	if (result == NULL) // Out of memory
		return NULL;
	jlong fill[2];
	fill[0] = vector.x;
	fill[1] = vector.y;
	env->SetLongArrayRegion(result, 0, 2, fill);

	return result;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1KerningX(JNIEnv *env, jclass org, jlong face, jchar left, jchar right, jint mode) {
	FT_Vector vector;
	if (FT_Get_Kerning((FT_Face)face, left, right, mode, &vector))
		return 0;
	return vector.x;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1KerningY(JNIEnv *env, jclass org, jlong face, jchar left, jchar right, jint mode) {
	FT_Vector vector;
	if (FT_Get_Kerning((FT_Face)face, left, right, mode, &vector))
		return 0;
	return vector.y;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Get_1glyph(JNIEnv *env, jclass obj, jlong face) {
	return (jlong)((FT_Face)face)->glyph;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1linearHoriAdvance(JNIEnv *env, jclass ob, jlong glyph) {
	return ((FT_GlyphSlot)glyph)->linearHoriAdvance;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1linearVertAdvance(JNIEnv *env, jclass ob, jlong glyph) {
	return ((FT_GlyphSlot)glyph)->linearVertAdvance;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1advanceX(JNIEnv *env, jclass ob, jlong glyph) {
	return ((FT_GlyphSlot)glyph)->advance.x;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1advanceY(JNIEnv *env, jclass ob, jlong glyph) {
	return ((FT_GlyphSlot)glyph)->advance.y;
}
JNIEXPORT jlongArray JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1advance(JNIEnv *env, jclass ob, jlong glyph) {
	FT_Vector vector = ((FT_GlyphSlot)glyph)->advance;

	jlongArray result = env->NewLongArray(2);
	if (result == NULL) // Out of memory
		return NULL;
	jlong fill[2];
	fill[0] = vector.x;
	fill[1] = vector.y;
	env->SetLongArrayRegion(result, 0, 2, fill);

	return result;
}
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1format(JNIEnv *env, jclass obj, jlong glyph) {
	return ((FT_GlyphSlot)glyph)->format;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1bitmap(JNIEnv *env, jclass obj, jlong glyph) {
	return (jlong)(&((FT_GlyphSlot)glyph)->bitmap);
}
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1bitmap_1left(JNIEnv *env, jclass obj, jlong glyph) {
	return ((FT_GlyphSlot)glyph)->bitmap_left;
}
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1bitmap_1top(JNIEnv *env, jclass obj, jlong glyph) {
	return ((FT_GlyphSlot)glyph)->bitmap_top;
}
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Render_1Glyph(JNIEnv *env, jclass obj, jlong glyph, jint renderMode) {
	return FT_Render_Glyph((FT_GlyphSlot)glyph, (FT_Render_Mode)renderMode);
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1GlyphSlot_1Get_1metrics(JNIEnv *env, jclass obj, jlong glyph) {
	return (jlong)&((FT_GlyphSlot)glyph)->metrics;
}
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Bitmap_1Get_1width(JNIEnv *env, jclass obj, jlong bitmap) {
	return ((FT_Bitmap*)bitmap)->width;
}
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Bitmap_1Get_1rows(JNIEnv *env, jclass obj, jlong bitmap) {
	return ((FT_Bitmap*)bitmap)->rows;
}
JNIEXPORT jint JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Bitmap_1Get_1pitch(JNIEnv *env, jclass obj, jlong bitmap) {
	return ((FT_Bitmap*)bitmap)->pitch;
}
JNIEXPORT jshort JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Bitmap_1Get_1num_1grays(JNIEnv *env, jclass obj, jlong bitmap) {
	return ((FT_Bitmap*)bitmap)->num_grays;
}
JNIEXPORT jchar JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Bitmap_1Get_1palette_1mode(JNIEnv *env, jclass obj, jlong bitmap) {
	return ((FT_Bitmap*)bitmap)->palette_mode;
}
JNIEXPORT jchar JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Bitmap_1Get_1pixel_1mode(JNIEnv *env, jclass obj, jlong bitmap) {
	return ((FT_Bitmap*)bitmap)->pixel_mode;
}
JNIEXPORT jobject JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Bitmap_1Get_1buffer(JNIEnv *env, jclass obj, jlong bitmap) {
	FT_Bitmap* bmp = (FT_Bitmap*)bitmap;
	return env->NewDirectByteBuffer((void*)bmp->buffer, bmp->rows * bmp->width * abs(bmp->pitch));
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1width(JNIEnv *env, jclass obj, jlong glyphMetrics) {
	return ((FT_Glyph_Metrics*)glyphMetrics)->width;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1height(JNIEnv *env, jclass obj, jlong glyphMetrics) {
	return ((FT_Glyph_Metrics*)glyphMetrics)->height;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1horiAdvance(JNIEnv *env, jclass obj, jlong glyphMetrics) {
	return ((FT_Glyph_Metrics*)glyphMetrics)->horiAdvance;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1vertAdvance(JNIEnv *env, jclass obj, jlong glyphMetrics) {
	return ((FT_Glyph_Metrics*)glyphMetrics)->vertAdvance;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1horiBearingX(JNIEnv *env, jclass obj, jlong glyphMetrics) {
	return ((FT_Glyph_Metrics*)glyphMetrics)->horiBearingX;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1horiBearingY(JNIEnv *env, jclass obj, jlong glyphMetrics) {
	return ((FT_Glyph_Metrics*)glyphMetrics)->horiBearingY;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1vertBearingX(JNIEnv *env, jclass obj, jlong glyphMetrics) {
	return ((FT_Glyph_Metrics*)glyphMetrics)->vertBearingX;
}
JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Glyph_1Metrics_1Get_1vertBearingY(JNIEnv *env, jclass obj, jlong glyphMetrics) {
	return ((FT_Glyph_Metrics*)glyphMetrics)->vertBearingY;
}