package edu.eci.arsw.treecore.persistence;

import edu.eci.arsw.treecore.exceptions.TreeCoreStoreException;

import java.io.File;
import java.util.List;

public interface TreeCoreStore {
    public List<String> consultar(final String ruta) throws TreeCoreStoreException;
    public void uploadFile(final File localFile, final String path) throws TreeCoreStoreException;
    public List<String> downloadFile(String path) throws TreeCoreStoreException;
}