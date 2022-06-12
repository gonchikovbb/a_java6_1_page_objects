package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        public String cardNumber;
    }

    public static CardInfo getFirstCardNumber() {
        return new CardInfo("5559000000000001");
    }

    public static CardInfo getSecondCardNumber() {
        return new CardInfo("5559000000000002");
    }

    public static CardInfo getThirdCardNumber() {
        return new CardInfo("5559000000000003");
    }
}