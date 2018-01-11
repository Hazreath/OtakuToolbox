package otakutoolbox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;



/**
 * OtakuToolBox est un utilitaire destiné à assembler
 * une série de pages d'un manga en un seul et unique 
 * fichier PDF.
 * Il constitue aussi, entre autres, une remise en forme
 * 
 * TODO :
 * - Détecter la taille d'une page, si double page afficher en paysage
 * - Interface rudimentaire
 * @author Hazreath
 * @version 4.20
 */
public class OtakuToolbox {
	// YOUR SCAN DIRECTORY GOES THERE
	public static final String a_scan = "E:\\Scans";
	
	// YOUR PDF GOES THERE
	public static final String a_manga = "E:\\TESTS\\Pokemon Adventures [1-142].pdf";
	
	/**
	 * Affiche les fichiers contenu dans arg
	 * @param arg dossier à vérif le contenu
	 * #Test
	 */
	public static void afficher(File[] arg) {
		
		for (File elt : arg) {
			System.out.println(elt.getName());
		}
	}
	
	
	/**
	 * Main
	 * @param args unused
	 * @throws IOException si err lecture fichier
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("Ohayo gozaimasu ! :D");
		System.out.println("OTAKUTOOLBOX V4.20 (fortwaini)");
		// Parcours des dossiers courant du dossier scan
		File scan = new File(a_scan);
		
		
		System.out.println("Dossier de scan : " + scan.getName());
		File[] fils = scan.listFiles();
		//afficher(fils);

		// Pdf final
		PDDocument manga = new PDDocument();
		manga.addPage(new PDPage());
		
		
		// Traitement =======================
		String adrImg;
		
		for (File dossier : scan.listFiles()) {
			// On traite les dossiers d'images
			if (dossier.isDirectory()) {
				System.out.println(dossier.getName() + " ...");
				
				File[] images = dossier.listFiles();
				PDPage page;
				PDImageXObject img;
				PDPageContentStream content;
				// parcours des images de chaque dossier et traitement
				for (File image : images) {
					adrImg = image.getPath();
					page = new PDPage();
					manga.addPage(page);
					img = PDImageXObject.createFromFile(adrImg, manga); // ajout de l'img à la page
					content = new PDPageContentStream(manga, page);
					// A4 595 pixels x 842 pixels
					content.drawImage(img, 20,20,590.0F*0.9F,837.0F*0.9F); // dessin img
					content.close();
					manga.save(a_manga);
					
				}
			}
			
		}
		
		// Fin du programme
		System.out.println("Fini ! :D");
		System.out.println("Nombre de pages générées : " + manga.getNumberOfPages());
		System.out.println("Ja Atode ! :D");
		manga.removePage(0);
		manga.save(a_manga);
		manga.close();
		
		
	}
}
