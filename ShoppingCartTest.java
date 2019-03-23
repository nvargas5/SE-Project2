import static org.junit.Assert.*;
import org.junit.Test;
public class ShoppingCartTest {

    @Test
    public void testAddItemNull(){

        Item i = null;
        ShoppingCart sc = new ShoppingCart();

        try{
            sc.addItem(i);
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
        assertTrue(sc.getCartItems().contains(i1));

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
        assertFalse(sc.getCartItems().contains(i1));
    }

    @Test
    public void testDeleteItemNull(){
        Item i = null;
        ShoppingCart sc = new ShoppingCart();

        try{
            sc.deleteItem(i);
        }
        catch(Exception e){
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testDeleteInList(){


    }
    @Test
    public void testDeleteNotInList(){

    }
}