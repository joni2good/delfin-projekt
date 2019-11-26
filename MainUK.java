import java.util.*;
import java.io.*;
import java.time.*;
public class MainUK {

    public static Scanner userIn = new Scanner(System.in);
    public static ArrayList<MemberUK> clubMemberList = new ArrayList<MemberUK>();
    public static ArrayList<TrainerUK> trainerList = new ArrayList<TrainerUK>();
    public static ArrayList<TeamUK> teamList = new ArrayList<TeamUK>();
    public static TrainerUK trainer1 = new TrainerUK("James Lund");
    public static TrainerUK trainer2 = new TrainerUK("David Madsen");
    public static TeamUK teamAdult;
    public static TeamUK teamChild;     
    
    public static void main(String[] args) throws Exception {
 
        setMembers();
        
        teamAdult = new TeamUK(clubMemberList, true);
        teamChild = new TeamUK(clubMemberList, false);   
        ResultsUK results = new ResultsUK();
        
        login(results);
        
        saveMembers();
    }
    
    public static void login(ResultsUK result) {
      int end = 0;      
      String user, password;
            
      System.out.println("Welcome to swim club Delfinen! Tap ENTER to continue.");
      userIn.nextLine();
      while (end != 1){  
          System.out.println("---------------------------------------\nWrite 1 to end\n");
          System.out.print("Username: ");
          user = userIn.nextLine();
          
          if (user.equals("1")){
            break;
          }
          
          System.out.print("Password: " );
          password = userIn.nextLine();
          
      
         if(user.equals("Foreman") && (password.equals("1234"))){
            System.out.println("\nWelcome to Delfinen!\n");
            foremanInterface();
         }else if(user.equals("Trainer") && (password.equals("1234"))){
            System.out.println("\nWelcome to Delfinen!\n");
            trainerInterface(result);
         }else if (user.equals("Treasurer") && (password.equals("1234"))){
            System.out.println("\nWelcome to Delfinen!\n");
            treasurerInterface();    
         } 
         else{
            System.out.println("Password or username wrong, try again!");
            System.out.println("Try again, write a username or write 1 for exit.");
         }
      
      }
   }
    
    public static void treasurerInterface(){
        int pagePick = 0;
        while (pagePick != 3){
            System.out.println("---------------------------------------\n\t\t\t[Treasurer menu]" + 
            "\n1: Get memberList \n2: Get debt List" +  
            "\n3: Exit ");
            System.out.print("Page pick: ");
            
            pagePick = Integer.parseInt(userIn.nextLine());
            System.out.println();
            
            if (pagePick == 1) { 
                System.out.println("\n" + clubMemberList + "\n");
            }else if (pagePick == 2){
                for (MemberUK member : clubMemberList){
                    if (member.getHasDebt()){
                        System.out.println(member.getDebt());
                    }    
                }
                System.out.println();
            }else if (pagePick == 3){   
                return;
            }else if (pagePick != 3){
                System.out.println("Error, not a valid command");
            }    
        }
    }
    
    public static void trainerInterface(ResultsUK results) {
        int pagePick = 0;
        while (pagePick != 7){
            System.out.println("---------------------------------------\n\t\t\t[Trainer menu]\n1: MemberList \n2: Get best Swimmers" +  
            "\n3: Set new best time \n4: New competition result \n5: Get competition results \n6: Get team List \n7: Exit");
            System.out.print("Page pick: ");
        
            pagePick = Integer.parseInt(userIn.nextLine());
            System.out.println("");
        
            if (pagePick == 1) {
                System.out.println("\n" + clubMemberList + "\n");
            }else if (pagePick == 2){ 
                System.out.println("Best results\n---------------------------------------");
                System.out.println(results.getTop5Times("Dogpaddle")); 
                System.out.println(results.getTop5Times("100mcrawl"));
                System.out.println();        
            }else if (pagePick == 3){ 
                for (MemberUK member : clubMemberList){
                    System.out.println("ID: " + member.getID() + "\tName: " + member.getName());
                }
                System.out.println("\n Write ID for the member you wish to change");
                System.out.print("ID: ");
                int ID = Integer.parseInt(userIn.nextLine());
                
                for (MemberUK member : clubMemberList){
            
                    if (member.getID() == ID){
                        System.out.println("Member: " + member.getName());
                        System.out.println("---------------------------------------\n");
                        System.out.print("What is the new disciplin? ");
                        String disciplin = userIn.nextLine();
                    
                        System.out.print("\nWhat is the new time? ");
                        double time = Double.parseDouble(userIn.nextLine());
                    
                        System.out.print("\nWhat year? ");
                        int year = Integer.parseInt(userIn.nextLine());
                    
                        System.out.print("\nWhat month? ");
                        int month = Integer.parseInt(userIn.nextLine());
                    
                        System.out.print("\nWhat day? ");
                        int day = Integer.parseInt(userIn.nextLine());
                    
                        LocalDate date = LocalDate.of(year, month, day);
                        
                        results.setTime(member,disciplin,time,date);
                        System.out.println("The new time has been added");
                   
                    }
                }
            
            }
            else if (pagePick == 4){
                for (MemberUK member : clubMemberList){
                    System.out.println("ID: " + member.getID() + "\tName: " + member.getName());
                }
                System.out.println("\nWrite ID for the member you wish to change");
                System.out.print("ID: ");
                int ID = Integer.parseInt(userIn.nextLine());
                
                for (MemberUK member : clubMemberList){
                    if (member.getID() == ID){
                        System.out.println("Name of the competition: ");
                        String competition = userIn.nextLine();
                        
                        System.out.println("Disciplin?: ");
                        String disciplin = userIn.nextLine();
                        
                        System.out.println("Placement: ");
                        int placering = Integer.parseInt(userIn.nextLine());
                        
                        System.out.println("The new time in seconds: ");
                        double time = Double.parseDouble(userIn.nextLine());
                        
                        System.out.print("\nWhat year? ");
                        int year = Integer.parseInt(userIn.nextLine());
                        
                        System.out.print("\nWhat month? ");
                        int month = Integer.parseInt(userIn.nextLine());
                    
                        System.out.print("\nWhat day? ");
                        int day = Integer.parseInt(userIn.nextLine());
                        
                        LocalDate date = LocalDate.of(year,month,day);
                        
                        results.setCompetetionTime(member, competition, disciplin, placering, time, date);
                    }
                }
            }else if (pagePick == 5){
                results.getCompetitionResults(clubMemberList);
            }else if (pagePick == 6){
                System.out.println("Participants in the adult team: " + teamAdult.getMemberList());
                System.out.println("Participants in the children team: " + teamChild.getMemberList());
            }else if (pagePick != 7) {
                System.out.println("\nNot a valid choice, try again.\n");
            }
        }   
    }
    
