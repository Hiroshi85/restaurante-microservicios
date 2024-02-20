package com.elbh.plataforma.Controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elbh.plataforma.Models.DetallePedido;
import com.elbh.plataforma.Models.Mesa;
import com.elbh.plataforma.Models.Pedido;
import com.elbh.plataforma.Models.PedidoConDetalle;
import com.elbh.plataforma.Models.PlatoRequestDTO;
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
    public String postPedido(@RequestParam("mesa") Integer idMesa, @RequestParam("cantidad") List<Integer> cantidades, @RequestParam List<Integer> carrito) {
        PedidoConDetalle pd = new PedidoConDetalle();
        pd.setIdMesa(idMesa);
        ArrayList<DetallePedido> detalles = new ArrayList<>();
        for (int i = 0; i < carrito.size(); i++) {
            DetallePedido dp = new DetallePedido();
            dp.setCantidad(cantidades.get(i));
            dp.setIdPlato(carrito.get(i));
            System.out.println(dp.toString());
            detalles.add(dp);
        }

        pd.setDetalles(detalles);
        PedidoConDetalle nuevoPd = vwService.createPedidoConDetalle(pd);
        return "redirect:/pedidos/"+nuevoPd.getId();
    }

    @GetMapping("/pedidos/{id}")
    public String showVenta(@PathVariable Integer id, Model model) {
        PedidoConDetalle pd = vwService.getPedidoConDetalle(id);
        Double suma = 0d;

        for (DetallePedido detalle : pd.getDetalles()) {
           suma+= (detalle.getPrecio()*detalle.getCantidad());
        }
        model.addAttribute("pedido", pd);
        model.addAttribute("total", suma);
        return "Pedidos/show";
    }

    @GetMapping("/pedidos")
    public String listarPedidos(Model model) {
        List<Pedido> pedidos = vwService.listarPedidos();

        model.addAttribute("pedidos", pedidos);
        return "Pedidos/index";
    }

    @GetMapping("/platos")
    public String createPlatos(Model model) {
        val categorias = vwService.getCategorias();
        model.addAttribute("categorias", categorias);
        return "platos/index";
    }

    @GetMapping("/mesas")
    public String createMesas(Model model) {
        return "mesas/index";
    }

    @PostMapping("/platos")
    public String postPlato(@RequestParam Map<String, String> body) {

        PlatoRequestDTO dto = new PlatoRequestDTO(
            body.get("descripcion"), 
            Double.parseDouble(body.get("precioUnitario")), 
            Integer.parseInt(body.get("categoria"))
        );
        vwService.createPlato(dto);
        
        return "redirect:/";
    }

    @PostMapping("/mesas")
    public String postMesa(@RequestParam Map<String, String> body) {
        Mesa dto = new Mesa();
        dto.setCodigo(Integer.parseInt(body.get("codigo")));
        vwService.createMesa(dto);

        return "redirect:/";
    }
    
    
    
}
