package vn.momo.repositories;

import vn.momo.domains.User;

public class UserRepository {
    private User user;

    public UserRepository() {
        this.user = new User(1, "Vu Mai", 0);
    }

    public User getUser() {
        return user;
    }

    public User save(User user) {
        this.user = user;
        return user;
    }
}
