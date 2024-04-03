import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.containsString;
import org.hamcrest.MatcherAssert;
import pages.homePage;
import pages.orderPage;

@RunWith(Parameterized.class)
public class orderTest {
    @Rule
    public driverRule driverFactory = new driverRule();
    private final String name;
    private final String family;
    private final String data;
    private final String number;
    private final String adress;
    private final String period;
    private final String chosenButton;
    private final int metroPoint;

    public orderTest(String chosenButton, String name, String family, String adress, int metroPoint, String number, String data, String period) {
        this.chosenButton = chosenButton;
        this.name = name;
        this.adress = adress;
        this.metroPoint = metroPoint;
        this.data = data;
        this.family = family;
        this.number = number;
        this.period = period;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                {"Top","Илья", "Сахаров", "ул. Пушкина", 1, "88002132212", "31", "двое суток"},
                {"Bottom", "Петр", "Петров", "ул. Ангелов", 2, "+78002132212", "05", "сутки"},
        };
    }

    @Test
    public void orderTest() {
        orderPage objOrderPage = new orderPage(driverFactory.getDriver());
        homePage objHomePage = new homePage(driverFactory.getDriver());

        if (chosenButton == "Top") {
            objHomePage.clickOrderTopButton();
        } else objHomePage.clickOrderBottomButton();

        objOrderPage.order(name, family, adress, metroPoint, number, data, period);
        MatcherAssert.assertThat(objOrderPage.headerOrderConfirm(), containsString("Заказ оформлен"));
    }
}
