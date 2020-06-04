package org.acme.Model;

import java.util.ArrayList;

public class Accounts {
    private ArrayList<Account> accounts = new ArrayList<Account>();

    public ArrayList getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public Accounts addItem(Account ac){
        accounts.add(ac);
        return this;
    }
    public Accounts() {
    }
}
