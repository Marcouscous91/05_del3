package cdio3;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Board {
    private Field[] fields;
    private PropertyManager propertyManager;

    public Board(Actor bank){
        createPropertyManager(bank);
        createFields(propertyManager.getProperties());
    }

    /*
     * Creates a hashtable of properties, with key corresponding to their position on the board.
     * Hashtable is used in create object "Property Manager", who will keep track of objects of 
     * type "Property". Responsabilities described in class "PropertyManager".
     * 
     * Param:   bank: the "actor" who will be the initial owner of property.
     */
    private void createPropertyManager(Actor bank){
        Map<Integer, Property> properties = new HashMap<>();
        properties.put(1, new Property("Baker", 2, bank, Color.RED));
        properties.put(3, new Property("Marcus' House", 1, bank, Color.BROWN));
        properties.put(4, new Property("Train Station", 1, bank, Color.PURPLE));
        properties.put(5, new Property("School", 2, bank, Color.RED));
        properties.put(6, new Property("Police Station", 3, bank, Color.BROWN));
        properties.put(7, new Property("Restaurant", 2, bank, Color.PURPLE));
       
        propertyManager = new PropertyManager(properties);
    }

    /*
     * Creates an array of fields, which will be indexed according to their position on the board.
     * Uses the hashtable of properties, to store them as type fields. Stores them according to their
     * key, so it corresponds with their position on board.
     * 
     * Param:   properties: the hashtable stored under the propertyManager object
     */
    private void createFields(Map<Integer, Property> properties){
        fields = new Field[8];
        fields[0] = new Start("Start");
        fields[2] = new Prison("Prison");

        for(int i = 0; i < fields.length; i++){
            if (fields[i] == null) {
                fields[i] = properties.get(i);
            }
        }
    }

    /*
     * Returns the field corresponding to the position on board.
     * 
     * Param:   position: the position of the player on board.
     * 
     * Return:  field: the field the player lands on.
     */
    public Field getField(int position) {
        return fields[position];
    }

    /*
     * Simulates movement of player on board.
     * Calls on the doAction() function of the field, which the player lands on.
     * Keeps track whether the doAction() is succesfully performed, or not.
     * Checks to see if property landed on, and other property of same color, are owned by same player. 
     * 
     * Param:   player: the player of turn it is currently
     * 
     * Return:  continueGame: boolean depending on outcome of doAction() from field
     */
    public boolean movePlayer(Player player){
        boolean continueGame;
        int playerPosition = player.getPosition();
        Field landOn = fields[playerPosition];
        continueGame = landOn.doAction(player);
        if(landOn instanceof Property){
            Property property = propertyManager.getProperties().get(playerPosition);
            propertyManager.isDoubleRent(property);
        }
        return continueGame;
    }
}

class PropertyManager{
    Map<Integer, Property> properties = new HashMap<>();
    Map<Color, Property[]> pairs = new HashMap<>();

    /*
     * Constructor
     * 
     * Param:   properties: hashtable of properties on the board. Hashtable create in object 'Board'
     */
    public PropertyManager(Map<Integer, Property> properties){
        this.properties = properties;
        pairProperties();
    }

    public Map<Integer, Property> getProperties() {
        return properties;
    }

    /*
     * Pairs the properties by their color 
     * (Used to keep track if they are owned by same player, thus doubling rent) 
     */
    public void pairPropertiesBeta(){
        Property[] red = new Property[]{properties.get(1), properties.get(5)};
        Property[] brown = new Property[]{properties.get(3), properties.get(4)};
        Property[] purple = new Property[]{properties.get(4), properties.get(7)};
        pairs.put(Color.RED, red);
        pairs.put(Color.BROWN, brown);
        pairs.put(Color.PURPLE, purple);
    }

    /*
     * Pairs the properties according to color, and stores them in HashMap 'pairs'
     */
    public void pairProperties(){
        for(Property property: properties.values()){
            insertPair(property);
        }
    }
    
    /*
     * Auxiliary function in 'pairProperties()'
     * Checks a property and searches for the other property with same color, stores them as and
     * array of properties, and inserts them into HashMap 'pairs'
     * 
     * Param:   property: the property to be checked with others.
     */
    private void insertPair(Property property){
        Color color = property.getColor();
        if(pairs.get(color) == null){
            Property[] pair = new Property[2];
            for(Property x: properties.values()){
                if(property != x && x.getColor() == color){
                    pair[0] = property;
                    pair[1] = x;
                    pairs.put(color, pair);
                    break;
                }
            }
        }
    }

    /*
     * Checks to see if a given property and it's corresponding property (according to color),
     * are owned by same player. Doubles the rent of both properties if it is the case.
     * 
     * Param:   property: the property to check which is the one the player landed on the current turn.
     */
    public boolean isDoubleRent(Property property){
        boolean rentDoubled = false;
        if(!(property.getOwner() instanceof Player)){
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
