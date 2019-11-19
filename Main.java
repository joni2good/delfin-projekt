import java.util.*;
import java.io.*;
public class Main {
    public static Scanner userIn = new Scanner(System.in);
    public static ArrayList<Member> clubMemberList = new ArrayList<Member>();
    public static ArrayList<Trainer> trainerList = new ArrayList<Trainer>();
    public static ArrayList<Team> teamList = new ArrayList<Team>();
    
    public static void main(String[] args) throws Exception {
    
    setMembers();
    
    foremanInterface();
    
    saveMembers();
    
    }
    
    public static void foremanInterface() {
        int pagePick = 0;
        int tempCreate;
        String tempRemove = "";
        while (pagePick != 4) {
            System.out.println("---------------------------------------\n\t\t\t[Foreman Menu]\n1: MemberList \n2: Create member \n3: Remove member \n4: Exit");
            System.out.print("Page pick: ");
            pagePick = Integer.parseInt(userIn.nextLine());
        
            if (pagePick == 1) {
                System.out.println("\n" + clubMemberList + "\n");
            }else if (pagePick == 2) {
                System.out.print("\nHow many members do you want to add? ");
                tempCreate = Integer.parseInt(userIn.nextLine());
                for (int i = 0; i < tempCreate; i++) {
                    clubMemberList.add(createMember());
                }
            }else if (pagePick == 3) {
                System.out.print("What member do you wish to remove? ");
                tempRemove = userIn.nextLine();
                removeMember(tempRemove);
            }else if (pagePick != 4) {
                System.out.println("\nNot a valid choice, try again.\n");
            }
        }
    }
        
    
    public static void setMembers()  throws Exception {
        try {
            Scanner sysInMember = new Scanner(new File("memberList.txt"));
            while (sysInMember.hasNextLine()) {
                clubMemberList.add(new Member(sysInMember.nextLine()));
            }
        }catch (Exception e) {
            System.out.println("Programmet startede forkert");
        }
    }
    
    public static void removeMember(String remName) {
        for (Member member : clubMemberList) {
            if (remName.equals(member.getName())) {
                clubMemberList.remove(member);
                return;
            }
        }
        System.out.println("Member not found");
    }
    
    public static void saveMembers() {
        try {
            PrintStream sysOutMember = new PrintStream(new FileOutputStream("memberList.txt", false));
            for (Member member : clubMemberList) {
                sysOutMember.println(member.saveInfo());
            } 
        }catch (Exception e) {
            System.out.println("programmet lukkede forkert");
        }
    }
    
    public static Member createMember(){
        System.out.println("Members name? ");
        String tempName = userIn.nextLine();
        System.out.println("Members age? ");
        int tempAge = Integer.parseInt(userIn.nextLine());
        System.out.println("Membership type? ");
        boolean tempMemberType = userIn.nextLine().equals("true");
        System.out.println("Members activity type? ");
        boolean tempActivityType = userIn.nextLine().equals("true");
        
        return new Member(tempName, tempAge, tempMemberType, tempActivityType);
    }
}