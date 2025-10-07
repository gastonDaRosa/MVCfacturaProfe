/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ort.da.mvc.facturas.dto;

import java.util.ArrayList;
import java.util.List;
import ort.da.mvc.facturas.modelo.Cliente;

/**
 *
 * @author PC
 */
public class NombreDto {
    
    private String nombre;
  
    public NombreDto(String nombre) {
       this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    
    
}
