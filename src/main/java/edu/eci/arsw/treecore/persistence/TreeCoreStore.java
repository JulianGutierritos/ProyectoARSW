package edu.eci.arsw.treecore.persistence;

import edu.eci.arsw.treecore.exceptions.TreeCoreStoreException;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface TreeCoreStore {
	/**
	 * Metodo para consultar archivos de una ruta
	 * @param ruta Ruta de la carpeta a consultar
	 * @return Lista de los archivos en la carpeta. 
	 * @throws TreeCoreStoreException Si la ruta no existe.
	 */
	public List<String> consultar(final String ruta) throws TreeCoreStoreException;

	/**
	 * Metodo que sube un archivo.
	 * @param localFile archivo que se subira.
	 * @param path ruta para subir el archivo
	 * @param option opcion, si es para publicar o sustituir un archivo.
	 * @throws TreeCoreStoreException
	 */
	public void uploadFile(File localFile, String path, String option) throws TreeCoreStoreException;

	/**
	 * Metodo para descargar un archivo.
	 * @param path ruta del archivo.
	 * @return Nombre y enlace de descarga del archivo.
	 * @throws TreeCoreStoreException Si la ruta no existe.
	 */
	public List<String> downloadFile(String path) throws TreeCoreStoreException;

	/**
	 * Metodo que recibe parte de un archivo y lo sube.
	 * @param file archivo obtenido de la solicitud http
	 * @param path ruta para subir el archivo
	 * @param option opcion, si es para publicar o sustituir un archivo.
	 * @throws TreeCoreStoreException
	 */
	public void receiveFile(MultipartFile file, String path, String option) throws TreeCoreStoreException;
}