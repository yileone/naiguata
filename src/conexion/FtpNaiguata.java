/**
 *
 */
package conexion;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import archivos.Archivo;

/**
 * @author yisheng
 *
 */
public class FtpNaiguata {

	private Archivo archivo;
	private final FTPClient cliente;
	private boolean login;

	/**
	 *
	 */
	public FtpNaiguata(Archivo archivo) {
		// TODO Auto-generated constructor stub
		setLogin(false);
		this.archivo = archivo;
		cliente = new FTPClient();
		try {
			System.out.println(
					"conectado:" + cliente.isConnected() + " servidor:" + archivo.getPropiedades().getServidorFtp());
			System.out.println(archivo.getPropiedades().getLogin());
			System.out.println(archivo.getPropiedades().getClave());
			cliente.connect(archivo.getPropiedades().getServidorFtp());
			System.out.println(
					"conectado:" + cliente.isConnected() + " servidor:" + archivo.getPropiedades().getServidorFtp());

			setLogin(cliente.login(archivo.getPropiedades().getLogin(), archivo.getPropiedades().getClave()));
			System.out.println("conectado:" + cliente.isConnected());
			System.out.println("conectado:" + isLogin());
		} catch (final Exception e) {

			System.out.println(e.getMessage());
		}

	}

	/**
	 * desloguearse del servidor y cerrar lla conexión con el servidor
	 */
	public void cerrar() {
		try {
			cliente.logout();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cliente.disconnect();
		} catch (final IOException e) {
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
	 * @return the cliente
	 */
	public FTPClient getCliente() {
		return cliente;
	}

	/**
	 * obtiene el archivo del servidor FTP y lo coloca en la carpeta de Entrada
	 *
	 * @return
	 */
	public FileOutputStream getFile() {
		FileOutputStream entrada = null;
		try {
			entrada = new FileOutputStream(getArchivo().getPropiedades().getArchivoEntrada());
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			cliente.retrieveFile("/" + getArchivo().getPropiedades().getCarpEntrada() + "/"
					+ getArchivo().getPropiedades().getArchivoEntrada(), entrada);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return entrada;

	}

	/**
	 * @return the login
	 */
	public boolean isLogin() {
		return login;
	}

	/**
	 * @param archivo
	 *            the archivo to set
	 */
	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	private void setLogin(boolean login) {
		this.login = login;
	}

}
