package edu.eci.arsw.treecore.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.eci.arsw.treecore.exceptions.TreeCoreStoreException;

public interface TreeCoreStoreServices {
	/**
	 * 
	 * @param ruta
	 * @return
	 * @throws TreeCoreStoreException
	 */
	public List<String> consultar(final String ruta) throws TreeCoreStoreException;

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

	/**
	 * 
	 * @param tokenId
	 * @return
	 * @throws TreeCoreStoreException
	 */
	public String getAccessToken(int tokenId) throws TreeCoreStoreException;

}