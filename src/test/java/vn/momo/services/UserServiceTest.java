package vn.momo.services;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import vn.momo.domains.User;
import vn.momo.repositories.UserRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private final UserRepository mockedUserRepository;
    private final UserService userService;

    public UserServiceTest() {
        this.mockedUserRepository = mock(UserRepository.class);
        this.userService = new UserService(mockedUserRepository);
    }

    @Test
    public void givenUser_whenDepositAmount_thenUserAmountIncreasement_True() {
        // Given
        int initAmount = 1993;
        int depositAmount = 200;
        User user = new User(1, "Vu Mai", initAmount);

        // When
        when(mockedUserRepository.getUser()).thenReturn(user);
        ArgumentCaptor<User> userArgument =
                ArgumentCaptor.forClass(User.class);
        when(mockedUserRepository.save(userArgument.capture()))
                .thenAnswer(iom -> iom.getArgument(0));
        User userResponse = userService.depositBalance(depositAmount);

        // Then
        Assertions.assertEquals(initAmount + depositAmount, userResponse.getBalance());
    }

    @Test
    public void givenUser_whenSubtractAmount_thenUserAmountDescreasement_True() {
        // Given
        int initAmount = 1993;
        int depositAmount = 200;
        User user = new User(1, "Vu Mai", initAmount);

        // When
        when(mockedUserRepository.getUser()).thenReturn(user);
        ArgumentCaptor<User> userArgument =
                ArgumentCaptor.forClass(User.class);
        when(mockedUserRepository.save(userArgument.capture()))
                .thenAnswer(iom -> iom.getArgument(0));
        User userResponse = userService.subtractBalance(depositAmount);

        // Then
        Assertions.assertEquals(initAmount - depositAmount, userResponse.getBalance());
    }
}
