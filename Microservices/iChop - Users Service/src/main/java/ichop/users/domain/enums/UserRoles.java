package ichop.users.domain.enums;

public enum UserRoles {

    USER(0),MODERATOR(1),ADMIN(2),OWNER(3);

    private Integer position;

    UserRoles(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }
}
