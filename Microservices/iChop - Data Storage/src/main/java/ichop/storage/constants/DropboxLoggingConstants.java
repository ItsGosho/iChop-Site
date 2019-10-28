package ichop.storage.constants;

import static ichop.storage.common.constants.ColorCodesConstants.ANSI_BLACK;
import static ichop.storage.common.constants.ColorCodesConstants.ANSI_GREEN;

public final class DropboxLoggingConstants {

    public static final String DOWNLOAD_SUCCESSFUL = wrap("Download was successful for path -> %s");
    public static final String UPLOAD_SUCCESSFUL = wrap("Upload was successful to path -> %s");
    public static final String FOLDER_CREATION_SUCCESSFUL = wrap("Folder creation was successful for path -> %s");
    public static final String FOLDER_DELETE_SUCCESSFUL = wrap("Folder deletion was successful for path -> %s");
    public static final String FILE_DELETE_SUCCESSFUL = wrap("File deletion was successful for path -> %s");

    private static String wrap(String s) {
        return ANSI_GREEN + s + ANSI_BLACK;
    }

}
