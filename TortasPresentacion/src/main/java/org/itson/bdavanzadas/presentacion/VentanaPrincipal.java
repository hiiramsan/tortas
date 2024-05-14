/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.itson.bdavanzadas.presentacion;

import org.itson.bdavanzadas.adminInventario.FacadeAdminInventario;
import org.itson.bdavanzadas.adminInventario.IInventario;
import org.itson.bdavanzadas.adminOrden.FacadeAdminOrden;
import org.itson.bdavanzadas.adminOrden.IAdminOrden;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.dtos.TortaDTO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.itson.bdavanzadas.persistencia.exception.FindException;

/**
 * @author Abel Eduardo Sanchez Guerrero | 00000
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Carlos Hiram Sanchez Menseses | 00000246787
 * @author Ana Cristina Castro Noriega | 00000
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private static VentanaPrincipal instance;

    //precios
    double precioCoca;
    double precioPepsi;
    double precioFanta;
    double precioJamaica;
    double precioHorchata;
    double precioAgua;

    int cantidadSencilla = 0;
    int cantidadEspecial = 0;
    int cantidadAhogada = 0;
    int cantidadCoca = 0;
    int cantidadPepsi = 0;
    int cantidadFanta = 0;
    int cantidadJamaica = 0;
    int cantidadHorchata = 0;
    int cantidadAgua = 0;

    // tortas : este DTO ya tiene atributos de ingredientes
    TortaDTO tortaSencilla = new TortaDTO("Sencilla", 0, 60, "Torta");
    TortaDTO tortaEspecial = new TortaDTO("Especial", 0, 70, "Torta");

    // productos : no tienen atributos de ingredientes
    NuevoProductoDTO ahogada = new NuevoProductoDTO("ahogada", 0, 15, "Bebida");
    NuevoProductoDTO coca = new NuevoProductoDTO("Coca-cola", 0, precioCoca, "Bebida");
    NuevoProductoDTO pepsi = new NuevoProductoDTO("Pepsi", 0, precioPepsi, "Bebida");
    NuevoProductoDTO fanta = new NuevoProductoDTO("Fanta", 0, precioFanta, "Bebida");
    NuevoProductoDTO jamaica = new NuevoProductoDTO("Jamaica", 0, precioJamaica, "Bebida");
    NuevoProductoDTO horchata = new NuevoProductoDTO("Horchata", 0, precioHorchata, "Bebida");
    NuevoProductoDTO agua = new NuevoProductoDTO("Agua", 0, precioAgua, "Bebida");

    Map<String, Integer> ingredientes = new HashMap<>();
    private static DetallesTorta detallesTorta;
    private static List<NuevoProductoDTO> listaProductos = new ArrayList<>();
    private static List<NuevaOrdenDTO> listaOrden = new ArrayList<>();

    IInventario inventario;
    IAdminOrden adminOrden;

    // stock
    int stockCocaCola;
    int stockPepsi;
    int stockFanta;
    int stockJamaica;
    int stockHorchata;
    int stockAgua;

    // stock temporal
    int stockTemporalCocaCola;
    int stockTemporalPepsi;
    int stockTemporalFanta;
    int stockTemporalJamaica;
    int stockTemporalHorchata;
    int stockTemporalAgua;

    /**
     * Creates new form VentanaPrincipal
     *
     * @throws org.itson.bdavanzadas.persistencia.exception.FindException
     */
    public VentanaPrincipal() throws FindException {
        this.inventario = new FacadeAdminInventario();
        this.adminOrden = new FacadeAdminOrden();
        initComponents();
        ordenPanel.setVisible(false);

        // actualizar tiempo
        Thread updaterThread = new Thread(() -> {
            while (true) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String horaActual = sdf.format(new Date());
                lblHora.setText(horaActual);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        updaterThread.start();

        desactivarPaneles();
        actualizarStock();
        actualizarPrecios();
        eliminarProductoBtn.setEnabled(false);
    }

    public void desactivarPaneles() {
        txtBebidasTitulo.setVisible(false);
        txtTortasTitulo.setVisible(false);
        panelAgua.setVisible(false);
        panelAhogada.setVisible(false);
        panelCoca.setVisible(false);
        panelEspecial.setVisible(false);
        panelFanta.setVisible(false);
        panelHorchata.setVisible(false);
        panelJamaica.setVisible(false);
        panelPepsi.setVisible(false);
        panelSencilla.setVisible(false);

    }

    public void activarPaneles() {
        txtTortasTitulo.setVisible(true);
        txtBebidasTitulo.setVisible(true);
        panelAgua.setVisible(false);
        panelAgua.setVisible(false);
        panelAgua.setVisible(true);
        panelAhogada.setVisible(true);
        panelCoca.setVisible(true);
        panelEspecial.setVisible(true);
        panelFanta.setVisible(true);
        panelHorchata.setVisible(true);
        panelJamaica.setVisible(true);
        panelPepsi.setVisible(true);
        panelSencilla.setVisible(true);

    }

    public void actualizarPrecios() {
        precioCoca = adminOrden.obtenerPrecioPorNombre("Coca-cola");
        coca.setPrecio(precioCoca);
        cocaPrecioTxt.setText(String.valueOf("$" + precioCoca));

        precioPepsi = adminOrden.obtenerPrecioPorNombre("Pepsi");
        pepsi.setPrecio(precioPepsi);
        pepsiPrecioTxt.setText(String.valueOf("$" + precioPepsi));

        precioFanta = adminOrden.obtenerPrecioPorNombre("Fanta");
        fanta.setPrecio(precioFanta);
        fantaPrecioTxt.setText(String.valueOf("$" + precioFanta));

        precioJamaica = adminOrden.obtenerPrecioPorNombre("Jamaica");
        jamaica.setPrecio(precioJamaica);
        jamaicaPrecioTxt.setText(String.valueOf("$" + precioJamaica));

        precioHorchata = adminOrden.obtenerPrecioPorNombre("Horchata");
        horchata.setPrecio(precioHorchata);
        horchataPrecioTxt.setText(String.valueOf("$" + precioHorchata));

        precioAgua = adminOrden.obtenerPrecioPorNombre("Agua");
        agua.setPrecio(precioAgua);
        aguaPrecioTxt.setText(String.valueOf("$" + precioAgua));
    }

    public void actualizarStock() {
        stockCocaCola = inventario.getProductStock("Coca-cola");
        stockTemporalCocaCola = stockCocaCola;
        stockCocaColaTxt.setText("Stock: " + String.valueOf(stockCocaCola));

        stockPepsi = inventario.getProductStock("Pepsi");
        stockTemporalPepsi = stockPepsi;
        stockPepsiTxt.setText("Stock: " + String.valueOf(stockPepsi));

        stockFanta = inventario.getProductStock("Fanta");
        stockTemporalFanta = stockFanta;
        stockFantaTxt.setText("Stock: " + String.valueOf(stockFanta));

        stockJamaica = inventario.getProductStock("Jamaica");
        stockTemporalJamaica = stockJamaica;
        stockJamaicaTxt.setText("Stock: " + String.valueOf(stockJamaica));

        stockHorchata = inventario.getProductStock("Horchata");
        stockTemporalHorchata = stockHorchata;
        stockHorchataTxt.setText("Stock: " + String.valueOf(stockHorchata));

        stockAgua = inventario.getProductStock("Agua");
        stockTemporalAgua = stockAgua;
        stockAguaTxt.setText("Stock: " + String.valueOf(stockAgua));

    }

    public void actualizarStockTemporal() {
        stockCocaColaTxt.setText("Stock: " + String.valueOf(stockTemporalCocaCola));
        stockPepsiTxt.setText("Stock: " + String.valueOf(stockTemporalPepsi));
        stockFantaTxt.setText("Stock: " + String.valueOf(stockTemporalFanta));
        stockJamaicaTxt.setText("Stock: " + String.valueOf(stockTemporalJamaica));
        stockHorchataTxt.setText("Stock: " + String.valueOf(stockTemporalHorchata));
        stockAguaTxt.setText("Stock: " + String.valueOf(stockTemporalAgua));
    }

    public List<NuevoProductoDTO> getListaProductos() {
        return listaProductos;
    }

    public void agregarProducto(NuevoProductoDTO producto) {
        listaProductos.add(producto);
        cargarDatosTabla(tablaOrden, listaProductos);
    }

    public static VentanaPrincipal getInstance() throws FindException {
        if (instance == null) {
            instance = new VentanaPrincipal();
        }
        return instance;
    }

    public void resetCantidades() {
        cantidadSencilla = 0;
        cantidadEspecial = 0;
        cantidadAhogada = 0;
        cantidadCoca = 0;
        cantidadPepsi = 0;
        cantidadFanta = 0;
        cantidadJamaica = 0;
        cantidadHorchata = 0;
        cantidadAgua = 0;

        cantSencilla.setText("0");
        cantAhogada.setText("0");
        cantEspecial.setText("0");
        cantFanta.setText("0");
        cantAgua.setText("0");
        cantSencilla.setText("0");
        cantHorchata.setText("0");
        cantJamaica.setText("0");
        cantPepsi.setText("0");
        cantCoca.setText("0");
        desactivarPaneles();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtBebidasTitulo = new javax.swing.JLabel();
        panelSencilla = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnSumarSencilla = new javax.swing.JButton();
        btnRestarSencilla = new javax.swing.JButton();
        cantSencilla = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelEspecial = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        btnRestarEspecial = new javax.swing.JButton();
        cantEspecial = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        panelAhogada = new javax.swing.JPanel();
        jButton19 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        btnSumarAhogada = new javax.swing.JButton();
        btnRestarAhogada = new javax.swing.JButton();
        cantAhogada = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        panelFanta = new javax.swing.JPanel();
        jButton28 = new javax.swing.JButton();
        fantaPrecioTxt = new javax.swing.JLabel();
        btnSumarFanta = new javax.swing.JButton();
        btnRestarFanta = new javax.swing.JButton();
        cantFanta = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        stockFantaTxt = new javax.swing.JLabel();
        panelCoca = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        cocaPrecioTxt = new javax.swing.JLabel();
        btnSumarCoca = new javax.swing.JButton();
        btnRestarCoca = new javax.swing.JButton();
        cantCoca = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        stockCocaColaTxt = new javax.swing.JLabel();
        panelPepsi = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        pepsiPrecioTxt = new javax.swing.JLabel();
        btnSumarPepsi = new javax.swing.JButton();
        btnRestarPepsi = new javax.swing.JButton();
        cantPepsi = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        stockPepsiTxt = new javax.swing.JLabel();
        panelHorchata = new javax.swing.JPanel();
        jButton37 = new javax.swing.JButton();
        horchataPrecioTxt = new javax.swing.JLabel();
        btnSumarHorchata = new javax.swing.JButton();
        btnRestarHorchata = new javax.swing.JButton();
        cantHorchata = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        stockHorchataTxt = new javax.swing.JLabel();
        panelJamaica = new javax.swing.JPanel();
        jButton34 = new javax.swing.JButton();
        jamaicaPrecioTxt = new javax.swing.JLabel();
        btnSumarJamaica = new javax.swing.JButton();
        btnRestarJamaica = new javax.swing.JButton();
        cantJamaica = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        stockJamaicaTxt = new javax.swing.JLabel();
        ordenPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaOrden = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnBorrar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        subtotal = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        nombreCliente = new javax.swing.JLabel();
        eliminarProductoBtn = new javax.swing.JButton();
        panelAgua = new javax.swing.JPanel();
        jButton40 = new javax.swing.JButton();
        aguaPrecioTxt = new javax.swing.JLabel();
        btnSumarAgua = new javax.swing.JButton();
        btnRestarAgua = new javax.swing.JButton();
        cantAgua = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        stockAguaTxt = new javax.swing.JLabel();
        txtTortasTitulo = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jButton31 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jButton32 = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        ordenBtn = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblHora = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1098, 744));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/IconoComida.png"))); // NOI18N
        jLabel34.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icono1.png"))); // NOI18N
        jLabel36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/VentaBlanca.png"))); // NOI18N
        jLabel37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/supplier.png"))); // NOI18N
        jLabel1.setText(".");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/boxes.png"))); // NOI18N
        jLabel38.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel38MouseClicked(evt);
            }
        });

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icono historial.png"))); // NOI18N
        jLabel40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(275, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(164, 180, 148));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBebidasTitulo.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        txtBebidasTitulo.setText("Bebidas");
        jPanel6.add(txtBebidasTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 303, 89, -1));

        panelSencilla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelSencilla.setPreferredSize(new java.awt.Dimension(196, 118));
        panelSencilla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelSencillaMouseClicked(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sencilla.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setOpaque(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("$60");

        btnSumarSencilla.setBackground(new java.awt.Color(36, 123, 160));
        btnSumarSencilla.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSumarSencilla.setForeground(new java.awt.Color(255, 255, 255));
        btnSumarSencilla.setText("+");
        btnSumarSencilla.setBorder(null);
        btnSumarSencilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarSencillaActionPerformed(evt);
            }
        });

        btnRestarSencilla.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestarSencilla.setText("-");
        btnRestarSencilla.setBorder(null);
        btnRestarSencilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarSencillaActionPerformed(evt);
            }
        });

        cantSencilla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantSencilla.setText("0");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel8.setText("Sencilla");

        javax.swing.GroupLayout panelSencillaLayout = new javax.swing.GroupLayout(panelSencilla);
        panelSencilla.setLayout(panelSencillaLayout);
        panelSencillaLayout.setHorizontalGroup(
            panelSencillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSencillaLayout.createSequentialGroup()
                .addGroup(panelSencillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSencillaLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSencillaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4)
                        .addGap(6, 6, 6)))
                .addGroup(panelSencillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSencillaLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSencillaLayout.createSequentialGroup()
                        .addComponent(btnRestarSencilla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantSencilla, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSumarSencilla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelSencillaLayout.setVerticalGroup(
            panelSencillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSencillaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelSencillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelSencillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSumarSencilla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestarSencilla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantSencilla)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jPanel6.add(panelSencilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 173, -1, -1));

        panelEspecial.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/especial.png"))); // NOI18N
        jButton16.setBorder(null);
        jButton16.setOpaque(true);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("$70");

        jButton17.setBackground(new java.awt.Color(36, 123, 160));
        jButton17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("+");
        jButton17.setBorder(null);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        btnRestarEspecial.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestarEspecial.setText("-");
        btnRestarEspecial.setBorder(null);
        btnRestarEspecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarEspecialActionPerformed(evt);
            }
        });

        cantEspecial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantEspecial.setText("0");

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel20.setText("Especial");

        javax.swing.GroupLayout panelEspecialLayout = new javax.swing.GroupLayout(panelEspecial);
        panelEspecial.setLayout(panelEspecialLayout);
        panelEspecialLayout.setHorizontalGroup(
            panelEspecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEspecialLayout.createSequentialGroup()
                .addGroup(panelEspecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEspecialLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEspecialLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton16)
                        .addGap(6, 6, 6)))
                .addGroup(panelEspecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEspecialLayout.createSequentialGroup()
                        .addComponent(btnRestarEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantEspecial, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEspecialLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelEspecialLayout.setVerticalGroup(
            panelEspecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEspecialLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelEspecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelEspecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestarEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantEspecial)
                    .addComponent(jLabel18))
                .addContainerGap())
        );

        jPanel6.add(panelEspecial, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 173, -1, -1));

        panelAhogada.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ahogada.png"))); // NOI18N
        jButton19.setBorder(null);
        jButton19.setOpaque(true);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("$15+");

        btnSumarAhogada.setBackground(new java.awt.Color(36, 123, 160));
        btnSumarAhogada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSumarAhogada.setForeground(new java.awt.Color(255, 255, 255));
        btnSumarAhogada.setText("+");
        btnSumarAhogada.setBorder(null);
        btnSumarAhogada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarAhogadaActionPerformed(evt);
            }
        });

        btnRestarAhogada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestarAhogada.setText("-");
        btnRestarAhogada.setBorder(null);
        btnRestarAhogada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarAhogadaActionPerformed(evt);
            }
        });

        cantAhogada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantAhogada.setText("0");

        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel23.setText("Ahogada");

        javax.swing.GroupLayout panelAhogadaLayout = new javax.swing.GroupLayout(panelAhogada);
        panelAhogada.setLayout(panelAhogadaLayout);
        panelAhogadaLayout.setHorizontalGroup(
            panelAhogadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAhogadaLayout.createSequentialGroup()
                .addGroup(panelAhogadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAhogadaLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAhogadaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton19)
                        .addGap(6, 6, 6)))
                .addGroup(panelAhogadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAhogadaLayout.createSequentialGroup()
                        .addComponent(btnRestarAhogada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantAhogada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSumarAhogada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAhogadaLayout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelAhogadaLayout.setVerticalGroup(
            panelAhogadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAhogadaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelAhogadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelAhogadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSumarAhogada, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestarAhogada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantAhogada)
                    .addComponent(jLabel21))
                .addContainerGap())
        );

        jPanel6.add(panelAhogada, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 173, -1, -1));

        panelFanta.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fanta.png"))); // NOI18N
        jButton28.setBorder(null);
        jButton28.setOpaque(true);
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        fantaPrecioTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fantaPrecioTxt.setText("$22");

        btnSumarFanta.setBackground(new java.awt.Color(36, 123, 160));
        btnSumarFanta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSumarFanta.setForeground(new java.awt.Color(255, 255, 255));
        btnSumarFanta.setText("+");
        btnSumarFanta.setBorder(null);
        btnSumarFanta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarFantaActionPerformed(evt);
            }
        });

        btnRestarFanta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestarFanta.setText("-");
        btnRestarFanta.setBorder(null);
        btnRestarFanta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarFantaActionPerformed(evt);
            }
        });

        cantFanta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantFanta.setText("0");

        jLabel33.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel33.setText("Fanta");

        stockFantaTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockFantaTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockFantaTxt.setText("Stock: ");

        javax.swing.GroupLayout panelFantaLayout = new javax.swing.GroupLayout(panelFanta);
        panelFanta.setLayout(panelFantaLayout);
        panelFantaLayout.setHorizontalGroup(
            panelFantaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFantaLayout.createSequentialGroup()
                .addGroup(panelFantaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFantaLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(fantaPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFantaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton28)
                        .addGap(6, 6, 6)))
                .addGroup(panelFantaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFantaLayout.createSequentialGroup()
                        .addGroup(panelFantaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFantaLayout.createSequentialGroup()
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelFantaLayout.createSequentialGroup()
                                .addComponent(btnRestarFanta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantFanta, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSumarFanta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFantaLayout.createSequentialGroup()
                        .addComponent(stockFantaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        panelFantaLayout.setVerticalGroup(
            panelFantaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFantaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelFantaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFantaLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stockFantaTxt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(panelFantaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSumarFanta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestarFanta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantFanta)
                    .addComponent(fantaPrecioTxt))
                .addContainerGap())
        );

        jPanel6.add(panelFanta, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 344, -1, -1));

        panelCoca.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/coca.png"))); // NOI18N
        jButton22.setBorder(null);
        jButton22.setOpaque(true);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        cocaPrecioTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cocaPrecioTxt.setText("$25");

        btnSumarCoca.setBackground(new java.awt.Color(36, 123, 160));
        btnSumarCoca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSumarCoca.setForeground(new java.awt.Color(255, 255, 255));
        btnSumarCoca.setText("+");
        btnSumarCoca.setBorder(null);
        btnSumarCoca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarCocaActionPerformed(evt);
            }
        });

        btnRestarCoca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestarCoca.setText("-");
        btnRestarCoca.setBorder(null);
        btnRestarCoca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarCocaActionPerformed(evt);
            }
        });

        cantCoca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantCoca.setText("0");

        jLabel27.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel27.setText("Coca cola");

        stockCocaColaTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockCocaColaTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockCocaColaTxt.setText("Stock: ");

        javax.swing.GroupLayout panelCocaLayout = new javax.swing.GroupLayout(panelCoca);
        panelCoca.setLayout(panelCocaLayout);
        panelCocaLayout.setHorizontalGroup(
            panelCocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCocaLayout.createSequentialGroup()
                .addGroup(panelCocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCocaLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(cocaPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCocaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton22)
                        .addGap(6, 6, 6)))
                .addGroup(panelCocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCocaLayout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCocaLayout.createSequentialGroup()
                        .addGroup(panelCocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCocaLayout.createSequentialGroup()
                                .addComponent(btnRestarCoca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantCoca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(stockCocaColaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSumarCoca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelCocaLayout.setVerticalGroup(
            panelCocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCocaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelCocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCocaLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stockCocaColaTxt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(panelCocaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSumarCoca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestarCoca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantCoca)
                    .addComponent(cocaPrecioTxt))
                .addContainerGap())
        );

        jPanel6.add(panelCoca, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 344, -1, -1));

        panelPepsi.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pepsi.png"))); // NOI18N
        jButton25.setBorder(null);
        jButton25.setOpaque(true);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        pepsiPrecioTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pepsiPrecioTxt.setText("$22");

        btnSumarPepsi.setBackground(new java.awt.Color(36, 123, 160));
        btnSumarPepsi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSumarPepsi.setForeground(new java.awt.Color(255, 255, 255));
        btnSumarPepsi.setText("+");
        btnSumarPepsi.setBorder(null);
        btnSumarPepsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarPepsiActionPerformed(evt);
            }
        });

        btnRestarPepsi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestarPepsi.setText("-");
        btnRestarPepsi.setBorder(null);
        btnRestarPepsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarPepsiActionPerformed(evt);
            }
        });

        cantPepsi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantPepsi.setText("0");

        jLabel30.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel30.setText("Pepsi");

        stockPepsiTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockPepsiTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockPepsiTxt.setText("Stock: ");

        javax.swing.GroupLayout panelPepsiLayout = new javax.swing.GroupLayout(panelPepsi);
        panelPepsi.setLayout(panelPepsiLayout);
        panelPepsiLayout.setHorizontalGroup(
            panelPepsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPepsiLayout.createSequentialGroup()
                .addGroup(panelPepsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPepsiLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(pepsiPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPepsiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton25)
                        .addGap(6, 6, 6)))
                .addGroup(panelPepsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPepsiLayout.createSequentialGroup()
                        .addGroup(panelPepsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPepsiLayout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPepsiLayout.createSequentialGroup()
                                .addComponent(btnRestarPepsi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantPepsi, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSumarPepsi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPepsiLayout.createSequentialGroup()
                        .addComponent(stockPepsiTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        panelPepsiLayout.setVerticalGroup(
            panelPepsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPepsiLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelPepsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelPepsiLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(18, 18, 18)
                        .addComponent(stockPepsiTxt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(panelPepsiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSumarPepsi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestarPepsi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantPepsi)
                    .addComponent(pepsiPrecioTxt))
                .addContainerGap())
        );

        jPanel6.add(panelPepsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 344, -1, -1));

        panelHorchata.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/horchata.png"))); // NOI18N
        jButton37.setBorder(null);
        jButton37.setOpaque(true);
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        horchataPrecioTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        horchataPrecioTxt.setText("$20");

        btnSumarHorchata.setBackground(new java.awt.Color(36, 123, 160));
        btnSumarHorchata.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSumarHorchata.setForeground(new java.awt.Color(255, 255, 255));
        btnSumarHorchata.setText("+");
        btnSumarHorchata.setBorder(null);
        btnSumarHorchata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarHorchataActionPerformed(evt);
            }
        });

        btnRestarHorchata.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestarHorchata.setText("-");
        btnRestarHorchata.setBorder(null);
        btnRestarHorchata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarHorchataActionPerformed(evt);
            }
        });

        cantHorchata.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantHorchata.setText("0");

        jLabel42.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel42.setText("Horchata");

        stockHorchataTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockHorchataTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockHorchataTxt.setText("Stock: ");

        javax.swing.GroupLayout panelHorchataLayout = new javax.swing.GroupLayout(panelHorchata);
        panelHorchata.setLayout(panelHorchataLayout);
        panelHorchataLayout.setHorizontalGroup(
            panelHorchataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHorchataLayout.createSequentialGroup()
                .addGroup(panelHorchataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHorchataLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(horchataPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHorchataLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton37)
                        .addGap(6, 6, 6)))
                .addGroup(panelHorchataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHorchataLayout.createSequentialGroup()
                        .addComponent(btnRestarHorchata, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantHorchata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSumarHorchata, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelHorchataLayout.createSequentialGroup()
                        .addGroup(panelHorchataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stockHorchataTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelHorchataLayout.setVerticalGroup(
            panelHorchataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHorchataLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelHorchataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelHorchataLayout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stockHorchataTxt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(panelHorchataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSumarHorchata, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestarHorchata, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantHorchata)
                    .addComponent(horchataPrecioTxt))
                .addContainerGap())
        );

        jPanel6.add(panelHorchata, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 474, -1, -1));

        panelJamaica.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/jamaica.png"))); // NOI18N
        jButton34.setBorder(null);
        jButton34.setOpaque(true);
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jamaicaPrecioTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jamaicaPrecioTxt.setText("$20");

        btnSumarJamaica.setBackground(new java.awt.Color(36, 123, 160));
        btnSumarJamaica.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSumarJamaica.setForeground(new java.awt.Color(255, 255, 255));
        btnSumarJamaica.setText("+");
        btnSumarJamaica.setBorder(null);
        btnSumarJamaica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarJamaicaActionPerformed(evt);
            }
        });

        btnRestarJamaica.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestarJamaica.setText("-");
        btnRestarJamaica.setBorder(null);
        btnRestarJamaica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarJamaicaActionPerformed(evt);
            }
        });

        cantJamaica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantJamaica.setText("0");

        jLabel39.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel39.setText("Jamaica");

        stockJamaicaTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockJamaicaTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockJamaicaTxt.setText("Stock: ");

        javax.swing.GroupLayout panelJamaicaLayout = new javax.swing.GroupLayout(panelJamaica);
        panelJamaica.setLayout(panelJamaicaLayout);
        panelJamaicaLayout.setHorizontalGroup(
            panelJamaicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJamaicaLayout.createSequentialGroup()
                .addGroup(panelJamaicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJamaicaLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jamaicaPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJamaicaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton34)
                        .addGap(6, 6, 6)))
                .addGroup(panelJamaicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJamaicaLayout.createSequentialGroup()
                        .addComponent(btnRestarJamaica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantJamaica, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSumarJamaica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelJamaicaLayout.createSequentialGroup()
                        .addGroup(panelJamaicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stockJamaicaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelJamaicaLayout.setVerticalGroup(
            panelJamaicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJamaicaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelJamaicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelJamaicaLayout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stockJamaicaTxt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(panelJamaicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSumarJamaica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestarJamaica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantJamaica)
                    .addComponent(jamaicaPrecioTxt))
                .addContainerGap())
        );

        jPanel6.add(panelJamaica, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 474, -1, -1));

        ordenPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(36, 123, 160));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Continuar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        ordenPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 240, 33));

        tablaOrden.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Cantidad", "Torta", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaOrden.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tablaOrdenFocusLost(evt);
            }
        });
        tablaOrden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaOrdenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaOrden);

        ordenPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 49, 240, 185));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Orden: ");
        ordenPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        btnBorrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBorrar.setText("x");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        ordenPanel.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 35, 29));

        jLabel4.setText("Sub total");
        ordenPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 61, -1));

        jLabel5.setText("IVA");
        ordenPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 61, -1));

        jLabel43.setText("Total");
        ordenPanel.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 61, -1));

        subtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        subtotal.setText("$0");
        ordenPanel.add(subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 61, -1));

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel45.setText("$0");
        ordenPanel.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 61, -1));

        total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        total.setText("$0");
        ordenPanel.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 61, -1));

        nombreCliente.setText("nombre del cliente");
        ordenPanel.add(nombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 20));

        eliminarProductoBtn.setText("Eliminar");
        eliminarProductoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarProductoBtnActionPerformed(evt);
            }
        });
        ordenPanel.add(eliminarProductoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, -1, -1));

        jPanel6.add(ordenPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 270, 380));

        panelAgua.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agua.png"))); // NOI18N
        jButton40.setBorder(null);
        jButton40.setOpaque(true);
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        aguaPrecioTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        aguaPrecioTxt.setText("$15");

        btnSumarAgua.setBackground(new java.awt.Color(36, 123, 160));
        btnSumarAgua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSumarAgua.setForeground(new java.awt.Color(255, 255, 255));
        btnSumarAgua.setText("+");
        btnSumarAgua.setBorder(null);
        btnSumarAgua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarAguaActionPerformed(evt);
            }
        });

        btnRestarAgua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestarAgua.setText("-");
        btnRestarAgua.setBorder(null);
        btnRestarAgua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarAguaActionPerformed(evt);
            }
        });

        cantAgua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantAgua.setText("0");

        jLabel49.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel49.setText("Agua");

        stockAguaTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockAguaTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockAguaTxt.setText("Stock: ");

        javax.swing.GroupLayout panelAguaLayout = new javax.swing.GroupLayout(panelAgua);
        panelAgua.setLayout(panelAguaLayout);
        panelAguaLayout.setHorizontalGroup(
            panelAguaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAguaLayout.createSequentialGroup()
                .addGroup(panelAguaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAguaLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(aguaPrecioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAguaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton40)
                        .addGap(6, 6, 6)))
                .addGroup(panelAguaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAguaLayout.createSequentialGroup()
                        .addComponent(btnRestarAgua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantAgua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSumarAgua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAguaLayout.createSequentialGroup()
                        .addGroup(panelAguaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stockAguaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelAguaLayout.setVerticalGroup(
            panelAguaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAguaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelAguaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelAguaLayout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stockAguaTxt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(panelAguaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSumarAgua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestarAgua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantAgua)
                    .addComponent(aguaPrecioTxt))
                .addContainerGap())
        );

        jPanel6.add(panelAgua, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 474, -1, -1));

        txtTortasTitulo.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        txtTortasTitulo.setText("Tortas");
        jPanel6.add(txtTortasTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 132, 74, -1));

        jPanel18.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sencilla.png"))); // NOI18N
        jButton31.setBorder(null);
        jButton31.setOpaque(true);
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel51.setText("3 opciones");

        jLabel52.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel52.setText("Tortas");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton31)
                .addGap(18, 18, 18)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                    .addContainerGap(96, Short.MAX_VALUE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31)))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel51)
                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(jLabel52)
                    .addContainerGap(41, Short.MAX_VALUE)))
        );

        jPanel6.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 15, -1, 91));

        jPanel19.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agua.png"))); // NOI18N
        jButton32.setBorder(null);
        jButton32.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton32.setOpaque(true);
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel53.setText("6 opciones");

        jLabel54.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel54.setText("Bebidas");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton32)
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel53)
                    .addComponent(jLabel54))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53))
                    .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 15, -1, 91));

        ordenBtn.setText("Tomar orden");
        ordenBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordenBtnActionPerformed(evt);
            }
        });
        jPanel6.add(ordenBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 270, 39));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel35.setText("MENU");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(864, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel35)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(147, 187, 204));

        lblHora.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblHora.setText(" ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(476, 476, 476)
                .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(lblHora)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 982, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        DetallesTorta DT = new DetallesTorta();
        DT.setVisible(true);
        cargarDatosTabla(tablaOrden, listaProductos);
     }//GEN-LAST:event_jButton4ActionPerformed

    private void btnSumarSencillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarSencillaActionPerformed
        if (!listaProductos.contains(tortaSencilla)) {
            listaProductos.add(tortaSencilla);
        }

        cantidadSencilla++;
        tortaSencilla.setCantidad(cantidadSencilla);
        String cSencilla = String.valueOf(cantidadSencilla);
        cantSencilla.setText(cSencilla);
        cargarDatosTabla(tablaOrden, listaProductos);
        actualizarTotal(listaProductos);
    }//GEN-LAST:event_btnSumarSencillaActionPerformed

    private void btnRestarSencillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarSencillaActionPerformed
        if (listaProductos.contains(tortaSencilla) && cantidadSencilla > 1) {
            cantidadSencilla--;
            tortaSencilla.setCantidad(cantidadSencilla);
            String cSencilla = String.valueOf(cantidadSencilla);
            cantSencilla.setText(cSencilla);
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        } else {
            cantidadSencilla = 0;
            tortaSencilla.setCantidad(cantidadSencilla);
            cantSencilla.setText("0");
            if (listaProductos.contains(tortaSencilla)) {
                listaProductos.remove(tortaSencilla);
            }
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        }
    }//GEN-LAST:event_btnRestarSencillaActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        DetallesTorta DT = new DetallesTorta();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        if (!listaProductos.contains(tortaEspecial)) {
            listaProductos.add(tortaEspecial);
        }

        cantidadEspecial++;
        tortaEspecial.setCantidad(cantidadEspecial);
        String cEspecial = String.valueOf(cantidadEspecial);
        cantEspecial.setText(cEspecial);
        cargarDatosTabla(tablaOrden, listaProductos);
        actualizarTotal(listaProductos);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void btnRestarEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarEspecialActionPerformed

        if (listaProductos.contains(tortaEspecial) && cantidadEspecial > 1) {
            cantidadEspecial--;
            tortaEspecial.setCantidad(cantidadEspecial);
            String cSencilla = String.valueOf(cantidadEspecial);
            cantEspecial.setText(cSencilla);
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        } else {
            cantidadEspecial = 0;
            tortaEspecial.setCantidad(cantidadEspecial);
            cantEspecial.setText("0");
            if (listaProductos.contains(tortaEspecial)) {
                listaProductos.remove(tortaEspecial);
            }
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        }
    }//GEN-LAST:event_btnRestarEspecialActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void btnSumarAhogadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarAhogadaActionPerformed
        if (!listaProductos.contains(ahogada)) {
            listaProductos.add(ahogada);
        }

        cantidadAhogada++;
        ahogada.setCantidad(cantidadAhogada);
        String cAhogada = String.valueOf(cantidadAhogada);
        cantAhogada.setText(cAhogada);
        cargarDatosTabla(tablaOrden, listaProductos);
        actualizarTotal(listaProductos);
    }//GEN-LAST:event_btnSumarAhogadaActionPerformed

    private void btnRestarAhogadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarAhogadaActionPerformed
        if (listaProductos.contains(ahogada) && cantidadAhogada > 1) {
            cantidadAhogada--;
            ahogada.setCantidad(cantidadAhogada);
            String cAhogada = String.valueOf(cantidadAhogada);
            cantAhogada.setText(cAhogada);
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        } else {
            cantidadAhogada = 0;
            ahogada.setCantidad(cantidadAhogada);
            cantAhogada.setText("0");
            if (listaProductos.contains(ahogada)) {
                listaProductos.remove(ahogada);
            }
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        }
    }//GEN-LAST:event_btnRestarAhogadaActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void btnSumarCocaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarCocaActionPerformed
        if (stockTemporalCocaCola > 0) {
            stockTemporalCocaCola--;
            actualizarStockTemporal();
            if (!listaProductos.contains(coca)) {
                listaProductos.add(coca);
            }
            cantidadCoca++;
            coca.setCantidad(cantidadCoca);
            String cCoca = String.valueOf(cantidadCoca);
            cantCoca.setText(cCoca);
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        } else {
            System.out.println("No hay más stock disponible de Coca-Cola.");
        }
    }//GEN-LAST:event_btnSumarCocaActionPerformed

    private void btnRestarCocaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarCocaActionPerformed
        if (cantidadCoca > 0) {
            stockTemporalCocaCola++;
            actualizarStockTemporal();
            cantidadCoca--;
            if (cantidadCoca == 0) {
                listaProductos.remove(coca);
            } else {
                coca.setCantidad(cantidadCoca);
            }
            String cCoca = String.valueOf(cantidadCoca);
            cantCoca.setText(cCoca);
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        }
    }//GEN-LAST:event_btnRestarCocaActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

    private void btnSumarPepsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarPepsiActionPerformed

        if (stockTemporalPepsi > 0) {
            stockTemporalPepsi--;
            actualizarStockTemporal();
            if (!listaProductos.contains(pepsi)) {
                listaProductos.add(pepsi);
            }
            cantidadPepsi++;
            pepsi.setCantidad(cantidadPepsi);
            String cPepsi = String.valueOf(cantidadPepsi);
            cantPepsi.setText(cPepsi);
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        } else {
            System.out.println("No hay más stock disponible de Pepsi.");
        }

    }//GEN-LAST:event_btnSumarPepsiActionPerformed

    private void btnRestarPepsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarPepsiActionPerformed
        if (cantidadPepsi > 0) {
            stockTemporalPepsi++;
            actualizarStockTemporal();
            cantidadPepsi--;
            if (cantidadPepsi == 0) {
                listaProductos.remove(pepsi);
            } else {
                pepsi.setCantidad(cantidadPepsi);
            }
            String cPepsi = String.valueOf(cantidadPepsi);
            cantPepsi.setText(cPepsi);
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        }
    }//GEN-LAST:event_btnRestarPepsiActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void btnSumarFantaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarFantaActionPerformed

        if (stockTemporalFanta > 0) {
            stockTemporalFanta--;
            actualizarStockTemporal();
            if (!listaProductos.contains(fanta)) {
                listaProductos.add(fanta);
            }
            cantidadFanta++;
            fanta.setCantidad(cantidadFanta);
            String cFanta = String.valueOf(cantidadFanta);
            cantFanta.setText(cFanta);
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        } else {
            System.out.println("No hay más stock disponible de Fanta.");
        }

    }//GEN-LAST:event_btnSumarFantaActionPerformed

    private void btnRestarFantaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarFantaActionPerformed

        if (cantidadFanta > 0) {
            stockTemporalFanta++;
            actualizarStockTemporal();
            cantidadFanta--;
            if (cantidadFanta == 0) {
                listaProductos.remove(fanta);
            } else {
                fanta.setCantidad(cantidadFanta);
            }
            String cFanta = String.valueOf(cantidadFanta);
            cantFanta.setText(cFanta);
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        }
    }//GEN-LAST:event_btnRestarFantaActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34ActionPerformed

    private void btnSumarJamaicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarJamaicaActionPerformed
        if (stockTemporalJamaica > 0) {
            stockTemporalJamaica--;
            actualizarStockTemporal();
            if (!listaProductos.contains(jamaica)) {
                listaProductos.add(jamaica);
            }
            cantidadJamaica++;
            jamaica.setCantidad(cantidadJamaica);
            String cJamaica = String.valueOf(cantidadJamaica);
            cantJamaica.setText(cJamaica);
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        } else {
            System.out.println("No hay más stock disponible de Jamaica.");
        }
    }//GEN-LAST:event_btnSumarJamaicaActionPerformed

    private void btnRestarJamaicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarJamaicaActionPerformed
        if (cantidadJamaica > 0) {
            stockTemporalJamaica++;
            actualizarStockTemporal();
            cantidadJamaica--;
            if (cantidadJamaica == 0) {
                listaProductos.remove(jamaica);
            } else {
                jamaica.setCantidad(cantidadJamaica);
            }
            String cJamaica = String.valueOf(cantidadJamaica);
            cantJamaica.setText(cJamaica);
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        }
    }//GEN-LAST:event_btnRestarJamaicaActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton37ActionPerformed

    private void btnSumarHorchataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarHorchataActionPerformed
        if (stockTemporalHorchata > 0) {
            stockTemporalHorchata--;
            actualizarStockTemporal();
            if (!listaProductos.contains(horchata)) {
                listaProductos.add(horchata);
            }
            cantidadHorchata++;
            horchata.setCantidad(cantidadHorchata);
            String cHorchata = String.valueOf(cantidadHorchata);
            cantHorchata.setText(cHorchata);
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        } else {
            System.out.println("No hay más stock disponible de Horchata.");
        }
    }//GEN-LAST:event_btnSumarHorchataActionPerformed

    private void btnRestarHorchataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarHorchataActionPerformed
        if (cantidadHorchata > 0) {
            stockTemporalHorchata++;
            actualizarStockTemporal();
            cantidadHorchata--;
            if (cantidadHorchata == 0) {
                listaProductos.remove(horchata);
            } else {
                horchata.setCantidad(cantidadHorchata);
            }
            String cHorchata = String.valueOf(cantidadHorchata);
            cantHorchata.setText(cHorchata);
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        }
    }//GEN-LAST:event_btnRestarHorchataActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        listaProductos.clear();
        ordenPanel.setVisible(false);
        desactivarPaneles();
        cargarDatosTabla(tablaOrden, listaProductos);
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton40ActionPerformed

    private void btnSumarAguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarAguaActionPerformed
        if (stockTemporalAgua > 0) {
            stockTemporalAgua--;
            actualizarStockTemporal();
            if (!listaProductos.contains(agua)) {
                listaProductos.add(agua);
            }
            cantidadAgua++;
            agua.setCantidad(cantidadAgua);
            String cAgua = String.valueOf(cantidadAgua);
            cantAgua.setText(cAgua);
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        } else {
            System.out.println("No hay más stock disponible de Agua.");
        }
    }//GEN-LAST:event_btnSumarAguaActionPerformed

    private void btnRestarAguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarAguaActionPerformed
        if (cantidadAgua > 0) {
            stockTemporalAgua++;
            actualizarStockTemporal();
            cantidadAgua--;
            if (cantidadAgua == 0) {
                listaProductos.remove(agua);
            } else {
                agua.setCantidad(cantidadAgua);
            }
            String cAgua = String.valueOf(cantidadAgua);
            cantAgua.setText(cAgua);
            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        }
    }//GEN-LAST:event_btnRestarAguaActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton32ActionPerformed

    private void ordenBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordenBtnActionPerformed
        String nombreDelCliente = JOptionPane.showInputDialog(null, "Nombre del cliente:");

        // Comprueba si el usuario ha pulsado "Aceptar" o "Cancelar"
        if (!nombreDelCliente.isBlank()) {
            nombreCliente.setText(nombreDelCliente);
            ordenPanel.setVisible(true);
            activarPaneles();
        } else {
            // El usuario ha pulsado "Cancelar"
            JOptionPane.showMessageDialog(null, "No se ha introducido ningún texto.");
        }
    }//GEN-LAST:event_ordenBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!listaProductos.isEmpty()) {
            NuevaOrdenDTO orden = new NuevaOrdenDTO(nombreCliente.getText(), listaProductos, Float.parseFloat(total.getText()), new Date());
            adminOrden.generarOrden(orden);
            //
            for (NuevoProductoDTO listaProducto : listaProductos) {
                inventario.actualizarInventario(listaProducto.getNombre(), listaProducto.getCantidad());
            }
            resetCantidades();
            JOptionPane.showMessageDialog(null, "La orden se ha enviado con éxito");
            listaProductos.clear();
            ordenPanel.setVisible(false);
            cargarDatosTabla(tablaOrden, listaProductos);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void panelSencillaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSencillaMouseClicked
        detallesTorta = new DetallesTorta();

        detallesTorta.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                cargarDatosTabla(tablaOrden, listaProductos);
            }
        });
        detallesTorta.setVisible(true);
    }//GEN-LAST:event_panelSencillaMouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        // TODO add your handling code here:
        Ordenes o = new Ordenes();
        o.adminOrden = new FacadeAdminOrden();
        o.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel36MouseClicked

    private void tablaOrdenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaOrdenMouseClicked
        // TODO add your handling code here:
        eliminarProductoBtn.setEnabled(true);
    }//GEN-LAST:event_tablaOrdenMouseClicked

    private void tablaOrdenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tablaOrdenFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaOrdenFocusLost

    private void eliminarProductoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarProductoBtnActionPerformed
        // TODO add your handling code here:
        eliminarProductoBtn.setEnabled(true);
        int selectedRow = tablaOrden.getSelectedRow();
        if (selectedRow != -1) {
            String prodSelected = tablaOrden.getValueAt(selectedRow, 1).toString();
            System.out.println(prodSelected);

            Object value = tablaOrden.getValueAt(selectedRow, 1);
            String attributeName = value.toString();
            //System.out.println("att:" + attributeName);

            for (Iterator<NuevoProductoDTO> iterator = listaProductos.iterator(); iterator.hasNext();) {
                NuevoProductoDTO item = iterator.next();
                if (item.getNombre().equals(attributeName)) {
                    iterator.remove();
                    break;
                }
            }

            cargarDatosTabla(tablaOrden, listaProductos);
            actualizarTotal(listaProductos);
        } else {
            // no hay fila selecetd
        }

    }//GEN-LAST:event_eliminarProductoBtnActionPerformed

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        VentanaVentas VV = new VentanaVentas(this, true);
        try {
            VentanaPrincipal.getInstance().setVisible(false);
            VV.setVisible(true);
        } catch (FindException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jLabel37MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        // orden compra integracion
        VentanaOrdenCompra voc;
        try {
            voc = new VentanaOrdenCompra();
            voc.setVisible(true);
            dispose();
        } catch (FindException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MouseClicked
        FrmInventariar o = null;
        try {
            o = new FrmInventariar();
        } catch (FindException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        o.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel38MouseClicked

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        HistorialOrdenes o = new HistorialOrdenes();
        o.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel40MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new VentanaPrincipal().setVisible(true);
                } catch (FindException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void mostrarTabla(List<NuevoProductoDTO> productos) {
        DefaultTableModel model = (DefaultTableModel) tablaOrden.getModel();

        model.setRowCount(0);

        for (NuevoProductoDTO producto : productos) {
            Object[] rowData = {producto.getCantidad(), producto.getNombre(), producto.getPrecio()};
            model.addRow(rowData);
        }
    }

    public static void cargarDatosTabla(JTable tabla, List<NuevoProductoDTO> listaProductos) {
        DefaultTableModel modelo = new DefaultTableModel();

        tabla.setModel(modelo);

        modelo.addColumn("cantidad");
        modelo.addColumn("producto");
        modelo.addColumn("precio");

        for (NuevoProductoDTO pr : listaProductos) {
            Object[] filas = {
                pr.getCantidad(),
                pr.getNombre(),
                pr.getPrecio()
            };
            modelo.addRow(filas);
        }
    }

    public int calcularTotal(List<NuevoProductoDTO> listaProductos) {
        int total = 0;
        for (NuevoProductoDTO producto : listaProductos) {
            total += producto.getPrecio() * producto.getCantidad();
        }
        return total;
    }

    public void actualizarTotal(List<NuevoProductoDTO> listaProductos) {
        String totalStr = String.valueOf(calcularTotal(listaProductos));
        total.setText(totalStr);
        subtotal.setText(totalStr);
    }

    public void agregarTorta(TortaDTO torta) {
        listaProductos.add(torta);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aguaPrecioTxt;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnRestarAgua;
    private javax.swing.JButton btnRestarAhogada;
    private javax.swing.JButton btnRestarCoca;
    private javax.swing.JButton btnRestarEspecial;
    private javax.swing.JButton btnRestarFanta;
    private javax.swing.JButton btnRestarHorchata;
    private javax.swing.JButton btnRestarJamaica;
    private javax.swing.JButton btnRestarPepsi;
    private javax.swing.JButton btnRestarSencilla;
    private javax.swing.JButton btnSumarAgua;
    private javax.swing.JButton btnSumarAhogada;
    private javax.swing.JButton btnSumarCoca;
    private javax.swing.JButton btnSumarFanta;
    private javax.swing.JButton btnSumarHorchata;
    private javax.swing.JButton btnSumarJamaica;
    private javax.swing.JButton btnSumarPepsi;
    private javax.swing.JButton btnSumarSencilla;
    private javax.swing.JLabel cantAgua;
    private javax.swing.JLabel cantAhogada;
    private javax.swing.JLabel cantCoca;
    private javax.swing.JLabel cantEspecial;
    private javax.swing.JLabel cantFanta;
    private javax.swing.JLabel cantHorchata;
    private javax.swing.JLabel cantJamaica;
    private javax.swing.JLabel cantPepsi;
    private javax.swing.JLabel cantSencilla;
    private javax.swing.JLabel cocaPrecioTxt;
    private javax.swing.JButton eliminarProductoBtn;
    private javax.swing.JLabel fantaPrecioTxt;
    private javax.swing.JLabel horchataPrecioTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jamaicaPrecioTxt;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel nombreCliente;
    private javax.swing.JButton ordenBtn;
    private javax.swing.JPanel ordenPanel;
    private javax.swing.JPanel panelAgua;
    private javax.swing.JPanel panelAhogada;
    private javax.swing.JPanel panelCoca;
    private javax.swing.JPanel panelEspecial;
    private javax.swing.JPanel panelFanta;
    private javax.swing.JPanel panelHorchata;
    private javax.swing.JPanel panelJamaica;
    private javax.swing.JPanel panelPepsi;
    private javax.swing.JPanel panelSencilla;
    private javax.swing.JLabel pepsiPrecioTxt;
    private javax.swing.JLabel stockAguaTxt;
    private javax.swing.JLabel stockCocaColaTxt;
    private javax.swing.JLabel stockFantaTxt;
    private javax.swing.JLabel stockHorchataTxt;
    private javax.swing.JLabel stockJamaicaTxt;
    private javax.swing.JLabel stockPepsiTxt;
    private javax.swing.JLabel subtotal;
    private javax.swing.JTable tablaOrden;
    private javax.swing.JLabel total;
    private javax.swing.JLabel txtBebidasTitulo;
    private javax.swing.JLabel txtTortasTitulo;
    // End of variables declaration//GEN-END:variables

}
