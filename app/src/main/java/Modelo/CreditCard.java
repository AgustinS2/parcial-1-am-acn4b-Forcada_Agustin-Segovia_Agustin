package Modelo;

import java.io.Serializable;
import java.util.Date;

public class CreditCard implements Serializable {
    private String marca;
    private String estado;
    private double saldo_actual;
    private int numero_cuenta;
    private int numero_tarjeta;
    private Date fecha_vencimiento;

    public CreditCard(String marca, String estado, double saldo_actual, int numero_cuenta, int numero_tarjeta) {
        this.marca = marca;
        this.estado = estado;
        this.saldo_actual = saldo_actual;
        this.numero_cuenta = numero_cuenta;
        this.numero_tarjeta = numero_tarjeta;
    }

    public String getMarca (){return marca;}
    public String getEstado (){return estado;}
    public double getSaldo_actual() {return saldo_actual;}
    public int getNumero_cuenta() { return numero_cuenta;}
    public int getNumero_tarjeta() {return numero_tarjeta;}
    public void setSaldo_actual(double saldo) {saldo_actual = saldo;}

}
