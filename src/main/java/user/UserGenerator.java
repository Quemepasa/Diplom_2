package user;

import models.CreateUserRequest;

import java.util.Random;

import static utils.Utils.randomString;

public class UserGenerator {
    private static Random random = new Random();
    public static CreateUserRequest randomUser() {
        return new CreateUserRequest()
                .withEmail(randomString(7) + random.nextInt(10000000) + "@yandex.ru")
                .withPassword(randomString(12))
                .withName(randomString(10));
    }

    public static CreateUserRequest userWithoutEmail() {
        return new CreateUserRequest()
                .withPassword(randomString(12))
                .withName(randomString(10));
    }

    public static CreateUserRequest userWithoutPassword() {
        return new CreateUserRequest()
                .withEmail(randomString(7) + random.nextInt(10000000) + "@yandex.ru")
                .withName(randomString(10));
    }

    public static CreateUserRequest userWithoutName() {
        return new CreateUserRequest()
                .withEmail(randomString(7) + random.nextInt(10000000) + "@yandex.ru")
                .withPassword(randomString(12));
    }
}
