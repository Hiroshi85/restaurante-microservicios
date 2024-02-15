package com.elbh.plataforma.Controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elbh.plataforma.Models.DetalleCompletoDTO;
import com.elbh.plataforma.Models.ProductoPostDTO;
import com.elbh.plataforma.Models.VPRequest;
import com.elbh.plataforma.Models.Venta;
import com.elbh.plataforma.Models.VentaProducto;
import com.elbh.plataforma.Service.VentaWebService;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class PlataformaController {
    private final VentaWebService vwService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("platos", vwService.getPlatosIndex());
        model.addAttribute("mesas", vwService.getMesas());
        return "index";
    }

    @PostMapping("/comprar")
    public String postMethodName(@RequestParam("cantidad") List<Integer> cantidades, @RequestParam List<Integer> carrito, @RequestParam String comprador) {
        Venta ventaRealizada = vwService.createVenta(new Venta(comprador));
        ArrayList<VentaProducto> detallesVenta = new ArrayList<>();
        for (int i = 0; i < carrito.size(); i++) {
            VPRequest vpRequest = new VPRequest(carrito.get(i), cantidades.get(i));
            VentaProducto detalleVenta = vwService.createVentaProducto(ventaRealizada.getId(), vpRequest);
            System.out.println(detalleVenta.toString());
            detallesVenta.add(detalleVenta);
        }
        return "redirect:/ventas/"+ventaRealizada.getId();
    }

    @GetMapping("/ventas/{id}")
    public String showVenta(@PathVariable Integer id, Model model) {
        Venta ventaRealizada = vwService.getVenta(id);
        List<DetalleCompletoDTO> detallesVenta = vwService.getDetalles(id);
        if(detallesVenta.isEmpty()){
            return "redirect:/";
        }
        Double suma = 0d;
        for (DetalleCompletoDTO detalle : detallesVenta) {
           suma+= (detalle.getPrecioUnitario()*detalle.getCantidad());
        }
        model.addAttribute("venta", ventaRealizada);
        model.addAttribute("detalles", detallesVenta);
        model.addAttribute("total", suma);
        return "Ventas/show";
    }

    @GetMapping("/productos")
    public String createProductos(Model model) {
        val categorias = vwService.getCategorias();
        model.addAttribute("productos", vwService.getProductosIndex());
        model.addAttribute("categorias", categorias);
        return "productos/index";
    }

    @PostMapping("/productos")
    public String postProducto(@RequestParam Map<String, String> body) {
        ProductoPostDTO dto = new ProductoPostDTO(
            body.get("descripcion"), 
            Double.parseDouble(body.get("precioUnitario")), 
            Integer.parseInt(body.get("categoria"))
        );
        vwService.createProducto(dto);
        
        return "redirect:/";
    }
    
    
}
