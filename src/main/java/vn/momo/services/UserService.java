package vn.momo.services;

import vn.momo.domains.User;
import vn.momo.repositories.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User depositBalance(int amount) {
        return this.updateBalance(amount);
    }

    public User subtractBalance(int amount) {
        return this.updateBalance(-1 * amount);
    }

    private User updateBalance(int amount) {
        final User user = this.userRepository.getUser();
        if (user.getBalance() + amount < 0) {
            throw new IllegalArgumentException("Invalid balance: your balance is not enough");
        }
        user.setBalance(user.getBalance() + amount);
        return this.userRepository.save(user);
    }
}
