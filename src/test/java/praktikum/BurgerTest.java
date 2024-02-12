package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static praktikum.constants.Constants.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;
    @Mock
    Bun bun;
    @Mock
    private Ingredient meet, lettuce, cheese;

    @Before
    public void newBurger() {
        burger = new Burger();
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(bun);
        burger.addIngredient(meet);
        burger.addIngredient(lettuce);

        Mockito.when(bun.getPrice()).thenReturn(BUN_PRICE);
        Mockito.when(meet.getPrice()).thenReturn(MEET_PRICE);
        Mockito.when(lettuce.getPrice()).thenReturn(LETTUCE_PRICE);

        float expected = BUN_PRICE * 2 + MEET_PRICE + LETTUCE_PRICE;
        float actualResult = burger.getPrice();

        assertEquals("Ошибка! Стоимость не верна!", expected, actualResult, 0);
    }

    @Test
    public void getReceiptTest() {
        burger.setBuns(bun);
        burger.addIngredient(meet);
        burger.addIngredient(lettuce);

        Mockito.when(bun.getName()).thenReturn(BURGER_NAME);
        Mockito.when(meet.getName()).thenReturn(MEET_NAME);
        Mockito.when(lettuce.getName()).thenReturn(LETTUCE_NAME);
        Mockito.when(bun.getPrice()).thenReturn(BUN_PRICE);
        Mockito.when(meet.getPrice()).thenReturn(MEET_PRICE);
        Mockito.when(lettuce.getPrice()).thenReturn(LETTUCE_PRICE);
        Mockito.when(meet.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(lettuce.getType()).thenReturn(IngredientType.FILLING);

        String price = String.format("%f", BUN_PRICE * 2 + MEET_PRICE + LETTUCE_PRICE);

        String expectedResult = "(==== " + BURGER_NAME + " ====)\r\n" + "= sauce " + MEET_NAME + " =\r\n" + "= filling " + LETTUCE_NAME + " =\r\n" + "(==== " + BURGER_NAME + " ====)\r\n" + "\r\n" + "Price: " + price + "\r\n";
        String actualResult = burger.getReceipt();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(meet);
        burger.addIngredient(lettuce);
        burger.addIngredient(cheese);
        int expectedResult = 3;

        assertEquals(expectedResult, burger.ingredients.size());
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(meet);
        burger.addIngredient(lettuce);
        burger.addIngredient(cheese);
        int indexForRemove = 1;
        burger.removeIngredient(indexForRemove);
        int expectedResult = 2;
        assertEquals(expectedResult, burger.ingredients.size());
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(meet);
        burger.addIngredient(lettuce);
        burger.addIngredient(cheese);

        burger.moveIngredient(0, 2);
        assertEquals(lettuce, burger.ingredients.get(0));
        assertEquals(cheese, burger.ingredients.get(1));
    }
}