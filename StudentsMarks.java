import java.io.File;
import java.util.Scanner;
import java.util.*;

/**
 * PROG5001 Assignment 2
 * @author (Muhammad Kashif - Student ID: 23598588)
 * @version (Final version)
 */

public class StudentsMarks
{
    private ArrayList<StudentsMarks> list;
    private String fullName;
    private int id;
    private double a1,a2,a3;
    
    
    public StudentsMarks()
    {
       
    }
    
    public StudentsMarks(String lastName, String firstName, int id, double a1,  double a2, double a3)
    {
           this.fullName = firstName+' '+lastName;
           this.id = id;
           this.a1 = a1;
           this.a2 = a2;
           this.a3 = a3;
    }
    
    public static void main(String[] args) throws Exception{
        StudentsMarks st = new StudentsMarks();
        st.mainMenu();
    }
    
    public void mainMenu() throws Exception {
        
            list = fileRead();
            Scanner option = new Scanner(System.in);
            System.out.println("\n Select the menu: "+
            "\n 1. Enter 1 to display students information and assignment marks."+
            "\n 2. Enter 2 to display students information with total marks of all assignments."+
            "\n 3. Enter 3 to display Students Details, Marks and Total Marks for who less than threshold."+
            "\n 4. Enter 4 to display the top 10 students with the highest total marks and top 10 students with the lowest total marks."+
            "\n 5. Enter 0 to End ");
            
            int optionId;
            optionId = option.nextInt();
             switch(optionId){
                 case 1:
                   System.out.println("Student Details and Marks.");
                   for(StudentsMarks l:list){
                        System.out.println(l.toString());
                    }
                        mainMenu();
                        break;
                case 2:
                   System.out.println("Student Details, Marks and Total Marks.");
                   for(StudentsMarks l:list){
                        System.out.println(l.toString2());
                    }
                        mainMenu();
                        break;
                        
                case 3:
                   System.out.println("Student Details, Marks and Total Marks for who less than the threshold you will enter now:");
                   float th;
                   th = option.nextInt();
                   
                   
                   for(StudentsMarks l:list){
                       String s = l.toString3(th);
                       if(s!=null){
                        System.out.println(s);
                        }
                    }
                        mainMenu();
                        break;
                        
                case 4:
                   System.out.println("Top 10 students with the highest total marks and top 10 students with the lowest total marks:");

                    
                    for(StudentsMarks l:list){
                        
                       String s = l.toString4();
                       if(s!=null){
                        System.out.println(s);
                        
                    }
                        
                    }
                         
                        mainMenu();
                        break;
                        
                case 0:
                        break;
                   
            }
        
    }
    
     public static ArrayList<StudentsMarks> fileRead() throws Exception{
     
     ArrayList<StudentsMarks> list = new ArrayList<StudentsMarks>();
     Scanner file = new Scanner(new File("prog5001_students_grade_2022.csv"));
     file.useDelimiter(",");
     String unitName;
     
     while(file.hasNextLine()){
         String line = file.nextLine();
         String[] data;
         String initialLine;
         
         if(line.contains("Unit")){
            initialLine = line;
        }else if(!line.contains("First Name")){
            data = line.split(",");
            
            if(data.length == 4){
                StudentsMarks s1 = new StudentsMarks(data[0],data[1],Integer.parseInt(data[2]),Double.parseDouble(data[3]),0.0,0.0);
                list.add(s1);
            }else if(data.length == 5){
                list.add(new StudentsMarks(data[0],data[1],Integer.parseInt(data[2]),Double.parseDouble(data[3]),Double.parseDouble(data[4]),0.0));
            }else if(data.length == 3){
                list.add(new StudentsMarks(data[0],data[1],Integer.parseInt(data[2]),0.0,0.0,0.0));
            }else{
                String a1 = data[3];
                String a2 = data[4];
                String a3 = data[5];
                
                if(data[3].isEmpty()){
                a1 = "0.0";
                }
                if(data[4].isEmpty()){
                a2 = "0.0";
                }
                if(data[5].isEmpty()){
                a3 = "0.0";
                }
                list.add(new StudentsMarks(data[0],data[1],Integer.parseInt(data[2]),Double.parseDouble(a1),Double.parseDouble(a2),Double.parseDouble(a3)));
            }
        }
        
        }
     file.close();
     return list;
     
    }
    
    public String toString(){
        return String.format("id:%d, FullName: %s, A1:%.2f, A2:%.2f, A3:%.2f", 
        id,fullName,a1,a2,a3);
    }
    
   public String toString2(){
        double totalMarks = getTotalMarks();
        return String.format("id:%d, FullName: %s, A1:%.2f, A2:%.2f, A3:%.2f, Total:%.2f", 
        id,fullName,a1,a2,a3,totalMarks);
    }
    
    
   public String toString3(float th){
        double totalMarks = getTotalMarks();
        if(totalMarks<th){ 
            return String.format("id:%d, FullName: %s, A1:%.2f, A2:%.2f, A3:%.2f, Total:%.2f", 
            id,fullName,a1,a2,a3,totalMarks);
            
        }else{
        return null;
        }
    }
    
    
    public String toString4(){
        double totalMarks = getTotalMarks();
        return String.format("id:%d, FullName: %s, A1:%.2f, A2:%.2f, A3:%.2f, Total:%.2f", 
        id,fullName,a1,a2,a3,totalMarks);
    }
    
    public double getTotalMarks(){
        double total = a1+a2+a3;
        return total;
    }

   
}
