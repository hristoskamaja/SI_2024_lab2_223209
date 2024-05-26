import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
public class SILab2Test {
    @Test
    public void testAllItemsNull() {
        try {
            SILab2.checkCart(null, 100);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("allItems list can't be null!", e.getMessage());
        }
    }

    @Test
    public void testEmptyItemName() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("",  "123", 100,0));
        assertTrue(SILab2.checkCart(items, 100));
    }

    @Test
    public void testEmptyItemNameAndPayment() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("", "123", 100,  0));
        assertFalse(SILab2.checkCart(items, 99));
    }

    @Test
    public void testValidItemNameAndPayment() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("valid", "x", 12, 0));
        assertTrue(SILab2.checkCart(items, 12));
    }

    @Test
    public void testValidItemWithDiscount() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("valid",  "023", 100, 0.1F));
        items.add(new Item("valid",  "100", 123,0));
        assertTrue(SILab2.checkCart(items, 301));
    }

    @Test
    public void testInvalidItemBarcode() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("valid", "023", 100, 0.1F));
        items.add(new Item("valid", "300", 123,  0));
        try {
            SILab2.checkCart(items, 301);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("Invalid character in item barcode!", e.getMessage());
        }
    }


    @Test
    public void testAllConditionsTrue() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1",  "012345", 350, 0.1F));
        assertEquals(true, SILab2.checkCart(items, 400));
    }

    @Test
    public void testFirstConditionFalse() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "012345", 250, 0.1F));
        assertEquals(true, SILab2.checkCart(items, 275));
    }

    @Test
    public void testSecondConditionFalse() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "012345", 350,  0));
        assertEquals(true, SILab2.checkCart(items, 350));
    }

    @Test
    public void testThirdConditionFalse() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "112345", 350, 0.1F));
        assertEquals(true, SILab2.checkCart(items, 385));
    }


}
