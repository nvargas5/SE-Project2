import csc4700.Backup;
import csc4700.Item;
import csc4700.ShoppingCart;
import org.junit.Test;

import static org.junit.Assert.*;

public class BackupTest {

    @Test
    public void testSerializeCartNull() {
        ShoppingCart sc = null;
        Backup b = new Backup();
        try {
            assertEquals("null", b.serializeShoppingCart(sc));
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testSerializeCart() {
        Item i1 = new Item();
        i1.setName("apple");
        i1.setDescription("yum");
        i1.setCost(2);

        Item i2 = new Item();
        i2.setName("pear");
        i2.setDescription("ew");
        i2.setCost(3);

        ShoppingCart sc = new ShoppingCart();
        sc.addItem(i1);
        sc.addItem(i2);

        Backup b = new Backup();

        String expected = "apple,2,yum,1" + System.getProperty("line.separator")
                + "pear,3,ew,1" + System.getProperty("line.separator");
        assertEquals(expected, b.serializeShoppingCart(sc));
    }
}
