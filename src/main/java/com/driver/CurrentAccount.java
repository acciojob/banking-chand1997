package com.driver;

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

        try {
            String validLicenseId = rearrangeLicenseId(tradeLicenseId);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String rearrangeLicenseId(String licenseId) throws Exception {
        if (licenseId == null || licenseId.isEmpty()) {
            throw new IllegalArgumentException("License ID must not be null or empty.");
        }

        // Check if the given licenseId is already valid
        if (isValidLicenseId(licenseId)) {
            return licenseId;
        }

        // Rearrange the licenseId to make it valid
        char[] chars = licenseId.toCharArray();
        for (int i = 1; i < chars.length; i += 2) {
            if (chars[i] == chars[i - 1]) {
                int j = i + 1;
                while (j < chars.length && chars[j] == chars[i]) {
                    j++;
                }
                if (j == chars.length) {
                    throw new Exception("Valid License can not be generated");
                }
                char temp = chars[j];
                chars[j] = chars[i];
                chars[i] = temp;
            }
        }
        return new String(chars);
    }

    private static boolean isValidLicenseId(String licenseId) {
        for (int i = 1; i < licenseId.length(); i++) {
            if (licenseId.charAt(i) == licenseId.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }
}
