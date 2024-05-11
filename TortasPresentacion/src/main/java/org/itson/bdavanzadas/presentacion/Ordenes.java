/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import org.bson.Document;
import org.itson.bdavanzadas.adminOrden.FacadeAdminOrden;
import org.itson.bdavanzadas.adminOrden.IAdminOrden;

/**
 *
 * @author crist
 */
public class Ordenes  extends JFrame {

    private JPanel panelOrdenes;
    IAdminOrden adminOrden;
    
    Insets insets = new Insets(10, 10, 10, 10);

    public Ordenes() {
        this.adminOrden = new FacadeAdminOrden();
        initComponents();
        //hola
    }

    private void initComponents() {
        setTitle("Órdenes Pendientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout());

        panelOrdenes = new JPanel();
        panelOrdenes.setLayout(new GridBagLayout());
        Color color = new Color(164, 180, 148);
        panelOrdenes.setBackground(color);

        JScrollPane scrollPane = new JScrollPane(panelOrdenes);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        contentPane.add(scrollPane, BorderLayout.CENTER);
        setContentPane(contentPane);

        // Botón "Actualizar"
        JButton btnActualizar = new JButton("Actualizar");
        Color azul = new Color(36, 123, 160); 
        btnActualizar.setBackground(azul);
        btnActualizar.setForeground(Color.WHITE);

        btnActualizar.addActionListener(e -> {
            // Limpiar los paneles existentes
            panelOrdenes.removeAll();
            panelOrdenes.revalidate();
            panelOrdenes.repaint();

            // Obtener y mostrar las nuevas órdenes
            List<Document> ordenesPendientesActualizadas = adminOrden.obtenerOrdenesPendientes();
            for (Document orden : ordenesPendientesActualizadas) {
                agregarOrden(orden, insets);
            }
        });

        // Botón "Ordenar por prioridad"
        JButton btnOrdenarPorAsc = new JButton("Ordenar por fecha ascendente");

        btnOrdenarPorAsc.setBackground(azul);
        btnOrdenarPorAsc.setForeground(Color.WHITE);

        btnOrdenarPorAsc.addActionListener(e -> {
            // Limpiar los paneles existentes
            panelOrdenes.removeAll();
            panelOrdenes.revalidate();
            panelOrdenes.repaint();

            // Obtener y mostrar las nuevas órdenes
            List<Document> ordenesPendientesASC = adminOrden.obtenerOrdenesPorFechaAscendente();
            for (Document orden : ordenesPendientesASC) {
                agregarOrden(orden, insets);
            }
        });

        // Botón "Ordenar por cantidad de tortas"
        JButton btnOrdenarPorCantTortas = new JButton("Ordenar por cantidad de tortas");
        btnOrdenarPorCantTortas.setBackground(azul);
        btnOrdenarPorCantTortas.setForeground(Color.WHITE);

        btnOrdenarPorCantTortas.addActionListener(e -> {
            // Limpiar los paneles existentes
            panelOrdenes.removeAll();
            panelOrdenes.revalidate();
            panelOrdenes.repaint();

            // Obtener y mostrar las nuevas órdenes
            List<Document> ordenesPendientesMayorTortas = adminOrden.obtenerOrdenesPendientesPorCantidadTortas();
            for (Document orden : ordenesPendientesMayorTortas) {
                agregarOrden(orden, insets);
            }
        });

        // Crear un panel para contener el botón y establecer su layout
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.setBackground(Color.WHITE);
        JLabel lblEspacio = new JLabel("                                               "
                + "                                                                    "
                + "                               ");
        btnOrdenarPorAsc.setPreferredSize(new Dimension(270, 39));
        btnOrdenarPorCantTortas.setPreferredSize(new Dimension(270, 39));
        btnActualizar.setPreferredSize(new Dimension(270, 39));

        btnPanel.add(btnActualizar);
        btnPanel.add(lblEspacio);
        btnPanel.add(btnOrdenarPorAsc);
        btnPanel.add(btnOrdenarPorCantTortas);

        // Agregar el panel con el botón al norte del JFrame
        contentPane.add(btnPanel, BorderLayout.NORTH);

        pack(); // Ajusta el tamaño de la ventana para que quepa el contenido

        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        List<Document> ordenesPendientes = adminOrden.obtenerOrdenesPendientes();
        for (Document orden : ordenesPendientes) {
            agregarOrden(orden, insets);
        }

    }

    public void agregarOrden(Document orden, Insets insets) {
        JPanel panelOrden = new JPanel(); // 15 es el radio del borde

        panelOrden.setLayout(new BoxLayout(panelOrden, BoxLayout.Y_AXIS));
        Font font = new Font("Segoe UI Semibold", Font.BOLD, 18);

        JLabel lblNumeroOrden = new JLabel("Orden #" + orden.getInteger("numeroOrden"));
        lblNumeroOrden.setFont(font);
        panelOrden.add(lblNumeroOrden);
        panelOrden.add(Box.createVerticalStrut(5)); // Espacio vertical

        JLabel lblNombreCliente = new JLabel("Cliente: " + orden.getString("nombreCliente"));
        panelOrden.add(lblNombreCliente);

        JLabel lblEstado = new JLabel("Estado: " + orden.getString("estado"));
        panelOrden.add(lblEstado);

        // Obtener la fecha como un objeto Date
        Date fechaOrden = orden.getDate("fecha");

        // Formatear la fecha en el formato deseado, incluyendo AM/PM
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy hh:mm a", new Locale("es", "ES"));
        String fechaFormateada = sdf.format(fechaOrden);

        JLabel lblFecha = new JLabel("Fecha: " + fechaFormateada + "       ");
        panelOrden.add(lblFecha);

        JLabel lblTotal = new JLabel("Total: " + orden.getDouble("total"));
        panelOrden.add(lblTotal);
        panelOrden.add(Box.createVerticalStrut(10)); // Espacio vertical

        JLabel lblProductos = new JLabel("Productos:");
        panelOrden.add(lblProductos);

        List<Document> listaProductos = (List<Document>) orden.get("listaProductos");
        for (int i = 0; i < listaProductos.size(); i++) {
            Document producto = listaProductos.get(i);
            JLabel lblProducto = new JLabel("\t- " + producto.getString("nombre")
                    + ", Cantidad: " + producto.getInteger("cantidad"));
            lblProducto.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Borde inferior
            panelOrden.add(lblProducto);

            List<Document> ingredientes = (List<Document>) producto.get("ingredientes");

            if (producto.getString("categoria").equals("Torta")) {
                JLabel lblIngredientes = new JLabel("\t\tIngredientes:");
                panelOrden.add(lblIngredientes);

                for (Document ingrediente : ingredientes) {
                    Font fontIngrediente = new Font("Segoe UI", Font.PLAIN, 12);

                    // Remover el prefijo "cant" si existe
                    if (ingrediente.getInteger("cantidad") != 1) {
                        String nombreIngrediente = ingrediente.getString("nombreIngrediente");
                        // Remover el prefijo "cant" si existe
                        if (nombreIngrediente.startsWith("cant")) {
                            nombreIngrediente = nombreIngrediente.substring(4);
                            if (ingrediente.getInteger("cantidad") == 0) {
                                nombreIngrediente = "Sin " + nombreIngrediente;
                            }
                        }
                        if (nombreIngrediente.startsWith("can")) {
                            nombreIngrediente = nombreIngrediente.substring(3);
                            if (ingrediente.getInteger("cantidad") == 0) {
                                nombreIngrediente = "sin " + nombreIngrediente;
                            }
                        }

                        JLabel lblIngrediente = new JLabel("\t\t\t    " + nombreIngrediente + " x" + ingrediente.getInteger("cantidad"));
                        lblIngrediente.setFont(fontIngrediente);
                        panelOrden.add(lblIngrediente);
                    }
                }

                // Mostrar la descripción solo si es una torta y la descripción no está vacía
                if (producto.getString("descripcion") != null && !producto.getString("descripcion").isEmpty()) {
                    JLabel lblDescripcion = new JLabel("Notas: " + producto.getString("descripcion"));
                    panelOrden.add(lblDescripcion);
                }
            }

        }
        panelOrden.add(Box.createVerticalStrut(10)); // Espacio vertical
        final JPanel[] panelOrdenRef = {panelOrden};

        JButton btnCancelar = new JButton("Cancelar");
        Color rojo = new Color(225, 121, 117);
        btnCancelar.setBackground(rojo);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.addActionListener(e -> {
            System.out.println("Botón Cancelar presionado para la orden: " + orden.getInteger("numeroOrden"));

            System.out.println("Cancelando orden #" + orden.getInteger("numeroOrden"));
            adminOrden.cancelarOrden(orden.getInteger("numeroOrden"));
            panelOrdenRef[0].setVisible(false);
             // Limpiar los paneles existentes
            panelOrdenes.removeAll();
            panelOrdenes.revalidate();
            panelOrdenes.repaint();
            
            // Obtener y mostrar las nuevas órdenes
            List<Document> ordenesPendientesActualizadas = adminOrden.obtenerOrdenesPendientes();
            for (Document ordenesA : ordenesPendientesActualizadas) {
                agregarOrden(ordenesA, insets);
            }
        });

        JButton btnCompletar = new JButton("Completar");
        Color azul = new Color(155, 181, 210);
        btnCompletar.setBackground(azul);
        btnCompletar.setForeground(Color.WHITE);

        btnCompletar.addActionListener(e -> {
            System.out.println("Botón Completar presionado para la orden: " + orden.getInteger("numeroOrden"));

            System.out.println("Completando orden #" + orden.getInteger("numeroOrden"));
            adminOrden.completarOrden(orden.getInteger("numeroOrden"));
            panelOrdenRef[0].setVisible(false);
            // Limpiar los paneles existentes
            panelOrdenes.removeAll();
            panelOrdenes.revalidate();
            panelOrdenes.repaint();

            // Obtener y mostrar las nuevas órdenes
            List<Document> ordenesPendientesActualizadas = adminOrden.obtenerOrdenesPendientes();
            for (Document ordenesA : ordenesPendientesActualizadas) {
                agregarOrden(ordenesA, insets);
            }
        });
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0)); // 10 es el espacio entre los botones

        panelBotones.add(btnCancelar);
        panelBotones.add(btnCompletar);

        panelOrden.add(panelBotones);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = GridBagConstraints.RELATIVE;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = insets; // Aplica los márgenes
        panelOrdenes.add(panelOrden, gbc);

        revalidate();
        repaint();
            
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Inicializa adminOrden aquí
            Ordenes frame = new Ordenes();
            
            frame.adminOrden = new FacadeAdminOrden();
            frame.setVisible(true);
        });
    }
}