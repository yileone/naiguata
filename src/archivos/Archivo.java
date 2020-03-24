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
import java.nio.channels.FileChannel;
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
	 * @param archivo
	 *            the archivo to set
	 */
	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	/**
	 * @param propiedades
	 *            the propiedades to set
	 */
	public void setPropiedades(Propiedades propiedades) {
		this.propiedades = propiedades;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	public  void paseHistorico() throws IOException {
        copy(this.propiedades.getCarpEntrada(), this.propiedades.getCarpHistEnt());
        copy(this.propiedades.getCarpSalida(), this.propiedades.getCarpHistSal());
        //borrardirectorio(this.propiedades.getCarpEntrada());
        //borrardirectorio(this.propiedades.getCarpSalida());
	}
	/**
	 * 
	 * @param directorio
	 */
	public void borrardirectorio(String directorio)
	{
		File f = new File(directorio);
		if (f.isDirectory())
		{
			for (String file : f.list()) {
	            borrardirectorio(file);
				            
	        }	
		}
		else
		{
			if (f.delete())
				  System.out.println("El directorio " + directorio + " ha sido borrado correctamente");
				else
				  System.out.println("El directorio " + directorio + " no se ha podido borrar");
		}
		
	}
	
	
	/**
	 * 
	 * @param sourceLocation
	 * @param targetLocation
	 * @throws IOException
	 */
	private  void copy(File sourceLocation, File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            copyDirectory(sourceLocation, targetLocation);
        } else {
            copyFile(sourceLocation,targetLocation);
        }
    }	

	
	
	
	/**
	 * 
	 * @param sSourceLocation
	 * @param stargetLocation
	 * @throws IOException
	 */
     private void copy(String sSourceLocation, String stargetLocation) throws IOException {         
         File sourceLocation = new File(sSourceLocation);
         File targetLocation = new File(stargetLocation);

        if (sourceLocation.isDirectory()) { //Es directorio.
            copyDirectory(sourceLocation, targetLocation);
        } else { //Es archivo.
            targetLocation = new File(fecha() +stargetLocation);
            copyFile(sourceLocation, targetLocation);
        }
    }
     /**
      * f
      * @return fecha actual
      */
     public String fecha() {
 		String fecha = "";
 		Calendar c=Calendar.getInstance();
 		fecha = fecha + 
 				c.get(Calendar.YEAR) + 
 				c.get(Calendar.MONTH)+
 				c.get(Calendar.DATE) + "-"+ 
 				c.get(Calendar.HOUR_OF_DAY) + 
 				c.get(Calendar.MINUTE)
 				+ c.get(Calendar.SECOND);
 		return fecha;
 	}
/**
 * 
 * @param source
 * @param target
 * @throws IOException
 */
    private  void copyDirectory(File source, File target) throws IOException {
        if (!target.exists()) {
            //No existe directorio destino, lo crea.
            target.mkdir();
        }
        for (String f : source.list()) {
            //Copia archivos de directorio fuente a destino.
            copy(new File(source, f), new File(target, f));
        }
    }
/**
 * 
 * @param source
 * @param target
 * @throws IOException
 */
    private  void copyFile(File source, File target) throws IOException {
 
    	try (
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(target)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            File parent = target.getParentFile();
    	    String filename = fecha()+target.getName();
    	    target.renameTo(new File(parent, filename));
    	   
        }
    }

}
