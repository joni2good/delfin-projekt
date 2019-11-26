import java.util.*;
public class TrainerUK {
    
    ArrayList<MemberUK> swimmers = new ArrayList<MemberUK>();
    int login;
    String name;
    
    public TrainerUK(String name) {
        this.name = name;
    }

    public ArrayList<MemberUK> getSwimmers() {
        return swimmers;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String toString(){
        return this.name;
    }
}