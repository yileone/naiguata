/**

 *
 */
package cobis.Gui;

import java.io.Console;
import java.io.IOException;

import archivos.Archivo;
import archivos.Claves;
import archivos.Propiedades;
import conexion.DemonioFtpCobis;
import conexion.FtpNaiguata;
import conexion.SftpNaiguata;
import conexion.Sftpyileone;

/**
 * @author yisheng
 *
 */
public class FTPCobis {

	private static String error = "la opción no es válida ejecute el parametro -? para mostrar la ayuda";
	private static Propiedades propiedades;
	private static DemonioFtpCobis demonio = new DemonioFtpCobis();

	/**
	 * cambio de claves del servidor login y password
	 * 
	 * @param segura verifica si es la conexion es ftp o sftp
	 */
	private static void cambiarClaves(boolean segura) {

		String login;
		String clave;
		String reClave;

		System.out.println(".:: Cambio de Login ::.");
		final Console entrada = System.console();

		System.out.print("Ingrese su login: ");
		login = entrada.readLine();

		clave = new String(entrada.readPassword("Ingrese su clave: "));

		reClave = new String(entrada.readPassword("Repita su clave por favor: "));

		if (clave.equals(reClave)) {
			cargarPropiedades();
			final Archivo archivo = new Archivo(propiedades, 'S');
			archivo.cambioClaves(login, clave);
			System.out.println("Probando sus nuevas credenciales");
			if (segura) {
				System.out.println("Probando conexion SFTP");

				probarConexionSegura();
			} else {
				probarConexion();
			}

		} else {
			System.out.println("Las claves no coinciden por favor repetir la operación");
		}

	}

	/**
	 * cambio de claves del servidor login y password
	 */
	private static void cambiarClavesSegura() {
		cambiarClaves(true);
	}

	private static void cargarArchivo() {
		cargarPropiedades();
		final Archivo archivo = new Archivo(propiedades, 'S');
		final FtpNaiguata conexion = new FtpNaiguata(archivo);
		if (conexion.isLogin()) {
			conexion.putFile();
			conexion.cerrar();
		}
	}