    public static void foremanInterface() {
        int pagePick = 0;
        int tempCreate;
        String tempRemove = "";
        
        while (pagePick != 5) {
            System.out.println("---------------------------------------\n\t\t\t[Foreman Menu]\n1: MemberList \n2: Create member \n3: Remove member \n4: Get member information \n5: Exit");
            System.out.print("Page pick: ");
            pagePick = Integer.parseInt(userIn.nextLine());
        
            if (pagePick == 1) {
                System.out.println("\n" + clubMemberList + "\n");
            }else if (pagePick == 2) {
                System.out.print("\nHow many members do you wish to add? ");
                tempCreate = Integer.parseInt(userIn.nextLine());
                
                for (int i = 0; i < tempCreate; i++) {
                    clubMemberList.add(createMember());
                    if (i != (tempCreate - 1)){
                        System.out.println("---------------------------------------");
                    }
                }
            }else if (pagePick == 3) {
                System.out.print("What member do you wish to remove? ");
                tempRemove = userIn.nextLine();
                removeMember(tempRemove);
            }else if (pagePick == 4){
                System.out.println();
                for (MemberUK member : clubMemberList){
                    System.out.println(member.getInfo());
                }
            }else if (pagePick != 5) {
                System.out.println("\nNot a valid choice, try again.\n");
            }
        }
    }
        
    public static void setMembers()  throws Exception {
        try {
            Scanner sysInMember = new Scanner(new File("memberList.txt"));
            while (sysInMember.hasNextLine()) {
                clubMemberList.add(new MemberUK(sysInMember.nextLine()));
            }
        }catch (IOException e) {
            System.out.println("Program didn't boot correctly");
        }
    }
    
    public static void removeMember(String remName) {
        for (MemberUK member : clubMemberList) {
            if (remName.equals(member.getName())) {
                clubMemberList.remove(member);
                return;
            }
        }
        System.out.println("Member not found");
    }
    
    public static void saveMembers()  {
        try {
            PrintStream sysOutMember = new PrintStream(new FileOutputStream("memberList.txt", false));
            for (MemberUK member : clubMemberList) {
                sysOutMember.println(member.saveInfo());
            } 
        }catch (Exception e) {
            System.out.println("Program didn't close correctly");
        }
    }
    
    public static MemberUK createMember(){
        System.out.println("Members name? ");
        String tempName = userIn.nextLine();
        
        System.out.println("Members age? ");
        int tempAge = Integer.parseInt(userIn.nextLine());
        
        System.out.println("Membership type? ");
        boolean tempMemberType = userIn.nextLine().equals("true");
        
        System.out.println("Members activity type? ");
        boolean tempActivityType = userIn.nextLine().equals("true");
        
        if (tempActivityType){
            System.out.println("Trainers in the club: " + trainer1 +", "+ trainer2);
            System.out.print("Trainer name: ");
            String trainer = userIn.nextLine();
            
            if (trainer.equals(trainer1.getName())){
                return new MemberUK(tempName, tempAge, tempMemberType, tempActivityType, trainer1);
            }else {
                return new MemberUK(tempName, tempAge, tempMemberType, tempActivityType, trainer2);
            }
        }
        return new MemberUK(tempName, tempAge, tempMemberType, tempActivityType);
    }
}