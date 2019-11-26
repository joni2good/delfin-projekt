import java.util.*;
public class Trainer {
    
    ArrayList<Member> swimmers = new ArrayList<Member>();
    int login;
    String name;
    
    public Trainer(String name) {
        this.name = name;
    }
    
  
  
    
    public ArrayList<Member> getSwimmers() {
        return swimmers;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String toString(){
        return this.name;
    }
}