/**

 * 
 */
package cobis.Gui;

/**
 * @author a
 *
 */
public class FTPCobis {

private static String error="la opción no es válida ejecute el parametro -? para mostrar la ayuda"; 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length>1)
		{
			System.out.println(error);
		}
		else if (args[0].equals("-?")) {
			mostrarAyuda();
		}else if (args[0].equals("-?")) {
			mostrarAyuda();
		}else if (args[0].equals("-?")) {
			mostrarAyuda();
		}else if (args[0].equals("-?")) {
			mostrarAyuda();
		}else if (args[0].equals("-?")) {
			mostrarAyuda();
		}else if (args[0].equals("-?")) {
			mostrarAyuda();
		}else  {
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
		System.out.println("* -P la ayuda que esta leyendo *");
		System.out.println("*  ? la ayuda que esta leyendo *");
		System.out.println("*  ? la ayuda que esta leyendo *");
		System.out.println("*  ? la ayuda que esta leyendo *");
		System.out.println("*  ? la ayuda que esta leyendo *");
		System.out.println("********************************");
		
	}

}
