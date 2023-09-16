package user;

import models.CreateUserRequest;
import models.LoginUserRequest;

import java.util.Random;

import static utils.Utils.randomString;

public class UserGenerator {
    private static Random random = new Random();
    public static CreateUserRequest randomUserForRegistration() {
        return new CreateUserRequest()
                .withEmail(randomString(5) + random.nextInt(10000000) + "@yandex.ru")
                .withPassword(randomString(7))
                .withName(randomString(6));
    }

    public static CreateUserRequest userWithoutEmail() {
        return new CreateUserRequest()
                .withPassword(randomString(7))
                .withName(randomString(6));
    }

    public static CreateUserRequest userWithoutPassword() {
        return new CreateUserRequest()
                .withEmail(randomString(5) + random.nextInt(10000000) + "@yandex.ru")
                .withName(randomString(6));
    }

    public static CreateUserRequest userWithoutName() {
        return new CreateUserRequest()
                .withEmail(randomString(5) + random.nextInt(10000000) + "@yandex.ru")
                .withPassword(randomString(6));
    }

    public static LoginUserRequest randomUserForLogin() {
        return new LoginUserRequest()
                .setEmail(randomString(5) + random.nextInt(10000000) + "@yandex.ru")
                .setPassword(randomString(6));
    }
}
