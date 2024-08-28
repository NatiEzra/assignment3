package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
public class BloomFilter {
    public int size;
    public BitSet bitSet;
    public MessageDigest[] hashFuncs;
	public BloomFilter(int _size, String...args){
        this.bitSet=new BitSet(_size);
        this.size=_size;
        this.hashFuncs=new MessageDigest[args.length];
        for (int i=0; i<args.length;i++){
            try{
            hashFuncs[i]=MessageDigest.getInstance(args[i]);
            }catch(NoSuchAlgorithmException s){
                throw new RuntimeException(s);
            }
        }

    }
    public void add(String s){
        byte[] b;
        BigInteger bi;
        int i;
        for (MessageDigest func : this.hashFuncs){
            func.update(s.getBytes());
            b=func.digest();
            bi= new BigInteger(b);
            i=Math.abs(bi.intValue());
            i=i%this.size;
            this.bitSet.set(i);
        }
    }
    public Boolean contains(String s){
        byte[] b;
        BigInteger bi;
        int i;
        for (MessageDigest func : this.hashFuncs){
            func.update(s.getBytes());
            b=func.digest();
            bi= new BigInteger(b);
            i=Math.abs(bi.intValue());
            i=i%this.size;
            if(this.bitSet.get(i)!=true)
                return false;
        }
        return true;
    }

    @Override
    public String toString(){
        String writingString="";
        for(int i=0;i<bitSet.length();i++)
        {
            if(bitSet.get(i))
            {
                writingString+='1';
            }
            else{
                writingString+='0';
            }
        }
        return writingString;
    }


}
