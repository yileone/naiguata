/**
 *
 */
package archivos;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

/**
 * Clase que procesa archivo tipo Properties
 *
 * @author yisheng
 *
 */
public class Propiedades {

	static private String archivoDefecto = "FtpCobis.properties";

	/**
	 *
	 * @param valor
	 * @return
	 */
	public static String desencripta(String valor) {

		// final byte[] encoded = Base64.getEncoder().encode(property.getBytes());
		return Claves.AESdecrypt(valor);
	}

	/**
	 * @return the archivoDefecto
	 */
	public static String getArchivoDefecto() {
		return archivoDefecto;
	}

	private String archivoPropiedades;
	private String servidorFtp;
	private int servidorPuerto;
	private String carpLocal;
	private String carpDestino;
	private String carpHistLocal;
	private String carpHistDestinoString;
	private String login;
	private String Clave;
	private String ArchivoLocal;
	private String ArchivoLocal2;
	private String ArchivoLocal3;
	private String ArchivoLocal4;
	private String ArchivoLocal5;
	private String ArchivoLocal6;
	
	/**
	 * @return the archivoDestino2
	 */
	public String getArchivoDestino2() {
		return archivoDestino2;
	}

	/**
	 * @param archivoDestino2 the archivoDestino2 to set
	 */
	public void setArchivoDestino2(String archivoDestino2) {
		this.archivoDestino2 = archivoDestino2;
	}

	/**
	 * @return the archivoDestino3
	 */
	public String getArchivoDestino3() {
		return archivoDestino3;
	}

	/**
	 * @param archivoDestino3 the archivoDestino3 to set
	 */
	public void setArchivoDestino3(String archivoDestino3) {
		this.archivoDestino3 = archivoDestino3;
	}

	/**
	 * @return the archivoDestino4
	 */
	public String getArchivoDestino4() {
		return archivoDestino4;
	}

	/**
	 * @param archivoDestino4 the archivoDestino4 to set
	 */
	public void setArchivoDestino4(String archivoDestino4) {
		this.archivoDestino4 = archivoDestino4;
	}

	/**
	 * @return the archivoDestino5
	 */
	public String getArchivoDestino5() {
		return archivoDestino5;
	}

	/**
	 * @param archivoDestino5 the archivoDestino5 to set
	 */
	public void setArchivoDestino5(String archivoDestino5) {
		this.archivoDestino5 = archivoDestino5;
	}

	/**
	 * @return the archivoDestino6
	 */
	public String getArchivoDestino6() {
		return archivoDestino6;
	}

	/**
	 * @param archivoDestino6 the archivoDestino6 to set
	 */
	public void setArchivoDestino6(String archivoDestino6) {
		this.archivoDestino6 = archivoDestino6;
	}

	/**
	 * @return the carpHistDestinoString
	 */
	public String getCarpHistDestinoString() {
		return carpHistDestinoString;
	}

	private int CantidadArchivos;
	private String archivoDestino;
	private String archivoDestino2;
	private String archivoDestino3;
	private String archivoDestino4;
	private String archivoDestino5;
	private String archivoDestino6;
	/**
	 * @return the carpDestino
	 */
	public String getCarpDestino() {
		return carpDestino;
	}

	/**
	 * @param carpDestino the carpDestino to set
	 */
	public void setCarpDestino(String carpDestino) {
		this.carpDestino = carpDestino;
	}

	/**
	 * @return the carpHistLocal
	 */
	public String getCarpHistLocal() {
		return carpHistLocal;
	}

	/**
	 * @param carpHistLocal the carpHistLocal to set
	 */
	public void setCarpHistLocal(String carpHistLocal) {
		this.carpHistLocal = carpHistLocal;
	}

	/**
	 * @return the carpHistDestinoString
	 */
	public String getCarpHistDestino() {
		return carpHistDestinoString;
	}

	/**
	 * @param carpHistDestinoString the carpHistDestinoString to set
	 */
	public void setCarpHistDestino(String carpHistDestinoString) {
		this.carpHistDestinoString = carpHistDestinoString;
	}

