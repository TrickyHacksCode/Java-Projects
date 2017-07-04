import datagen.FichaUtente;
import datagen.GeradorDeFichas;

public class Tester {
	public static void main(String[] args) {
		GeradorDeFichas gdf = new GeradorDeFichas();
		for (int i = 0; i < 500; i++) {
			FichaUtente fu = gdf.next();
			System.out.print(fu.urgencia());
			System.out.println(fu+"    " + i);
		}
	}
}
