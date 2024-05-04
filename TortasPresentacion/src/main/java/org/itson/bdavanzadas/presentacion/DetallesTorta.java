/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package org.itson.bdavanzadas.presentacion;

import org.itson.bdavanzadas.dtos.NuevoProductoDTO;
import org.itson.bdavanzadas.dtos.TortaDTO;
import java.util.List;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Abe
 */
public class DetallesTorta extends javax.swing.JDialog {

    //private TortaDTO tortaDTO;
    TortaDTO tortaDTO = new TortaDTO("Detalles", 0, 60, "Torta");
    int cantCebolla = 1;
    int cantTomate = 1;
    int cantRepollo = 1;
    int cantMayonesa = 1;
    int cantMostaza = 1;
    int cantJalapeño = 1;
    int cantCarne = 1;

    /**
     * Creates new form DetallesTorta
     *
     * @param tortaDTO
     */
    public DetallesTorta() {
        initComponents();
        cantCebolla = 1;
        cantTomate = 1;
        cantRepollo = 1;
        cantMayonesa = 1;
        cantMostaza = 1;
        cantJalapeño = 1;
        cantCarne = 1;

        cebollaTxt.setText("1");
        tomateTxt.setText("1");
        repolloTxt.setText("1");
        mayonesaTxt.setText("1");
        mostazaTxt.setText("1");
        jalapenoTxt.setText("1");
        carneTxt.setText("1");
                
        cantCarne = 1;
        jLabel35.setText("Torta " + tortaDTO.getNombre());
        txtNota.setLineWrap(true);
        txtNota.setWrapStyleWord(true);

        scrollNota.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollNota.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        scrollNota = new javax.swing.JScrollPane();
        txtNota = new javax.swing.JTextArea();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnMenosPan1 = new javax.swing.JButton();
        btnMasPan1 = new javax.swing.JButton();
        cebollaTxt = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnMenosPan3 = new javax.swing.JButton();
        btnMasPan2 = new javax.swing.JButton();
        tomateTxt = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnMenosPan4 = new javax.swing.JButton();
        btnMasPan3 = new javax.swing.JButton();
        repolloTxt = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        aceptarBtn = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnMenosPan5 = new javax.swing.JButton();
        btnMasPan5 = new javax.swing.JButton();
        mostazaTxt = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnMenosPan6 = new javax.swing.JButton();
        btnMasPan6 = new javax.swing.JButton();
        jalapenoTxt = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btnMenosPan7 = new javax.swing.JButton();
        btnMasPan7 = new javax.swing.JButton();
        carneTxt = new javax.swing.JLabel();
        btnMenosPan2 = new javax.swing.JButton();
        mayonesaTxt = new javax.swing.JLabel();
        btnMasPan4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtNota.setColumns(20);
        txtNota.setRows(5);
        txtNota.setPreferredSize(new java.awt.Dimension(316, 117));
        scrollNota.setViewportView(txtNota);

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel35.setText("Nombre torta");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setText("Tomate");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel38.setText("Cebolla");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel39.setText("Repollo");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel40.setText("Añadir una nota");

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMenosPan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BotonMenos2.png"))); // NOI18N
        btnMenosPan1.setBorder(null);
        btnMenosPan1.setBorderPainted(false);
        btnMenosPan1.setFocusPainted(false);
        btnMenosPan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosPan1ActionPerformed(evt);
            }
        });
        jPanel3.add(btnMenosPan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 21, 21));

        btnMasPan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BotonMas2.png"))); // NOI18N
        btnMasPan1.setBorder(null);
        btnMasPan1.setOpaque(true);
        btnMasPan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasPan1ActionPerformed(evt);
            }
        });
        jPanel3.add(btnMasPan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 0, 21, 21));

        cebollaTxt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cebollaTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoBarra.png"))); // NOI18N
        cebollaTxt.setText("1");
        cebollaTxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(cebollaTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 21));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMenosPan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BotonMenos2.png"))); // NOI18N
        btnMenosPan3.setBorder(null);
        btnMenosPan3.setBorderPainted(false);
        btnMenosPan3.setFocusPainted(false);
        btnMenosPan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosPan3ActionPerformed(evt);
            }
        });
        jPanel5.add(btnMenosPan3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 21, 21));

        btnMasPan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BotonMas2.png"))); // NOI18N
        btnMasPan2.setBorder(null);
        btnMasPan2.setOpaque(true);
        btnMasPan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasPan2ActionPerformed(evt);
            }
        });
        jPanel5.add(btnMasPan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 0, 21, 21));

        tomateTxt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tomateTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoBarra.png"))); // NOI18N
        tomateTxt.setText("1");
        tomateTxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel5.add(tomateTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 21));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMenosPan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BotonMenos2.png"))); // NOI18N
        btnMenosPan4.setBorder(null);
        btnMenosPan4.setBorderPainted(false);
        btnMenosPan4.setFocusPainted(false);
        btnMenosPan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosPan4ActionPerformed(evt);
            }
        });
        jPanel6.add(btnMenosPan4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 21, 21));

        btnMasPan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BotonMas2.png"))); // NOI18N
        btnMasPan3.setBorder(null);
        btnMasPan3.setOpaque(true);
        btnMasPan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasPan3ActionPerformed(evt);
            }
        });
        jPanel6.add(btnMasPan3, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 0, 21, 21));

        repolloTxt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        repolloTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoBarra.png"))); // NOI18N
        repolloTxt.setText("1");
        repolloTxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(repolloTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 21));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/SencillaDetalle.png"))); // NOI18N

        aceptarBtn.setBackground(new java.awt.Color(66, 107, 105));
        aceptarBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        aceptarBtn.setForeground(new java.awt.Color(255, 255, 255));
        aceptarBtn.setText("Aceptar");
        aceptarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarBtnActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel41.setText("Mayonesa");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel42.setText("Mostaza");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel43.setText("Jalapeño");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel44.setText("Carne");

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMenosPan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BotonMenos2.png"))); // NOI18N
        btnMenosPan5.setBorder(null);
        btnMenosPan5.setBorderPainted(false);
        btnMenosPan5.setFocusPainted(false);
        btnMenosPan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosPan5ActionPerformed(evt);
            }
        });
        jPanel7.add(btnMenosPan5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 21, 21));

        btnMasPan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BotonMas2.png"))); // NOI18N
        btnMasPan5.setBorder(null);
        btnMasPan5.setOpaque(true);
        btnMasPan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasPan5ActionPerformed(evt);
            }
        });
        jPanel7.add(btnMasPan5, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 0, 21, 21));

        mostazaTxt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        mostazaTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoBarra.png"))); // NOI18N
        mostazaTxt.setText("1");
        mostazaTxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel7.add(mostazaTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 21));

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMenosPan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BotonMenos2.png"))); // NOI18N
        btnMenosPan6.setBorder(null);
        btnMenosPan6.setBorderPainted(false);
        btnMenosPan6.setFocusPainted(false);
        btnMenosPan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosPan6ActionPerformed(evt);
            }
        });
        jPanel8.add(btnMenosPan6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 21, 21));

        btnMasPan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BotonMas2.png"))); // NOI18N
        btnMasPan6.setBorder(null);
        btnMasPan6.setOpaque(true);
        btnMasPan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasPan6ActionPerformed(evt);
            }
        });
        jPanel8.add(btnMasPan6, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 0, 21, 21));

        jalapenoTxt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jalapenoTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoBarra.png"))); // NOI18N
        jalapenoTxt.setText("1");
        jalapenoTxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel8.add(jalapenoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 21));

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMenosPan7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BotonMenos2.png"))); // NOI18N
        btnMenosPan7.setBorder(null);
        btnMenosPan7.setBorderPainted(false);
        btnMenosPan7.setFocusPainted(false);
        btnMenosPan7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosPan7ActionPerformed(evt);
            }
        });
        jPanel9.add(btnMenosPan7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 21, 21));

        btnMasPan7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BotonMas2.png"))); // NOI18N
        btnMasPan7.setBorder(null);
        btnMasPan7.setOpaque(true);
        btnMasPan7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasPan7ActionPerformed(evt);
            }
        });
        jPanel9.add(btnMasPan7, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 0, 21, 21));

        carneTxt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        carneTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoBarra.png"))); // NOI18N
        carneTxt.setText("1");
        carneTxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel9.add(carneTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 21));

        btnMenosPan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BotonMenos2.png"))); // NOI18N
        btnMenosPan2.setBorder(null);
        btnMenosPan2.setBorderPainted(false);
        btnMenosPan2.setFocusPainted(false);
        btnMenosPan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosPan2ActionPerformed(evt);
            }
        });

        mayonesaTxt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        mayonesaTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoBarra.png"))); // NOI18N
        mayonesaTxt.setText("1");
        mayonesaTxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnMasPan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BotonMas2.png"))); // NOI18N
        btnMasPan4.setBorder(null);
        btnMasPan4.setOpaque(true);
        btnMasPan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasPan4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(scrollNota, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(aceptarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel37)
                                                    .addComponent(jLabel38))
                                                .addGap(39, 39, 39)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel41)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(btnMenosPan2)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(45, 45, 45)
                                                        .addComponent(btnMasPan4))
                                                    .addComponent(mayonesaTxt))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel39))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel42)
                                            .addComponent(jLabel43)
                                            .addComponent(jLabel44))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(102, 102, 102)
                        .addComponent(aceptarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMenosPan2)
                                    .addComponent(btnMasPan4)
                                    .addComponent(mayonesaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollNota, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 440));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMasPan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasPan1ActionPerformed
        // TODO add your handling code here:
        cantCebolla++;
        cebollaTxt.setText(String.valueOf(cantCebolla));
    }//GEN-LAST:event_btnMasPan1ActionPerformed

    private void btnMasPan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasPan2ActionPerformed
        // TODO add your handling code here:
        cantTomate++;
        tomateTxt.setText(String.valueOf(cantTomate));
    }//GEN-LAST:event_btnMasPan2ActionPerformed

    private void btnMasPan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasPan3ActionPerformed
        // TODO add your handling code here:c
        cantRepollo++;
        repolloTxt.setText(String.valueOf(cantRepollo));
    }//GEN-LAST:event_btnMasPan3ActionPerformed

    private void btnMasPan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasPan4ActionPerformed
        // TODO add your handling code here:
        cantMayonesa++;
        mayonesaTxt.setText(String.valueOf(cantMayonesa));
    }//GEN-LAST:event_btnMasPan4ActionPerformed

    private void btnMasPan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasPan5ActionPerformed
        // TODO add your handling code here:
        cantMostaza++;
        mostazaTxt.setText(String.valueOf(cantMostaza));

    }//GEN-LAST:event_btnMasPan5ActionPerformed

    private void btnMasPan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasPan6ActionPerformed
        // TODO add your handling code here:
        cantJalapeño++;
        jalapenoTxt.setText(String.valueOf(cantJalapeño));
    }//GEN-LAST:event_btnMasPan6ActionPerformed

    private void btnMasPan7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasPan7ActionPerformed
        // TODO add your handling code here:
        cantCarne++;
        carneTxt.setText(String.valueOf(cantCarne));
    }//GEN-LAST:event_btnMasPan7ActionPerformed

    private void btnMenosPan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosPan1ActionPerformed
        // TODO add your handling code here:
        if (cantCebolla > 0) {
            cantCebolla--;
        }
        cebollaTxt.setText(String.valueOf(cantCebolla));
    }//GEN-LAST:event_btnMenosPan1ActionPerformed

    private void btnMenosPan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosPan3ActionPerformed
        // TODO add your handling code here:
        if (cantTomate > 0) {
            cantTomate--;
        }
        tomateTxt.setText(String.valueOf(cantTomate));
    }//GEN-LAST:event_btnMenosPan3ActionPerformed

    private void btnMenosPan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosPan4ActionPerformed
        // TODO add your handling code here:
        if (cantRepollo > 0) {
            cantRepollo--;
        }
        repolloTxt.setText(String.valueOf(cantRepollo));
    }//GEN-LAST:event_btnMenosPan4ActionPerformed

    private void btnMenosPan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosPan2ActionPerformed
        // TODO add your handling code here:
        if (cantMayonesa > 0) {
            cantMayonesa--;
        }
        mayonesaTxt.setText(String.valueOf(cantMayonesa));
    }//GEN-LAST:event_btnMenosPan2ActionPerformed

    private void btnMenosPan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosPan5ActionPerformed
        // TODO add your handling code here:
        if (cantMostaza > 0) {
            cantMostaza--;
        }
        mostazaTxt.setText(String.valueOf(cantMostaza));
    }//GEN-LAST:event_btnMenosPan5ActionPerformed

    private void btnMenosPan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosPan6ActionPerformed
        // TODO add your handling code here:
        if (cantJalapeño > 0) {
            cantJalapeño--;
        }
        jalapenoTxt.setText(String.valueOf(cantJalapeño));
    }//GEN-LAST:event_btnMenosPan6ActionPerformed

    private void btnMenosPan7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosPan7ActionPerformed
        // TODO add your handling code here:
        if (cantCarne > 0) {
            cantCarne--;
        }
        carneTxt.setText(String.valueOf(cantCarne));
    }//GEN-LAST:event_btnMenosPan7ActionPerformed

    private void aceptarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarBtnActionPerformed
        // TODO add your handling code here:
        tortaDTO.setCantidad(1);
        tortaDTO.setCantCarne(cantCarne);
        tortaDTO.setCantCebolla(cantCebolla);
        tortaDTO.setCantJalapeño(cantJalapeño);
        tortaDTO.setCantMayonesa(cantMayonesa);
        tortaDTO.setCantMostaza(cantMostaza);
        tortaDTO.setCantTomate(cantTomate);
        tortaDTO.setCantRepollo(cantRepollo);
        tortaDTO.setDescripcion(txtNota.getText());
        agregarProductoALista();

        dispose();
    }//GEN-LAST:event_aceptarBtnActionPerformed

    public void agregarProductoALista() {
        VentanaPrincipal ventanaPrincipal = VentanaPrincipal.getInstance();
        List<NuevoProductoDTO> listaProductos = ventanaPrincipal.getListaProductos();

        for (NuevoProductoDTO prod : listaProductos) {
            System.out.println(prod.getNombre());
        }

        if (listaProductos.isEmpty()) {
            //System.out.println("no hay lista");
        }

        ventanaPrincipal.agregarProducto(tortaDTO);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarBtn;
    private javax.swing.JButton btnMasPan1;
    private javax.swing.JButton btnMasPan2;
    private javax.swing.JButton btnMasPan3;
    private javax.swing.JButton btnMasPan4;
    private javax.swing.JButton btnMasPan5;
    private javax.swing.JButton btnMasPan6;
    private javax.swing.JButton btnMasPan7;
    private javax.swing.JButton btnMenosPan1;
    private javax.swing.JButton btnMenosPan2;
    private javax.swing.JButton btnMenosPan3;
    private javax.swing.JButton btnMenosPan4;
    private javax.swing.JButton btnMenosPan5;
    private javax.swing.JButton btnMenosPan6;
    private javax.swing.JButton btnMenosPan7;
    private javax.swing.JLabel carneTxt;
    private javax.swing.JLabel cebollaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jalapenoTxt;
    private javax.swing.JLabel mayonesaTxt;
    private javax.swing.JLabel mostazaTxt;
    private javax.swing.JLabel repolloTxt;
    private javax.swing.JScrollPane scrollNota;
    private javax.swing.JLabel tomateTxt;
    private javax.swing.JTextArea txtNota;
    // End of variables declaration//GEN-END:variables
}