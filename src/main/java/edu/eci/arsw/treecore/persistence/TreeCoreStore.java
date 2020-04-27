package edu.eci.arsw.treecore.persistence;

import edu.eci.arsw.treecore.exceptions.TreeCoreStoreException;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface TreeCoreStore {
	/**
	 * 
	 * @param ruta
	 * @return
	 * @throws TreeCoreStoreException
	 */
	public List<String> consultar(final String ruta) throws TreeCoreStoreException;

	/**
	 * 
	 * @param localFile
	 * @param path
	 * @param option
	 * @throws TreeCoreStoreException
	 */
	public void uploadFile(File localFile, String path, String option) throws TreeCoreStoreException;

	/**
	 * 
	 * @param path
	 * @return
	 * @throws TreeCoreStoreException
	 */
	public List<String> downloadFile(String path) throws TreeCoreStoreException;

	/**
	 * 
	 * @param file
	 * @param path
	 * @param option
	 * @throws TreeCoreStoreException
	 */
	public void receiveFile(MultipartFile file, String path, String option) throws TreeCoreStoreException;
}