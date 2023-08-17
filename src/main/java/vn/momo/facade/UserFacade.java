package vn.momo.facade;

import vn.momo.domains.User;
import vn.momo.services.UserService;

import java.text.MessageFormat;

public class UserFacade {
    private final UserService userService;

    public UserFacade(UserService userService) {
        this.userService = userService;
    }

    public User depositBalance(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Invalid amount: amount need to larger than 0, but got {0}", amount));
        }
        return this.userService.depositBalance(amount);
    }
}
