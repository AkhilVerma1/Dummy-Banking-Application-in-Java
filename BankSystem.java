/*Write a program to create a banking application in which display the menu (Create Account/Deposit/Withdraw/Balance Enquiry).
        If the user wants to create the new account then ask the user to enter account holder name, age,
        initial deposit and password, then create an account object with account number automatically assigned
        to the user and display the details when the account is created. Provide the methods to deposit,
        withdraw and view balance. Store all the account objects in an array.
        If the user wants to deposit, withdraw or view balance then ask the user to enter the account number
        and password. Check the credentials. Fetch the details of that account number from the stored account
        objects and display the account holder name corresponding to the entered account number. Ask the user
        to confirm the account and then proceed according to the user’s choice.
        In case deposit, ask the user to enter the amount to be deposited and update the balance accordingly
        and display the updated balance.
        In case of withdrawal, ask the user to enter the amount to be withdrawn. Update the balance if the
        user is having sufficient balance. Otherwise display “Insufficient Balance”.
        In case of Balance Enquiry, Display the balance. */
import java.util.*;

import static java.lang.System.exit;

public class BankSystem {
    static ArrayList<Account> userAccounts = new ArrayList<Account>();
    static int accountId = 100122;
    static int index = -1;
    public void verificationOfAccount(){
        int accountNumber, a =-1;
        String password;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your account number and password.!");
        accountNumber = scanner.nextInt();
        scanner.nextLine();
        password = scanner.nextLine();
        for (Account account:userAccounts) {
            a++;
            if(accountNumber == account.accountId && password.equals(account.userPassword)){
                index = a;
            }
        }
        if(index == -1) {
            System.out.println("Verification failed!");
            exit(1);
        }
    }
    public void menu(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        outer:
        while (true){
            System.out.println("Please enter your choice.\n1.Create Account\n2.Deposit\n3.Withdraw\n4.Balance Enquiry\n5.Exit");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    //create account.!
                    String userName, userPassword;
                    int user_Age, initialDeposit;
                    System.out.println("Please enter your Name.!");
                    userName = scanner.next();
                    System.out.println("Please enter your Password.!");
                    userPassword = scanner.next();
                    System.out.println("Please enter your Age.!");
                    user_Age = scanner.nextInt();
                    System.out.println("Please enter your Initial Deposit.!");
                    initialDeposit = scanner.nextInt();
                    Account user;
                    user = new Account(userName,userPassword,user_Age,initialDeposit);
                    userAccounts.add(user);
                    break;
                case 2:
                    //Deposit the money.!
                    verificationOfAccount();
                    int moneyToDeposit;
                    System.out.println("Enter the amount you want to deposit.!");
                    moneyToDeposit = scanner.nextInt();
                    userAccounts.get(index).deposit(moneyToDeposit);
                    break;
                case 3:
                    //Withdraw the amount.!
                    verificationOfAccount();
                    int amount;
                    System.out.println("Enter the amount to withdraw.!");
                    amount= scanner.nextInt();
                    userAccounts.get(index).withdraw(amount);
                    break;
                case 4:
                    //Balance enquiry.!
                    verificationOfAccount();
                    userAccounts.get(index).balanceEnquiry();
                    break;
                case 5:
                    System.out.println("Thank you for visiting us.!");
                    break outer;
            }
        }
    }

    public static void main(String[] args) {
        new BankSystem().menu();
    }
}
class Account extends BankSystem{
    int accountId;
    String userName, userPassword;
    int user_Age, initialDeposit;
    int totalBalance = 0;
    public Account(String userName, String userPassword, int user_Age, int initialDeposit){
        this.accountId = BankSystem.accountId;
        ++BankSystem.accountId;
        this.user_Age = user_Age;
        this.userName = userName;
        this.userPassword = userPassword;
        this.initialDeposit = initialDeposit;
        totalBalance = initialDeposit;
        System.out.println("Congratulations, Account Created Successfully.");
        System.out.println("Account Id: "+accountId +  ",Name : " + userName + ", Password : " + userPassword + ", Initial Deposit : " + initialDeposit + ", Age : " + user_Age);
    }
    public void deposit(int money){
      totalBalance += money;
      System.out.println("Money deposited Successfully.!");
    }
    public void withdraw(int amount){
        if (amount <=totalBalance)
            totalBalance -=amount;
        else
            System.out.println("Insufficient balance.!");
    }
    public void balanceEnquiry(){
        System.out.println("So, your total Balance : "+totalBalance);
    }
}
