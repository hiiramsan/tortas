/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.bdavanzadas.adminTarjeta;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Abe
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // Ruta de la imagen
        // Ruta de la imagen
        String rutaImagen = "src\\main\\resources\\imagenes\\PagoAprobado.gif";

        // Crear un ImageIcon utilizando la ruta de la imagen
        ImageIcon imagenIcon = new ImageIcon(rutaImagen);

        // Crear un JLabel para mostrar la imagen
        JLabel label = new JLabel(imagenIcon);

        // Crear un JFrame para mostrar el JLabel con la imagen
        JFrame frame = new JFrame();
        frame.add(label);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
