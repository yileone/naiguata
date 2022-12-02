/**
 * 
 */
package conexion;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPSClient;

import archivos.Archivo;

/**
 * @author 醫生 (yisheng liang Espinoza)
 *
 */
public class SftpNaiguata {
	private Archivo archivo;
	private final FTPSClient cliente;
	private boolean login;
	/**
	 * 
	 */
	public SftpNaiguata(Archivo archivo) {
		// TODO Auto-generated constructor stub
		
	
			setLogin(false);
			this.archivo = archivo;
			cliente = new FTPSClient();
			try {
				cliente.connect(archivo.getPropiedades().getServidorFtp(),archivo.getPropiedades().getServidorPuerto());
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
	public FTPSClient getCliente() {
		return cliente;
	}

	/**
	 * obtiene el archivo del servidor FTP y lo coloca en la carpeta de Entrada
	 *
	 * @return
	 */
	public boolean getFile() {
		final String directorio = archivo.getPropiedades().getCarpDestino();
		final String nombreArchivo = archivo.getPropiedades().getArchivoDestino();
		return getFile(directorio, nombreArchivo);
	}

	public boolean getFile(String directorio, String nombreArchivo) {
		boolean success;
		cliente.enterLocalPassiveMode();
		try {
			cliente.setFileType(FTP.BINARY_FILE_TYPE);
		} catch (final IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		try {
			final String remoteFile = nombreArchivo;
			File downloadFile;
			if (directorio.isEmpty() || directorio.equals("") || directorio == null) {
				downloadFile = new File(nombreArchivo);
			} else {
				downloadFile = new File(directorio + "/" + nombreArchivo);
			}

			final OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));

			success = false;
			try {
				success = cliente.retrieveFile(remoteFile, outputStream);
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				outputStream.close();
				return false;
			}
			outputStream.close();

			if (success) {
				System.out.println("Archivo " + nombreArchivo + " ha sido descargado exitosamente.");
				return true;
			}

		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return success;

	}

	/**
	 * @return the login
	 */
	public boolean isLogin() {
		return login;
	}

	/**
	 * envia el archivo al servidor FTP y que se encuetnra la carpeta de Salida
	 *
	 */

	public boolean putFile() {

		final String directorio = archivo.getPropiedades().getCarpLocal();
		final String nombreArchivo = archivo.getPropiedades().getArchivoLocal();
		return putFile(directorio, nombreArchivo);
	}

	public boolean putFile(String directorio, String nombreArchivo) {
		// final String remote_working_dir_path = "/ruta/remota/del/ftp/";
		File uploadFile;
		if (directorio.isEmpty() || directorio.equals("") || directorio == null) {
			uploadFile = new File(nombreArchivo);
		} else {
			uploadFile = new File(directorio + "/" + nombreArchivo);
		}
		FileInputStream fis;
		try {
			fis = new FileInputStream(uploadFile);
			cliente.enterLocalPassiveMode(); // IMPORTANTE!!!!
			cliente.setFileType(FTP.BINARY_FILE_TYPE);
			// cliente.changeWorkingDirectory(remote_working_dir_path);
			final boolean sucess = cliente.storeFile(nombreArchivo, fis);
			fis.close();
			if (sucess == false) {

				System.out.println("Error al subir el fichero");
				return false;
			} else {
				System.out.println("Archivo " + uploadFile + " ha sido cargado exitosamente.");
				return true;
			}
		} catch (final Exception eFTPClient) {
			System.out.println(eFTPClient.getMessage());
			return false;
		}

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
