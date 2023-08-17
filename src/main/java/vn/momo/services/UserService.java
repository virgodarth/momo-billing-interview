package vn.momo.services;

import vn.momo.domains.User;
import vn.momo.repositories.UserRepository;

import java.text.MessageFormat;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User depositBalance(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Invalid amount: amount need to larger than 0, but got {0}", amount));
        }
        return this.updateBalance(amount);
    }

    public User subtractBalance(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Invalid amount: amount need to larger than 0, but got {0}", amount));
        }

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