	/**
	 * @return the archivoLocal
	 */
	public String getArchivoLocal() {
		return ArchivoLocal;
	}

	/**
	 * @param archivoLocal the archivoLocal to set
	 */
	public void setArchivoLocal(String archivoLocal) {
		ArchivoLocal = archivoLocal;
	}

	/**
	 * @return the archivoLocal2
	 */
	public String getArchivoLocal2() {
		return ArchivoLocal2;
	}

	/**
	 * @param archivoLocal2 the archivoLocal2 to set
	 */
	public void setArchivoLocal2(String archivoLocal2) {
		ArchivoLocal2 = archivoLocal2;
	}

	/**
	 * @return the archivoLocal3
	 */
	public String getArchivoLocal3() {
		return ArchivoLocal3;
	}

	/**
	 * @param archivoLocal3 the archivoLocal3 to set
	 */
	public void setArchivoLocal3(String archivoLocal3) {
		ArchivoLocal3 = archivoLocal3;
	}

	/**
	 * @return the archivoLocal4
	 */
	public String getArchivoLocal4() {
		return ArchivoLocal4;
	}

	/**
	 * @param archivoLocal4 the archivoLocal4 to set
	 */
	public void setArchivoLocal4(String archivoLocal4) {
		ArchivoLocal4 = archivoLocal4;
	}

	/**
	 * @return the archivoLocal5
	 */
	public String getArchivoLocal5() {
		return ArchivoLocal5;
	}

	/**
	 * @param archivoLocal5 the archivoLocal5 to set
	 */
	public void setArchivoLocal5(String archivoLocal5) {
		ArchivoLocal5 = archivoLocal5;
	}

	/**
	 * @return the archivoLocal6
	 */
	public String getArchivoLocal6() {
		return ArchivoLocal6;
	}

	/**
	 * @param archivoLocal6 the archivoLocal6 to set
	 */
	public void setArchivoLocal6(String archivoLocal6) {
		ArchivoLocal6 = archivoLocal6;
	}

	/**
	 * @return the archivoDestino
	 */
	public String getArchivoDestino() {
		return archivoDestino;
	}

	/**
	 * @param archivoDestino the archivoDestino to set
	 */
	public void setArchivoDestino(String archivoDestino) {
		this.archivoDestino = archivoDestino;
	}

	/**
	 * @return the carpLocal
	 */
	public String getCarpLocal() {
		return carpLocal;
	}

	/**
	 * @param carpLocal the carpLocal to set
	 */
	public void setCarpLocal(String carpLocal) {
		this.carpLocal = carpLocal;
	}

	/**
	 * @param archivoDefecto the archivoDefecto to set
	 */
	public static void setArchivoDefecto(String archivoDefecto) {
		Propiedades.archivoDefecto = archivoDefecto;
	}

	/**
	 * @param servidorPuerto the servidorPuerto to set
	 */
	public void setServidorPuerto(int servidorPuerto) {
		this.servidorPuerto = servidorPuerto;
	}

	/**
	 * propiedades de la configuración
	 */
	Properties propiedades;

	/**
	 * Constructor que trae el archivo por defecto
	 */
	public Propiedades() {
		// TODO Auto-generated constructor stub
		this(archivoDefecto);
	}

