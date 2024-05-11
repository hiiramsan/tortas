/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adminreporte;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author carlo
 */
public class ControlReportes {

    public void generarReporteOrdenDeCompra(List<NuevoProductoDTO> listaInventario, String descripcion, String proveedor) {
        Document doc = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("OrdenDeCompra.pdf"));
            writer.setPageEvent(new PageNumberEvent());

            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph titulo = new Paragraph("Orden de Compra", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);

            Date fechaActual = new Date();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            Paragraph fecha = new Paragraph("Fecha de generación: " + formatoFecha.format(fechaActual));
            fecha.setAlignment(Element.ALIGN_RIGHT);

            // Agregar descripción y proveedor
            Paragraph infoAdicional = new Paragraph("Descripción: " + descripcion + "\n" + "Proveedor: " + proveedor);
            infoAdicional.setAlignment(Element.ALIGN_LEFT);

            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell(createCell("Producto", true));
            tabla.addCell(createCell("Precio de Venta", true));
            tabla.addCell(createCell("Cantidad", true));

            for (NuevoProductoDTO producto : listaInventario) {
                tabla.addCell(createCell(String.valueOf(producto.getNombre()), false));
                tabla.addCell(createCell(String.valueOf(producto.getPrecio()), false));
                tabla.addCell(createCell(String.valueOf(producto.getCantidad()), false));
            }

            doc.open();
            doc.add(titulo);
            doc.add(Chunk.NEWLINE);
            doc.add(fecha);
            doc.add(Chunk.NEWLINE);
            doc.add(infoAdicional);
            doc.add(Chunk.NEWLINE);
            doc.add(tabla);
            doc.close();

            JOptionPane.showMessageDialog(null, "Orden de Compra generado: OrdenDeCompra.PDF", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al generar el reporte de orden de compra.");
        }
    }

    /**
     * Método privado para crear una celda en una tabla PDF.
     *
     * @param content Contenido de la celda.
     * @param header Indicador de si la celda pertenece a la cabecera de la
     * tabla.
     * @return Celda creada con el contenido especificado.
     */
    private PdfPCell createCell(String content, boolean header) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        if (header) {
            cell.setBackgroundColor(new BaseColor(122, 194, 225));
        } else {
            cell.setBackgroundColor(new BaseColor(205, 232, 244));
        }

        return cell;
    }

    /**
     * Clase interna para manejar la numeración de páginas en el documento PDF.
     */
    private static class PageNumberEvent extends PdfPageEventHelper {

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            Phrase footer = new Phrase("Página " + writer.getPageNumber(), new Font(Font.FontFamily.HELVETICA, 12));
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer,
                    document.right() - 50,
                    document.bottom() - 10, 0);
        }
    }
}
