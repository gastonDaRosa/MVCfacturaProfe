/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ort.da.mvc.facturas.servicios;

import java.util.ArrayList;

import ort.da.mvc.facturas.modelo.Cliente;
import ort.da.mvc.facturas.modelo.Factura;
import ort.da.mvc.facturas.modelo.Producto;

/**
 *
 * @author magda
 */
public class SistemaFacturas {
    
    private static SistemaFacturas instancia; 

    private ArrayList<Factura> facturas = new ArrayList();
    private int proximoNumero;
    
    public static SistemaFacturas getInstancia(){
    
           if ( instancia == null){
                instancia = new SistemaFacturas();
           }
          return instancia;
    }
   
    private SistemaFacturas(){
    
    }
    
    public ArrayList<Factura> getFacturas() {
        return facturas;
    }
    public Factura nuevaFactura(String cedula){
        Cliente c = SistemaClientes.getInstancia().buscarCliente(cedula);
        if(c!=null){
            return new Factura(c);
        }
        return null;
    }
    
    public boolean agregar(Factura unaFactura){
        if(unaFactura.validar()){
            unaFactura.setNumero(generarProximoNumero());
            facturas.add(unaFactura);
            unaFactura.bajarStock();
            return true;
        }
        return false;
    }
    
    public boolean clienteComproProducto(Cliente c, Producto p){
        boolean ret = false;
        for(Factura f: facturas){
            if (f.getCliente().equals(c) && f.tieneProducto(p)){
                ret = true;
            }
        }
      
        return ret;
    }
    public Factura ultimaCompra(Cliente c, Producto p){
        Factura ultima=null;
        for(int x=facturas.size()-1; x>=0;x--){
            ultima = facturas.get(x);
            if(ultima.getCliente()==c && ultima.tieneProducto(p)){
                return ultima;
            }
        }
        return null;
    }

    private int generarProximoNumero(){
        proximoNumero++;
        return proximoNumero;
    }
    public int totalFacturado(){
        int total = 0;
        for(Factura f:facturas){
            total+=f.total();
        }
        return total;
    }
}
