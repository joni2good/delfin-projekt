import java.util.*;
public class Member {

    String name = "";
    int contigent;
    int age;
    int debt;
    int stanCont = 500;
    boolean memberType;
    boolean activityType;
    double swimResult;
    Trainer trainer;
    
    public Member(String name, int age, boolean memberType, boolean activityType) {
        this.name = name;
        this.age = age;
        this.memberType = memberType;
        this.activityType = activityType;
        calcCont();
    }
    
    public Member(String info) {
        Scanner scan = new Scanner(info);
        while (scan.hasNext()) {
            if (scan.hasNextInt()) {
                break;
            } 
            this.name += scan.next() + " ";
        }this.name = this.name.substring(0, name.length() - 1);
        
        this.age = scan.nextInt();
        this.memberType = scan.next().equals("true");
        this.activityType = scan.next().equals("true");
        
        if (memberType == true) {
            this.trainer = new Trainer(scan.next());
        }
        calcCont();
    }
    
    public void calcCont() {
        if (this.activityType != true) {
            this.contigent = stanCont;
        }else if (this.age < 18) {
            this.contigent = stanCont + 500;
        }else if (this.age < 60) {
            this.contigent = stanCont + 1100;
        }else {
            this.contigent = stanCont + 700;
        }
    }
    
    //nedenstående er konkurrence eller motionist
    public void setMemberType(boolean type) {
        memberType = type;
    }
    
    //nedenstående er aktiv eller passiv
    public void setActivityType(boolean type) {
        activityType = type;
    }
    
    public void setSwimResult(double result, String disciplin) {
        swimResult = result;
        /*disciplin kommer til at gemmes på en måde så
        alle discipliner følger en bestemt rækkefølge*/
        //this.disciplin = disciplin;
    }
    
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public String getInfo() {
        return "Name: " + name + "\nContigent: " + contigent + "\nAge: " + age + "\nMembership Type: " + memberType + "\nActivity Type: " + activityType + "\nTrainer: " + trainer;
    }
    
    public String saveInfo() {
        return name + " " + age + " " + memberType + " " + activityType + " " + trainer;
    }
    
    public String toString() {
        return this.name;
    }
    
}