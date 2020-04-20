package edu.eci.arsw.treecore.persistence;

import edu.eci.arsw.treecore.exceptions.TreeCoreStoreException;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface TreeCoreStore {
    public List<String> consultar(final String ruta) throws TreeCoreStoreException;
    public void uploadFile(File localFile, String path) throws TreeCoreStoreException;
    public List<String> downloadFile(String path) throws TreeCoreStoreException;
    public void receiveFile(MultipartFile file, String path) throws TreeCoreStoreException;
}