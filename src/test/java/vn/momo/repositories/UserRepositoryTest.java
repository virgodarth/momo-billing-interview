package vn.momo.repositories;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import vn.momo.domains.User;

import java.lang.reflect.Field;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRepositoryTest {
    private final User mockedUser;

    public UserRepositoryTest() {
        this.mockedUser = mock(User.class);
    }

    @Test
    public void givenUserReflection_whenGetUser_thenReturnUser_True() throws NoSuchFieldException, IllegalAccessException {
        // Given
        UserRepository userRepository = new UserRepository();
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
    public void givenUser_whenUpdateUser_thenReturnUpdatedUser_True() {
        // Given
        UserRepository userRepository = new UserRepository();

        // When
        User userRequest = new User(2, "Tester 2", 1993);
        User updatedUser = userRepository.save(userRequest);

        // Then
        Assertions.assertEquals(userRequest.getId(), updatedUser.getId());
        Assertions.assertEquals(userRequest.getUsername(), updatedUser.getUsername());
        Assertions.assertEquals(userRequest.getBalance(), updatedUser.getBalance());
    }
}
