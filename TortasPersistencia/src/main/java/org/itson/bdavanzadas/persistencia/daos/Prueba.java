/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.daos;

import org.itson.bdavanzadas.persistencia.conexion.Conexion;
import org.itson.bdavanzadas.persistencia.conexion.IConexion;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author Abe
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PersistenciaException {
        IConexion conexion = new Conexion();
        // TODO code application logic 
        OrdenDAO ordenDAO = new OrdenDAO(conexion);
        System.out.println(ordenDAO.NumeroOrdenes());
    }
    
}
