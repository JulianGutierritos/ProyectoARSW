package edu.eci.arsw.treecore.persistence;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.util.IOUtil.ProgressListener;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DownloadErrorException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.users.FullAccount;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.io.IOException;

@Service
public class TreeCoreStore {
    private static final String ACCESS_TOKEN = "OAn_tALL5dAAAAAAAAAAL_psR2H47WFgNlMjNZGH3YmmYD5HNm_DfBwCVCF8yCaU";

    public void init() throws DbxException, IOException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("/Asignaturas");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = client.files().listFolder("/Asignaturas");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }
        // File localFile = new File("Help.mp3");
        // if (localFile.exists()){
        //     System.out.println(localFile.getAbsolutePath());
        //     uploadFile(client,localFile,"/Help.mp3");
        // }
        downloadFile(client, "test.txt", "/Help.mp3");
    }

    private static void uploadFile(DbxClientV2 client, File localFile, String path) {

        try{
            client.files().deleteV2(path);
        }catch(Exception e){

        }

        try (InputStream in = new FileInputStream(localFile)) {
            ProgressListener progressListener = l -> printProgress(l, localFile.length());
            
            FileMetadata metadata = client.files().uploadBuilder(path)
                .withMode(WriteMode.ADD)
                .withClientModified(new Date(localFile.lastModified()))
                .uploadAndFinish(in, progressListener);

            System.out.println(metadata.toStringMultiline());
        } catch (UploadErrorException ex) {
            System.err.println("Error uploading to Dropbox: " + ex.getMessage());
        } catch (DbxException ex) {
            System.err.println("Error uploading to Dropbox: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error reading from file \"" + localFile + "\": " + ex.getMessage());
        }
    }
    private static void printProgress(long uploaded, long size) {
        System.out.printf("Uploaded %12d / %12d bytes (%5.2f%%)\n", uploaded, size, 100 * (uploaded / (double) size));
    }

    public void downloadFile(DbxClientV2 client,String fileName,String path) throws DownloadErrorException, DbxException, IOException {
        DbxDownloader<FileMetadata> downloader = client.files().download(path);
        System.out.println(client.files().getTemporaryLink(path));
        // try {
        //     FileOutputStream out = new FileOutputStream(fileName);
        //     downloader.download(out);
        //     out.close();
        // } catch (DbxException ex) {
        //     System.out.println(ex.getMessage());
        // }
    
    }
}