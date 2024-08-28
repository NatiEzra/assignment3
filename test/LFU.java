package test;
import java.util.ArrayList;

public class LFU implements CacheReplacementPolicy {
    public ArrayList<String> words=new ArrayList<>();
    public ArrayList<Integer> count=new ArrayList<>();
    @Override
    public void add(String s){
        this.words.add(s);
        count.add(1);
        for (int i=0; i<this.words.size();i++){
            if( s.equals(this.words.get(i))){
                this.count.set(i,this.count.get(i)+1);
                this.count.set(this.words.size()-1,this.count.get(this.words.size()-1)+1);

            }
        }
    }
    @Override
    public String remove(){
        int min=this.count.get(0);
        int minIndex=0;
        String finalString=null;
        for(int i=1; i<this.count.size();i++){
            if (this.count.get(i)<min){
                min=this.count.get(i);
                minIndex=i;
            }
        }
        finalString=this.words.get(minIndex);
        this.words.remove(minIndex);
        return finalString;
    }
}
