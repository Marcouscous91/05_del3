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

    public Field getField(int position) {
        return fields[position];
    }

    public boolean movePlayer(Game game){
        boolean continueGame;
        int playerLandOn = game.getCurrentPlayer().getPosition();
        continueGame = fields[playerLandOn].doAction(game);
        return continueGame;
    }
}

class PropertyManager{
    Dictionary<Integer, Property> properties;
    Dictionary<Color, Property[]> pairs;

    public PropertyManager(Dictionary<Integer, Property> properties){
        this.properties = properties;
        pairProperties();
    }

    public Dictionary<Integer, Property> getProperties() {
        return properties;
    }

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
