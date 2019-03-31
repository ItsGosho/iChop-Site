package com.ichop.webstorage.components.dropbox;

import com.dropbox.core.v2.files.CreateFolderResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;

import java.io.InputStream;

public interface DropboxServices {

    InputStream downloadFile(String filePath);

    FileMetadata uploadFile(String filePath, InputStream fileStream);

    CreateFolderResult createFolder(String folderPath);

    ListFolderResult listFolder(String folderPath, boolean recursiveListing, long limit);

    ListFolderResult listFolderContinue(String cursor);

    void deleteFile(String filePath);

    void deleteFolder(String folderPath);


}
