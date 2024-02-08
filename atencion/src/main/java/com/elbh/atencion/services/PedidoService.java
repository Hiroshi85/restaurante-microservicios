package com.elbh.atencion.services;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.hibernate.mapping.Table;
import org.hibernate.query.Page;
import org.springframework.stereotype.Service;

import com.elbh.atencion.Dto.DetallePedido;
import com.elbh.atencion.Dto.PedidoConDetalle;
import com.elbh.atencion.entities.Mesa;
import com.elbh.atencion.entities.Pedido;
import com.elbh.atencion.repositories.MesaRepository;
import com.elbh.atencion.repositories.PedidoRepository;
import com.elbh.atencion.repositories.external.DetallePedidoRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository repo;
    private final MesaRepository mesaRepo;
    private final DetallePedidoRepository detalleRepo;

    public PedidoConDetalle findById(Integer id){
        Pedido pedido = repo.findById(id).orElse(null);
        if(pedido == null){
            return null;
        }
        List<DetallePedido> detalles = detalleRepo.getByIdPedido(id);
        PedidoConDetalle pedidoConDetalle = new PedidoConDetalle(pedido.getCreatedAt());
        pedidoConDetalle.setId(pedido.getId());
        pedidoConDetalle.setIdMesa(pedido.getMesa().getId());
        pedidoConDetalle.setMesa(pedido.getMesa().getCodigo());
        pedidoConDetalle.setDetalles(detalles);
        return pedidoConDetalle;
    }

    public PedidoConDetalle savePedidoConDetalle(PedidoConDetalle pedidoConDetalle){
        Mesa mesa = mesaRepo.findById(pedidoConDetalle.getIdMesa()).orElse(null);
        Pedido pedido = new Pedido(null, mesa, null);
        pedido = repo.save(pedido);

        for(DetallePedido detalle : pedidoConDetalle.getDetalles()){
            detalle.setId(null);
            detalle.setIdPedido(pedido.getId());
        }

        //guardar detalles
        detalleRepo.saveAll(pedidoConDetalle.getDetalles());
        List<DetallePedido> detalles = detalleRepo.getByIdPedido(pedido.getId());
        PedidoConDetalle pcd = new PedidoConDetalle(
            pedido.getId(),
            pedido.getMesa().getId(),
            pedido.getMesa().getCodigo(),
            pedido.getCreatedAt(),
            detalles
        );
        return pcd;

    }

    public Pedido update(Integer id, Pedido pedido){
        pedido.setId(id);
        return repo.save(pedido);
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

    public byte[] getPedidoPDF(Integer id){
        PedidoConDetalle pedidoConDetalle = this.findById(id);
        if(pedidoConDetalle == null){
            return null;
        }
        byte[] pdfBytes;
        // Inicializar un objeto ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        document.setPageSize(PageSize.A5.rotate());
        try {
            PdfWriter.getInstance(document, outputStream);    
            document.open();

            Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("Boleta #"+id, font);
            paragraph.setSpacingAfter(14f);
            Paragraph paragraphMesa = new Paragraph("Mesa: "+pedidoConDetalle.getMesa(), font);
            paragraphMesa.setSpacingAfter(20f);
            

            PdfPTable table = new PdfPTable(new float[]{1,3,1,1});
            this.addTableHeader(table);
            this.addRows(table, pedidoConDetalle);
            table.setSpacingAfter(20f);

            document.add(paragraph);
            document.add(paragraphMesa);
            document.add(table);
            document.add(this.formatEndParagraph());

            document.close();

            pdfBytes = outputStream.toByteArray();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
        return pdfBytes;
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Cant", "Descripcion", "P. Unitario", "Importe")
        .forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setFixedHeight(30);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });
    }

    private void addRows(PdfPTable table, PedidoConDetalle pedidoConDetalle) {
        Double total = 0d;
        for(DetallePedido detalle : pedidoConDetalle.getDetalles()){
            table.addCell(detalle.getCantidad().toString());
            table.addCell(detalle.getPlato());
            table.addCell(detalle.getPrecio().toString());
            Double importe = detalle.getCantidad() * detalle.getPrecio();
            table.addCell(Double.toString(importe));
            total += importe;
        }   

        PdfPCell finalRowColspan = new PdfPCell();
        finalRowColspan.setColspan(2);
        table.addCell(finalRowColspan);
        table.addCell("Total: ");
        table.addCell(total.toString());
    }

    private Paragraph formatEndParagraph() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Paragraph endParagraph = new Paragraph("Impreso en "+ dateFormat.format(date));
        endParagraph.setFont(new Font(FontFamily.COURIER, 6));
        return endParagraph;
    }
}
