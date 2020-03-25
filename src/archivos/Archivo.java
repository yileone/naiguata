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
        if (copy(this.propiedades.getCarpEntrada(), this.propiedades.getCarpHistEnt()))
        {
        	borrardirectorio(new File(propiedades.getCarpEntrada()));
            	
        }
        if (copy(this.propiedades.getCarpSalida(), this.propiedades.getCarpHistSal()))
        {
        	borrardirectorio(new File(propiedades.getCarpSalida()));
        }
	}
	/**
	 * 
	 * @param directorio
	 */
	public void borrardirectorio(File directorio)
	{
		File f = directorio;
		if (f.isDirectory())
		{
			for (String file : f.list()) {
	            borrardirectorio(new File(f,file));
				            
	        }	
		}
		else
		{ 
			if (f.delete())
				  System.out.println("El archivo " + directorio + " ha sido borrado correctamente");
				else
				  System.out.println("El archivo " + directorio + " no se ha podido borrar");
		}	
	}
	
	/**
	 * 
	 * @param sourceLocation
	 * @param targetLocation
	 * @return
	 * @throws IOException
	 */
	private  boolean copy(File sourceLocation, File targetLocation) throws IOException {
        boolean resp =true;
		if (sourceLocation.isDirectory()) {
			resp=resp && copyDirectory(sourceLocation, targetLocation);
        } else {
        	resp=resp && copyFile(sourceLocation,targetLocation);
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
         File sourceLocation = new File(sSourceLocation);
         File targetLocation = new File(stargetLocation);
         boolean resp=true;
        if (sourceLocation.isDirectory()) { //Es directorio.
            
        	resp=resp&&  copyDirectory(sourceLocation, targetLocation);
        } else { //Es archivo.
            targetLocation = new File(fecha() +stargetLocation);
            resp=resp && copyFile(sourceLocation, targetLocation);
        }
        return resp;
    }
     /**
      * 
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
 * @return un valor si la copia fue efectiva
 * @throws IOException
 */
    private  boolean copyDirectory(File source, File target) throws IOException {
        boolean resp=true;
    	if (!target.exists()) {
            //No existe directorio destino, lo crea.
            target.mkdir();
        }
        for (String f : source.list()) {
            //Copia archivos de directorio fuente a destino.
            resp=resp&& copy(new File(source, f), new File(target, f));
        }
        return resp;
    }
/**
 * copia el archivo y lo renombra con su prefijo historico
 * @param source
 * @param target
 * @return boolean si fue efectiva la copia
 * @throws IOException
 */
    private  boolean copyFile(File source, File target) throws IOException {
 
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
    	    return true;
        }
    	catch (Exception e) {
    		System.out.println(e.getMessage());
    		return false;
		}
    }

}
