import java.io.*;
import java.util.*;
import java.time.*;

public class ResultsUK{
 
    int count;
 
    public void setTime(MemberUK member, String disciplin, double newTime,  LocalDate date){
        boolean hasMade = false;
        File file = new File("bestTime.txt");
        try {
            Scanner scan = new Scanner(file);
            String tempString;
            Scanner stringScan;
            while(scan.hasNextLine()){
                tempString = scan.nextLine();
                stringScan = new Scanner(tempString);
                
                if (Integer.parseInt(stringScan.next()) == member.getID()){
                    if (!stringScan.next().equals(disciplin)){
                        continue;
                    }
                    if (newTime >= Double.parseDouble(stringScan.next())){
                        System.out.println("The new time is not better than the old one");
                        return;
                    }else { 
                        File tempFile = new File("tempBestTime.txt"); 
                        PrintStream tempStream = new PrintStream (new FileOutputStream(tempFile,false));
                        Scanner tempScan = new Scanner(file);
                        String aTempString;
                        while (tempScan.hasNextLine()){
                            aTempString = tempScan.nextLine();
                            if (aTempString.equals(tempString)){
                                continue;
                            }else {  
                                tempStream.println(aTempString);
                            }
                        }
                        tempScan.close();
                        tempStream.close();
                        try {
                            PrintStream stream = new PrintStream (new FileOutputStream(tempFile,true));
                            stream.println(member.getID() + " " + disciplin + " " + newTime + " " + date);
                            stream.close();       
                        }catch (IOException e){
                            System.out.println("Error at setTime()");
                        }  
                        scan.close();
                        stringScan.close();
                                
                        file.delete();
                        File newFile = new File("bestTime.txt");
                        tempFile.renameTo(newFile);
                        hasMade = true;
                        return;
                    }
                }
            }   
        } catch (IOException e){
            System.out.println("Error at setTime()");
        }  
        if (!hasMade){
            try {              
                 PrintStream stream = new PrintStream (new FileOutputStream(file,true));
                 stream.println(member.getID() + " " + disciplin + " " + newTime + " " + date);
                 stream.close();       
            }catch (IOException e){
                 System.out.println("Error at setTime()");
            }
        }         
    } 

    public void setCompetetionTime(MemberUK member, String name, String disciplin, int placement, double newTime, LocalDate date){
        try{
            PrintStream stream = new PrintStream (new FileOutputStream("competitionTime.txt", true));
            stream.println(member.getID() + " " + name + " " + disciplin + " " + placement + " " + newTime);
            stream.close();     
        }catch (IOException e){
            System.out.println("Error at setCompetetionTime()");
        }       
        setTime(member,disciplin,newTime,date);
    }
    
    public String getTop5Times(String disciplin){
        ArrayList<String> bestTimes = new ArrayList<String>();
        try {
            File file = new File("bestTime.txt");
            Scanner scan = new Scanner(file);
                     
            Scanner stringScan;
            double temp;
            
            while (scan.hasNextLine()){
                String tempString = scan.nextLine();
                
                stringScan = new Scanner(tempString);
                stringScan.next();
                if (!stringScan.next().equals(disciplin)){
                    continue;
                }
                temp = Double.valueOf(stringScan.next());
                if (bestTimes.size() < 5){
                    bestTimes.add(tempString);
                    continue;
                }
                stringScan = new Scanner(bestTimes.get(0));
                stringScan.next();
                stringScan.next();
                double worstTime = Double.valueOf(stringScan.next());
                int index = 0;
                double worstTimeTemp;
                for (int i = 1; i < bestTimes.size();i++){
                    stringScan = new Scanner(bestTimes.get(i));
                    stringScan.next();
                    stringScan.next();
                    worstTimeTemp = Double.valueOf(stringScan.next());
                    
                    if (worstTime < worstTimeTemp){ 
                        index = i;
                        worstTime = worstTimeTemp; 
                    } 
                }
                if (worstTime > temp){
                    bestTimes.remove(index);
                    bestTimes.add(tempString);
                }
            }
            scan.close(); 
        }catch (IOException e){
            System.out.println("Error at getTop5Times()");
        }

    return "The best times for " + disciplin + ": " + bestTimes; 
    }
    
    public void getCompetitionResults(ArrayList<MemberUK> memberList){
        try{
            File file = new File("competitionTime.txt");
            Scanner fileScan = new Scanner(file);
            MemberUK tempMember;
            String tempString;
            Scanner stringScan;
            
            while (fileScan.hasNextLine()){
                tempString = fileScan.nextLine();
                stringScan = new Scanner(tempString);
                int ID = Integer.parseInt(stringScan.next());
                for (MemberUK member : memberList){
                    if (ID == (member.getID())){
                        System.out.println(member.getName() + " swam at: " + stringScan.next() + 
                        ". In the category: " + stringScan.next() + 
                        ". " + member.getName() + " got the placement: " + stringScan.next() + 
                        ", with the time: " + stringScan.next() + " seconds");
                    }
                } 
            }
            fileScan.close(); 
        }
        catch (IOException e){
            System.out.println("Error getCompetitionResults()");
        }
    }
}