	private static void cargarArchivoSeguro() {
		cargarPropiedades();
		final Archivo archivo = new Archivo(propiedades, 'E');
		final Sftpyileone conexion = new Sftpyileone(archivo, false);
		if (conexion.isLogin()) {
			conexion.putFile();
			try {
				conexion.cerrar();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void cargarDirectorioSeguro() {
		cargarPropiedades();
		final Archivo archivo = new Archivo(propiedades, 'E');
		final Sftpyileone conexion = new Sftpyileone(archivo, false);
		if (conexion.isLogin()) {
			conexion.putDirectorio();
			try {
				conexion.cerrar();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static void cargarPropiedades() {
		propiedades = new Propiedades();
	}

	/**
	 *
	 */
	private static void despertarDemonio() {
		cargarPropiedades();
		final Archivo archivo = new Archivo(propiedades, 'E');
		demonio.setArchivo(archivo);
		demonio.start();
		System.out.println("empezó");
		try {
			Thread.sleep(5000);
		} catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 *
	 */
	private static void generaClaves() {
		// TODO Auto-generated method stub
		final Claves claves = new Claves();
		claves.generaKey();
	}

	/**
	 *
	 */
	private static void generarClaveAleatoria() {
		final Claves claves = new Claves();
		System.out.println(claves.generarClave());

	}

	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println(error);
		} else if (args[0].equals("-P")) {
			cargarPropiedades();
			mostrarPropiedades();
		} else if (args[0].equals("-O")) {
			obtenerArchivo();
		} else if (args[0].equals("-T")) {
			probarConexion();
		} else if (args[0].equals("-C")) {
			cargarArchivo();
		} else if (args[0].equals("-Q")) {
			cargarArchivoSeguro();
		} else if (args[0].equals("-H")) {
			pasarHistorico();
		} else if (args[0].equals("-K")) {
			generaClaves();
		} else if (args[0].equals("-G")) {
			generarClaveAleatoria();
		} else if (args[0].equals("-N")) {
			cambiarClaves(false);
		} else if (args[0].equals("-M")) {
			cambiarClavesSegura();
		} else if (args[0].equals("-W")) {
			probarConexionSeguraJsch();
		} else if (args[0].equals("-D")) {
			despertarDemonio();
		} else if (args[0].equals("-F")) {
			cargarDirectorioSeguro();
		} else if (args[0].equals("-X")) {
			//System.out.println("curazao alla voy");
			cargarCurazao();
		} else if (args[0].equals("-R")) {
			//System.out.println("curazao alla voy");
			renombraArchivo();

		} else if (args[0].equals("-?")) {
			mostrarAyuda();
		} else {
			System.out.println(error);
		}

	}

	private static void cargarCurazao() {
		// TODO Auto-generated method stub
		cargarPropiedades();
		Archivo archivo = new Archivo(propiedades, 'E');
		//System.out.println("entrando");
		final Sftpyileone conexion = new Sftpyileone(archivo, false);
		if (conexion.isLogin()) {
		//	System.out.println("conectado");
			conexion.putDirectorio();
		
			try {
				conexion.cerrar();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		archivo = new Archivo(propiedades, 'S');
		try {
			archivo.paseHistorico();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conexion.cerrar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Muestra todos los menús con sus opciones de ejecucion
	 */
	public static void mostrarAyuda() {
		System.out.println("********************************");
		System.out.println("***FtP Cobis - Naiguata V1.0****");
		System.out.println("********************************");
		System.out.println("* -? la ayuda que esta leyendo *");
		System.out.println("* -P muestras las propiedades del archivo de configuraión *");
		System.out.println("* -O obtiene el archivo de entrada del servidor FTP *");
		System.out.println("* -T prueba la conexion con el servidor FTP *");
		System.out.println("* -C carga el archivo de salida al servidor FTP *");
		System.out.println("* -Q carga el archivo de salida al servidor SFTP *");
		System.out.println("* -F carga el directorio local al servidor SFTP *");
		System.out.println("* -H Pase a histórico  *");
		System.out.println("* -K Generador de claves para crt *");
		System.out.println("* -G Generador de claves  *");
		System.out.println("* -N nueva login y clave del servidor ftp  *");
		System.out.println("* -M nueva login y clave del servidor Sftp  *");
		System.out.println("* -W Probar conexion segura  configurada  *");
		System.out.println("* -D Despertar el demonio  *");
		System.out.println("* -X Probar opción Curazao  *");
		System.out.println("* -R Renombrar archivos  *");
		System.out.println("********************************");

	}

	private static void mostrarPropiedades() {
		propiedades.mostrarAtributos();
	}

	/**
	 * Obtiene archivo del servidor FtP
	 */
	private static void renombraArchivo() {
		cargarPropiedades();
		final Archivo archivo = new Archivo(propiedades, 'E');
		
		
		try {
			if (archivo.copy(propiedades.getCarpLocal(), propiedades.getCarpHistLocal())) {
				
			archivo.renombrarArchivo(propiedades.getCarpHistLocal(), propiedades.getArchivoLocal(),propiedades.getArchivoDestino(),true);
			archivo.renombrarArchivo(propiedades.getCarpHistLocal(), propiedades.getArchivoLocal2(),propiedades.getArchivoDestino2(),true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Obtiene archivo del servidor FtP
	 */
	private static void obtenerArchivo() {
		cargarPropiedades();
		final Archivo archivo = new Archivo(propiedades, 'E');
		final FtpNaiguata conexion = new FtpNaiguata(archivo);
		if (conexion.isLogin()) {
			conexion.getFile();
			conexion.cerrar();

		}
	}

	private static void pasarHistorico() {
		cargarPropiedades();
		final Archivo archivo = new Archivo(propiedades, 'S');
		try {
			archivo.paseHistorico();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Testea la conexión
	 */
	private static void probarConexion() {
		cargarPropiedades();
		final Archivo archivo = new Archivo(propiedades, 'E');
		final FtpNaiguata conexion = new FtpNaiguata(archivo);
		if (conexion.isLogin()) {
			System.out.println("**************  CONEXION ESTABLECIDA CON : " + propiedades.getServidorFtp());
			conexion.cerrar();
		} else {
			System.out.println("**************   ERROR EN CONEXION  ****** " + propiedades.getServidorFtp());

		}
	}

	/**
	 * Testea la conexión
	 */
	private static void probarConexionSegura() {
		cargarPropiedades();
		final Archivo archivo = new Archivo(propiedades, 'E');
		final SftpNaiguata conexion = new SftpNaiguata(archivo);
		if (conexion.isLogin()) {
			System.out.println("**************  CONEXION SEGURA ESTABLECIDA CON : " + propiedades.getServidorFtp());
			conexion.cerrar();
		} else {
			System.out.println("**************   ERROR EN CONEXION SEGURA****** " + propiedades.getServidorFtp());

		}
	}

	/**
	 * Testea la conexión
	 */
	private static void probarConexionSeguraJsch() {
		cargarPropiedades();
		final Archivo archivo = new Archivo(propiedades, 'E');
		final Sftpyileone conexion = new Sftpyileone(archivo, true);
		if (conexion.isLogin()) {
			System.out.println("**************  CONEXION SEGURA ESTABLECIDA CON : " + propiedades.getServidorFtp());

		} else {
			System.out.println("**************   ERROR EN CONEXION SEGURA****** " + propiedades.getServidorFtp());

		}
	}
}
