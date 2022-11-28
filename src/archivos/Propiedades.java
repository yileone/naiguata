/**
 *
 */
package archivos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	private String carpEntrada;
	private String carpSalida;
	private String carpHistEnt;
	private String carpHistSal;
	private String login;
	private String Clave;
	private String ArchivoEntrada;
	private String ArchivoSalida;

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

			setCarpEntrada(propiedades.getProperty("carpeta.Entrada"));
			setCarpHistEnt(propiedades.getProperty("carpeta.HistEntrada"));
			setCarpSalida(propiedades.getProperty("carpeta.Salida"));
			setCarpHistSal(propiedades.getProperty("carpeta.HistSalida"));
			setClave(desencripta(propiedades.getProperty("servidor.clave")));
			setServidorFtp(propiedades.getProperty("servidor.FTP", "localhost"));
			setServidorPuerto(propiedades.getProperty("servidor.puerto", "21"));
			setLogin(desencripta(propiedades.getProperty("servidor.login")));
			setArchivoEntrada(propiedades.getProperty("archivo.entrada"));
			setArchivoSalida(propiedades.getProperty("archivo.salida"));
		} catch (final FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("el archivo no existe");
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
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
	 *
	 * @return ArchivoEntradda
	 */
	public String getArchivoEntrada() {
		return ArchivoEntrada;
	}

	/**
	 * @return the archivoPropiedades
	 */
	public String getArchivoPropiedades() {
		return archivoPropiedades;
	}

	/**
	 * @return the archivoSalida
	 */
	public String getArchivoSalida() {
		return ArchivoSalida;
	}

	/**
	 * @return the carpEntrada
	 */
	public String getCarpEntrada() {
		return carpEntrada;
	}

	/**
	 * @return the carpHistEnt
	 */
	public String getCarpHistEnt() {
		return carpHistEnt;
	}

	/**
	 * @return the carpHistSal
	 */
	public String getCarpHistSal() {
		return carpHistSal;
	}

	/**
	 * @return the carpSalida
	 */
	public String getCarpSalida() {
		return carpSalida;
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
		System.out.println("Carpeta.Entrada:" + getCarpEntrada());
		System.out.println("carpeta.HistEntrada:" + getCarpHistEnt());
		System.out.println("carpeta.Salida:" + getCarpSalida());
		System.out.println("carpeta.HistSalida:" + getCarpHistSal());
		System.out.println("servidor.clave:" + getClave());
		System.out.println("servidor.FTP:" + getServidorFtp());
		System.out.println("servidor.Puerto:" + getServidorPuerto());
		System.out.println("servidor.login:" + getLogin());
		System.out.println("archivo.entrada:" + getArchivoEntrada());
		System.out.println("archivo.salida:" + getArchivoSalida());
	}

	/**
	 *
	 * @param archivoEntrada
	 *            set archivoEntrada
	 */
	public void setArchivoEntrada(String archivoEntrada) {
		ArchivoEntrada = archivoEntrada;
	}

	/**
	 * @param archivoPropiedades
	 *            the archivoPropiedades to set
	 */
	public void setArchivoPropiedades(String archivoPropiedades) {
		this.archivoPropiedades = archivoPropiedades;
	}

	/**
	 * @param archivoSalida
	 *            the archivoSalida to set
	 */
	public void setArchivoSalida(String archivoSalida) {
		ArchivoSalida = archivoSalida;
	}

	/**
	 * @param carpEntrada
	 *            the carpEntrada to set
	 */
	public void setCarpEntrada(String carpEntrada) {
		this.carpEntrada = carpEntrada;
	}

	/**
	 * @param carpHistEnt
	 *            the carpHistEnt to set
	 */
	public void setCarpHistEnt(String carpHistEnt) {
		this.carpHistEnt = carpHistEnt;
	}

	/**
	 * @param carpHistSal
	 *            the carpHistSal to set
	 */
	public void setCarpHistSal(String carpHistSal) {
		this.carpHistSal = carpHistSal;
	}

	/**
	 * @param carpSalida
	 *            the carpSalida to set
	 */
	public void setCarpSalida(String carpSalida) {
		this.carpSalida = carpSalida;
	}

	/**
	 * @param clave
	 *            the clave to set
	 */
	public void setClave(String clave) {
		Clave = clave;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @param propiedades
	 *            the propiedades to set
	 */
	public void setPropiedades(Properties propiedades) {
		this.propiedades = propiedades;
	}

	/**
	 * @param servidorFtp
	 *            the servidorFtp to set
	 */
	public void setServidorFtp(String servidorFtp) {
		this.servidorFtp = servidorFtp;
	}
	
	/**
	 * 
	 * @param puertoServidor
	 */
	private void setServidorPuerto(String puertoServidor) {

		this.servidorPuerto= Integer.parseInt(puertoServidor);
		
	}

}
