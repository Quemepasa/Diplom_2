package user;

import user.models.ChangeUserDataRequest;
import user.models.CreateUserRequest;
import user.models.LoginUserRequest;

import java.util.Random;

import static utils.Utils.randomString;

public class UserGenerator {
    private static final Random random = new Random();
    public static CreateUserRequest randomUser() {
        return new CreateUserRequest()
                .withEmail(randomString(5) + random.nextInt(10000000) + "@yandex.ru")
                .withPassword(randomString(7))
                .withName(randomString(6));
    }

    public static CreateUserRequest randomUserWithoutEmail() {
        return new CreateUserRequest()
                .withPassword(randomString(7))
                .withName(randomString(6));
    }

    public static CreateUserRequest randomUserWithoutPassword() {
        return new CreateUserRequest()
                .withEmail(randomString(5) + random.nextInt(10000000) + "@yandex.ru")
                .withName(randomString(6));
    }

    public static CreateUserRequest randomUserWithoutName() {
        return new CreateUserRequest()
                .withEmail(randomString(5) + random.nextInt(10000000) + "@yandex.ru")
                .withPassword(randomString(7));
    }

    public static LoginUserRequest randomUserLogin() {
        return new LoginUserRequest()
                .setEmail(randomString(5) + random.nextInt(10000000) + "@yandex.ru")
                .setPassword(randomString(7));
    }

    public static ChangeUserDataRequest changeUserEmail(CreateUserRequest createUserRequest) {
        return new ChangeUserDataRequest()
                .setEmail(randomString(5) + random.nextInt(10000000) + "@yandex.ru")
                .setPassword(createUserRequest.getPassword())
                .setName(createUserRequest.getName());
    }

    public static ChangeUserDataRequest changeUserPassword(CreateUserRequest createUserRequest) {
        return new ChangeUserDataRequest()
                .setEmail(createUserRequest.getEmail())
                .setPassword(randomString(7))
                .setName(createUserRequest.getName());
    }

    public static ChangeUserDataRequest changeUserName(CreateUserRequest createUserRequest) {
        return new ChangeUserDataRequest()
                .setEmail(createUserRequest.getEmail())
                .setPassword(createUserRequest.getPassword())
                .setName(randomString(6));
    }
}
