package com.potandr1977.mongoPoc.persons;

import an.awesome.pipelinr.repack.com.google.common.base.Splitter;
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
    private UUID id;

    private String name;

    private String inn;

    private Date createDate;

    private List<Account> accounts = new ArrayList<Account>();

    public List<Account> getAccount(){
        return Collections.unmodifiableList(accounts);
    };

    public static Person Create(UUID id, String name, String inn, List<Account> accounts)
    {
        var person = new Person();
        person.setId(id);
        person.setName(name);
        person.setInn(inn);
        if (accounts != null) {
            person.accounts.addAll(accounts);
        }

        return person;
    }

    public void SetName(String newName)
    {
        var digits = "0123456789".toCharArray();
        var newNameChars = newName.toCharArray();
        var digitsSet = Set.of(digits);
        var newNameSet = new HashSet<>(Set.of(newNameChars));
        newNameSet.retainAll(digitsSet);

        if ((long) newNameSet.size() != 0) {
            throw new IllegalArgumentException("В имени должны быть только буквы");
        }

        this.name = newName;
    }

    public void SetInn(String newInn)
    {
        var digits = "0123456789".toCharArray();
        var newInnChars = newInn.toCharArray();
        var digitsSet = Set.of(digits);
        var newInnSet = new HashSet<>(Set.of(newInnChars));
        newInnSet.removeAll(digitsSet);

        if ((long) newInnSet.size() !=0) {
            throw new IllegalArgumentException("В имени должны быть только буквы");
        }

        inn = newInn;
    }
    public void AddAccount(Account account)
    {
        if (accounts.stream().anyMatch(x -> x.getName().equals(account.getName())))
        {
            throw new IllegalArgumentException("Такой счёт уже существует");
        }

        accounts.add(account);
    }
}
