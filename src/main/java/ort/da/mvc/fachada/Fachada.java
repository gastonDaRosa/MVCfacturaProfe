package ort.da.mvc.fachada;

import java.util.ArrayList;

import ort.da.mvc.facturas.modelo.Cliente;
import ort.da.mvc.facturas.modelo.Factura;
import ort.da.mvc.facturas.modelo.Producto;
import ort.da.mvc.facturas.modelo.Proveedor;
import ort.da.mvc.facturas.servicios.SistemaClientes;
import ort.da.mvc.facturas.servicios.SistemaFacturas;
import ort.da.mvc.facturas.servicios.SistemaStock;

public class Fachada {

    private static Fachada instancia;


    public static Fachada getInstancia(){
        
        if (instancia==null){
            instancia =  new Fachada();
        }
        return instancia;
    }

    private Fachada(){
    }

    //sistema clientes
    public ArrayList<Cliente> getClientes() {
        return SistemaClientes.getInstancia().getClientes();
    }
    public ArrayList clientesNoCompraronProductoMenorPrecio(){
          return SistemaClientes.getInstancia().clientesNoCompraronProductoMenorPrecio();
    }
    public ArrayList clientesCompraronProductoMenorPrecio(){
       return SistemaClientes.getInstancia().clientesCompraronProductoMenorPrecio();
    }

    public boolean existeCliente(String unaCedula) {
           return SistemaClientes.getInstancia().existeCliente(unaCedula);
    }
    public Cliente buscarCliente(String unaCedula) {
            return SistemaClientes.getInstancia().buscarCliente(unaCedula);
    }
     
        
    public boolean agregar(Cliente c){
        return SistemaClientes.getInstancia().agregar(c);
    }

    
    //sistema facturas
    public ArrayList<Factura> getFacturas() {
        return SistemaFacturas.getInstancia().getFacturas();
    }
    public Factura nuevaFactura(String cedula){
        return SistemaFacturas.getInstancia().nuevaFactura(cedula);
    }
    
    public boolean agregar(Factura unaFactura){
        return SistemaFacturas.getInstancia().agregar(unaFactura);
    }
    
    public boolean clienteComproProducto(Cliente c, Producto p){
        return SistemaFacturas.getInstancia().clienteComproProducto(c, p);
    }
    public Factura ultimaCompra(Cliente c, Producto p){
        return SistemaFacturas.getInstancia().ultimaCompra(c, p);
    }
    public int totalFacturado(){
        return SistemaFacturas.getInstancia().totalFacturado();
    }

    

    //sistema stock
    public ArrayList<Producto> getProductos() {
        return SistemaStock.getInstancia().getProductos();
    }

    public ArrayList<Proveedor> getProveedores() {
        return SistemaStock.getInstancia().getProveedores();
    }

    
    public Producto getProductoMenorPrecio(){
        return SistemaStock.getInstancia().getProductoMenorPrecio();  
    }
        
    public void agregar(Proveedor unProveedor){
        SistemaStock.getInstancia().agregar(unProveedor);
    }
    
    public  boolean altaProducto(Producto unProducto){
        return SistemaStock.getInstancia().altaProducto(unProducto);
    }
    public Producto buscarProducto(String nombre){
        return SistemaStock.getInstancia().buscarProducto(nombre);
    }
    public Producto buscar(int codigo){
        return SistemaStock.getInstancia().buscar(codigo);
    }

    
    
}
