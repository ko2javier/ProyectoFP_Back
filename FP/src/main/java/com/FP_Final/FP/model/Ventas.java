package com.FP_Final.FP.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ventas")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_increment
    private int id;

    @Column(nullable = false)
    private LocalDate fecha; // Fecha de la venta

    @Column(nullable = false)
    private LocalTime hora; // Hora de la venta

    @Column(name = "id_username", nullable = false)
    private String username; // Este es el campo relacionado con el username de Users

    @Column(nullable = false, length = 20)
    private String dnicliente; // DNI del cliente

    @Column(nullable = false, length = 100)
    private String nameproducto; // Nombre del producto vendido

    @Column(nullable = false)
    private int cantidad; // Cantidad de productos vendidos

    @Column(nullable = false)
    private double importe; // Importe total de la venta

    @ManyToOne // Relación opcional con ComprasCliente
    @JoinColumn(name = "id_compras_cliente", referencedColumnName = "id", nullable = true)
    private ComprasCliente compra;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getUsername() {
        return username; // Getter para username como String
    }

    public void setUsername(String username) {
        this.username = username; // Setter para username como String
    }

    public String getDnicliente() {
        return dnicliente;
    }

    public void setDnicliente(String dnicliente) {
        this.dnicliente = dnicliente;
    }

    public String getNameproducto() {
        return nameproducto;
    }

    public void setNameproducto(String nameproducto) {
        this.nameproducto = nameproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public ComprasCliente getCompra() {
        return compra;
    }

    public void setCompra(ComprasCliente compra) {
        this.compra = compra;
    }
}
