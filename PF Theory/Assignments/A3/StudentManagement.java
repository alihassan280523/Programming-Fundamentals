import java.util.*;
import java.io.*;
import javax.swing.*;


public class StudentManagement {
    
    public static void main(String[] args)throws Exception{
        
        String menuInput=JOptionPane.showInputDialog("Enter 1 to insert a new record");

        int optionSelected=Integer.parseInt(menuInput);
        switch(optionSelected){
            case 1:
                {   
                    String insertMenuInput=JOptionPane.showInputDialog("Enter 1 to insert at start of the file\n"+
                                                                        "Enter 2 to insert at a specific position\n "+
                                                                        "Enter 3 to insert at end");
                    int option=Integer.parseInt(insertMenuInput);
                    switch(option){
                        case 1:{
                            insertAtStart();
                            break;
                        }
                        case 2:{
                            insertAt(Integer.parseInt(JOptionPane.showInputDialog("Enter the position: ")));
                            break;
                        }
                        case 3:{
                            insert();
                            break;
                        }
                        default:{
                            break;
                        }
                    }
                    break;
                }
        }
        
    }

    public static void insertAtStart()throws Exception{
        String studentData="";
        studentData+=JOptionPane.showInputDialog("Enter student Name: ")+",";
        studentData+=JOptionPane.showInputDialog("Enter student age: ")+",";
        studentData+=JOptionPane.showInputDialog("Enter student Phone number: ")+"\n";


        
        
        
        FileReader fr=new FileReader("StudentData.txt");
        BufferedReader br=new BufferedReader(fr);
        
        FileWriter fw=new FileWriter("StudentDataTemp.txt");
        BufferedWriter outputData=new BufferedWriter(fw);

        String line=null;

        while((line=br.readLine())!=null){

            outputData.write(line+"\n");
        }

        outputData.close();
        fw.close();
        br.close();
        fr.close();


        fw=new FileWriter("StudentData.txt");
        outputData=new BufferedWriter(fw);

        outputData.write(studentData);

        outputData.close();
        fw.close();

        fr=new FileReader("StudentDataTemp.txt");
        br=new BufferedReader(fr);

        fw=new FileWriter("StudentData.txt",true);
        outputData=new BufferedWriter(fw);

        while((line=br.readLine())!=null){
            outputData.write(line+"\n");
        }

        br.close();
        fr.close();
        outputData.close();
        fw.close();

        
    }

    public static void insertAt(int pos) throws Exception{

        
        int i=0;
        
        FileReader fr=new FileReader("StudentData.txt");
        BufferedReader br=new BufferedReader(fr);
        
        FileWriter fw=new FileWriter("StudentDataTemp.txt");
        BufferedWriter bw=new BufferedWriter(fw);
        
        String line="";
        
        while((line=br.readLine())!=null){
            bw.write(line+"\n");
            i++;
        }

        bw.close();
        fw.close();
        br.close();
        fr.close();

        
        if(pos>i){
            JOptionPane.showMessageDialog(null, "Invalid Posiiton, Max student count is "+(i+1));
            return;
        }

        String studentData="";

        studentData+=JOptionPane.showInputDialog("Enter student name: ")+",";
        studentData+=JOptionPane.showInputDialog("Enter Student Age: ")+",";
        studentData+=JOptionPane.showInputDialog("Enter Student Phone Number: ")+"\n";

        fr=new FileReader("StudentDataTemp.txt");
        br=new BufferedReader(fr);

        fw=new FileWriter("StudentData.txt");
        bw=new BufferedWriter(fw);

        i=1;

        while((line=br.readLine())!=null){
            if(i==pos){
                bw.write(studentData);
                bw.write(line+"\n");
            }else{
                bw.write(line+"\n");
            }
            i++;
        }

        bw.close();
        fw.close();
        br.close();
        fr.close();

    }

    public static void insert()throws Exception{
        String studentData="";

        studentData+=JOptionPane.showInputDialog("Enter Student Name: ")+",";
        studentData+=JOptionPane.showInputDialog("Enter Student Age: ")+",";
        studentData+=JOptionPane.showInputDialog("Enter Student Phone Number: ")+"\n";

        FileWriter fw=new FileWriter("StudentData.txt",true);
        BufferedWriter bw=new BufferedWriter(fw);

        bw.write(studentData);

        bw.close();
        fw.close();
    }
    
}
