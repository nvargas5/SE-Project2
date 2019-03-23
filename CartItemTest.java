import csc4700.CartItem;
import csc4700.Item;
import csc4700.exceptions.InvalidCountException;
import org.junit.Test;
import static org.junit.Assert.*;

public class CartItemTest {

    @Test
    public void testConstructor(){
        Item i = new Item();
        i.setName("apple");
        CartItem c1 = new CartItem(i);

        assertEquals(0, c1.getCount());
        assertTrue(c1.getItem().getName().equals(i.getName()));
    }


    @Test
    public void testConstructorNull(){
        CartItem c1 = new CartItem(null);

        assertEquals(0, c1.getCount());
        assertNull(c1.getItem());
    }

    @Test
    public void testIncrementCountByOne(){
        Item i = new Item();
        CartItem c1 = new CartItem(i);

        c1.incrementCountByOne();
        assertEquals(1, c1.getCount());
    }

    @Test
    public void testDecrementCountByOne(){
        Item i = new Item();
        CartItem c1 = new CartItem(i);
        c1.setCount(2);
        c1.decrementCountByOne();
        assertEquals(1, c1.getCount());
    }

    @Test
    public void testEqualsSameObj() {
        Item i = new Item();
        CartItem c = new CartItem(i);
        assertTrue(c.equals(c));
    }

    @Test
    public void testEquals() {
        Item i1 = new Item();
        i1.setName("apple");
        i1.setCost(1);
        i1.setDescription("ew");
        CartItem c1 = new CartItem(i1);

        Item i2 = new Item();
        i2.setName("apple");
        i2.setCost(2);
        i2.setDescription("yum");
        CartItem c2 = new CartItem(i2);

        assertTrue(c1.equals(c2));
    }

    @Test
    public void testNotEqualsNull() {
        Item i1 = new Item();
        CartItem c1 = new CartItem(i1);
        CartItem c2 = null;

        assertFalse(c1.equals(c2));
    }

    @Test
    public void testNotEqualsDiffClass() {
        Item i1 = new Item();
        CartItem c1 = new CartItem(i1);
        String t = "test";

        assertFalse(c1.equals(t));
    }

    @Test
    public void testHashCode(){
        Item i = new Item();
        i.setName("apple");
        CartItem c1 = new CartItem(i);

        assertEquals(c1.hashCode(), i.hashCode());
    }

    @Test
    public void testHashCodeNoItem(){
        CartItem c1 = new CartItem(null);

        assertEquals(c1.hashCode(), 0);
    }

    @Test
    public void testGetItem(){
        Item i = new Item();
        i.setName("apple");
        CartItem c1 = new CartItem(i);

        assertTrue(c1.getItem().equals(i));
    }

    @Test
    public void testSetItem(){
        Item i1 = new Item();
        Item i2 = new Item();

        i1.setName("apple");
        i1.setCost(1);
        i1.setDescription("yum");

        i2.setName("pear");
        i2.setCost(1);
        i2.setDescription("yum");

        CartItem c1 = new CartItem(i1);
        c1.setItem(i2);
        assertTrue(c1.getItem().equals(i2));
    }

    @Test
    public void testGetCount(){
        Item i = new Item();
        i.setName("apple");

        CartItem c1 = new CartItem(i);

        assertEquals(0,c1.getCount());
    }

    @Test
    public void testSetCount(){
        Item i = new Item();
        i.setName("apple");

        CartItem c1 = new CartItem(i);
        c1.setCount(5);

        assertEquals(5, c1.getCount());
    }

    @Test
    public void testSetCountSubZero(){
        Item i = new Item();
        i.setName("apple");

        CartItem c1 = new CartItem(i);
        try {
            c1.setCount(0);
        }
        catch (Exception e){
            assertTrue(e instanceof InvalidCountException);
        }
    }

}
