/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ort.da.mvc.facturas.dto;

import java.util.ArrayList;
import java.util.List;
import ort.da.mvc.facturas.modelo.Cliente;
import ort.da.mvc.facturas.modelo.Producto;

/**
 *
 * @author PC
 */
public class ProductoDto {
    
    private String nombre;
    private String precio;
    private String unidades;
    private String proveedor;
    private String codigo;


    public ProductoDto(Producto producto) {
       nombre = producto.getNombre();
       precio = producto.getPrecio() + "";
       unidades = producto.getUnidades() + "";
       proveedor = producto.getProveedor().getNombre();
       codigo = producto.getCodigo() + "";
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public String getUnidades() {
        return unidades;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getCodigo() {
        return codigo;
    }

   
    
    public static List<ProductoDto> listaDtos(List<Producto> lista){
        List<ProductoDto> dtos = new ArrayList<>();
        for(Producto p: lista){
           dtos.add(new ProductoDto(p));
        }
        return dtos;
    }
    
}
