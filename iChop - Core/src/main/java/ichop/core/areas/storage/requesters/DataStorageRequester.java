package ichop.core.areas.storage.requesters;

import ichop.core.areas.storage.models.jms.UserSetAvatarRequest;

public interface DataStorageRequester {
    void setUserAvatar(UserSetAvatarRequest request);
}
