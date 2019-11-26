import java.util.*;
import java.io.*;

public class TeamUK {
    
    ArrayList<MemberUK> memberList = new ArrayList<MemberUK>();
    boolean teamType;
    
    public TeamUK(ArrayList<MemberUK> clubListMembers, boolean teamType){
        int Age = 18;
        if (teamType){
            for (MemberUK member : clubListMembers){
                if (!member.getMemberType()){
                    continue;
                }else if (member.getAge() < 18){
                    continue;
                }else {
                    memberList.add(member);
                }
            }            
        }else {
            for (MemberUK member : clubListMembers){
                if (!member.getMemberType()){
                    continue;
                }else if (!(member.getAge() < 18)){
                    continue;                
                }else {
                    memberList.add(member);
                }
            }
        }     
    }
    
    public void addMember(MemberUK member) {    
        if (!member.getMemberType()){
            System.out.println("Member isn't active");
            return;
        }
        if (teamType){
            if (member.getAge() < 18){
                return;
            }else {
                memberList.add(member);
            }
        }else {
            if (!(member.getAge() < 18)){
                return;
            }else {
                memberList.add(member);
            }
        }
    }
    
    public void removeMember(MemberUK member) {
        try {
            memberList.remove(member);
        }catch (Exception e){
            return;
        }
    }
    
    public ArrayList<MemberUK> getMemberList() {
        return memberList;
    }
}