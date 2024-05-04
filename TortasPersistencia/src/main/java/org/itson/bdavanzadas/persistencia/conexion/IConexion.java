/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.bdavanzadas.persistencia.conexion;

import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Abe
 */
public interface IConexion {

    public MongoDatabase obtenerBaseDatos();
}
