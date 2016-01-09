package examples;

import com.pvporbit.freetype.Face;
import com.pvporbit.freetype.FreeType;
import com.pvporbit.freetype.FreeType.FT_Size_Request_Type;
import com.pvporbit.freetype.GlyphMetrics;
import com.pvporbit.freetype.GlyphSlot;
import com.pvporbit.freetype.Library;
import com.pvporbit.freetype.SizeMetrics;
import com.pvporbit.freetype.SizeRequest;

public class Example1 {

	public static void main(String args[]) throws Exception {
		String fontPath = "res/OpenSans-Regular.ttf";

		/* --- Init FreeType --- */
		Library library = FreeType.newLibrary();
		if (library == null)
			throw new Exception("Error initializing FreeType.");
		int[] ver = library.getVersion();
		System.out.println("FreeType " + library.getVersionString() + " initialized.");
		/* --- Create face from .TTF --- */
		Face face = library.newFace(fontPath, 0);
		if (face == null)
			throw new Exception("Error creating face from file '" + fontPath + "'.");
		System.out.println("Face from file '" + fontPath + "' created.");
		System.out.println("TrueType: " + face.checkTrueTypePatents());

		/* --- Work with the face --- */
		// Change font size
		if (face.setPixelSizes(0, 30))
			throw new Exception("Error changing size.");

		System.out.println(face.getAscender());
		System.out.println(face.getCharIndex('b'));
		System.out.println(face.getDescender());
		System.out.println(face.getFaceFlags());
		System.out.println(face.getHeight());
		System.out.println(face.getMaxAdvanceHeight());
		System.out.println(face.getMaxAdvanceWidth());
		System.out.println(face.getNumGlyphs());
		System.out.println(face.getStyleFlags());
		System.out.println(face.getUnderlinePosition());
		System.out.println(face.getUnderlineThickness());
		System.out.println(face.getFaceIndex());
		System.out.println(face.getFamilyName());
		System.out.println(face.getNumFaces());
		System.out.println(face.getStyleName());
		System.out.println(face.getUnitsPerEM());
		System.out.println(face.hasKerning());
		System.out.println(face.loadChar('v', 0));
		GlyphSlot gs = face.getGlyphSlot();
		System.out.println(gs.getLinearHoriAdvance());
		System.out.println(gs.getLinearVertAdvance());
		System.out.println(gs.getAdvanceX());
		System.out.println(gs.getAdvanceY());
		System.out.println(gs.getFormat());
		System.out.println(gs.getBitmapLeft());
		System.out.println(gs.getBitmapTop());
		System.out.println("-- Metrics --");
		GlyphMetrics m = gs.getMetrics();
		System.out.println(m.getWidth());
		System.out.println(m.getHeight());
		System.out.println(m.getHoriAdvance());
		System.out.println(m.getHoriBearingX());
		System.out.println(m.getHoriBearingY());
		System.out.println(m.getVertAdvance());
		System.out.println(m.getVertBearingX());
		System.out.println(m.getVertBearingY());
		System.out.println("-- size metrics -- ");
		SizeMetrics sm = face.getSize().getMetrics();
		System.out.println(sm.getAscender());
		System.out.println(sm.getDescender());
		System.out.println(sm.getHeight());
		System.out.println(sm.getMaxAdvance());
		System.out.println(sm.getXppem());
		System.out.println(sm.getXScale());
		System.out.println(sm.getYppem());
		System.out.println(sm.getYScale());
		System.out.println("---------- Kerning -------------");
		for (char a = ' '; a < '~'; a++) {
			for (char b = ' '; b < '~'; b++) {
				int kern = (int) face.getKerning(a, b).getHorizontalKerning();
				if (kern != 0) {
					System.out.println(a + " - " + b + " = " + kern);
				}
			}
		}
		System.out.println("--------------------------------");

		System.out.println("--");
		System.out.println(
				face.requestSize(new SizeRequest(FT_Size_Request_Type.FT_SIZE_REQUEST_TYPE_NOMINAL, 50000000, 30, 100, 100)));
		System.out.println("Done");

		/* --- Delete face --- */
		if (face.delete())
			throw new Exception("Error deleting face from file '" + fontPath + "'.");
		System.out.println("Face from file '" + fontPath + "' deleted.");

		/* --- Destroy FreeType --- */
		if (library.delete())
			throw new Exception("Error deleting FreeType.");
		System.out.println("FreeType deleted.");
	}
}