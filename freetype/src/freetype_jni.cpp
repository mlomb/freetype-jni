#include "com_pvporbit_freetype_FreeType.h"
#include "com_pvporbit_freetype_Utils.h"
#include <ft2build.h>
#include FT_FREETYPE_H

/* Please compile with Release Multithreaded */

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
JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Load_1Glyph(JNIEnv *env, jclass obj, jlong face, jint glyphIndex, jint loadFlags){
	return FT_Load_Glyph((FT_Face)face, glyphIndex, loadFlags);
}