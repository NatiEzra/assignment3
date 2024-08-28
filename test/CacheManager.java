package test;

import java.util.HashSet;


public class CacheManager{
	public int size;
    public CacheReplacementPolicy crp;
    public HashSet<String> words;
    public CacheManager(int _size,  CacheReplacementPolicy _crp ){
        this.size=_size;
        this.crp=_crp;
        this.words=new HashSet<>();
    }
	public boolean query(String s){
        return this.words.contains(s);
    }
    public void add(String s){
        if (this.words.size()==this.size){
            String foo=this.crp.remove();
            this.words.remove(foo);
        }
        this.words.add(s);
        this.crp.add(s);
        
    }

}
