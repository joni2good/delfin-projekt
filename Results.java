import java.io.*;
import java.util.*;
import java.time.*;

public class Results{
 
    int count;
 
    public void setTime(Member member, String disciplin, double newTime,  LocalDate date){
    //skal opdatere så hvert medlem kun kan have 1 bedste tid for hver disciplin.
        boolean hasMade = false;
        File file = new File("bestTime.txt");
        try {
            Scanner scan = new Scanner(file);
            String tempString;
            Scanner stringScan;
            while(scan.hasNextLine()){
            //sikre at der kun er 1 tid per member per disciplin.
                tempString = scan.nextLine();
                stringScan = new Scanner(tempString);
                
                if (Integer.parseInt(stringScan.next()) == member.getID()){
                    if (!stringScan.next().equals(disciplin)){  //tjekker om tiden er for rigtige disciplin.
                        
                        continue;
                    }
                    if (newTime >= Double.parseDouble(stringScan.next())){ //tjekker om tiden er bedre.
                        System.out.println("Den nye tid er ikke bedre end den gamle");
                        return; //hvis den er dårligere, går vi ud af metoden, fordi intet skal ændres.
                        
                    }
                    else { //den nye tid er bedre og skal skiftes. her skal den gamle fjernes og nye tilføjes.
                    //bagefter return for at minimere whileloop ting brug.
                      
                        File tempFile = new File("tempBestTime.txt"); 
                        PrintStream tempStream = new PrintStream (new FileOutputStream(tempFile,false));
                        Scanner tempScan = new Scanner(file);
                        String aTempString;
                        //fjerne 1 linje ved at skrive hele filen til en ny fil minus den linje der skal fjernes.
                        
                        while (tempScan.hasNextLine()){
                            aTempString = tempScan.nextLine();
                            if (aTempString.equals(tempString)){
                                //hvis linjen er den linje der skal fjernes
                                
                                continue;
                            }
                            else {
                               
                                tempStream.println(aTempString);
                            }
                        }
                        tempScan.close(); //lukke stream/scan så man kan ændrer filer uden fejl.
                        tempStream.close();
                        try {
                       
                            PrintStream stream = new PrintStream (new FileOutputStream(tempFile,true));
                            stream.println(member.getID() + " " + disciplin + " " + newTime + " " + date);
                            stream.close();       
                        } 
                        catch (IOException e){
                            System.out.println("error at setTime()");
                        }  
                        scan.close();//lukke stream/scan så man kan ændrer filer uden fejl.
                        stringScan.close();
                                
                        file.delete(); //fjerner gamle fil 
                        File newFile = new File("bestTime.txt");
                        tempFile.renameTo(newFile); //giver nye fil rigtige navn (har fjernet 1 linje og added 1)
                        hasMade = true;   //teknisk ikke nødvendig, men hvis koden bliver ændret kan det være en god fail safe. 
                        return;
                    
                    }
                    //fjerne gamle hvis nye bedre, how.
                    //en mulighed er at skrive til en ny fil uden den gamle tid.

                }
            }   
        } 
        catch (IOException e){
            System.out.println("Error at setTime()");
        }  
        if (!hasMade){ //hvis der ikke findes et tidliger resultat, oprettes 1 nyt her.
            try {
                               
                 PrintStream stream = new PrintStream (new FileOutputStream(file,true));
                 stream.println(member.getID() + " " + disciplin + " " + newTime + " " + date);
                 stream.close();       
            } 
                 catch (IOException e){
                 System.out.println("error at setTime()");
            }
        }
        
         
    } 

    public void setCompetetionTime(Member member, String name, String disciplin, int placement, double newTime, LocalDate date){
        try{
            PrintStream stream = new PrintStream (new FileOutputStream("competitionTime.txt"));
            stream.println(member.getID() + " " + name + " " + disciplin + " " + placement + " " + newTime);
            stream.close();
                
        }
        catch (IOException e){
            System.out.println("Error at setCompetetionTime()");
        }
        //giver tiden videre til setTime, for at give mulighed for at bedste tid bliver registeret.
        setTime(member,disciplin,newTime,date);
    }
    
