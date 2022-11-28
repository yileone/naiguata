/**
 * 
 */
package conexion;

/**
 * @author 醫生
 *
 */

import archivos.Archivo;
import java.io.IOException;
import javax.management.JMRuntimeException;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class Sftpyileone {

	/**
	 * 
	 */
	private Archivo archivo;

	private boolean login;
	ChannelSftp cliente;

	public Sftpyileone(Archivo archivo) {
		new Sftpyileone(archivo, false);
	}

	/**
	 * 
	 */
	public Sftpyileone(Archivo archivo, Boolean debug) {
		// TODO Auto-generated constructor stub

		setLogin(false);
		this.archivo = archivo;

		String user = archivo.getPropiedades().getLogin();
		String password = archivo.getPropiedades().getClave();
		String host = archivo.getPropiedades().getServidorFtp();
		int port = archivo.getPropiedades().getServidorPuerto();

		if (debug) {
			System.out.println("Creando SFTP Channel");
			System.out.println("user:" + user);
			System.out.println("password:" + password);
			System.out.println("servidor:" + host);
			System.out.println("puerto:" + port);
		}

		try {
			JSch jsch = new JSch();
			//jsch.setKnownHosts("servidores.conocidos");
			Session session = jsch.getSession(user, host, port);
			java.util.Properties config = new java.util.Properties(); 
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(password);
			if (debug) {
				System.out.println("Establishing Connection...");
			}
			session.connect();
			if (debug) {
				System.out.println("Connection established.");
				System.out.println("Crating SFTP Channel.");
			}
			ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
			sftpChannel.connect();

			System.out.println("SFTP Channel created.");

			setCliente(sftpChannel);
			setLogin(true);
			if (debug) {
				cerrar();
			}
		} catch (Exception e) {
			System.err.print(e);
			setLogin(false);
		}
	}

	/**
	 * desloguearse del servidor y cerrar lla conexión con el servidor
	 */
	public void cerrar() throws IOException {
		if (cliente.isConnected()) {

			cliente.disconnect();
		}
	}

	public void setCliente(ChannelSftp cliente) {
		this.cliente = cliente;
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
	ChannelSftp getCliente() {
		return cliente;
	}

	/**
	 * obtiene el archivo del servidor FTP y lo coloca en la carpeta de Entrada
	 *
	 * @return
	 */
	public boolean getFile() {
		final String directorio = archivo.getPropiedades().getCarpSalida();
		final String nombreArchivo = archivo.getPropiedades().getArchivoSalida();
		final String directorioServidor = archivo.getPropiedades().getCarpEntrada();
		final String nombreArchivoServidor = archivo.getPropiedades().getArchivoEntrada();

		return getFile(directorio + nombreArchivo, directorioServidor + nombreArchivoServidor);
	}

	public boolean getFile(String directorio, String nombreArchivo) {
		boolean success;

		success = true;
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

		final String directorioServidor = archivo.getPropiedades().getCarpEntrada();
		final String nombreArchivoServidor = archivo.getPropiedades().getArchivoEntrada();
		final String directorio = archivo.getPropiedades().getCarpSalida();
		final String nombreArchivo = archivo.getPropiedades().getCarpSalida();
		return putFile(directorio + nombreArchivo, directorioServidor + nombreArchivoServidor);
	}

	/**
	 * 
	 * @param srcFilePath
	 * @param destPath
	 * @return
	 */
	public boolean putFile(String srcFilePath, String destPath) {
		System.out.println("srcFilePath:"+srcFilePath);
		System.out.println("destPath:"+destPath);
		
		boolean success;
		success = true;
		try {
			getCliente().put(srcFilePath, destPath);
		} catch (SftpException e) {
			success = false;
			System.out.println(e.getMessage());
			throw new JMRuntimeException();

		}

		return success;

	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

	/**
	 * @param login the login to set
	 */
	private void setLogin(boolean login) {
		this.login = login;
	}

}
