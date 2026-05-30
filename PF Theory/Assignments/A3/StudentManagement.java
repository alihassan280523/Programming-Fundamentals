import java.util.*;
import java.io.*;
import javax.swing.*;


public class StudentManagement {
    
    public static void main(String[] args)throws Exception{

        Scanner sc=new Scanner(System.in);
        int optionSelected;
        
        do{
            
            String menuInput=JOptionPane.showInputDialog("Enter 1 to insert a new record\n"+
            "Enter 2 to update record\n"+
            "Enter 3 to delete a record\n"+
            "Enter 4 to show all student records\n"+
            "Enter 5 to exit\n"
        );
        optionSelected=Integer.parseInt(menuInput);
        
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
                case 2:{
                    
                    String name=JOptionPane.showInputDialog("Enter Student name: ");
                    updateRecord(name);
                    break;
                }
                case 3:{
                    break;
                }
                case 4:{
                    showRecord();
                    break;
                }
                case 5:{
                    System.exit(0);
                    break;
                }
                default:{
                    break;
                }
        }
            
    }while(optionSelected!=5);

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

    public static void updateRecord(String name)throws Exception{

        boolean isFound=false;
        String line;

        FileReader fr=new FileReader("StudentData.txt");
        BufferedReader br=new BufferedReader(fr);
        
        String[] arr={};
        int count=1;
        name=name.toUpperCase();

        while(!isFound&&(line=br.readLine())!=null){
            arr = line.split(",");

            if((arr[0].toUpperCase()).equals(name)){
                isFound=true;
                int option=Integer.parseInt(JOptionPane.showInputDialog("What do you want to update:\n"+
                                                        "1. Name\n"+
                                                         "2. Age\n"+
                                                         "3. Phone Number\n"));

                switch(option){
                    case 1:{
                        String n=JOptionPane.showInputDialog("Enter updated name: ",arr[0]);
                        arr[0]=n;
                        break;
                    }
                    case 2:{
                        String age=JOptionPane.showInputDialog("Enter the updated age: ",arr[1]);
                        arr[1]=age;
                        break;
                    }
                    case 3:{
                        String number=JOptionPane.showInputDialog("Enter the updated number: ",arr[2]);
                        arr[2]=number;
                        break;
                    }
                    default:{
                        break;
                    }
                }

            }
            else{
                count++;
            }

            
            
        }

        if(!isFound){
            JOptionPane.showMessageDialog(null,"No Record Found");
            br.close();
            fr.close();
            return;
        }

        String updatedRecord=arr[0]+","+arr[1]+","+arr[2]+"\n";

        br.close();
        fr.close();

        fr=new FileReader("StudentData.txt");
        br=new BufferedReader(fr);
        
        FileWriter fw=new FileWriter("StudentDataTemp.txt");
        BufferedWriter outputData=new BufferedWriter(fw);

        while((line=br.readLine())!=null){

            outputData.write(line+"\n");
        }

        outputData.close();
        fw.close();
        br.close();
        fr.close();


        fr=new FileReader("StudentDataTemp.txt");
        br=new BufferedReader(fr);

        fw=new FileWriter("StudentData.txt");
        outputData=new BufferedWriter(fw);

        int i=1;

        while((line=br.readLine())!=null){
            if(i==count){
                outputData.write(updatedRecord);
            }
            else
            outputData.write(line+"\n");

            i++;
        }

        br.close();
        fr.close();
        outputData.close();
        fw.close();
    }

    public static void removeRecord(){

        
    }

    public static void showRecord()throws Exception{

        FileReader fr =new FileReader("StudentData.txt");
        BufferedReader br=new BufferedReader(fr);

        int i=1;

        String line;
        String[] arr;
        String print="";

        while((line=br.readLine())!=null){
            arr=line.split(",");
            print+=Integer.toString(i)+". ";
            i++;
            for(int j=0;j<3;j++){
                print+=arr[j]+"     ";
            }

            print+="\n";
        }

       
        JOptionPane.showMessageDialog(null, print);

        br.close();
        fr.close();
    }


    
}
