import java.util.*;
public class MemberUK {

    int memberID; 
    static int count = 0;
    String name = "";
    int contigent;
    int age;
    int debt;
    int stanCont = 500;
    boolean memberType;
    boolean activityType; 
    TrainerUK trainer;
    
    public MemberUK(String name, int age, boolean memberType, boolean activityType) {
        this.count++;
        this.memberID = count;
        this.name = name;
        this.age = age;
        this.memberType = memberType; 
        this.activityType = activityType;
        calcCont();
    }
    
    public MemberUK(String name, int age, boolean memberType, boolean activityType, TrainerUK trainer) {
        this.count++;
        this.memberID = count;
        this.name = name;
        this.age = age;
        this.memberType = memberType; 
        this.activityType = activityType;
        this.trainer = trainer;
        calcCont();
    }
    
    public MemberUK(String info) {
        Scanner scan = new Scanner(info);
        while (scan.hasNext()) {
            if (scan.hasNextInt()) {
                break;
            } 
            this.name += scan.next() + " ";
        }
        this.name = this.name.substring(0, name.length() - 1);
        this.memberID = scan.nextInt();
        this.age = scan.nextInt();
        this.memberType = scan.next().equals("true");
        this.activityType = scan.next().equals("true");
        this.debt = Integer.parseInt(scan.next());
        if (memberType == true) {
            this.trainer = new TrainerUK(scan.next());
        }
        calcCont();
        count = Math.max(count,this.memberID);
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
    
    public void setMemberType(boolean type) {
        memberType = type;
    }

    public void setActivityType(boolean type) {
        activityType = type;
    }
  
    public void setTrainer(TrainerUK trainer) {
        this.trainer = trainer;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public int getAge(){
        return this.age;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean getHasDebt(){
        return debt > 0;    
    }
    
    public String getDebt(){
        return this.name + " has: " + debt + " kr. in debt.";
    }
    
    public boolean getMemberType(){
        return this.memberType;
    }
    
    public int getID(){
        return this.memberID;
    }
    
    public String getInfo() {
        return "Name: " + name + " Contigent: " + contigent + " Age: " + age + " Membership Type: " + memberType + " Activity Type: " + activityType + " Trainer: " + trainer;
    }
    
    public String saveInfo() {
        return name + " " + memberID + " " + age + " " + memberType + " " + activityType + " " + debt + " " + trainer ;
    }
    
    public String toString() {
        return this.name;
    }
}