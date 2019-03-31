package com.ichop.webstorage.components.dropbox;

import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class DropboxServicesImp implements DropboxServices {

    private final DbxClientV2 client;

    @Autowired
    public DropboxServicesImp(DbxClientV2 client) {
        this.client = client;
    }

    @Override
    public InputStream downloadFile(String filePath) {
        return handleDropboxAction(() -> client.files().download(filePath).getInputStream(),
                String.format("Error downloading file: %s", filePath));
    }

    @Override
    public FileMetadata uploadFile(String filePath, InputStream fileStream) {
        return handleDropboxAction(() -> client.files().uploadBuilder(filePath).uploadAndFinish(fileStream),
                String.format("Error uploading file: %s", filePath));
    }

    @Override
    public CreateFolderResult createFolder(String folderPath) {
        return handleDropboxAction(() -> client.files().createFolderV2(folderPath), "Error creating folder");
    }

    @Override
    public ListFolderResult listFolder(String folderPath, boolean recursiveListing, long limit) {
        ListFolderBuilder listFolderBuilder = client.files().listFolderBuilder(folderPath);
        listFolderBuilder.withRecursive(recursiveListing);
        listFolderBuilder.withLimit(limit);

        return handleDropboxAction(listFolderBuilder::start, String.format("Error listing folder: %s", folderPath));
    }

    @Override
    public ListFolderResult listFolderContinue(String cursor) {
        return handleDropboxAction(() -> client.files().listFolderContinue(cursor), "Error listing folder");
    }

    @Override
    public void deleteFile(String filePath) {
        handleDropboxAction(() -> client.files().deleteV2(filePath), String.format("Error deleting file: %s", filePath));
    }

    @Override
    public void deleteFolder(String folderPath) {
        handleDropboxAction(() -> client.files().deleteV2(folderPath), String.format("Error deleting folder: %s", folderPath));
    }

    private <T> T handleDropboxAction(DropboxActionResolver<T> action, String exceptionMessage) {
        try {
            return action.perform();
        } catch (Exception e) {
            String messageWithCause = String.format("%s with cause: %s", exceptionMessage, e.getMessage());
            System.out.println(messageWithCause);
        }
        return null;
    }

}
