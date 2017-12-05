package com.example.crm.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "contact")
public class Contact extends People{

    @Id
    @GeneratedValue
    private int id;
    private String hobby;
    private Calendar birthday;

    Contact(){

    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormatBirthday(int type) {
        switch (type) {
            case 0:
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                return format.format(birthday.getTime());
            case 1:
                return String.valueOf(birthday.get(Calendar.YEAR));
            case 2:
                return String.valueOf(birthday.get(Calendar.MONTH) + 1);
            case 3:
                return String.valueOf(birthday.get(Calendar.DAY_OF_MONTH));
            default:
                return "";
        }

    }
}
