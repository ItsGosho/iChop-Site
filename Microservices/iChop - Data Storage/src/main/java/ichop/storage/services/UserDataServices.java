package ichop.storage.services;

public interface UserDataServices {

    void updateAvatar(String username, String avatarAsBase64);
    byte[] getAvatarAsBase64Array(String username);

}
