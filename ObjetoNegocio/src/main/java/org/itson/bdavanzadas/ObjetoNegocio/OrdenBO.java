/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.ObjetoNegocio;

import conexion.Conexion;
import conexion.IConexion;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import org.itson.bdavanzadas.persistencia.daos.IOrdenDAO;
import org.itson.bdavanzadas.persistencia.daos.OrdenDAO;

/**
 *
 * @author Abe
 */
public class OrdenBO {
    private IOrdenDAO ordenDAO;
    
    public OrdenBO() {
        IConexion conexion = new Conexion();
        ordenDAO = new OrdenDAO(conexion);
    }
    
    public void agregarOrden(NuevaOrdenDTO nuevaOrdenDTO){
        ordenDAO.registrarOrden(nuevaOrdenDTO);
    }
}
