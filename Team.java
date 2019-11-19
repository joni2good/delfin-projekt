import java.util.*;
public class Team {
    
    ArrayList<Member> memberList = new ArrayList<Member>();
    boolean teamType;
    
    public void addMember(Member member) {
        //skal på et tidspunkt også gemmes i fil
        memberList.add(member);
    }
    
    public void removeMember(Member member) {
        //skal slettes i en fil på et tidspunkt
        memberList.remove(member);
    }
    
    public ArrayList<Member> getMemberList() {
        return memberList;
    }
}