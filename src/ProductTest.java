import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product p1, p2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        p1 = new Product("00000A", "Pipeweed", "Long Bottom Leaf", 600.0f);

        p2 = new Product("00000B", "Mushrooms", "Farmer Took’s Finest", 125.0);
    }

    @org.junit.jupiter.api.Test
    void setM_sID() {
        p1.setM_sID("00000A");
        assertEquals("00000A", p1.getM_sID());
    }

    @org.junit.jupiter.api.Test
    void setM_sName() {
        p1.setM_sName("Pipeweed");
        assertEquals("Pipeweed", p1.getM_sName());
    }

    @org.junit.jupiter.api.Test
    void setM_sDescription() {
        p1.setM_sDescription("Long Bottom Leaf");
        assertEquals("Long Bottom Leaf", p1.getM_sDescription());
    }

    @org.junit.jupiter.api.Test
    void setM_fCost() {
        p1.setM_fCost(600.0f);
        assertEquals(600.0f, p1.getM_fCost());
    }

    @org.junit.jupiter.api.Test
    void toCSVDataRecord() {
        assertEquals("00000A, Pipeweed, Long Bottom Leaf, 600.0", p1.toCSVDataRecord());
    }
}