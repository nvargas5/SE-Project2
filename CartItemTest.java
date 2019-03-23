import exceptions.InvalidCountException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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
    public void testIncrementCountByOne(){
        Item i = new Item();
        CartItem c1 = new CartItem(i);

        c1.incrementCountByOne();
        assertEquals(1, c1.getCount());
    }

    @Test //count cannot go below 0, how to test if non deterministic test order
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
        Item i2 = new Item();

        i2.setName("apple");
        i2.setCost(1);
        i2.setDescription("ew");

        CartItem c1 = new CartItem(i2);
        CartItem c2 = new CartItem(i2);


        assertTrue(c1.equals(c2));
    }

    @Test
    public void testNotEqualsNull() {
        Item i1 = new Item();
        i1.setName("apple");

        Item i2 = null;

        CartItem c1 = new CartItem(i1);
        CartItem c2 = new CartItem(i2);

        assertFalse(c1.equals(c2));
    }

    @Test
    public void testNotEqualsDiffClass() {
        Item i1 = new Item();
        i1.setName("apple");

        int i = 10;

        CartItem c1 = new CartItem(i1);

        assertFalse(c1.equals(i));
    }

    @Test
    public void testHashCode(){
        Item i = new Item();
        i.setName("name");

    }

    @Test
    public void testHashCodeNull(){

    }

    @Test
    public void testHashCodeNoItem(){

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
        try{
            c1.setCount(0);
        }
        catch (Exception e){
            assertTrue(e instanceof InvalidCountException);
        }
    }

}