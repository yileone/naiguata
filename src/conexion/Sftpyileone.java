/**
 * 
 */
package conexion;

/**
 * @author 醫生
 *
 */

import archivos.Archivo;

import java.io.File;
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
	 * @param archivo
	 * @param debug
	 */
	public Sftpyileone(Archivo archivo, Boolean debug) {

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
			// jsch.setKnownHosts("servidores.conocidos");
			Session session = jsch.getSession(user, host, port);

			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig("HashKnownHosts", "no");
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
		final String directorio = archivo.getPropiedades().getCarpDestino();
		final String nombreArchivo = archivo.getPropiedades().getArchivoDestino();
		final String directorioServidor = archivo.getPropiedades().getCarpLocal();
		final String nombreArchivoServidor = archivo.getPropiedades().getArchivoLocal();

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

		final String directorio = archivo.getPropiedades().getCarpLocal();
		final String nombreArchivo = archivo.getPropiedades().getArchivoLocal();
		final String directorioServidor = archivo.getPropiedades().getCarpDestino();
		final String nombreArchivoServidor = archivo.getPropiedades().getArchivoDestino();
		return putFile(directorio + nombreArchivo, directorioServidor + nombreArchivoServidor);
	}

	public boolean putDirectorio() {
		//System.out.println("entrar en put directorio");
		String directorioServidor = archivo.getPropiedades().getCarpDestino();
		String nombreArchivoServidor;
		String directorio = archivo.getPropiedades().getCarpLocal();
		String nombreArchivo;
		boolean salida = true;
		int contador = archivo.getPropiedades().getCantidadArchivos();
		
		//System.out.println("chino qque mierda pasa");
		//archivo.getPropiedades().mostrarAtributos();
		contador--;
		if (contador >= 0) {
			nombreArchivo = archivo.getPropiedades().getArchivoLocal();
			nombreArchivoServidor = archivo.getPropiedades().getArchivoDestino();
		//	System.out.println("origen:"+directorio + nombreArchivo);
		//	System.out.println("destino:"+directorioServidor + nombreArchivoServidor);
			salida = salida && putFile(directorio + nombreArchivo, directorioServidor + nombreArchivoServidor);
		}
		contador--;
		if (contador >= 0) {
			nombreArchivo = archivo.getPropiedades().getArchivoLocal2();
			nombreArchivoServidor = archivo.getPropiedades().getArchivoDestino2();
			//System.out.println("origen:"+directorio + nombreArchivo);
			//System.out.println("destino:"+directorioServidor + nombreArchivoServidor);
		
			salida = salida && putFile(directorio + nombreArchivo, directorioServidor + nombreArchivoServidor);
		}
		
		contador--;
		//System.out.println("contador:"+contador);
		if (contador >= 0) {
			nombreArchivo = archivo.getPropiedades().getArchivoLocal3();
			nombreArchivoServidor = archivo.getPropiedades().getArchivoDestino3();
			salida = salida && putFile(directorio + nombreArchivo, directorioServidor + nombreArchivoServidor);
		}
		contador--;
		if (contador >= 0) {
			nombreArchivo = archivo.getPropiedades().getArchivoLocal4();
			nombreArchivoServidor = archivo.getPropiedades().getArchivoDestino4();
			salida = salida && putFile(directorio + nombreArchivo, directorioServidor + nombreArchivoServidor);
		}
		contador--;
		if (contador >= 0) {
			nombreArchivo = archivo.getPropiedades().getArchivoLocal5();
			nombreArchivoServidor = archivo.getPropiedades().getArchivoDestino5();
			salida = salida && putFile(directorio + nombreArchivo, directorioServidor + nombreArchivoServidor);
		}
		contador--;
		if (contador >= 0) {
			nombreArchivo = archivo.getPropiedades().getArchivoLocal6();
			nombreArchivoServidor = archivo.getPropiedades().getArchivoDestino6();
			salida = salida && putFile(directorio + nombreArchivo, directorioServidor + nombreArchivoServidor);
		}
		
		return salida;
	}

	/**
	 * 
	 * @param srcFilePath
	 * @param destPath
	 * @return
	 */
	public boolean putFile(String srcFilePath, String destPath) {
		System.out.println("ruta origen -----");
		System.out.println("ruta origen:" + srcFilePath);
		System.out.println("ruta destino:" + destPath);
		if ((srcFilePath == "") || (destPath == "")) {

			System.out.println("Las ruta de destino y del servidor origen no pueden estar vacías");
			return false;
		}

		boolean success;
		success = true;
		try {
			getCliente().put(srcFilePath, destPath);

		} catch (SftpException e) {
			success = false;
			System.out.println("error subiendo archivo:" + srcFilePath);
			System.out.println(e.getMessage());
			throw new JMRuntimeException();

		}

		return success;

	}

	/**
	 * 
	 * @param sourcePath
	 * @param destPath
	 * @return
	 */
	Boolean copiarDirectorio(String sourcePath, String destPath) {
		File localFile = new File(sourcePath);

		if (localFile.isDirectory()) {
			try {
				getCliente().cd(destPath);
			} catch (SftpException e) {

				e.printStackTrace();
				return false;
			}

			for (File file : localFile.listFiles()) {
				System.out.println("copiando " + sourcePath + file.getName());
				putFile(sourcePath + file.getName(), destPath);
			}

			try {
				getCliente().cd(destPath.substring(0, destPath.lastIndexOf('/')));
			} catch (SftpException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			System.out.println("Error el origen no es un directorio ");
			return false;
		}
		return true;
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
