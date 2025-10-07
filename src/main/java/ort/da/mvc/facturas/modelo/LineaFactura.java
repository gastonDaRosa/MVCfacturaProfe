/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ort.da.mvc.facturas.modelo;

/**
 *
 * @author magda
 */
public class LineaFactura {
    
    private Producto producto;
    private int cantidad;

    public LineaFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
     
    @Override
    public String toString() {
        return "LineaFactura{" + "producto=" + producto + ", cantidad=" + cantidad + '}';
    }

    protected boolean incrementar(int cuanto) {
        if(cuanto <1 ) return false;
        cantidad+=cuanto;
        if(validar()) return true;
        //No hay stock suficiente
        cantidad-=cuanto;
        return false;
    }

    public int total() {
        return cantidad * producto.getPrecio();
    }

    public void bajarStock() {
        producto.modificarStock(-cantidad);
    }
    public boolean validar(){
        return cantidad>0 && cantidad<=producto.getUnidades();
    }
    
}
