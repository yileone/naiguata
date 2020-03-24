/**

 *
 */
package cobis.Gui;

import java.io.IOException;

import archivos.Archivo;
import archivos.Propiedades;
import conexion.FtpNaiguata;

/**
 * @author yisheng
 *
 */
public class FTPCobis {

	private static String error = "la opción no es válida ejecute el parametro -? para mostrar la ayuda";
	private static Propiedades propiedades;

	private static void cargarArchivo() {
		cargarPropiedades();
		final Archivo archivo = new Archivo(propiedades, 'S');
		final FtpNaiguata conexion = new FtpNaiguata(archivo);
		if (conexion.isLogin()) {
			conexion.putFile();
			conexion.cerrar();
		}
	}

	private static void cargarPropiedades() {
		propiedades = new Propiedades();
	}

	/**
	 * @param args
	 */
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
		} else if (args[0].equals("-H")) {
			pasarHistorico();
		} else if (args[0].equals("-?")) {
			mostrarAyuda();
		} else {
			System.out.println(error);
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
		System.out.println("* -H Pase a histórico  *");
		System.out.println("********************************");

	}

	private static void mostrarPropiedades() {
		propiedades.mostrarAtributos();
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
		} catch (IOException e) {
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

}
