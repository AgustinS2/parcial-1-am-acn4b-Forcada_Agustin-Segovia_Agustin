package Modelo;

import android.content.Intent;
import android.widget.TextView;

import com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin.HomeActivity;
import com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin.MainActivity;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String name;
    private String surname;
    private String email;
    private String password;
    private int age;
    private Account account;

    public User(String name, String surname, String email, String password, int age) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.age = age;
        this.account = new Account(12312343, "asdas.bank");
    }
    public User(String name, String surname, String email, String password, int age, Account account) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.age = age;
        this.account = account;
    }

    // Métodos getters y setters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword(){
        return password;
    }


   public Account getAccount() { return account;}


}
