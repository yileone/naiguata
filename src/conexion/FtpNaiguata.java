/**
 *
 */
package conexion;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
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
		setLogin(false);
		this.archivo = archivo;
		cliente = new FTPClient();
		try {
			cliente.connect(archivo.getPropiedades().getServidorFtp());
			setLogin(cliente.login(archivo.getPropiedades().getLogin(), archivo.getPropiedades().getClave()));
		} catch (final Exception e) {

			System.out.println(e.getMessage());
		}

	}

	/**
	 * desloguearse del servidor y cerrar lla conexión con el servidor
	 */
	public void cerrar() {
		try {
			if (cliente.isConnected()) {
				cliente.logout();
				cliente.disconnect();
			}
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
	public void getFile() {
		cliente.enterLocalPassiveMode();
		try {
			cliente.setFileType(FTP.BINARY_FILE_TYPE);
		} catch (final IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			final String remoteFile = archivo.getPropiedades().getArchivoEntrada();
			final String directorio = archivo.getPropiedades().getCarpEntrada();
			final String nombreArchivo = archivo.getPropiedades().getArchivoEntrada();
			File downloadFile;
			if (directorio.isEmpty() || directorio.equals("") || directorio == null) {
				downloadFile = new File(nombreArchivo);
			} else {
				downloadFile = new File(directorio + "/" + nombreArchivo);
			}

			final OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
			boolean success;
			success = false;
			try {
				success = cliente.retrieveFile(remoteFile, outputStream);
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			outputStream.close();

			if (success) {
				System.out.println("File #1 has been downloaded successfully.");
			}

		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
