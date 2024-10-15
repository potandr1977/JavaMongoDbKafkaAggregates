package com.potandr1977.mongoPoc.persons;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collection="persons")
@Getter
@Setter(AccessLevel.PROTECTED)
public class Person {
    @Id
    private String id;

    private String name;

    private String inn;

    private Date createDate;

    private List<Account> accounts = new ArrayList<Account>();

    public List<Account> getAccounts(){
        return Collections.unmodifiableList(accounts);
    };

    public static Person create(String name, String inn, List<Account> accounts)
    {
        var id = "Person-"+UUID.randomUUID();
        var person = new Person();
        person.setId(id);
        person.setName(name);
        person.setInn(inn);
        if (accounts != null) {
            person.accounts.addAll(accounts);
        }

        return person;
    }

    public void setName(String newName)
    {
        var digits = "0123456789";
        var intersection = GetStringIntersection(digits, newName);

        /*
        if (intersection.length > 0) {
            throw new IllegalArgumentException("В имени должны быть только буквы");
        }
        */
        this.name = newName;
    }

    public void setInn(String newInn)
    {
        var digits = "0123456789";
        var intersection = GetStringIntersection(digits, newInn);

        /*
        if (intersection.length < digits.length()) {
            throw new IllegalArgumentException("В инн должны быть только цифры");
        }
         */

        inn = newInn;
    }

    public void addAccount(Account account)
    {
        if (accounts.stream().anyMatch(x -> x.getName().equals(account.getName())))
        {
            throw new IllegalArgumentException("Такой счёт уже существует");
        }

        accounts.add(account);
    }

    private Character[] GetStringIntersection(String s1, String s2)
    {
        char[] charArray1 = s1.toCharArray();
        char[] charArray2 = s2.toCharArray();
        HashSet<Character> h1 = new HashSet<Character>(), h2 = new HashSet<Character>();
        for(int i = 0; i < charArray1.length; i++)
        {
            h1.add(charArray1[i]);
        }
        for(int i = 0; i < charArray2.length; i++)
        {
            h2.add(charArray2[i]);
        }
        h1.retainAll(h2);
        
        return h1.toArray(new Character[0]);
    }
}
