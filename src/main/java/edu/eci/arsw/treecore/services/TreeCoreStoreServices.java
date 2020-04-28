package edu.eci.arsw.treecore.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.eci.arsw.treecore.exceptions.TreeCoreStoreException;

public interface TreeCoreStoreServices {
	/**
	 * Metodo para consultar archivos de una ruta
	 * @param ruta Ruta de la carpeta a consultar
	 * @return Lista de los archivos en la carpeta. 
	 * @throws TreeCoreStoreException Si la ruta no existe.
	 */
	public List<String> consultar(String ruta) throws TreeCoreStoreException;

	/**
	 * Metodo para descargar un archivo.
	 * @param path ruta del archivo.
	 * @return Nombre y enlace de descarga del archivo.
	 * @throws TreeCoreStoreException Si la ruta no existe.
	 */
	public List<String> downloadFile(String path) throws TreeCoreStoreException;

	/**
	 * Metodo que recibe parte de un archivo para subirlo.
	 * @param file archivo obtenido de la solicitud http
	 * @param path ruta para subir el archivo
	 * @param option opcion, si es para publicar o sustituir un archivo.
	 * @throws TreeCoreStoreException
	 */
	public void receiveFile(MultipartFile file, String path, String option) throws TreeCoreStoreException;

	/**
	 * Metodo para obtener el token de acceso de la app.
	 * @param tokenId id en la base de datos
	 * @return token de acceso a la carpeta
	 * @throws TreeCoreStoreException
	 */
	public String getAccessToken(int tokenId) throws TreeCoreStoreException;

}