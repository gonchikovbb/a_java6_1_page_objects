package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage    = verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferFromFirstCardToSecond() {
        var dashboardPage = new DashboardPage();
        var firstNumber = DataHelper.getFirstCardNumber();
        var secondNumber = DataHelper.getSecondCardNumber();
        int amount = 1000;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstNumber) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondNumber) + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondNumber);
        dashboardPage = transferPage.makeMoneyTransfer(String.valueOf(amount), firstNumber);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstNumber);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondNumber);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
    @Test
    void shouldTransferFromSecondCardToFirst() {
        var dashboardPage = new DashboardPage();
        var firstNumber = DataHelper.getSecondCardNumber();
        var secondNumber = DataHelper.getFirstCardNumber();
        int amount = 1000;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstNumber) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondNumber) + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondNumber);
        dashboardPage = transferPage.makeMoneyTransfer(String.valueOf(amount), firstNumber);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstNumber);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondNumber);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void shouldErrorTransfer() {
        var dashboardPage = new DashboardPage();
        var firstNumber = DataHelper.getThirdCardNumber();
        var secondNumber = DataHelper.getFirstCardNumber();
        int amount = 200;
        var transferPage = dashboardPage.selectCardToTransfer(secondNumber);
        transferPage.makeMoneyTransfer(String.valueOf(amount), firstNumber);
        transferPage.errorMessage();
    }

}