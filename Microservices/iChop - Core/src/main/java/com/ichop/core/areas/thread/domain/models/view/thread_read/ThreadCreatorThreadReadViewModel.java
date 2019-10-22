package com.ichop.core.areas.thread.domain.models.view.thread_read;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThreadCreatorThreadReadViewModel {

    private String username;
    private String minecraftAccountName;
    private String minecraftAccountUUID;
    private Integer totalComments;

}
