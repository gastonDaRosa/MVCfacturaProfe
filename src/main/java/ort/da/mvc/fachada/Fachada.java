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

    private static SistemaClientes sc;
    private static SistemaFacturas sf;
    private static SistemaStock ss;

    public static Fachada getInstancia(){
        
        if (instancia==null){
            instancia =  new Fachada();
        }
        return instancia;
    }

    private Fachada(){
        sc = SistemaClientes.getInstancia();
        sf = SistemaFacturas.getInstancia();
        ss = SistemaStock.getInstancia();
    }

    //sistema clientes
    public ArrayList<Cliente> getClientes() {
        return sc.getClientes();
    }
    public ArrayList clientesNoCompraronProductoMenorPrecio(){
          return sc.clientesNoCompraronProductoMenorPrecio();
    }
    public ArrayList clientesCompraronProductoMenorPrecio(){
       return sc.clientesCompraronProductoMenorPrecio();
    }

    public boolean existeCliente(String unaCedula) {
           return sc.existeCliente(unaCedula);
    }
    public Cliente buscarCliente(String unaCedula) {
            return sc.buscarCliente(unaCedula);
    }
     
        
    public boolean agregar(Cliente c){
        return sc.agregar(c);
    }

    
    //sistema facturas
    public ArrayList<Factura> getFacturas() {
        return sf.getFacturas();
    }
    public Factura nuevaFactura(String cedula){
        return sf.nuevaFactura(cedula);
    }
    
    public boolean agregar(Factura unaFactura){
        return sf.agregar(unaFactura);
    }
    
    public boolean clienteComproProducto(Cliente c, Producto p){
        return sf.clienteComproProducto(c, p);
    }
    public Factura ultimaCompra(Cliente c, Producto p){
        return sf.ultimaCompra(c, p);
    }
    public int totalFacturado(){
        return sf.totalFacturado();
    }

    

    //sistema stock
    public ArrayList<Producto> getProductos() {
        return ss.getProductos();
    }

    public ArrayList<Proveedor> getProveedores() {
        return ss.getProveedores();
    }

    
    public Producto getProductoMenorPrecio(){
        return ss.getProductoMenorPrecio();  
    }
        
    public void agregar(Proveedor unProveedor){
        ss.agregar(unProveedor);
    }
    
    public  boolean altaProducto(Producto unProducto){
        return ss.altaProducto(unProducto);
    }
    public Producto buscarProducto(String nombre){
        return ss.buscarProducto(nombre);
    }
    public Producto buscar(int codigo){
        return ss.buscar(codigo);
    }

    
    
}
