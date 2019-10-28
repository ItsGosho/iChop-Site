package ichop.users.domain.enums;

public enum Roles {

    USER(0),MODERATOR(1),ADMIN(2),OWNER(3);

    private Integer position;

    Roles(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }
}
