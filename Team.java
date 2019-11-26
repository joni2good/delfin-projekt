import java.util.*;
import java.io.*;

public class Team {
    
    ArrayList<Member> memberList = new ArrayList<Member>();
    boolean teamType; //true for voksne
    
    //hold bliver initialiseret i main, og alle der er aktive konkurrance swimmere burde komme på et hold.
    //man kan kun fjerne dem fra deres hold permanent ved at ændre deres konkurrance variabel.
    
    public Team(ArrayList<Member> clubListMembers, boolean teamType){
        int Age = 18;
        if (teamType){ //true hvis det er voksen hold.
            for (Member member : clubListMembers){
                if (!member.getMemberType()){
                    continue;
                }
                else if (member.getAge() < 18){
                    continue;
                }
                else {
                    memberList.add(member);
                }
            }            
        }
        else {
            for (Member member : clubListMembers){
                if (!member.getMemberType()){
                    continue;
                }
                else if (!(member.getAge() < 18)){
                    continue;                
                }
                else {
                    memberList.add(member);
                }
            }
        }     
    }
    
  
    
    public void addMember(Member member) {
    //er den er metode nødvendig? De blir smidt på et hold hvis de er
    //aktive konkurrance swimmers. 
    
        if (!member.getMemberType()){
            System.out.println("Medlemmet er ikke aktiv");
            return;
        }
        if (teamType){
            if (member.getAge() < 18){
                return;
            }
            else {
                memberList.add(member);
            }
        }
        else {
            if (!(member.getAge() < 18)){
                return;
            }
            else {
                memberList.add(member);
            }
        }
    }
    
    public void removeMember(Member member) {
        //try/catch hvis de ikke er med på holdet.
        try {
            memberList.remove(member);
        }
        catch (Exception e){
            return;
        }
    }
    
    public ArrayList<Member> getMemberList() {
        return memberList;
    }
}