    public String getTop5Times(String disciplin){
    //skrald kode, ikke tÃ¦nk over det!
        ArrayList<String> bestTimes = new ArrayList<String>();   //de 5 bedste tider
        try {
            File file = new File("bestTime.txt"); //filen med tider
            Scanner scan = new Scanner(file); //scanner filen 
            
                     
            Scanner stringScan;  //for at scanne hver linje efter de strings vi skal bruge
            double temp; //temp tid per besttime linje
            
            while (scan.hasNextLine()){ //scanner alle linjer
                String tempString = scan.nextLine(); //midlertidig string, tempSTring kan bruges flere gange.
                
                stringScan = new Scanner(tempString); //scanner string for at finde data der skal bruges
                stringScan.next(); //springer id over, de bør være uniqqe når set time virker
                if (!stringScan.next().equals(disciplin)){ //hvis der er rigtig disciplin, eller skip.
                    continue;
                }
                temp = Double.valueOf(stringScan.next()); //gemmer bestTime linjens tid i temp.
                if (bestTimes.size() < 5){ //hvis der ikke er top 5 endnu, bliver de filføjet.
                    bestTimes.add(tempString);
                    
                    continue;
                }
                stringScan = new Scanner(bestTimes.get(0)); //starter ved 0, for at have noget at sammenligne med
                stringScan.next();
                stringScan.next();
                double worstTime = Double.valueOf(stringScan.next()); //worstTime sammenlignes med temp
                int index = 0;
                double worstTimeTemp;
                for (int i = 1; i < bestTimes.size();i++){
                    stringScan = new Scanner(bestTimes.get(i)); //loop finder worstTime
                    stringScan.next();
                    stringScan.next();
                    worstTimeTemp = Double.valueOf(stringScan.next());
                    
                    if (worstTime < worstTimeTemp){ 
                        index = i; //gemmer index for at finde worstTime i Array så det kan skiftes ud senere
                        worstTime = worstTimeTemp; 
                    } 
                }
                if (worstTime > temp){ //hvis temp er beder end worstTime, bliver den skiftet ind i array.
                    bestTimes.remove(index);
                    bestTimes.add(tempString);
                }
            }
            scan.close();//altid lukke Scanner og Streams :D 
            
        }
        catch (IOException e){
            System.out.println("Error at getTop5Times()");
        }
    
    return "De bedste tider for " + disciplin + ": " + bestTimes; 
    }
    public void getCompetitionResults(ArrayList<Member> memberList){
    //bruger memberlist til at finde ud af hvem der gjorde hvad. 
        try{
            File file = new File("competitionTime.txt");
            Scanner fileScan = new Scanner(file);
            Member tempMember; //tror ikke den bliver brugt men man ved aldrig
            String tempString;
            Scanner stringScan;
            
            while (fileScan.hasNextLine()){
                tempString = fileScan.nextLine();
                stringScan = new Scanner(tempString);
                int ID = Integer.parseInt(stringScan.next());
                for (Member member : memberList){
                    if (ID == (member.getID())){
                        
                        //ikke den mest elegante string, jgrasp fucker desuden danske bogstaver op.
                        System.out.println(member.getName() + " har svømmet stævnet: " + stringScan.next() + 
                        ". I katogorien: " + stringScan.next() + 
                        ". " + member.getName() + "fik en placering af: " + stringScan.next() + 
                        ". " + member.getName() + " fik tiden: " + stringScan.next());
                        break;
                    }
                }
                 
                
            }
            
            fileScan.close();  //altid lukke for at sikre sig mod fejl.    
                
        }
        catch (IOException e){
            System.out.println("Error getCompetitionResults()");
        }
    
    }
}