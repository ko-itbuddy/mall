package com.example.mall.core.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

class UserTest {

    @Nested
    @DisplayName("Create")
    class Create {

        @Test
        @DisplayName("성공 - USER")
        void success_0() {
            assertDoesNotThrow(() -> {
                new User(
                    null,
                    "ko.itbuddy@gmail.com",
                    "firstName",
                    "lastName",
                    "password",
                    RoleType.USER
                );
            });
        }

        @Test
        @DisplayName("성공 - ADMIN")
        void success_1() {
            assertDoesNotThrow(() -> {
                new User(
                    null,
                    "ko.itbuddy@gmail.com",
                    "firstName",
                    "lastName",
                    "password",
                    RoleType.ADMIN
                );
            });
        }

        @Test
        @DisplayName("성공 - MANAGER")
        void success_2() {
            assertDoesNotThrow(() -> {
                new User(
                    null,
                    "user@example.com",
                    "firstName",
                    "lastName",
                    "password",
                    RoleType.MANAGER
                );
            });
        }

        @NullSource
        @EmptySource
        @ParameterizedTest
        @DisplayName("실패 - 이메일이 null, blank 인 경우")
        void fail_0(String email) {
            assertThrows(IllegalArgumentException.class, () -> {
                new User(
                    null,
                    email,
                    "firstName",
                    "lastName",
                    "password",
                    RoleType.USER
                );
            });
        }

        @Test
        @DisplayName("실패 - 이메일이 최대길이를 초과하는 경우")
        void fail_1() {
            String email = "a".repeat(User.MAX_EMAIL_LENGTH - 11) + "@example.com";
            assertThrows(IllegalArgumentException.class, () -> {
                new User(
                    null,
                    email,
                    "firstName",
                    "lastName",
                    "password",
                    RoleType.USER
                );
            });
        }

        @Test
        @DisplayName("성공 - 이메일이 형식이 맞지 않는 경우")
        void fail_2() {
            assertThrows(IllegalArgumentException.class, () -> {
                new User(
                    null,
                    "user@example",
                    "firstName",
                    "lastName",
                    "password",
                    RoleType.USER
                );
            });
        }

        @NullSource
        @EmptySource
        @ParameterizedTest
        @DisplayName("실패 - 이름이 null, blank 인경우")
        void fail_3(String firstName) {
            assertThrows(IllegalArgumentException.class, () -> {
                    new User(
                        null,
                        "user@example.com",
                        firstName,
                        "lastName",
                        "password",
                        RoleType.USER
                    );
                }
            );
        }

        @Test
        @DisplayName("실패 - 이름이 최대길이를 초과하는 경우")
        void fail_4() {
            String firstName = "a".repeat(User.MAX_FIRSTNAME_LENGTH + 1);
            assertThrows(IllegalArgumentException.class, () -> {
                    new User(
                        null,
                        "user@example.com",
                        firstName,
                        "lastName",
                        "password",
                        RoleType.USER
                    );
                }
            );
        }

        @NullSource
        @EmptySource
        @ParameterizedTest
        @DisplayName("실패 - 성이 null, blank 인경우")
        void fail_5(String lastName) {
            assertThrows(IllegalArgumentException.class, () -> {
                    new User(
                        null,
                        "user@example.com",
                        "firstName",
                        lastName,
                        "password",
                        RoleType.USER
                    );
                }
            );
        }

        @Test
        @DisplayName("실패 - 성이 최대길이를 초과하는 경우")
        void fail_6() {
            String lastName = "a".repeat(User.MAX_LASTNAME_LENGTH + 1);
            assertThrows(IllegalArgumentException.class, () -> {
                    new User(
                        null,
                        "user@example.com",
                        "firstName",
                        lastName,
                        "password",
                        RoleType.USER
                    );
                }
            );
        }

        @NullSource
        @EmptySource
        @ParameterizedTest
        @DisplayName("실패 - 비밀번호가 null, blank 인 경우")
        void fail_7(String password) {
            assertThrows(IllegalArgumentException.class, () -> {
                    new User(
                        null,
                        "user@example.com",
                        "firstName",
                        "lastName",
                        password,
                        RoleType.USER
                    );
                }
            );
        }

        @Test
        @DisplayName("실패 - 비밀번호가 최대길이를 초과하는 경우")
        void fail_8() {
            String password = "a".repeat(User.MAX_PASSWORD_LENGTH + 1);
            assertThrows(IllegalArgumentException.class, () -> {
                    new User(
                        null,
                        "user@example.com",
                        "firstName",
                        "lastName",
                        password,
                        RoleType.USER
                    );
                }
            );
        }
    }
}