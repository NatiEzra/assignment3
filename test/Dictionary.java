package test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Dictionary {
    public CacheManager cache1;
    public CacheManager chache2;
    public BloomFilter bf;
    public String[] filenames;
    public Dictionary (String... filenames){
        cache1=new CacheManager(400, new LRU()); //exist
        chache2=new CacheManager(100, new LFU()); // lo KAYAM
        bf =new BloomFilter(256,"MD5","SHA1");
        this.filenames=new String[filenames.length];
        for(int i=0;i<filenames.length;i++){
            this.filenames[i]=filenames[i];
    }
   // bf.add(cache1.words);  
    Scanner s1=null;
    for(int i=0;i<filenames.length;i++){
            try{
                s1=new Scanner(new File(filenames[i])); // s1 is the file              
                while(s1.hasNext()){
                    bf.add(s1.next());
                }
                s1.close();
            }catch(IOException e){
                s1.close();
                e.printStackTrace();
            }
        }
}
public boolean query(String s){

    if(cache1.query(s))
        return true;
    else if(chache2.query(s)){
            return false;
        }
    else if(bf.contains(s)){
        cache1.add(s);
        return true;
    }
    else{
        chache2.add(s);
        return false;
    }

}

public boolean challenge(String s){
    IOSearcher io=new IOSearcher();
    boolean isIn=false;
    try{
    isIn=io.search(s,this.filenames);
    if(isIn)
    {
        cache1.add(s);
        return true;
    }
    else if(isIn==false)
    {
        chache2.add(s);
        return false;
    }
} catch(Exception e){
    e.printStackTrace();
    return false;
    }
    return false;
}


}
