package edu.eci.arsw.treecore.services.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.eci.arsw.treecore.exceptions.PersistenceException;
import edu.eci.arsw.treecore.exceptions.TreeCoreStoreException;
import edu.eci.arsw.treecore.persistence.TreeCoreStore;
import edu.eci.arsw.treecore.persistence.impl.TreeCoreStoreImpl;
import edu.eci.arsw.treecore.persistenceDAO.ProyectoDAO;
import edu.eci.arsw.treecore.services.TreeCoreStoreServices;
@Service
public class TreeCoreStoreServicesImpl implements TreeCoreStoreServices {

    @Autowired
    private ProyectoDAO proyectoDAO;

    @Override
    public List<String> consultar(String ruta) throws TreeCoreStoreException {
        String token = this.getAccessToken(0);
        TreeCoreStore store = new TreeCoreStoreImpl(token);
        List<String> archivos = store.consultar(ruta);
        return archivos;
    }

    @Override
    public List<String> downloadFile(String path) throws TreeCoreStoreException {
        String token = this.getAccessToken(0);
        TreeCoreStore store = new TreeCoreStoreImpl(token);
        return store.downloadFile(path);
    }

    @Override
    public void receiveFile(MultipartFile file, String path, String option) throws TreeCoreStoreException {
        String token = this.getAccessToken(0);
        TreeCoreStore store = new TreeCoreStoreImpl(token);
        store.receiveFile(file, path, option);
    }

    @Override
    public String getAccessToken(int tokenId) throws TreeCoreStoreException {
        try {
            return proyectoDAO.getAccessToken(tokenId);
        } catch (PersistenceException e) {
            throw new TreeCoreStoreException(e.getMessage());
        }

    }


}