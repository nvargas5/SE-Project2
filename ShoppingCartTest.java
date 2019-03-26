import static org.junit.Assert.*;

import csc4700.CartItem;
import csc4700.ShoppingCart;
import csc4700.Item;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartTest {

    @Test
    public void testAddItemNull(){
        Item i = null;
        ShoppingCart sc = new ShoppingCart();

        try {
            sc.addItem(i);
            fail();
        }
        catch(Exception e){
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testAddItemNotInList(){
        ShoppingCart sc = new ShoppingCart();
        Item i1 = new Item();
        i1.setName("apple");
        i1.setCost(1);
        i1.setDescription("yum");

        sc.addItem(i1);
        assertNotNull(sc.findCartItem(i1));
        assertEquals(1, sc.findCartItem(i1).getCount());
    }

    @Test
    public void testAddItemInList(){
        ShoppingCart sc = new ShoppingCart();
        Item i1 = new Item();
        i1.setName("apple");
        i1.setCost(1);
        i1.setDescription("yum");

        sc.addItem(i1);
        sc.addItem(i1);
        assertNotNull(sc.findCartItem(i1));
        assertEquals(2, sc.findCartItem(i1).getCount());
    }

    @Test
    public void testDeleteItemNull(){
        Item i = null;
        ShoppingCart sc = new ShoppingCart();

        try{
            sc.deleteItem(i);
            fail();
        }
        catch(Exception e){
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testDeleteInListOnlyOne(){
        Item i1 = new Item();
        i1.setName("apple");

        ShoppingCart sc = new ShoppingCart();
        sc.addItem(i1);
        sc.deleteItem(i1);

        assertNull(sc.findCartItem(i1));
    }

    @Test
    public void testDeleteInListMoreThanOne(){
        Item i1 = new Item();
        i1.setName("apple");

        ShoppingCart sc = new ShoppingCart();
        sc.addItem(i1);
        sc.addItem(i1);
        sc.deleteItem(i1);

        assertNotNull(sc.findCartItem(i1));
        assertEquals(1, sc.findCartItem(i1).getCount());
    }

    @Test
    public void testDeleteNotInList(){
        Item i1 = new Item();
        i1.setName("apple");

        ShoppingCart sc = new ShoppingCart();

        List<CartItem> compare = new ArrayList<CartItem>(sc.getCartItems());
        sc.deleteItem(i1);

        assertArrayEquals(compare.toArray(), sc.getCartItems().toArray());
    }

    @Test
    public void testFindCartItem() {
        Item i1 = new Item();
        i1.setName("apple");

        Item i2 = new Item();
        i2.setName("pear");

        ShoppingCart sc = new ShoppingCart();
        sc.addItem(i1);
        sc.addItem(i2);

        assertEquals(i1, sc.findCartItem(i1).getItem());
    }

    @Test
    public void testFindCartItemNull() {
        Item i1 = new Item();
        i1.setName("apple");
        ShoppingCart sc = new ShoppingCart();

        assertNull(sc.findCartItem(i1));
    }

    @Test
    public void testGetCartItems() {
        Item i1 =  new Item();
        i1.setName("apple");

        Item i2 =  new Item();
        i2.setName("pear");

        List<CartItem> compare = new ArrayList<CartItem>();
        compare.add(new CartItem(i1));
        compare.add(new CartItem(i2));

        ShoppingCart sc = new ShoppingCart();
        sc.addItem(i1);
        sc.addItem(i2);

        assertArrayEquals(compare.toArray(), sc.getCartItems().toArray());
    }
}