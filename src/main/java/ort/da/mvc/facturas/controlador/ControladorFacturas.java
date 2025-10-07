/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/RestController.java to edit this template
 */
package ort.da.mvc.facturas.controlador;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ort.da.mvc.facturas.dto.FacturaDto;
import ort.da.mvc.facturas.modelo.Factura;
import ort.da.mvc.facturas.modelo.Respuesta;
import ort.da.mvc.facturas.servicios.SistemaFacturas;

/**
 *
 * @author PC
 */
@RestController
@RequestMapping("/ingresarFactura")
public class ControladorFacturas {
    
    private Factura factura = null;
    
  
    @PostMapping("/vistaConectada")
    public List<Respuesta> vistaConectada() {
        return Respuesta.lista(factura(),totalFacturado());
    }
    @PostMapping("/iniciarFactura")
    public List<Respuesta> iniciarFactura(@RequestParam Integer cedula) {
        if(factura==null){
            factura = SistemaFacturas.getInstancia().nuevaFactura(cedula.toString());
            if(factura!=null){
                return Respuesta.lista(factura());
            }return Respuesta.lista(mensaje("No se pudo iniciar la factura, verifique la cedula"));
        }return Respuesta.lista(mensaje("Debe finalizar la factura actual antes de inciar otra"));
    }
    
    @PostMapping("/agregarProducto")
    public List<Respuesta> agregarProducto(@RequestParam Integer codigoProducto, @RequestParam Integer cantidad) {
        if(factura!=null){
            if(factura.agregarPorCodigo(cantidad, codigoProducto)){
                return Respuesta.lista(factura());
            }return Respuesta.lista(mensaje("No se pudo agregar el producto"));
        }return Respuesta.lista(mensaje("Debe iniciar la factura antes de agregar productos"));
    }
    
    @PostMapping("/confirmarFactura")
    public List<Respuesta> confirmarFactura(){
        if(factura!=null){
            if(SistemaFacturas.getInstancia().agregar(factura)){
                int nro = factura.getNumero();
                factura = null;  
                return Respuesta.lista(factura(),
                              totalFacturado(),
                              mensaje("Se guardo correctamente con numero: " + nro));
            }
            return Respuesta.lista(mensaje("No se pudo confirmar la factura, verifique si hay productos"));
        }
        return Respuesta.lista(mensaje("Debe iniciar la factura antes de confirmarla"));
    }
   @PostMapping("/descartarFactura")
    public List<Respuesta> descartarFactura() {
        if(factura!=null){
            factura = null;
            return Respuesta.lista(factura());
        }
        return Respuesta.lista(mensaje("No se ha iniciado la factura"));
    }
   
   private Respuesta factura(){
       return new Respuesta("factura",new FacturaDto(factura));
   }
    private Respuesta totalFacturado(){
       return new Respuesta("totalFacturado",SistemaFacturas.getInstancia().totalFacturado());
   }
   private Respuesta mensaje(String texto) {
        return new Respuesta("mensaje",texto);
   }
   
    
}
