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
        properties.put(1, new Property("Burger Palace", 1, bank, Color.BROWN));
        properties.put(2, new Property("Pizza Heaven", 1, bank, Color.BROWN));
        properties.put(4, new Property("Candy Store", 1, bank, Color.LIGHTBLUE));
        properties.put(5, new Property("Ice shop", 1, bank, Color.LIGHTBLUE));
        properties.put(7, new Property("Museum", 2, bank, Color.PURPLE));
        properties.put(8, new Property("Library", 2, bank, Color.PURPLE));
        properties.put(10, new Property("Skate Park", 2, bank, Color.ORANGE));
        properties.put(11, new Property("Swim Pool", 2, bank, Color.ORANGE));
        properties.put(13, new Property("Casino", 3, bank, Color.RED));
        properties.put(14, new Property("Cinema", 3, bank, Color.RED));
        properties.put(16, new Property("Toy Store", 3, bank, Color.YELLOW));
        properties.put(17, new Property("Pet Shop", 3, bank, Color.YELLOW));
        properties.put(19, new Property("Bowling Alley", 4, bank, Color.GREEN));
        properties.put(20, new Property("Zoo", 4, bank, Color.GREEN));
        properties.put(22, new Property("Water World", 4, bank, Color.DARKBLUE));
        properties.put(23, new Property("Beach Way", 4, bank, Color.DARKBLUE));
       
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
        fields = new Field[24];
        fields[0] = new Start("Start");
        fields[3] = new Prison("Prison");
        fields[6] = new Start("Start");
        fields[9] = new Prison("Prison");
        fields[12] = new Start("Start");
        fields[15] = new Prison("Prison");
        fields[18] = new Start("Start");
        fields[21] = new Prison("Prison");

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

    public Property getProperty(int position){
        return propertyManager.getProperties().get(position);
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
