/**

 *
 */
package cobis.Gui;

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

	private static void cargarPropiedades() {
		// TODO Auto-generated method stub
		propiedades = new Propiedades();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length != 1) {
			System.out.println(error);
		} else if (args[0].equals("-P")) {
			cargarPropiedades();
			mostrarPropiedades();
		} else if (args[0].equals("-O")) {
			obtenerArchivo();
		} else if (args[0].equals("-T")) {
			probarConexion();
		} else if (args[0].equals("-?")) {
			mostrarAyuda();
		} else if (args[0].equals("-?")) {
			mostrarAyuda();
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
		// TODO Auto-generated method stub
		System.out.println("********************************");
		System.out.println("***FtP Cobis - Naiguata V1.0****");
		System.out.println("********************************");
		System.out.println("* -? la ayuda que esta leyendo *");
		System.out.println("* -P muestras las propiedades del archivo de configuraión *");
		System.out.println("* -O obtiene el archivo de entrada del servidor FTP *");
		System.out.println("* -T prueba la conexion con el servidor FTP *");
		System.out.println("*  ? la ayuda que esta leyendo *");
		System.out.println("*  ? la ayuda que esta leyendo *");
		System.out.println("********************************");

	}

	private static void mostrarPropiedades() {
		// TODO Auto-generated method stub
		propiedades.mostrarAtributos();
	}

	/**
	 * Obtiene archivo del servidor FtP
	 */
	private static void obtenerArchivo() {
		// TODO Auto-generated method stub
		cargarPropiedades();
		final Archivo archivo = new Archivo(propiedades, 'E');
		final FtpNaiguata conexion = new FtpNaiguata(archivo);
		if (conexion.isLogin()) {
			conexion.getFile();
			conexion.cerrar();

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
