package vn.momo.repositories;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import vn.momo.domains.User;

import java.lang.reflect.Field;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRepositoryTest {
    private final User mockedUser;
    private final UserRepository userRepository;

    public UserRepositoryTest() {
        this.mockedUser = mock(User.class);
        this.userRepository = new UserRepository();
    }

    @Test
    public void givenUserReflection_whenGetUser_thenReturnUser() throws NoSuchFieldException, IllegalAccessException {
        // Given
        Class<?> repositoryClass = UserRepository.class;
        Field field = repositoryClass.getDeclaredField("user");
        field.setAccessible(true);
        field.set(userRepository, mockedUser);
        field.setAccessible(false);

        // When
        when(mockedUser.getUsername()).thenReturn("Vu Mai");
        when(mockedUser.getBalance()).thenReturn(1993);

        // Then
        User user = userRepository.getUser();
        Assertions.assertEquals("Vu Mai", user.getUsername());
        Assertions.assertEquals(1993, user.getBalance());
    }

    @Test
    public void givenUser_whenUpdateUser_thenReturnUpdatedUser() {
        // Given
        User userRequest = new User(2, "Tester 2", 1993);

        // When
        User updatedUser = userRepository.save(userRequest);

        // Then
        Assertions.assertEquals(userRequest.getId(), updatedUser.getId());
        Assertions.assertEquals(userRequest.getUsername(), updatedUser.getUsername());
        Assertions.assertEquals(userRequest.getBalance(), updatedUser.getBalance());
    }
}
