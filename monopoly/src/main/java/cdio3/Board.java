package cdio3;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Field[] fields;
    private PropertyManager propertyManager;

    public Board(Actor bank) {
        createPropertyManager(bank);
        createFields(propertyManager.getProperties());
    }

    /*
     * Creates a hashtable of properties, with key corresponding to their position
     * on the board.
     * Hashtable is used in create object "Property Manager", who will keep track of
     * objects of
     * type "Property". Responsabilities described in class "PropertyManager".
     * 
     * Param: bank: the "actor" who will be the initial owner of property.
     */
    private void createPropertyManager(Actor bank) {

        Map<Integer, Property> properties = new HashMap<>();
        properties.put(1, new Property(Textreader.getTextLineToPrint(0), 1, bank, Color.BROWN));// 0
        properties.put(2, new Property(Textreader.getTextLineToPrint(1), 1, bank, Color.BROWN));// 1
        properties.put(4, new Property(Textreader.getTextLineToPrint(2), 1, bank, Color.LIGHTBLUE));// 2
        properties.put(5, new Property(Textreader.getTextLineToPrint(3), 1, bank, Color.LIGHTBLUE));// 3
        properties.put(7, new Property(Textreader.getTextLineToPrint(4), 2, bank, Color.PURPLE));// 4
        properties.put(8, new Property(Textreader.getTextLineToPrint(5), 2, bank, Color.PURPLE));// 5
        properties.put(10, new Property(Textreader.getTextLineToPrint(6), 2, bank, Color.ORANGE));// 6
        properties.put(11, new Property(Textreader.getTextLineToPrint(7), 2, bank, Color.ORANGE));// 7
        properties.put(13, new Property(Textreader.getTextLineToPrint(8), 3, bank, Color.RED));// 8
        properties.put(14, new Property(Textreader.getTextLineToPrint(9), 3, bank, Color.RED));// 9
        properties.put(16, new Property(Textreader.getTextLineToPrint(10), 3, bank, Color.YELLOW));// 10
        properties.put(17, new Property(Textreader.getTextLineToPrint(11), 3, bank, Color.YELLOW));// 11
        properties.put(19, new Property(Textreader.getTextLineToPrint(12), 4, bank, Color.GREEN));// 12
        properties.put(20, new Property(Textreader.getTextLineToPrint(13), 4, bank, Color.GREEN));// 13
        properties.put(22, new Property(Textreader.getTextLineToPrint(14), 5, bank, Color.DARKBLUE));// 14
        properties.put(23, new Property(Textreader.getTextLineToPrint(15), 5, bank, Color.DARKBLUE));// 15

        propertyManager = new PropertyManager(properties);
    }

    /*
     * Creates an array of fields, which will be indexed according to their position
     * on the board.
     * Uses the hashtable of properties, to store them as type fields. Stores them
     * according to their
     * key, so it corresponds with their position on board.
     * 
     * Param: properties: the hashtable stored under the propertyManager object
     */

    private void createFields(Map<Integer, Property> properties) {
        fields = new Field[24];

        fields[0] = new InertField("Start");
        fields[3] = new InertField("Chance field 1");
        fields[6] = new InertField("Prison");
        fields[9] = new InertField("Chance field 2");
        fields[12] = new InertField("Free Parking");
        fields[15] = new InertField("Chance field 3");
        fields[18] = new Prison("Police station");
        fields[21] = new InertField("Chance field 4");

        for (int i = 0; i < fields.length; i++) {
            if (fields[i] == null) {
                fields[i] = properties.get(i);
            }
        }
    }

    /*
     * Returns the field corresponding to the position on board.
     * 
     * Param: position: the position of the player on board.
     * 
     * Return: field: the field the player lands on.
     */
    public Field getField(int position) {
        return fields[position];
    }

    public Property getProperty(int position) {
        return propertyManager.getProperties().get(position);
    }

    /*
     * Simulates movement of player on board.
     * Calls on the doAction() function of the field, which the player lands on.
     * Keeps track whether the doAction() is succesfully performed, or not.
     * Checks to see if property landed on, and other property of same color, are
     * owned by same player.
     * 
     * Param: player: the player of turn it is currently
     * 
     * Return: continueGame: boolean depending on outcome of doAction() from field
     */
    public boolean movePlayer(Player player) {
        boolean continueGame;
        int playerPosition = player.getPosition();
        Field landOn = fields[playerPosition];
        continueGame = landOn.doAction(player);
        if (landOn instanceof Property) {
            Property property = propertyManager.getProperties().get(playerPosition);
            propertyManager.isDoubleRent(property);
        }
        return continueGame;
    }
}

class PropertyManager {
    Map<Integer, Property> properties = new HashMap<>();
    Map<Color, Property[]> pairs = new HashMap<>();

    /*
     * Constructor
     * 
     * Param: properties: hashtable of properties on the board. Hashtable create in
     * object 'Board'
     */
    public PropertyManager(Map<Integer, Property> properties) {
        this.properties = properties;
        pairProperties();
    }

    public Map<Integer, Property> getProperties() {
        return properties;
    }

    /*
     * Pairs the properties according to color, and stores them in HashMap 'pairs'
     */
    public void pairProperties() {
        for (Property property : properties.values()) {
            insertPair(property);
        }
    }

    /*
     * Auxiliary function in 'pairProperties()'
     * Checks a property and searches for the other property with same color, stores
     * them as and
     * array of properties, and inserts them into HashMap 'pairs'
     * 
     * Param: property: the property to be checked with others.
     */
    private void insertPair(Property property) {
        Color color = property.getColor();
        if (pairs.get(color) == null) {
            Property[] pair = new Property[2];
            for (Property x : properties.values()) {
                if (property != x && x.getColor() == color) {
                    pair[0] = property;
                    pair[1] = x;
                    pairs.put(color, pair);
                    break;
                }
            }
        }
    }

    /*
     * Checks to see if a given property and it's corresponding property (according
     * to color),
     * are owned by same player. Doubles the rent of both properties if it is the
     * case.
     * 
     * Param: property: the property to check which is the one the player landed on
     * the current turn.
     */
    public boolean isDoubleRent(Property property) {
        boolean rentDoubled = false;
        if (!(property.getOwner() instanceof Player)) {
            return rentDoubled;
        }
        Color color = property.getColor();
        Actor owner1 = pairs.get(color)[0].getOwner();
        Actor owner2 = pairs.get(color)[1].getOwner();
        if (owner1 == owner2) {
            pairs.get(color)[0].doubleRent();
            pairs.get(color)[1].doubleRent();
        }
        return rentDoubled;
    }
}
