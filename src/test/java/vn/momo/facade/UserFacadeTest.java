package vn.momo.facade;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import vn.momo.domains.User;
import vn.momo.services.UserService;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserFacadeTest {
    private final UserService mockedUserService;
    private final UserFacade userFacade;

    public UserFacadeTest() {
        this.mockedUserService = mock(UserService.class);
        this.userFacade = new UserFacade(mockedUserService);
    }

    @Test
    public void givenUser_whenDepositAmount_thenReturnUser() {
        // Given
        User user = new User(1, "Vu Mai", 1993);

        // When
        when(mockedUserService.depositBalance(anyInt())).thenReturn(user);

        User userResponse = userFacade.depositBalance(100);

        // Then
        Assertions.assertEquals(user.getId(), userResponse.getBalance());
    }

    @Test
    public void givenUser_whenNegativeDepositAmount_thenThrowException() {
        // Given
        User user = new User(1, "Vu Mai", 1993);

        // When
        when(mockedUserService.depositBalance(anyInt())).thenReturn(user);
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> userFacade.depositBalance(-100));

        // Then
        Assertions.assertTrue(exception instanceof IllegalArgumentException);
    }
}
