package edu.eci.arsw.treecore.services;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.eci.arsw.treecore.exceptions.TreeCoreStoreException;

public interface TreeCoreStoreServices {
    public List<String> consultar(final String ruta) throws TreeCoreStoreException;
    public List<String> downloadFile(String path) throws TreeCoreStoreException;
    public void receiveFile(MultipartFile file, String path, String option) throws TreeCoreStoreException;
    public String getAccessToken(int tokenId) throws TreeCoreStoreException;
}