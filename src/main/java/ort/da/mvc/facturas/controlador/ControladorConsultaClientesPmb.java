/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ort.da.mvc.facturas.controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ort.da.mvc.facturas.dto.ClienteDto;
import ort.da.mvc.facturas.dto.ProductoDto;
import ort.da.mvc.facturas.modelo.Cliente;
import ort.da.mvc.facturas.modelo.Producto;
import ort.da.mvc.facturas.modelo.Respuesta;
import ort.da.mvc.facturas.servicios.SistemaClientes;
import ort.da.mvc.facturas.servicios.SistemaFacturas;
import ort.da.mvc.facturas.servicios.SistemaStock;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/consultaPmb")

public class ControladorConsultaClientesPmb {
    
    @PostMapping("/vistaConectada")
    public List<Respuesta> vistaConectada() {
       Producto pmb = SistemaStock.getInstancia().getProductoMenorPrecio();
       if(pmb==null) return Respuesta.lista(mensaje("No hay productos ingresados"));
       ArrayList<Cliente> clientes = SistemaClientes.getInstancia().clientesCompraronProductoMenorPrecio();
       if(clientes.isEmpty()){
           return Respuesta.lista(producto(pmb),mensaje("No hay clientes que hayan comprado el producto"));
       }
       return Respuesta.lista(producto(pmb),clientes(clientes,pmb));
              
       
    }
    private Respuesta clientes(ArrayList<Cliente> clientes,Producto p) {
        List<ClienteDto> dtos = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for(Cliente c: clientes){
            ClienteDto dto = new ClienteDto(c);
            Date ultimaCompra = SistemaFacturas.getInstancia().ultimaCompra(c, p).getFecha();
            dto.setUltimaCompra(sdf.format(ultimaCompra));
            dtos.add(dto);
        }
         return new Respuesta("clientes",dtos);
     }
    private Respuesta mensaje(String texto) {
        return new Respuesta("mensaje",texto);
        
    }
    private Respuesta producto(Producto p) {
        
        return new Respuesta("producto", new ProductoDto(p));
        
    }
    
    
}
