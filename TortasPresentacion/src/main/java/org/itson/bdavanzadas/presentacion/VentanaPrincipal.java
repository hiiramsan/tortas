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
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.itson.bdavanzadas.persistencia.componentes.Fecha;

/**
 * @author Abel Eduardo Sanchez Guerrero | 00000
 * @author Eduardo Talavera Ramos | 00000245244
 * @author Carlos Hiram Sanchez Menseses | 00000246787
 * @author Ana Cristina Castro Noriega | 00000
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private static VentanaPrincipal instance;

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
    NuevoProductoDTO coca = new NuevoProductoDTO("Coca-cola", 0, 25, "Bebida");
    NuevoProductoDTO pepsi = new NuevoProductoDTO("Pepsi", 0, 22, "Bebida");
    NuevoProductoDTO fanta = new NuevoProductoDTO("Fanta", 0, 22, "Bebida");
    NuevoProductoDTO jamaica = new NuevoProductoDTO("Jamaica", 0, 20, "Bebida");
    NuevoProductoDTO horchata = new NuevoProductoDTO("Horchata", 0, 20, "Bebida");
    NuevoProductoDTO agua = new NuevoProductoDTO("Agua", 0, 15, "Bebida");

    Map<String, Integer> ingredientes = new HashMap<>();
    private static DetallesTorta detallesTorta;
    private static List<NuevoProductoDTO> listaProductos = new ArrayList<>();
    private static List<NuevaOrdenDTO> listaOrden = new ArrayList<>();

    IInventario inventario = new FacadeAdminInventario();
    IAdminOrden adminOrden = new FacadeAdminOrden();

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
     */
    public VentanaPrincipal() {
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

        actualizarStock();
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

    public static VentanaPrincipal getInstance() {
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
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        sumarSencilla = new javax.swing.JButton();
        restarSencilla = new javax.swing.JButton();
        cantSencilla = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        cantEspecial = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jButton19 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        cantAhogada = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jButton28 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        cantFanta = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        stockFantaTxt = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        cantCoca = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        stockCocaColaTxt = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        cantPepsi = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        stockPepsiTxt = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jButton37 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        masHorchataBoton = new javax.swing.JButton();
        menosHorchataBoton = new javax.swing.JButton();
        cantHorchata = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        stockHorchataTxt = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jButton34 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        masJamaicaBoton = new javax.swing.JButton();
        menosJamaicaBoton = new javax.swing.JButton();
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
        jPanel17 = new javax.swing.JPanel();
        jButton40 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        masAguaBoton = new javax.swing.JButton();
        menosAguaBoton = new javax.swing.JButton();
        cantAgua = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        stockAguaTxt = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jButton31 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jButton32 = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jButton33 = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        ordenBtn = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblHora = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/IconoComida.png"))); // NOI18N

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icono1.png"))); // NOI18N
        jLabel36.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                .addContainerGap(519, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(164, 180, 148));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel3.setText("Bebidas");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 303, 89, -1));

        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.setPreferredSize(new java.awt.Dimension(196, 118));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
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

        sumarSencilla.setBackground(new java.awt.Color(36, 123, 160));
        sumarSencilla.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sumarSencilla.setForeground(new java.awt.Color(255, 255, 255));
        sumarSencilla.setText("+");
        sumarSencilla.setBorder(null);
        sumarSencilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sumarSencillaActionPerformed(evt);
            }
        });

        restarSencilla.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        restarSencilla.setText("-");
        restarSencilla.setBorder(null);
        restarSencilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restarSencillaActionPerformed(evt);
            }
        });

        cantSencilla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantSencilla.setText("0");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel8.setText("Sencilla");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(restarSencilla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantSencilla, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sumarSencilla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sumarSencilla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(restarSencilla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantSencilla)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jPanel6.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 173, -1, -1));

        jPanel9.setPreferredSize(new java.awt.Dimension(196, 118));

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

        jButton18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton18.setText("-");
        jButton18.setBorder(null);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        cantEspecial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantEspecial.setText("0");

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel20.setText("Especial");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton16)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantEspecial, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantEspecial)
                    .addComponent(jLabel18))
                .addContainerGap())
        );

        jPanel6.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 173, -1, -1));

        jPanel10.setPreferredSize(new java.awt.Dimension(196, 118));

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

        jButton20.setBackground(new java.awt.Color(36, 123, 160));
        jButton20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("+");
        jButton20.setBorder(null);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton21.setText("-");
        jButton21.setBorder(null);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        cantAhogada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantAhogada.setText("0");

        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel23.setText("Ahogada");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton19)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantAhogada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantAhogada)
                    .addComponent(jLabel21))
                .addContainerGap())
        );

        jPanel6.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 173, -1, -1));

        jPanel13.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fanta.png"))); // NOI18N
        jButton28.setBorder(null);
        jButton28.setOpaque(true);
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel31.setText("$22");

        jButton29.setBackground(new java.awt.Color(36, 123, 160));
        jButton29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton29.setForeground(new java.awt.Color(255, 255, 255));
        jButton29.setText("+");
        jButton29.setBorder(null);
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton30.setText("-");
        jButton30.setBorder(null);
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        cantFanta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantFanta.setText("0");

        jLabel33.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel33.setText("Fanta");

        stockFantaTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockFantaTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockFantaTxt.setText("Stock: ");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton28)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantFanta, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(stockFantaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stockFantaTxt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantFanta)
                    .addComponent(jLabel31))
                .addContainerGap())
        );

        jPanel6.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 344, -1, -1));

        jPanel11.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/coca.png"))); // NOI18N
        jButton22.setBorder(null);
        jButton22.setOpaque(true);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setText("$25");

        jButton23.setBackground(new java.awt.Color(36, 123, 160));
        jButton23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton23.setForeground(new java.awt.Color(255, 255, 255));
        jButton23.setText("+");
        jButton23.setBorder(null);
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton24.setText("-");
        jButton24.setBorder(null);
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        cantCoca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantCoca.setText("0");

        jLabel27.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel27.setText("Coca cola");

        stockCocaColaTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockCocaColaTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockCocaColaTxt.setText("Stock: ");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton22)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantCoca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(stockCocaColaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stockCocaColaTxt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantCoca)
                    .addComponent(jLabel25))
                .addContainerGap())
        );

        jPanel6.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 344, -1, -1));

        jPanel12.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pepsi.png"))); // NOI18N
        jButton25.setBorder(null);
        jButton25.setOpaque(true);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel28.setText("$22");

        jButton26.setBackground(new java.awt.Color(36, 123, 160));
        jButton26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setText("+");
        jButton26.setBorder(null);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton27.setText("-");
        jButton27.setBorder(null);
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        cantPepsi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantPepsi.setText("0");

        jLabel30.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel30.setText("Pepsi");

        stockPepsiTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockPepsiTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockPepsiTxt.setText("Stock: ");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton25)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantPepsi, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(stockPepsiTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(18, 18, 18)
                        .addComponent(stockPepsiTxt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantPepsi)
                    .addComponent(jLabel28))
                .addContainerGap())
        );

        jPanel6.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 344, -1, -1));

        jPanel16.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/horchata.png"))); // NOI18N
        jButton37.setBorder(null);
        jButton37.setOpaque(true);
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel40.setText("$20");

        masHorchataBoton.setBackground(new java.awt.Color(36, 123, 160));
        masHorchataBoton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        masHorchataBoton.setForeground(new java.awt.Color(255, 255, 255));
        masHorchataBoton.setText("+");
        masHorchataBoton.setBorder(null);
        masHorchataBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masHorchataBotonActionPerformed(evt);
            }
        });

        menosHorchataBoton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        menosHorchataBoton.setText("-");
        menosHorchataBoton.setBorder(null);
        menosHorchataBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menosHorchataBotonActionPerformed(evt);
            }
        });

        cantHorchata.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantHorchata.setText("0");

        jLabel42.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel42.setText("Horchata");

        stockHorchataTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockHorchataTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockHorchataTxt.setText("Stock: ");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton37)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(menosHorchataBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantHorchata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(masHorchataBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stockHorchataTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stockHorchataTxt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(masHorchataBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menosHorchataBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantHorchata)
                    .addComponent(jLabel40))
                .addContainerGap())
        );

        jPanel6.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 474, -1, -1));

        jPanel15.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/jamaica.png"))); // NOI18N
        jButton34.setBorder(null);
        jButton34.setOpaque(true);
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setText("$20");

        masJamaicaBoton.setBackground(new java.awt.Color(36, 123, 160));
        masJamaicaBoton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        masJamaicaBoton.setForeground(new java.awt.Color(255, 255, 255));
        masJamaicaBoton.setText("+");
        masJamaicaBoton.setBorder(null);
        masJamaicaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masJamaicaBotonActionPerformed(evt);
            }
        });

        menosJamaicaBoton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        menosJamaicaBoton.setText("-");
        menosJamaicaBoton.setBorder(null);
        menosJamaicaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menosJamaicaBotonActionPerformed(evt);
            }
        });

        cantJamaica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantJamaica.setText("0");

        jLabel39.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel39.setText("Jamaica");

        stockJamaicaTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockJamaicaTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockJamaicaTxt.setText("Stock: ");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton34)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(menosJamaicaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantJamaica, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(masJamaicaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stockJamaicaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stockJamaicaTxt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(masJamaicaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menosJamaicaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantJamaica)
                    .addComponent(jLabel37))
                .addContainerGap())
        );

        jPanel6.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 474, -1, -1));

        jButton1.setBackground(new java.awt.Color(36, 123, 160));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Continuar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
        jScrollPane1.setViewportView(tablaOrden);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Orden: ");

        btnBorrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBorrar.setText("x");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        jLabel4.setText("Sub total");

        jLabel5.setText("IVA");

        jLabel43.setText("Total");

        subtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        subtotal.setText("$0");

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel45.setText("$0");

        total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        total.setText("$0");

        nombreCliente.setText("nombre del cliente");

        javax.swing.GroupLayout ordenPanelLayout = new javax.swing.GroupLayout(ordenPanel);
        ordenPanel.setLayout(ordenPanelLayout);
        ordenPanelLayout.setHorizontalGroup(
            ordenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ordenPanelLayout.createSequentialGroup()
                .addGroup(ordenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ordenPanelLayout.createSequentialGroup()
                        .addContainerGap(8, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ordenPanelLayout.createSequentialGroup()
                        .addGroup(ordenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ordenPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(ordenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(ordenPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nombreCliente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(ordenPanelLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(ordenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ordenPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ordenPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ordenPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ordenPanelLayout.setVerticalGroup(
            ordenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ordenPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(ordenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ordenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(subtotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ordenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ordenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(total))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jPanel6.add(ordenPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, -1, 357));

        jPanel17.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agua.png"))); // NOI18N
        jButton40.setBorder(null);
        jButton40.setOpaque(true);
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel47.setText("$15");

        masAguaBoton.setBackground(new java.awt.Color(36, 123, 160));
        masAguaBoton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        masAguaBoton.setForeground(new java.awt.Color(255, 255, 255));
        masAguaBoton.setText("+");
        masAguaBoton.setBorder(null);
        masAguaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masAguaBotonActionPerformed(evt);
            }
        });

        menosAguaBoton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        menosAguaBoton.setText("-");
        menosAguaBoton.setBorder(null);
        menosAguaBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menosAguaBotonActionPerformed(evt);
            }
        });

        cantAgua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantAgua.setText("0");

        jLabel49.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel49.setText("Agua");

        stockAguaTxt.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        stockAguaTxt.setForeground(new java.awt.Color(0, 153, 0));
        stockAguaTxt.setText("Stock: ");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton40)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(menosAguaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantAgua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(masAguaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stockAguaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stockAguaTxt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(masAguaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menosAguaBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantAgua)
                    .addComponent(jLabel47))
                .addContainerGap())
        );

        jPanel6.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 474, -1, -1));

        jLabel50.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel50.setText("Tortas");
        jPanel6.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 132, 74, -1));

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

        jPanel20.setPreferredSize(new java.awt.Dimension(196, 118));

        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/coca.png"))); // NOI18N
        jButton33.setBorder(null);
        jButton33.setOpaque(true);
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel55.setText("6 opciones");

        jLabel56.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel56.setText("Extras");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton33)
                .addGap(18, 18, 18)
                .addComponent(jLabel55)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                    .addContainerGap(96, Short.MAX_VALUE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31)))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel55)
                    .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel20Layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(jLabel56)
                    .addContainerGap(41, Short.MAX_VALUE)))
        );

        jPanel6.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 15, -1, 91));

        ordenBtn.setText("Tomar orden");
        ordenBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordenBtnActionPerformed(evt);
            }
        });
        jPanel6.add(ordenBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 254, 39));

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
        DetallesTorta DT = new DetallesTorta(tortaSencilla);
        DT.setVisible(true);
        cargarDatosTabla(tablaOrden, listaProductos);
     }//GEN-LAST:event_jButton4ActionPerformed

    private void sumarSencillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sumarSencillaActionPerformed
        if (!listaProductos.contains(tortaSencilla)) {
            listaProductos.add(tortaSencilla);
        }

        cantidadSencilla++;
        tortaSencilla.setCantidad(cantidadSencilla);
        String cSencilla = String.valueOf(cantidadSencilla);
        cantSencilla.setText(cSencilla);
        cargarDatosTabla(tablaOrden, listaProductos);
        actualizarTotal(listaProductos);
    }//GEN-LAST:event_sumarSencillaActionPerformed

    private void restarSencillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restarSencillaActionPerformed
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
    }//GEN-LAST:event_restarSencillaActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        DetallesTorta DT = new DetallesTorta(tortaEspecial);
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

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed

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
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        if (!listaProductos.contains(ahogada)) {
            listaProductos.add(ahogada);
        }

        cantidadAhogada++;
        ahogada.setCantidad(cantidadAhogada);
        String cAhogada = String.valueOf(cantidadAhogada);
        cantAhogada.setText(cAhogada);
        cargarDatosTabla(tablaOrden, listaProductos);
        actualizarTotal(listaProductos);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
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
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
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
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
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
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed

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

    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
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
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed

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


    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed

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
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34ActionPerformed

    private void masJamaicaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masJamaicaBotonActionPerformed
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
    }//GEN-LAST:event_masJamaicaBotonActionPerformed

    private void menosJamaicaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menosJamaicaBotonActionPerformed
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
    }//GEN-LAST:event_menosJamaicaBotonActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton37ActionPerformed

    private void masHorchataBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masHorchataBotonActionPerformed
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
    }//GEN-LAST:event_masHorchataBotonActionPerformed

    private void menosHorchataBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menosHorchataBotonActionPerformed
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
    }//GEN-LAST:event_menosHorchataBotonActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        listaProductos.clear();
        ordenPanel.setVisible(false);
        cargarDatosTabla(tablaOrden, listaProductos);
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton40ActionPerformed

    private void masAguaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masAguaBotonActionPerformed
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
    }//GEN-LAST:event_masAguaBotonActionPerformed

    private void menosAguaBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menosAguaBotonActionPerformed
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
    }//GEN-LAST:event_menosAguaBotonActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton33ActionPerformed

    private void ordenBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordenBtnActionPerformed
        String nombreDelCliente = JOptionPane.showInputDialog(null, "Nombre del cliente:");

        // Comprueba si el usuario ha pulsado "Aceptar" o "Cancelar"
        if (nombreDelCliente != null) {
            nombreCliente.setText(nombreDelCliente);
            ordenPanel.setVisible(true);
        } else {
            // El usuario ha pulsado "Cancelar"
            JOptionPane.showMessageDialog(null, "No se ha introducido ningún texto.");
        }
    }//GEN-LAST:event_ordenBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        NuevaOrdenDTO orden = new NuevaOrdenDTO(nombreCliente.getText(), listaProductos, Float.parseFloat(total.getText()), new Fecha());
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
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        detallesTorta = new DetallesTorta(tortaSencilla);

        detallesTorta.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                cargarDatosTabla(tablaOrden, listaProductos);
            }
        });
        detallesTorta.setVisible(true);
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        // TODO add your handling code here:
        Ordenes o = new Ordenes(adminOrden.obtenerOrdenes());
        o.setVisible(true);
    }//GEN-LAST:event_jLabel36MouseClicked

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
                new VentanaPrincipal().setVisible(true);
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
    private javax.swing.JButton btnBorrar;
    private javax.swing.JLabel cantAgua;
    private javax.swing.JLabel cantAhogada;
    private javax.swing.JLabel cantCoca;
    private javax.swing.JLabel cantEspecial;
    private javax.swing.JLabel cantFanta;
    private javax.swing.JLabel cantHorchata;
    private javax.swing.JLabel cantJamaica;
    private javax.swing.JLabel cantPepsi;
    private javax.swing.JLabel cantSencilla;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHora;
    private javax.swing.JButton masAguaBoton;
    private javax.swing.JButton masHorchataBoton;
    private javax.swing.JButton masJamaicaBoton;
    private javax.swing.JButton menosAguaBoton;
    private javax.swing.JButton menosHorchataBoton;
    private javax.swing.JButton menosJamaicaBoton;
    private javax.swing.JLabel nombreCliente;
    private javax.swing.JButton ordenBtn;
    private javax.swing.JPanel ordenPanel;
    private javax.swing.JButton restarSencilla;
    private javax.swing.JLabel stockAguaTxt;
    private javax.swing.JLabel stockCocaColaTxt;
    private javax.swing.JLabel stockFantaTxt;
    private javax.swing.JLabel stockHorchataTxt;
    private javax.swing.JLabel stockJamaicaTxt;
    private javax.swing.JLabel stockPepsiTxt;
    private javax.swing.JLabel subtotal;
    private javax.swing.JButton sumarSencilla;
    private javax.swing.JTable tablaOrden;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables

}
