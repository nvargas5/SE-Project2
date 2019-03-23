import static org.junit.Assert.*;
import org.junit.Test;
public class ItemTest {

    @Test
    public void testEqualsSameObj() {
        Item i = new Item();
        i.setName("apple");
        assertTrue(i.equals(i));
    }

    @Test
    public void testNotEqualsNull() {
        Item i1 = new Item();
        i1.setName("apple");

        Item i2 = null;

        assertFalse(i1.equals(i2));
    }

    @Test
    public void testNotEqualsDiffClass() {
        Item i1 = new Item();
        String str = "test";

        assertFalse(i1.equals(str));
    }

    @Test
    public void testEquals() {
        Item i1 = new Item();
        Item i2 = new Item();

        i1.setName("apple");
        i1.setCost(2);
        i1.setDescription("yum");

        i2.setName("apple");
        i2.setCost(1);
        i2.setDescription("ew");

        assertTrue(i1.equals(i2));
    }

    @Test
    public void testNotEquals() {
        Item i1 = new Item();
        Item i2 = new Item();

        i1.setName("apple");
        i1.setCost(1);
        i1.setDescription("yum");

        i2.setName("pear");
        i2.setCost(1);
        i2.setDescription("yum");

        assertFalse(i1.equals(i2));
    }

    @Test
    public void testHashCode() {
        Item i = new Item();
        i.setName("name");
        assertEquals(i.hashCode(), i.getName().hashCode());
    }

    @Test
    public void testHashCodeNoName() {
        Item i = new Item();
        try {
            assertEquals(i.hashCode(), i.getName().hashCode());
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testSetNameGetNameString() {
        Item i = new Item();
        i.setName("name");
        assertEquals("name", i.getName());
    }

    @Test
    public void testSetNameGetNameNull() {
        Item i = new Item();
        i.setName(null);
        assertNull(i.getName());
    }

    @Test
    public void testGetNameNotSet() {
        Item i = new Item();
        assertNull(i.getName());
    }

    @Test
    public void testSetCostGetCost() {
        Item i = new Item();
        i.setCost(3);
        assertEquals(3, i.getCost());
    }

    @Test
    public void testGetCostNotSet() {
        Item i = new Item();
        assertEquals(0, i.getCost());
    }

    @Test
    public void testSetDescGetDescString() {
        Item i = new Item();
        i.setDescription("Description!");
        assertEquals("Description!", i.getDescription());
    }

    @Test
    public void testSetDescGetDescNull() {
        Item i = new Item();
        i.setDescription(null);
        assertNull(i.getDescription());
    }

    @Test
    public void testGetDescNotSet() {
        Item i = new Item();
        assertNull(i.getDescription());
    }
}