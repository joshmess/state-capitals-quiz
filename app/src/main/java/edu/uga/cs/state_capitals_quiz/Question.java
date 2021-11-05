package edu.uga.cs.state_capitals_quiz;
import android.provider.ContactsContract;

/**
 * This class (a POJO) represents a single question, including the id, state,
 * capital, and two other cities.
 * The id is -1 if the object has not been persisted in the database yet, and
 * the db table's primary key value, if it has been persisted.
 */
public class Question {

    // Instance variables for columns in table
    private long id;
    private String state;
    private String capital;
    private String city1;
    private String city2;

    // POJO Constructor
    public Question(){
        this.id = -1;
        this.state = null;
        this.capital = null;
        this.city1 = null;
        this.city2 = null;
    }

    // Accessors
    public long getId(){return id;}
    public String getState(){return  state;}
    public String getCapital(){return capital;}
    public String getCity1(){return city1;}
    public String getCity2(){return city2;}

    // Mutators
    public void setId(long id){this.id = id;}
    public void setState(String state){this.state = state;}
    public void setCapital(String capital){this.capital = capital;}
    public void setCity1(String city1){this.city1 = city1;}
    public void setCity2(String city2){this.city2 = city2;}

    // ToString
    public String toString(){
        return id + ": " + state + " " + capital + " " + city1 + " " + city2;
    }
}
