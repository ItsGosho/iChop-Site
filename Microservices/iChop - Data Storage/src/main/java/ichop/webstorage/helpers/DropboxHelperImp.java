package ichop.webstorage.helpers;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

import static ichop.webstorage.constants.DropboxLoggingConstants.*;

@Service
public class DropboxHelperImp implements DropboxHelper {

    private final Logger LOG = LogManager.getLogger(DropboxHelperImp.class);

    private final DbxClientV2 client;

    @Autowired
    public DropboxHelperImp(DbxClientV2 client) {
        this.client = client;
    }

    @Override
    public InputStream downloadFile(String filePath) {

        return handleDropboxAction(() -> {
            InputStream inputStream = this.client
                    .files()
                    .download(filePath)
                    .getInputStream();

            LOG.error(String.format(DOWNLOAD_SUCCESSFUL, filePath));
            return inputStream;
        }, filePath);
    }

    @Override
    public FileMetadata uploadFile(String filePath, InputStream fileStream) {

        return handleDropboxAction(() -> {
            FileMetadata fileMetadata = this.client
                    .files()
                    .uploadBuilder(filePath)
                    .uploadAndFinish(fileStream);

            LOG.error(String.format(UPLOAD_SUCCESSFUL, filePath));
            return fileMetadata;
        }, filePath);
    }

    @Override
    public CreateFolderResult createFolder(String folderPath) {
        return handleDropboxAction(() -> {
            CreateFolderResult creationResult = this.client.files().createFolderV2(folderPath);

            LOG.error(String.format(FOLDER_CREATION_SUCCESSFUL, folderPath));
            return creationResult;
        }, folderPath);
    }

    @Override
    public void deleteFile(String filePath) {
        handleDropboxAction(() -> {
            this.client.files().deleteV2(filePath);

            LOG.error(String.format(FILE_DELETE_SUCCESSFUL, filePath));
            return null;
        }, filePath);
    }


    private <T> T handleDropboxAction(DropboxActionResolver<T> action, String path) {
        try {
            return action.perform();
        } catch (Exception e) {
            LOG.error(String.format("Error executing operation for path %s due to %s", path, e.getMessage()));
        }
        return null;
    }

    @FunctionalInterface
    private interface DropboxActionResolver<T> {

        T perform() throws Exception;

    }

}
