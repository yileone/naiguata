package conexion;

import java.io.File;

import archivos.Archivo;

public class DemonioFtpCobis extends Thread {
	/**
	 *
	 */

	Archivo archivo;
	char estado = 'E';

	public DemonioFtpCobis() {
		// TODO Auto-generated constructor stub

	}

	public DemonioFtpCobis(Archivo archivo) {
		// TODO Auto-generated constructor stub
		setArchivo(archivo);

	}

	/**
	 * @return the archivo
	 */
	public Archivo getArchivo() {
		return archivo;
	}

	@Override
	public void run() {
		System.out.println("Despertando al demonio");
		final FtpNaiguata conexion = new FtpNaiguata(archivo);
		if (conexion.isLogin()) {

			try {

				while (true) {
					if (estado == 'E') {
						if (verificarArchivo()) {
							if (conexion.putFile()) {
								estado = 'S';
								archivo.paseHistorico();
								wait(2000);
							}

						}
					} else if (estado == 'S') {
						if (conexion.getFile()) {
							estado = 'E';
							wait(2000);

						}

					}
				}
			} catch (final Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			conexion.cerrar();
		}

		else {
			System.out.println("No se puede establecer conexión con el servidor");
		}

	}

	/**
	 * @param archivo
	 *            the archivo to set
	 */
	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

	public boolean verificarArchivo() {
		boolean revisar;
		if (estado == 'E') {
			revisar = verificarArchivo(archivo.getPropiedades().getCarpLocal(), archivo.getPropiedades().getArchivoLocal());
		} else {
			revisar = verificarArchivo(archivo.getPropiedades().getCarpDestino(), archivo.getPropiedades().getArchivoDestino());

		}
		return revisar;
	}
	
	/**
	 * 
	 * @param carpeta
	 * @param nombreArchivo
	 * @return
	 */
	public boolean verificarArchivo(String carpeta , String nombreArchivo) {
		File revisar;
		
			revisar = new File(carpeta, nombreArchivo);

		return revisar.exists();
	}

}
