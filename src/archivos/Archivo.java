/**
 *
 */
package archivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

/**
 * ARchivo leido , de tipo entrada o salida.
 *
 * @author yisheng León Espinoza
 *
 *
 */
public class Archivo {

	private static String archivoDefecto = "archivoBcv";
	private File archivo;
	private char tipo;
	private Propiedades propiedades;

	/**
	 * carga el archivo según el archivo y las propiedades
	 *
	 * @param propiedades
	 * @param tipo
	 */
	public Archivo(Propiedades propiedades, char tipo) {
		// TODO Auto-generated constructor stub

		this(propiedades, archivoDefecto, tipo);
	}

	/**
	 * carga el archivo según el archivo y las propiedades
	 *
	 * @param propiedades
	 * @param archivo
	 * @param tipo
	 */
	public Archivo(Propiedades propiedades, String archivo, char tipo) {
		// TODO Auto-generated constructor stub
		this.archivo = new File(archivo);
		this.tipo = tipo;
		this.propiedades = propiedades;
	}

	/**
	 *
	 * @param directorio
	 */
	public void borrardirectorio(File directorio) {
		final File f = directorio;
		if (f.isDirectory()) {
			for (final String file : f.list()) {
				borrardirectorio(new File(f, file));

			}
		} else {
			if (f.delete()) {
				System.out.println("El archivo " + directorio + " ha sido borrado correctamente");
			} else {
				System.out.println("El archivo " + directorio + " no se ha podido borrar");
			}
		}
	}

	/**
	 *
	 * @param login
	 * @param clave
	 */
	public void cambioClaves(String login, String clave) {
		// TODO Auto-generated method stub
		propiedades.cambiarClaves(login, clave);
	}

	/**
	 *
	 * @param sourceLocation
	 * @param targetLocation
	 * @return
	 * @throws IOException
	 */
	private boolean copy(File sourceLocation, File targetLocation) throws IOException {
		boolean resp = true;
		if (sourceLocation.isDirectory()) {
			resp = resp && copyDirectory(sourceLocation, targetLocation);
		} else {
			resp = resp && copyFile(sourceLocation, targetLocation);
		}
		return resp;
	}

	/**
	 *
	 * @param sSourceLocation
	 * @param stargetLocation
	 * @return
	 * @throws IOException
	 */
	private boolean copy(String sSourceLocation, String stargetLocation) throws IOException {
		final File sourceLocation = new File(sSourceLocation);
		File targetLocation = new File(stargetLocation);
		boolean resp = true;
		if (sourceLocation.isDirectory()) { // Es directorio.

			resp = resp && copyDirectory(sourceLocation, targetLocation);
		} else { // Es archivo.
			targetLocation = new File(fecha() + stargetLocation);
			resp = resp && copyFile(sourceLocation, targetLocation);
		}
		return resp;
	}

	/**
	 *
	 * @param source
	 * @param target
	 * @return un valor si la copia fue efectiva
	 * @throws IOException
	 */
	private boolean copyDirectory(File source, File target) throws IOException {
		boolean resp = true;
		if (!target.exists()) {
			// No existe directorio destino, lo crea.
			target.mkdir();
		}
		for (final String f : source.list()) {
			// Copia archivos de directorio fuente a destino.
			resp = resp && copy(new File(source, f), new File(target, f));
		}
		return resp;
	}

	/**
	 * 
	 * @param carpeta
	 * @param archivo
	 * @return
	 * @throws IOException
	 */
	private boolean renombrarArchivo(String carpeta, String archivo) throws IOException {
		return renombrarArchivo(carpeta, archivo, fecha());
	}

	/**
	 * 
	 * @param carpeta
	 * @param archivo
	 * @param append  lo que se le agrega al archivo para cambiarle el nombre
	 * @return
	 * @throws IOException
	 */
	private boolean renombrarArchivo(String carpeta, String archivo, String append) throws IOException {

		File oldfile = new File(carpeta + archivo);
		File newfile = new File( carpeta + append +archivo);
		if (oldfile.renameTo(newfile)) {
			System.out.println("archivo renombrado:"+newfile.getName());
		} else {
			System.out.println("error al renombrar "+newfile.getName());
			return false;
		}
		return true;

	}

	/**
	 * copia el archivo y lo renombra con su prefijo historico
	 *
	 * @param source
	 * @param target
	 * @return boolean si fue efectiva la copia
	 * @throws IOException
	 */
	private boolean copyFile(File source, File target) throws IOException {

		try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream(target)) {
			final byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			final File parent = target.getParentFile();
			final String filename = fecha() + target.getName();
			target.renameTo(new File(parent, filename));
			return true;
		} catch (final Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 *
	 * @return fecha actual
	 */
	public String fecha() {
		String fecha = "";
		final Calendar c = Calendar.getInstance();
		fecha = fecha + c.get(Calendar.YEAR) + c.get(Calendar.MONTH) + c.get(Calendar.DATE) + "-"
				+ c.get(Calendar.HOUR_OF_DAY) + c.get(Calendar.MINUTE) + c.get(Calendar.SECOND);
		return fecha;
	}

	/**
	 * @return the archivo
	 */
	public File getArchivo() {
		return archivo;
	}

	/**
	 * @return the propiedades
	 */
	public Propiedades getPropiedades() {
		return propiedades;
	}

	/**
	 * @return the tipo
	 */
	public char getTipo() {
		return tipo;
	}

	
	/**
	 * solo pasa a historico care
	 * @throws IOException
	 */
	public void paseHistorico() throws IOException {
		paseHistorico(1);
	}

	/**
	 * 
	 * @param carpeta valor 1 solo carpeta local , 2 solo carpeta histórica 3 ambas
	 *                carpetas
	 * @throws IOException
	 */
	public void paseHistorico(int carpeta) throws IOException {

		if (carpeta == 3 || carpeta == 2) {
			if (copy(propiedades.getCarpDestino(), propiedades.getCarpHistDestino())) {
				borrardirectorio(new File(propiedades.getCarpDestino()));
				
				renombrarArchivo(propiedades.getCarpHistDestino(), propiedades.getArchivoDestino());
			}
		}

		if (carpeta == 3 || carpeta == 1)

		{
			if (copy(propiedades.getCarpLocal(), propiedades.getCarpHistLocal())) {
				borrardirectorio(new File(propiedades.getCarpLocal()));
				renombrarArchivo(propiedades.getCarpHistLocal(), propiedades.getArchivoLocal());
			    renombrarArchivo(propiedades.getCarpHistLocal(), propiedades.getArchivoLocal2());
				
				
			}
		}

	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	/**
	 * @param propiedades the propiedades to set
	 */
	public void setPropiedades(Propiedades propiedades) {
		this.propiedades = propiedades;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	
	
	/**
	 * 
	 * @param carpeta
	 * @param nombreArchivo
	 * @return
	 */
	public boolean verificarArchivo(String carpeta , String nombreArchivo) {
		File revisar;
		
			revisar = new File(carpeta, nombreArchivo);

		return revisar.exists();
	}

}
