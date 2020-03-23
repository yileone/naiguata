/**
 * 
 */
package conexion;

import java.io.*;

import org.apache.commons.net.ftp.*;

import archivos.*;

/**
 * @author arte
 *
 */
public class FtpNaiguata {

	
	private Archivo archivo;
	private FTPClient cliente;
	private   boolean login;
	/**
	 * 
	 */
	public FtpNaiguata(Archivo archivo) {
		// TODO Auto-generated constructor stub
		this.login=false;
		this.archivo= archivo;
		cliente= new FTPClient();
		try {

				cliente.connect(archivo.getPropiedades().getServidorFtp());
			   login = cliente.login(archivo.getPropiedades().getLogin(),archivo.getPropiedades().getClave());
			} 
		catch (IOException ioe) 
			{
				
				System.out.println(ioe.getMessage());
			}
		
	}
	
	
	
	
	/**
	 * desloguearse del servidor y cerrar lla conexión con el servidor
	 */
	public void cerrar() {
		try {
			cliente.logout();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cliente.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the archivo
	 */
	public Archivo getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

}
