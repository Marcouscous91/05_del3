package cdio3;

import java.util.Random;

import javax.management.relation.Role;

public class Die{
    private int faceValue;
    private int min;
    private int max;

    public Die(int min, int max){
        this.min = min; //the min value of the die write when you call die
        this.max = max; //the max value of the die write when you call die
    }

    //Generate a random number between max and min and give it to "faceValue"
    public void roll(){
        Random random = new Random();
        faceValue = random.nextInt((max-min)+1)+min; 
    }

    public int getFaceValue(){
        return faceValue;
    }
}