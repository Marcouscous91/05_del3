package cdio3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class PropertyManagerTest {
    
    @Test
    public void propertiesRentShouldDoubleWhenSameOwnerAndColor(){
        Map<Integer, Property> properties = new HashMap<>();
        Player p1 = new Player("Player 1");
        Player p2 = new Player("Player 2");
        properties.put(1, new Property("Baker", 2, p1, Color.RED));
        properties.put(2, new Property("Marcus' House", 1, p2, Color.BROWN));
        properties.put(3, new Property("Train Station", 1, p1, Color.RED));
        properties.put(4, new Property("School", 2, p1, Color.BROWN));
        PropertyManager propertyManager = new PropertyManager(properties);

        Property property = propertyManager.getProperties().get(1);
        double rentBeforeDoubleCheck = property.getRent();
        propertyManager.isDoubleRent(property);
        double rentAfterDoubleCheck = property.getRent();
        assertEquals((rentBeforeDoubleCheck * 2), rentAfterDoubleCheck, 0);
    }

    @Test
    public void propertiesRentShouldNotDoubleWhenNotSameOwner(){
        Map<Integer, Property> properties = new HashMap<>();
        Player p1 = new Player("Player 1");
        Player p2 = new Player("Player 2");
        properties.put(1, new Property("Baker", 2, p1, Color.RED));
        properties.put(2, new Property("Marcus' House", 1, p2, Color.BROWN));
        properties.put(3, new Property("Train Station", 1, p1, Color.RED));
        properties.put(4, new Property("School", 2, p1, Color.BROWN));
        PropertyManager propertyManager = new PropertyManager(properties);

        Property property = propertyManager.getProperties().get(2);
        double rentBeforeDoubleCheck = property.getRent();
        propertyManager.isDoubleRent(property);
        double rentAfterDoubleCheck = property.getRent();
        assertEquals(rentBeforeDoubleCheck, rentAfterDoubleCheck, 0);
    }
}
