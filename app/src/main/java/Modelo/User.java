package Modelo;

import android.content.Intent;
import android.widget.TextView;

import com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin.HomeActivity;
import com.example.parcial_1_am_acn4b_segovia_agustin_forcada_agustin.MainActivity;
import com.google.firebase.firestore.PropertyName;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    @PropertyName("name")
    private String name;
    @PropertyName("surname")
    private String surname;
    @PropertyName("email")
    private String email;
    @PropertyName("age")
    private int age;
    @PropertyName("alias")
    private String alias;
    @PropertyName("estado")
    private String estado;
    @PropertyName("marca")
    private String marca;
    @PropertyName("numerocuenta")
    private int numerocuenta;
    @PropertyName("numerotarjeta")
    private int numerotarjeta;
    @PropertyName("balance")
    private double balance;
    @PropertyName("cbu")
    private int cbu;

    private Account account;

    public User() {
        // Puedes inicializar atributos si es necesario
    }


    public User(String name, String surname, String email, int age, String alias, String estado, String marca, int numerocuenta, int numerotarjeta, double balance, int cbu) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.alias = alias;
        this.estado = estado;
        this.marca = marca;
        this.numerocuenta = numerocuenta;
        this.numerotarjeta = numerotarjeta;
        this.balance = balance;
        this.cbu = cbu;
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

    public String getAlias() {
        return alias;
    }
    public String getEstado() {
        return estado;
    }
    public String getMarca() {
        return marca;
    }
    public int getNumeroCuenta() {
        return numerocuenta;
    }
    public int getNumeroTarjeta() {
        return numerotarjeta;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double bal){ balance = bal;}

    public int getCbu() {
        return cbu;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }





}
