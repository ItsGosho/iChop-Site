package com.ichop.core.areas.role.domain.entities;

public enum UserRoles {

    USER(0),MODERATOR(1),ADMIN(2),OWNER(3);

    private int position;

    UserRoles(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
