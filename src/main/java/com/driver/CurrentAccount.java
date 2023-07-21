package com.driver;

import java.util.HashMap;
import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
          validateBalance();
        this.tradeLicenseId=tradeLicenseId;
          validateLicenseId();


    }

    private void validateBalance() throws Exception {
        try{
            remainingBalance(getBalance());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void remainingBalance(double balance) throws Exception {
        if(balance<5000){
            throw new Exception("Insufficient Balance");
        }
    }


    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        try{
            tradeLicenseId=rearrangeLicenseId(tradeLicenseId);
        }catch(Exception e){
            System.out.println(e.getMessage());

        }



    }

    public static String rearrangeLicenseId(String licenseId) throws Exception {
        StringBuilder ans=new StringBuilder();
        HashMap<Character,Integer> hp=new HashMap<>();
        for(char c:licenseId.toCharArray()){
            hp.put(c,hp.getOrDefault(c,0)+1);
        }
        PriorityQueue<Character> queue=new PriorityQueue<>((a,b)->hp.get(b)-hp.get(a));
        queue.addAll(hp.keySet());
        while(!queue.isEmpty()){
            char topChar= queue.poll();
            if(ans.length()>0 && ans.charAt(ans.length()-1)==topChar){
                if(queue.isEmpty()) throw new Exception("Valid License can not be generated");
                char topSecChar= queue.poll();
                ans.append(topSecChar);
                hp.put(topSecChar,hp.getOrDefault(topSecChar,0)-1);
                if(hp.get(topSecChar)>0) queue.add(topSecChar);

            }
            ans.append(topChar);
            hp.put(topChar,hp.getOrDefault(topChar,0)-1);
            if(hp.get(topChar)>0) queue.add(topChar);
        }
        return ans.toString();
    }



    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }
}
