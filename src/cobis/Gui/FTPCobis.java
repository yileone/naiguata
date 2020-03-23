/**

 * 
 */
package cobis.Gui;

/**
 * @author a
 *
 */
public class FTPCobis {

private static String error="la opción no es válida ejecute el parametro -H para mostrar la ayuda"; 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length>1)
		{
			System.out.println(error);
		}
		
	}

}
