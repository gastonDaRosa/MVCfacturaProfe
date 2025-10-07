/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ort.da.mvc.facturas.dto;

/**
 *
 * @author PC
 */
public class LineaDto {
    
    private int cantidad;
    private String producto;
    private int total;

    public LineaDto(int cantidad, String producto, int total) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.total = total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getProducto() {
        return producto;
    }

    public int getTotal() {
        return total;
    }
    
    
    
}
