package test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class IOSearcher {

    //public IOSearcher();
    public static boolean search(String s, String ... fileNames){
        Scanner scanner1=null;
        for(int i=0;i<fileNames.length;i++){
            try{
                scanner1=new Scanner(new File(fileNames[i])); // scanner1 is the file              
                while(scanner1.hasNext()){
                    if(s == null ? scanner1.next() == null : s.equals(scanner1.next())) 
                        return true;
                }
                scanner1.close();
            }catch(IOException e){
                scanner1.close();
                e.printStackTrace();
            }
        }
        return false;
    }
}
