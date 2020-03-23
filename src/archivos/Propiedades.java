/**
 * 
 */
package archivos;

import java.io.*;

import java.util.*;
/**
 * Clase que procesa archivo tipo Properties
 * @author yisheng 
 *
 */
public class Propiedades {

	static private String archivoDefecto="FtpCobis.properties";
	private String servidorFtp;
	private String carpEntrada;
	private String carpSalida;
	private String carpHistEnt;
	private String carpHistSal;
	private String login;
	private String Clave;
	
	/**
	 * propiedades de la configuración
	 */
	Properties propiedades ;
	/**
	 * Constructor que trae el archivo por defecto
	 */
	public Propiedades() {
		// TODO Auto-generated constructor stub
		this(archivoDefecto);
	}

	/**
	 * carga los parametros del archivo otorgado
	 * @param archivo
	 */
	public Propiedades(String archivo) {
		// TODO Auto-generated constructor stub
		propiedades= new Properties();
		setCarpEntrada(propiedades.getProperty("carpeta.Entrada"));
		setCarpHistEnt(propiedades.getProperty("carpeta.HistEntrada"));
		setCarpSalida(propiedades.getProperty("carpeta.Salida"));
		setCarpHistSal(propiedades.getProperty("carpeta.HistSalida"));
		setClave(propiedades.getProperty("servidor.clave"));
		setServidorFtp(propiedades.getProperty("servidor.FTP","localhost"));
		setLogin(propiedades.getProperty("servidor.login"));
		try {
			propiedades.load(new FileInputStream(archivo));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("el archivo no existe");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * mostrar todos los atributos del archivo de propiedades
 */
	public void mostrarAtributos() {
		System.out.println("Carpeta.Entrada:"+getCarpEntrada());
		System.out.println("carpeta.HistEntrada:"+getCarpHistEnt());
		System.out.println("carpeta.Salida:"+getCarpSalida());
		System.out.println("carpeta.HistSalida:"+getCarpHistSal());
		System.out.println("servidor.clave:"+getClave());
		System.out.println("servidor.FTP:"+getServidorFtp());
		System.out.println("servidor.login:"+getLogin());
			}

/**
 * @return the archivoDefecto
 */
public static String getArchivoDefecto() {
	return archivoDefecto;
}



/**
 * @return the servidorFtp
 */
public String getServidorFtp() {
	return servidorFtp;
}

/**
 * @param servidorFtp the servidorFtp to set
 */
public void setServidorFtp(String servidorFtp) {
	this.servidorFtp = servidorFtp;
}

/**
 * @return the carpEntrada
 */
public String getCarpEntrada() {
	return carpEntrada;
}

/**
 * @param carpEntrada the carpEntrada to set
 */
public void setCarpEntrada(String carpEntrada) {
	this.carpEntrada = carpEntrada;
}

/**
 * @return the carpSalida
 */
public String getCarpSalida() {
	return carpSalida;
}

/**
 * @param carpSalida the carpSalida to set
 */
public void setCarpSalida(String carpSalida) {
	this.carpSalida = carpSalida;
}

/**
 * @return the carpHistEnt
 */
public String getCarpHistEnt() {
	return carpHistEnt;
}

/**
 * @param carpHistEnt the carpHistEnt to set
 */
public void setCarpHistEnt(String carpHistEnt) {
	this.carpHistEnt = carpHistEnt;
}

/**
 * @return the carpHistSal
 */
public String getCarpHistSal() {
	return carpHistSal;
}

/**
 * @param carpHistSal the carpHistSal to set
 */
public void setCarpHistSal(String carpHistSal) {
	this.carpHistSal = carpHistSal;
}

/**
 * @return the login
 */
public String getLogin() {
	return login;
}

/**
 * @param login the login to set
 */
public void setLogin(String login) {
	this.login = login;
}

/**
 * @return the clave
 */
public String getClave() {
	return Clave;
}

/**
 * @param clave the clave to set
 */
public void setClave(String clave) {
	Clave = clave;
}

/**
 * @return the propiedades
 */
public Properties getPropiedades() {
	return propiedades;
}

/**
 * @param propiedades the propiedades to set
 */
public void setPropiedades(Properties propiedades) {
	this.propiedades = propiedades;
}

	
	
}
