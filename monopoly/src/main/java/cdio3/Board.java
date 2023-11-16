package cdio3;

import java.util.Dictionary;
import java.util.Hashtable;

class Board {
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
        Dictionary<Integer, Property> properties = new Hashtable<>();
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
    private void createFields(Dictionary<Integer, Property> properties){
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
        return continueGame;
    }
}

class PropertyManager{
    Dictionary<Integer, Property> properties;
    Dictionary<Color, Property[]> pairs;

    /*
     * Constructor
     * 
     * Param:   properties: hashtable of properties on the board. Hashtable create in object 'Board'
     */
    public PropertyManager(Dictionary<Integer, Property> properties){
        this.properties = properties;
        pairProperties();
    }

    public Dictionary<Integer, Property> getProperties() {
        return properties;
    }

    /*
     * Pairs the properties by their color 
     * (Used to keep track if they are owned by same player, thus doubling rent) 
     */
    public void pairProperties(){
        pairs = new Hashtable<>();
        Property[] red = new Property[]{properties.get(1), properties.get(5)};
        Property[] brown = new Property[]{properties.get(3), properties.get(4)};
        Property[] purple = new Property[]{properties.get(4), properties.get(7)};
        pairs.put(Color.RED, red);
        pairs.put(Color.BROWN, brown);
        pairs.put(Color.PURPLE, purple);
    }
}
