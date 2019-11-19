import java.util.*;
public class Trainer {
    
    ArrayList<Member> swimmers = new ArrayList<Member>();
    int login;
    String name;
    
    public Trainer(String name) {
        this.name = name;
    }
    
    //nedemstående skal returnerer svømmetider for hver disciplin
    /*public double getTimes() {
        return swimmers;
    }*/
    
    public ArrayList<Member> getSwimmers() {
        return swimmers;
    }
}