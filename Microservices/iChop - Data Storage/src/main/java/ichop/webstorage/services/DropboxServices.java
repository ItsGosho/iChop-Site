package ichop.webstorage.services;

import com.dropbox.core.v2.files.CreateFolderResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;

import java.io.InputStream;

public interface DropboxServices {

    InputStream downloadFile(String filePath);

    FileMetadata uploadFile(String filePath, InputStream fileStream);

    CreateFolderResult createFolder(String folderPath);

    void deleteFile(String filePath);



}
