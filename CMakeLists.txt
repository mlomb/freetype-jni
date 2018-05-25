cmake_minimum_required(VERSION 3.11)
project(freetype-jni)

find_package(Java REQUIRED)
find_package(JNI  REQUIRED)

include(UseJava)

add_subdirectory(freetype2)

add_library(freetype-jni SHARED jni/freetype_jni.cpp)

set(NATIVE_HEADERS ${CMAKE_BINARY_DIR}/native)
set(CLASSES_BIN ${CMAKE_BINARY_DIR}/bin)

file(MAKE_DIRECTORY ${CLASSES_BIN})

add_custom_target(freetype-jni-headers
    COMMAND "${Java_JAVAC_EXECUTABLE}" -d "${CLASSES_BIN}" -h "${NATIVE_HEADERS}" "${CMAKE_CURRENT_SOURCE_DIR}/freetype-jni/*.java"
	DEPENDS freetype
)
add_dependencies(freetype-jni freetype-jni-headers)

include_directories(${JNI_INCLUDE_DIRS})
include_directories(${NATIVE_HEADERS})
include_directories(${freetype_SOURCE_DIR}/include)

target_link_libraries(freetype-jni freetype)

add_custom_command(TARGET freetype-jni POST_BUILD
    COMMAND cd "${CLASSES_BIN}" && "${Java_JAR_EXECUTABLE}" cvf ../freetype-jni.jar .
)
