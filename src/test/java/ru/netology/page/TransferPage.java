package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;

public class TransferPage {

    private SelenideElement sumTransfer = $("[data-test-id='amount'] input");
    private SelenideElement fromTransfer = $("[data-test-id='from'] input");
    private SelenideElement buttonTransfer = $("[data-test-id='action-transfer']");
    private SelenideElement headTransfer = $(byText("Пополнение карты"));
    private SelenideElement notification = $("[data-test-id=error-notification]");

    public TransferPage() {
        headTransfer.shouldBe(visible);
    }

    public DashboardPage makeMoneyTransfer(String amountTransfer, DataHelper.CardInfo cardInfo) {
        sumTransfer.setValue(amountTransfer);
        fromTransfer.setValue(cardInfo.getCardNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }

    public void errorMessage() {
        notification.shouldHave(Condition.text("Произошла ошибка"));
    }
}