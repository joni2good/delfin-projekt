import java.util.*;
import java.io.*;
import java.time.*;
public class Main {

    public static Scanner userIn = new Scanner(System.in);
    public static ArrayList<Member> clubMemberList = new ArrayList<Member>();
    public static ArrayList<Trainer> trainerList = new ArrayList<Trainer>();
    public static ArrayList<Team> teamList = new ArrayList<Team>();
    public static Trainer trainer1 = new Trainer("James Lund");
    public static Trainer trainer2 = new Trainer("David Madsen");
    public static Team teamAdult;
    public static Team teamChild; 
        
    
    public static void main(String[] args) throws Exception {
        
    
    
        setMembers();
        teamAdult = new Team(clubMemberList, true); //Opretter voksen hold, bool bestemmer om voksen eller ej. Virker pga der kun er 2 hold.
        teamChild = new Team(clubMemberList, false);//opretter barn hold
             
        Results results = new Results();
        
        login(results);

        saveMembers();
    
    }
    
    public static void login(Results result) {
      
      int end = 0;      
      String user, password;
            
      System.out.println("Welcome to swim club Delfinen! Tap ENTER to continue.");
      userIn.nextLine();
      while (end != 1){  
          System.out.print("Username: " );
          user = userIn.nextLine();
      if (user.equals("1")){
          break;
      }
          System.out.print("Password: " );
          password = userIn.nextLine();
          
      
      if(user.equals("Foreman") && (password.equals("1234"))){
         System.out.println("\nWelcome to Delfinen!\n");
         foremanInterface();
         //break;
         }
      else if(user.equals("Trainer") && (password.equals("1234"))){
         System.out.println("\nWelcome to Delfinen!\n");
         trainerInterface(result);
         //break;
         } 
      else if (user.equals("Treasurer") && (password.equals("1234"))){
         System.out.println("\nWelcome to Delfinen!\n");
         treasurerInterface();    
      } 
      else{
          System.out.println("password or username wrong, try again!");
          System.out.println("Try again,write a username or write 1 for exit.");
          
              // break;
          }
      
      }
            //System.exit(1);

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
            
            if (pagePick == 1) { //samme som foreman, printer member liste
                    System.out.println("\n" + clubMemberList + "\n");
            }
            else if (pagePick == 2){
                for (Member member : clubMemberList){
                    if (member.getHasDebt()){
                        System.out.println(member.getDebt());
                    }
                
                }
                System.out.println();
            }
            else if (pagePick == 3){
                
                return;
            }   
            else if (pagePick != 3){
                System.out.println("Error, not a valid command");
            }
            
            
            
        }
    
    
    }
    
    public static void trainerInterface(Results results) {
    int pagePick = 0;
    while (pagePick != 7){
        System.out.println("---------------------------------------\n\t\t\t[Trainer menu]\n1: MemberList \n2: Get best Swimmers" +  
        "\n3: Set en ny bedste tid \n4: Nye konkurrence resultater \n5: For at se resultater fra stævner \n6: Get team List \n7: Exit");
        System.out.print("Page pick: ");
        
        pagePick = Integer.parseInt(userIn.nextLine());
        System.out.println("");
        
        if (pagePick == 1) { //samme som foreman, printer member liste
                System.out.println("\n" + clubMemberList + "\n");
        }
        
        else if (pagePick == 2){ //printer top 5, kan kalde results.getTop5Times på alle discipliner
        //mangler flere discipliner, lige nu finder de kun for test ting hundeswimming
            System.out.println("Resultater for de bedste tider");
            System.out.println(results.getTop5Times("hundeswimming")); 
            System.out.println(results.getTop5Times("100mbryst"));        
        }
        
        else if (pagePick == 3){ //tilføjer en ny personlig bedste tid for et medlem
        //man kan kun have 1 bedste tid for hver disciplin.
            
            for (Member member : clubMemberList){
                System.out.println("ID: " + member.getID() + "\tNavn: " + member.getName());
            
            }
            System.out.println("\n Skriv ID for at give det member en ny tid");
            System.out.print("ID: ");
            int ID = Integer.parseInt(userIn.nextLine());
            for (Member member : clubMemberList){
            
                if (member.getID() == ID){
                    System.out.println("Medlem: " + member.getName());
                    System.out.print("Hvad er den nye disciplin?: ");
                    String disciplin = userIn.nextLine();
                    
                    System.out.print("\nHvad er den nye tid: ");
                    double time = Double.parseDouble(userIn.nextLine());
                    
                    System.out.print("\nHvilket år: ");
                    int year = Integer.parseInt(userIn.nextLine());
                    
                    System.out.print("\nHvilken måned: ");
                    int month = Integer.parseInt(userIn.nextLine());
                    
                    System.out.print("\nHvilken dag: ");
                    int day = Integer.parseInt(userIn.nextLine());
                    
                    LocalDate date = LocalDate.of(year,month,day);
                    
                    results.setTime(member,disciplin,time,date);
                    System.out.println("den nye tid er tilføjet");
                   
                }
            }
            
        }
        else if (pagePick == 4){
        //skriver competition resultat til en fil. Hvis man har slået personlige rekord 
        //bliver det også registreret
            for (Member member : clubMemberList){
                System.out.println("ID: " + member.getID() + "\tNavn: " + member.getName());
            
            }
            System.out.println("\n Skriv ID for at give det member en ny tid");
            System.out.print("ID: ");
            int ID = Integer.parseInt(userIn.nextLine());
            for (Member member : clubMemberList){
            
                if (member.getID() == ID){
                    System.out.println("navnet på konkurrancen: ");
                    String competition = userIn.nextLine();
                    System.out.println("svømme disciplin?: ");
                    String disciplin = userIn.nextLine();
                    System.out.println("placering: ");
                    int placering = Integer.parseInt(userIn.nextLine());
                    System.out.println("Den nye tid i sekunder: ");
                    double time = Double.parseDouble(userIn.nextLine());
                    System.out.print("\nHvilket år: ");
                    int year = Integer.parseInt(userIn.nextLine());
                    
                    System.out.print("\nHvilken måned: ");
                    int month = Integer.parseInt(userIn.nextLine());
                    
                    System.out.print("\nHvilken dag: ");
                    int day = Integer.parseInt(userIn.nextLine());
                    LocalDate date = LocalDate.of(year,month,day);
                    results.setCompetetionTime(member,competition,disciplin,placering,time,date);
                }
            }

        }
        else if (pagePick == 5){
            results.getCompetitionResults(clubMemberList);
        }
        else if (pagePick == 6){
            System.out.println("Participants in the adult team: " + teamAdult.getMemberList());
            System.out.println("Participants in the children team: " + teamChild.getMemberList());

        }
        else if (pagePick != 7) {
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
                System.out.print("\nHow many members do you want to add? ");
                tempCreate = Integer.parseInt(userIn.nextLine());
                for (int i = 0; i < tempCreate; i++) {
                    clubMemberList.add(createMember());
                    System.out.println();
                }
                
            }else if (pagePick == 3) {
                System.out.print("What member do you wish to remove? ");
                tempRemove = userIn.nextLine();
                removeMember(tempRemove);
            }
            else if (pagePick == 4){
                for (Member member : clubMemberList){
                    System.out.println(member.getInfo());
                }
            }
            else if (pagePick != 5) {
                System.out.println("\nNot a valid choice, try again.\n");
            }
        }
    }
        
    
    public static void setMembers()  throws Exception { //henter alle members i arraylist klar til brug
        try {
            Scanner sysInMember = new Scanner(new File("memberList.txt"));
            while (sysInMember.hasNextLine()) {
                
                clubMemberList.add(new Member(sysInMember.nextLine()));
            }
        }catch (IOException e) {
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
    
    public static void saveMembers()  {//opdatere ændringer
        try {
            PrintStream sysOutMember = new PrintStream(new FileOutputStream("memberList.txt", false));
            for (Member member : clubMemberList) {
                sysOutMember.println(member.saveInfo());
            } 
        }catch (Exception e) {
            System.out.println("programmet lukkede forkert");
        }
    }
    
    public static Member createMember(){ //foreman funktion, lav member.
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
                return new Member(tempName, tempAge, tempMemberType, tempActivityType, trainer1);
            }else {
                return new Member(tempName, tempAge, tempMemberType, tempActivityType, trainer2);
            }
        }
        
        
        return new Member(tempName, tempAge, tempMemberType, tempActivityType);
    }
}