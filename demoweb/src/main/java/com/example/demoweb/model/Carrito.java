package com.example.demoweb.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private List<CarritoItem> items = new ArrayList<>();

    public List<CarritoItem> getItems() {
        return items;
    }

    public void agregar(Producto p) {
        for (CarritoItem item : items) {
            if (item.getProducto().getIdProducto().equals(p.getIdProducto())) {
                item.setCantidad(item.getCantidad() + 1);
                return;
            }
        }
        items.add(new CarritoItem(p, 1));
    }

    public void eliminar(Long idProducto) {
        items.removeIf(item -> item.getProducto().getIdProducto().equals(idProducto));
    }

    public void actualizarCantidad(Long idProducto, int cantidad) {
        for (CarritoItem item : items) {
            if (item.getProducto().getIdProducto().equals(idProducto)) {
                item.setCantidad(cantidad);
            }
        }
    }

    public BigDecimal getSubtotal() {
        return items.stream()
                .map(CarritoItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getIgv() {
        return getSubtotal().multiply(BigDecimal.valueOf(0.18));
    }

    public BigDecimal getTotal() {
        return getSubtotal().add(getIgv());
    }
}
