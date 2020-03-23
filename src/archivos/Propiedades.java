/**
 * 
 */
package archivos;

import com.sun.xml.internal.fastinfoset.sax.*;

/**
 * Clase que procesa archivo tipo Properties
 * @author yisheng 
 *
 */
public class Propiedades {

	static private String archivoDefecto="FtpCobis.properties";
	Properties propiedades = new Properties();
	/**
	 * Constructor que trae el archivo por defecto
	 */
	public Propiedades() {
		// TODO Auto-generated constructor stub
		this(archivoDefecto);
	}

	/**
	 * carga los parametros del archivo otorgado
	 * @param archivo
	 */
	public Propiedades(String archivo) {
		// TODO Auto-generated constructor stub
	}

	
	
}
