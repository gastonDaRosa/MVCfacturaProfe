/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ort.da.mvc.facturas.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ort.da.mvc.facturas.modelo.Factura;
import ort.da.mvc.facturas.modelo.LineaFactura;

/**
 *
 * @author PC
 */
public class FacturaDto {
    private int numero;
    private int total;
    private String cliente;
    private String fecha;
    private String cedula;
           
    private List<LineaDto> lineas = new ArrayList();

    public int getTotal() {
        return total;
    }

    public String getCliente() {
        return cliente;
    }

    public List<LineaDto> getLineas() {
        return lineas;
    }

    public int getNumero() {
        return numero;
    }

    public String getFecha() {
        return fecha;
    }

    public String getCedula() {
        return cedula;
    }
    

    
    public FacturaDto(Factura factura){
        if(factura!=null){
            numero = factura.getNumero();
            total = factura.total();
            cliente = factura.getCliente().getNombre();
            cedula = factura.getCliente().getCedula();
            if(factura.getFecha()!=null){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                fecha = sdf.format(factura.getFecha());
            }else fecha = "";
            
            for(LineaFactura linea : factura.getLineas() ){
                lineas.add(new LineaDto(linea.getCantidad(),
                                        linea.getProducto().getNombre(),
                                                linea.total()
                ));                    
            }
        }
    }
    
}
