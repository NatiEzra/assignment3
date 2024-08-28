package test;

import java.util.ArrayList;

public class LRU implements CacheReplacementPolicy {
    public ArrayList<String> words=new ArrayList<>();
    
    @Override
    public void add(String s){
        
        this.words.add(s);
    }
    @Override
    public String remove(){
        int i, j;
        String removedWord=null;
        for (i=0; i<this.words.size() ;i++){
            for(j=i+1;j<this.words.size();j++){
                if(this.words.get(j).equals(this.words.get(i))){
                    break;
                }
                if(j==this.words.size()-1){
                    removedWord=this.words.get(i);
                    this.words.remove(i);
                    return removedWord;
                }

            }
            
        }
        return removedWord;
    }
}
