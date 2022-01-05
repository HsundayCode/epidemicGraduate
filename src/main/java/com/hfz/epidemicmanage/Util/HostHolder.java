package com.hfz.epidemicmanage.Util;

import com.hfz.epidemicmanage.Entity.Account;
import org.springframework.stereotype.Component;

@Component
public class HostHolder {
    private ThreadLocal<Account> accounts = new ThreadLocal<>();

    public void setAccount(Account account){
        accounts.set(account);
    }

    public Account getAccount(){
        return accounts.get();
    }

    public void clear(){
        accounts.remove();
    }

}
