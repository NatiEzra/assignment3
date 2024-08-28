package test;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class DictionaryManager {
    Map<String, Dictionary> dictionaryMap=new HashMap<>();
    public static DictionaryManager get(){
        return new DictionaryManager();
    }
    public boolean  query(String... bookList ){
        int last=bookList.length-1;
        boolean flag=false;
        boolean wordInFile;
        try {
            for (int i=0; i<bookList.length-1;i++){
                wordInFile=false;
                Dictionary d = dictionaryMap.get(bookList[i]);
                if(d==null){
                    d=new Dictionary(bookList[i]);
                    dictionaryMap.put(bookList[i], d);
                }
                Scanner in = new Scanner(new File(bookList[i]));
                if(!(d.cache1.query(bookList[last])||d.chache2.query(bookList[last])))
                {
                    while (in.hasNext()) 
                    {
                        String word = in.next();
                        if (word.equals(bookList[last]))
                        {
                            flag=true;
                            d.cache1.add(word);
                            wordInFile=true;
                        }
                    }
                    if(!wordInFile){
                        d.chache2.add(bookList[last]);
                    }
                }
                else if(d.cache1.query(bookList[last]))
                    flag=true;
                in.close();
            }
		}catch(Exception e) {}
        
        return flag;
    }
    public boolean  challenge(String... bookList ){
        boolean flag=false;
        int last=bookList.length-1;
        for (int i=0; i<bookList.length-1;i++){
            Dictionary d = dictionaryMap.get(bookList[i]);
            if(d==null){
                d=new Dictionary(bookList[i]);
                dictionaryMap.put(bookList[i], d);
            }
            if(d.challenge(bookList[last]))
                flag=true;
        }
        return flag;
    }
    public int getSize(){
        return this.dictionaryMap.size();
    }
}
