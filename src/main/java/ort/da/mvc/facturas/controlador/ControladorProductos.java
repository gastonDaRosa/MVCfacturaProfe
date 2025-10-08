
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ort.da.mvc.facturas.controlador;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ort.da.mvc.fachada.Fachada;
import ort.da.mvc.facturas.dto.NombreDto;
import ort.da.mvc.facturas.dto.ProductoDto;
import ort.da.mvc.facturas.modelo.Producto;
import ort.da.mvc.facturas.modelo.Proveedor;
import ort.da.mvc.facturas.modelo.Respuesta;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/productos")

public class ControladorProductos {
    
    private Producto producto = null;
    private ArrayList<Proveedor> proveedores;
    
    @PostMapping("/vistaConectada")
    public List<Respuesta> vistaConectada() {
       return Respuesta.lista(productos(), 
                              proveedores(),
                              new Respuesta("habilitarIngreso",false));
    }
    
    @PostMapping("/ingresarNombre")
    public List<Respuesta> ingresarNombre(@RequestParam String nombre) {
       producto = new Producto();
       if(!producto.setNombre(nombre)){
           producto = null;
           return Respuesta.lista(mensaje("Nombre de producto incorrecto"));
       }
       Producto tmp = Fachada.getInstancia().buscarProducto(nombre);
       if(tmp!=null){
           return Respuesta.lista(mensaje("Ya existe el producto"),
                                  producto(tmp));
       }
       
       return Respuesta.lista(new Respuesta("habilitarIngreso",true));
      
    }
     @PostMapping("/guardarProducto")
    public List<Respuesta> guardarProducto(@RequestParam int proveedor, @RequestParam int precio,@RequestParam int unidades) {
        if(producto == null)  return Respuesta.lista(mensaje("No se ha ingresado un nombre de producto"));
        if(proveedor!=-1){
           Proveedor proveedorSeleccionado = proveedores.get(proveedor); 
           producto.setProveedor(proveedorSeleccionado);
       }
       producto.setPrecio(precio);
       producto.setUnidades(unidades);
       if(Fachada.getInstancia().altaProducto(producto)){
            producto = null;
            return Respuesta.lista(productos(),
                                   new Respuesta("limpiarEntradas",true),
                                   new Respuesta("habilitarIngreso",false));
        }else{
            return Respuesta.lista(mensaje("No se pudo agregar el producto"));
        }
       
    }
    
    //Respuestas

    private Respuesta productos() {
        
        return new Respuesta("productos",
                      ProductoDto.listaDtos(Fachada.getInstancia().getProductos()));
        
    }
    private Respuesta proveedores() {
        ArrayList<NombreDto> lista = new ArrayList();
        proveedores = new ArrayList(Fachada.getInstancia().getProveedores());
        for(Proveedor p: proveedores){
            lista.add(new NombreDto(p.getNombre()));// + " (" + p.getProductos().size() + ")"));
        }
        return new Respuesta("proveedores",
                      lista);
        
    }
    private Respuesta mensaje(String texto) {
        return new Respuesta("mensaje",texto);
        
    }
    private Respuesta producto(Producto p) {
        
        return new Respuesta("producto",
                      new ProductoDto(p));
        
    }
    
    
}