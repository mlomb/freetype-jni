#include "com_pvporbit_freetype_FreeType.h"
#include <ft2build.h>
#include FT_FREETYPE_H

/* Please compile with Release Multithreaded */

JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Init_1FreeType(JNIEnv *env, jclass obj) {
	FT_Library lib = NULL;
	if (FT_Init_FreeType(&lib))
		return 0;
	return (jlong)lib;
}

JNIEXPORT jlong JNICALL Java_com_pvporbit_freetype_FreeType_FT_1New_1Memory_1Face(JNIEnv *env, jclass obj, jlong lib, jobject buffer, jint length, jint faceIndex) {
	char* data = (char*)(buffer ? env->GetDirectBufferAddress(buffer) : 0);
	FT_Face face = NULL;
	if (FT_New_Memory_Face((FT_Library)lib, (const FT_Byte*)data, length, faceIndex, &face))
		return 0;
	else return (jlong)face;
}

JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Set_1Pixel_1Sizes(JNIEnv *env, jclass obj, jlong face, jfloat width, jfloat heigth) {
	return !FT_Set_Pixel_Sizes((FT_Face)face, width, heigth);
}

JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Load_1Char(JNIEnv *env, jclass obj, jlong face, jchar c, jint flags) {
	return !FT_Load_Char((FT_Face)face, c, flags);
}

JNIEXPORT jboolean JNICALL Java_com_pvporbit_freetype_FreeType_FT_1Done_1Face(JNIEnv *env, jclass obj, jlong face) {
	return !FT_Done_Face((FT_Face)face);
}
/* Helpers */

JNIEXPORT jobject JNICALL Java_com_pvporbit_freetype_FreeType_newBuffer(JNIEnv *env, jclass obj, jint size) {
	return env->NewDirectByteBuffer((char*)malloc(size), size);
}
JNIEXPORT void JNICALL Java_com_pvporbit_freetype_FreeType_fillBuffer(JNIEnv *env, jclass obj, jbyteArray bytes, jobject buffer, jint length) {
	unsigned char* dst = (unsigned char*)(buffer ? env->GetDirectBufferAddress(buffer) : 0);
	char* src = (char*)env->GetPrimitiveArrayCritical(bytes, 0);

	memcpy(dst, src, length);

	env->ReleasePrimitiveArrayCritical(bytes, src, 0);
}
JNIEXPORT void JNICALL Java_com_pvporbit_freetype_FreeType_deleteBuffer(JNIEnv *env, jclass obj, jobject buffer) {
	char* b = (char*)(buffer ? env->GetDirectBufferAddress(buffer) : 0);
	free(b);
}