package ichop.storage.helpers;

public interface UserDataHelper {

    void updateAvatar(String username, String avatarAsBase64);
    byte[] getAvatarAsBase64Array(String username);

}
