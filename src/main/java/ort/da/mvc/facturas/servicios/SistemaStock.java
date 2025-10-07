
package ort.da.mvc.facturas.servicios;

import java.util.ArrayList;

import ort.da.mvc.facturas.modelo.Producto;
import ort.da.mvc.facturas.modelo.Proveedor;


public class SistemaStock {

    private static SistemaStock instancia;
    
    private ArrayList<Producto> productos = new ArrayList();
    private ArrayList<Proveedor> proveedores = new ArrayList();
    private int proximoCodigoproducto = 0;

    
    public static SistemaStock getInstancia(){
    
           if ( instancia == null){
                instancia = new SistemaStock();
           }
          return instancia;
    }
    
    private SistemaStock() {
    }
  
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Proveedor> getProveedores() {
        return proveedores;
    }

    
    public Producto getProductoMenorPrecio(){
        if(productos.isEmpty()) return null;
        Producto menor = productos.get(0);
        Producto p;
        for(int x=1;x<productos.size();x++){
            p = productos.get(x);
            if (p.getPrecio()<menor.getPrecio()){
                menor = p;
            }
        }            
        
        return menor;
        
      
    }
        
    public void agregar(Proveedor unProveedor){
        proveedores.add(unProveedor);
    }
    
    public  boolean altaProducto(Producto unProducto){
        if(unProducto.validar() && 
           !productos.contains(unProducto) &&
           unProducto.getProveedor().agregar(unProducto) ){
            unProducto.setCodigo(generarCodigoProducto());
            productos.add(unProducto);
            
            return true;
        }
        return false;
    }
    public Producto buscarProducto(String nombre){
        for(Producto p:productos){
            if(p.getNombre().equalsIgnoreCase(nombre)) return p;
        }
        return null;
    }
    private int generarCodigoProducto(){
        proximoCodigoproducto++;
        return proximoCodigoproducto;
    }
    public Producto buscar(int codigo){
        for(Producto p:productos){
            if(p.getCodigo()==codigo){
                return p;
            }
        }
        return null;
    }
}
