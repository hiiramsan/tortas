package org.itson.bdavanzadas.presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import org.itson.bdavanzadas.adminOrden.FacadeAdminOrden;
import org.itson.bdavanzadas.adminOrden.IAdminOrden;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.dtos.TortaDTO;
import org.itson.bdavanzadas.objetosNegocio.excepction.NegocioException;
import org.itson.bdavanzadas.persistencia.entidades.Orden;
import org.itson.bdavanzadas.persistencia.entidades.Producto;
import org.itson.bdavanzadas.persistencia.exception.FindException;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

public class Ordenes extends JFrame {

    private JPanel panelOrdenes;
    public IAdminOrden adminOrden;

    private final Insets insets = new Insets(10, 10, 10, 10);

    public Ordenes() {
        this.adminOrden = new FacadeAdminOrden();
        initComponents();
        setSize(1400, 700);
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

        JButton btnActualizar = new JButton("Actualizar");
        Color azul = new Color(36, 123, 160);
        btnActualizar.setBackground(azul);
        btnActualizar.setForeground(Color.WHITE);

        btnActualizar.addActionListener(e -> actualizarOrdenesPendientes());

        JButton btnOrdenarPorAsc = new JButton("Ordenar por fecha ascendente");
        btnOrdenarPorAsc.setBackground(azul);
        btnOrdenarPorAsc.setForeground(Color.WHITE);

        btnOrdenarPorAsc.addActionListener(e -> ordenarPorFechaAscendente());

        JButton btnOrdenarPorCantTortas = new JButton("Ordenar por cantidad de tortas");
        btnOrdenarPorCantTortas.setBackground(azul);
        btnOrdenarPorCantTortas.setForeground(Color.WHITE);

        btnOrdenarPorCantTortas.addActionListener(e -> ordenarPorCantidadDeTortas());

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

        contentPane.add(btnPanel, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);

        actualizarOrdenesPendientes();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    VentanaPrincipal vp = new VentanaPrincipal();
                    vp.setVisible(true);
                } catch (FindException ex) {
                    Logger.getLogger(FrmInventariar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void actualizarOrdenesPendientes() {
        panelOrdenes.removeAll();
        panelOrdenes.revalidate();
        panelOrdenes.repaint();

        List<NuevaOrdenDTO> ordenesPendientesActualizadas = adminOrden.obtenerOrdenesPendientes();
        for (NuevaOrdenDTO orden : ordenesPendientesActualizadas) {
            agregarOrden(orden, insets);
        }
    }

    private void ordenarPorFechaAscendente() {
        panelOrdenes.removeAll();
        panelOrdenes.revalidate();
        panelOrdenes.repaint();

        List<NuevaOrdenDTO> ordenesPendientesASC = adminOrden.obtenerOrdenesPorFechaAscendente();
        for (NuevaOrdenDTO orden : ordenesPendientesASC) {
            agregarOrden(orden, insets);
        }
    }

    private void ordenarPorCantidadDeTortas() {
        panelOrdenes.removeAll();
        panelOrdenes.revalidate();
        panelOrdenes.repaint();

        List<NuevaOrdenDTO> ordenesPendientesMayorTortas = adminOrden.obtenerOrdenesPendientesPorCantidadTortas();
        for (NuevaOrdenDTO orden : ordenesPendientesMayorTortas) {
            agregarOrden(orden, insets);
        }
    }

    public void agregarOrden(NuevaOrdenDTO orden, Insets insets) {
        JPanel panelOrden = new JPanel();
        panelOrden.setLayout(new BoxLayout(panelOrden, BoxLayout.Y_AXIS));
        Font font = new Font("Segoe UI Semibold", Font.BOLD, 18);

        JLabel lblNumeroOrden = new JLabel("Orden #" + orden.getNumeroOrden());
        lblNumeroOrden.setFont(font);
        panelOrden.add(lblNumeroOrden);
        panelOrden.add(Box.createVerticalStrut(5));

        JLabel lblNombreCliente = new JLabel("Cliente: " + orden.getNombreCliente());
        panelOrden.add(lblNombreCliente);

        JLabel lblEstado = new JLabel("Estado: " + orden.getEstado());
        panelOrden.add(lblEstado);

        Date fechaOrden = orden.getFecha();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy hh:mm a", new Locale("es", "ES"));
        String fechaFormateada = sdf.format(fechaOrden);

        JLabel lblFecha = new JLabel("Fecha: " + fechaFormateada + "       ");
        panelOrden.add(lblFecha);

        JLabel lblTotal = new JLabel("Total: " + orden.getTotal());
        panelOrden.add(lblTotal);
        panelOrden.add(Box.createVerticalStrut(10));

        JLabel lblProductos = new JLabel("Productos:");
        panelOrden.add(lblProductos);

        List<NuevoProductoDTO> listaProductos = orden.getListaProductos();
        for (NuevoProductoDTO producto : listaProductos) {
            JLabel lblProducto = new JLabel("\t- " + producto.getNombre()
                    + ", Cantidad: " + producto.getCantidad());
            lblProducto.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
            panelOrden.add(lblProducto);

            if (producto instanceof TortaDTO) {
                TortaDTO torta = (TortaDTO) producto;
                JLabel lblIngredientes = new JLabel("\t\tIngredientes:");
                panelOrden.add(lblIngredientes);

                if (torta.getCantCebolla() > 0) {
                    JLabel lblIngrediente = new JLabel("\t\t\t    Cebolla x" + torta.getCantCebolla());
                    panelOrden.add(lblIngrediente);
                }
                if (torta.getCantTomate() > 0) {
                    JLabel lblIngrediente = new JLabel("\t\t\t    Tomate x" + torta.getCantTomate());
                    panelOrden.add(lblIngrediente);
                }
                if (torta.getCantRepollo() > 0) {
                    JLabel lblIngrediente = new JLabel("\t\t\t    Repollo x" + torta.getCantRepollo());
                    panelOrden.add(lblIngrediente);
                }
                if (torta.getCantMayonesa() > 0) {
                    JLabel lblIngrediente = new JLabel("\t\t\t    Mayonesa x" + torta.getCantMayonesa());
                    panelOrden.add(lblIngrediente);
                }
                if (torta.getCantMostaza() > 0) {
                    JLabel lblIngrediente = new JLabel("\t\t\t    Mostaza x" + torta.getCantMostaza());
                    panelOrden.add(lblIngrediente);
                }
                if (torta.getCantJalapeno() > 0) {
                    JLabel lblIngrediente = new JLabel("\t\t\t    Jalapeño x" + torta.getCantJalapeno());
                    panelOrden.add(lblIngrediente);
                }
                if (torta.getCantCarne() > 0) {
                    JLabel lblIngrediente = new JLabel("\t\t\t    Carne x" + torta.getCantCarne());
                    panelOrden.add(lblIngrediente);
                }
            }

            if (producto.getDescripcion() != null && !producto.getDescripcion().isEmpty()) {
                JLabel lblDescripcion = new JLabel("Notas: " + producto.getDescripcion());
                panelOrden.add(lblDescripcion);
            }
        }

        panelOrden.add(Box.createVerticalStrut(10));

        JButton btnCancelar = new JButton("Cancelar");
        Color rojo = new Color(225, 121, 117);
        btnCancelar.setBackground(rojo);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.addActionListener(e -> {
            adminOrden.cancelarOrden(orden);
            panelOrden.setVisible(false);
            actualizarOrdenesPendientes();

        });

        JButton btnCompletar = new JButton("Completar");
        Color azul = new Color(155, 181, 210);
        btnCompletar.setBackground(azul);
        btnCompletar.setForeground(Color.WHITE);
        btnCompletar.addActionListener(e -> {

            try {
                adminOrden.ordenCompletada(orden);
            } catch (NegocioException ex) {
                Logger.getLogger(Ordenes.class.getName()).log(Level.SEVERE, null, ex);
            }
            panelOrden.setVisible(false);
            actualizarOrdenesPendientes();

        });

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotones.add(btnCancelar);
        panelBotones.add(btnCompletar);

        panelOrden.add(panelBotones);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = GridBagConstraints.RELATIVE;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = insets;
        panelOrdenes.add(panelOrden, gbc);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ordenes frame = new Ordenes();
            frame.setVisible(true);
        });
    }
}