	/**
	 * carga los parametros del archivo otorgado
	 *
	 * @param archivo
	 */
	public Propiedades(String archivo) {
		// TODO Auto-generated constructor stub
		propiedades = new Properties();
		setArchivoPropiedades(archivo);
		try {
			propiedades.load(new FileInputStream(archivo));

			setCarpLocal(propiedades.getProperty("carpeta.Local"));
			setCarpHistLocal(propiedades.getProperty("carpeta.HistLocal"));
			setCarpDestino(propiedades.getProperty("carpeta.Destino"));
			setCarpHistDestino(propiedades.getProperty("carpeta.HistDestino"));
			setClave(desencripta(propiedades.getProperty("servidor.clave")));
			setServidorFtp(propiedades.getProperty("servidor.FTP", "localhost"));
			setServidorPuerto(propiedades.getProperty("servidor.puerto", "21"));
			setLogin(desencripta(propiedades.getProperty("servidor.login")));
			setArchivoLocal(propiedades.getProperty("archivo.local"));
			setArchivoDestino(propiedades.getProperty("archivo.destino"));
			setArchivoLocal2(propiedades.getProperty("archivo.local2"));
			setArchivoDestino2(propiedades.getProperty("archivo.destino2"));
			setArchivoLocal3(propiedades.getProperty("archivo.local3"));
			setArchivoDestino3(propiedades.getProperty("archivo.destino3"));
			setArchivoLocal4(propiedades.getProperty("archivo.local4"));
			setArchivoDestino4(propiedades.getProperty("archivo.destino4"));
			setArchivoLocal5(propiedades.getProperty("archivo.local5"));
			setArchivoDestino5(propiedades.getProperty("archivo.destino5"));
			setArchivoLocal6(propiedades.getProperty("archivo.local6"));
			setArchivoDestino6(propiedades.getProperty("archivo.destino6"));
			setCantidadArchivos(Integer.parseInt( propiedades.getProperty("archivos.cantidad")));
			// TODO Auto-generated catch block
			
			
		} catch (final IOException e) {
			System.out.println("el archivo no existe");
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param login
	 * @param clave
	 */
	public void cambiarClaves(String login, String clave) {
		try {
			propiedades.setProperty("servidor.clave", encriptar(clave));
			propiedades.setProperty("servidor.login", encriptar(login));
			Calendar.getInstance();
			propiedades.store(new FileWriter(getArchivoPropiedades()), "");
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param valor
	 * @return
	 */
	public String encriptar(String valor) {
		return Claves.AESencrypt(valor);
	}

	/**
	 * @return the archivoPropiedades
	 */
	public String getArchivoPropiedades() {
		return archivoPropiedades;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return Clave;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @return the propiedades
	 */
	public Properties getPropiedades() {
		return propiedades;
	}

	/**
	 * @return the servidorFtp
	 */
	public String getServidorFtp() {
		return servidorFtp;
	}

	/**
	 * @return the servidorFtp
	 */
	public int getServidorPuerto() {
		return servidorPuerto;
	}

	/**
	 * mostrar todos los atributos del archivo de propiedades
	 */
	public void mostrarAtributos() {
		System.out.println("Carpeta.Local:" + getCarpLocal());
		System.out.println("carpeta.HistLocal:" + getCarpHistLocal());
		System.out.println("carpeta.Destino:" + getCarpDestino());
		System.out.println("carpeta.HistDestino:" + getCarpHistDestino());
		System.out.println("servidor.clave:" + getClave());
		System.out.println("servidor.FTP:" + getServidorFtp());
		System.out.println("servidor.Puerto:" + getServidorPuerto());
		System.out.println("servidor.login:" + getLogin());
		System.out.println("archivo.local:" + getArchivoLocal());
		System.out.println("archivo.local2:" + getArchivoLocal2());
		System.out.println("archivo.destino:" + getArchivoDestino());
		System.out.println("archivos.cantidad:" + getCantidadArchivos());
	}

	
	/**
	 * @param archivoPropiedades the archivoPropiedades to set
	 */
	public void setArchivoPropiedades(String archivoPropiedades) {
		this.archivoPropiedades = archivoPropiedades;
	}


	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		Clave = clave;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @param propiedades the propiedades to set
	 */
	public void setPropiedades(Properties propiedades) {
		this.propiedades = propiedades;
	}

	/**
	 * @param servidorFtp the servidorFtp to set
	 */
	public void setServidorFtp(String servidorFtp) {
		this.servidorFtp = servidorFtp;
	}

	/**
	 * 
	 * @param puertoServidor
	 */
	private void setServidorPuerto(String puertoServidor) {

		this.servidorPuerto = Integer.parseInt(puertoServidor);

	}

	public int getCantidadArchivos() {
		return CantidadArchivos;
	}

	public void setCantidadArchivos(int cantidadArchivos) {
		CantidadArchivos = cantidadArchivos;
	}

}
