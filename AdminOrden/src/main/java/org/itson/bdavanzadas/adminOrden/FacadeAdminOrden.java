/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.bdavanzadas.adminOrden;

import java.util.LinkedList;
import org.itson.bdavanzadas.dtos.NuevaOrdenDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.itson.bdavanzadas.objetosNegocio.excepction.NegocioException;
import org.itson.bdavanzadas.persistencia.entidades.Orden;
import org.itson.bdavanzadas.persistencia.exception.FindException;
import org.itson.bdavanzadas.persistencia.exception.PersistenciaException;

/**
 *
 * @author carlo
 */
public class FacadeAdminOrden implements IAdminOrden {

    OrdenControl ordenControl = new OrdenControl();

    @Override
    public void generarOrden(NuevaOrdenDTO nuevaOrden) {
        try {
            ordenControl.generarOrden(nuevaOrden);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FacadeAdminOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<NuevaOrdenDTO> obtenerOrdenes() {
        try {
            return ordenControl.obtenerOrdenes();
        } catch (FindException ex) {
            Logger.getLogger(FacadeAdminOrden.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NegocioException ex) {
            Logger.getLogger(FacadeAdminOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void cancelarOrden(int index) {
        ordenControl.cancelarOrden(index);
    }

    @Override
    public void completarOrden(int index) {
        ordenControl.completarOrden(index);
    }

    @Override
    public void especificacionesOrden(NuevaOrdenDTO orden) {
        ordenControl.especificacionesOrden(orden);
    }

    @Override
    public Double obtenerPrecioPorNombre(String nombreProducto) {
        try {
            return ordenControl.obtenerPrecioPorNombre(nombreProducto);
        } catch (FindException ex) {
            Logger.getLogger(FacadeAdminOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<NuevaOrdenDTO> obtenerOrdenesCompletadas() {
        try {
            List<NuevaOrdenDTO> ordenesDTO = new LinkedList<>();
            ordenesDTO = ordenControl.obtenerOrdenesCompletadas();
            return ordenesDTO;

        } catch (NegocioException ex) {
            Logger.getLogger(FacadeAdminOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public NuevaOrdenDTO cancelarOrden(NuevaOrdenDTO ordenDTO) {
        try {
            NuevaOrdenDTO ordenCancelada = ordenControl.cancelarOrden(ordenDTO);
            return ordenCancelada;

        } catch (NegocioException ex) {
            Logger.getLogger(FacadeAdminOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void cambiarEstadoCancelada(int numeroOrden) {
        ordenControl.cancelarOrden(numeroOrden);
    }

    @Override
    public void cambiarEstadoCompletada(int numeroOrden) {
        ordenControl.completarOrden(numeroOrden);
    }

    @Override
    public List<Document> obtenerOrdenesPorFechaAscendente() {
        try {
            return ordenControl.obtenerOrdenesPorFechaAscendente();
        } catch (FindException ex) {
            Logger.getLogger(FacadeAdminOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Document> obtenerOrdenesPendientesPorCantidadTortas() {
        try {
            return ordenControl.obtenerOrdenesPendientesPorCantidadTortas();
        } catch (FindException ex) {
            Logger.getLogger(FacadeAdminOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Document> obtenerOrdenesPendientes() {
        try {
            return ordenControl.obtenerOrdenesPendientes();
        } catch (FindException ex) {
            Logger.getLogger(FacadeAdminOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Orden obtenerOrdenPorNumeroOrden(Integer numOrden) {
        try {
            return ordenControl.obtenerOrdenPorNumeroOrden(numOrden);
        } catch (NegocioException ex) {
            Logger.getLogger(FacadeAdminOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void completadaOrden(NuevaOrdenDTO ordenDTO) {
        try {
            ordenControl.ordenCompletada(ordenDTO);
        } catch (NegocioException ex) {
            Logger.getLogger(FacadeAdminOrden.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
