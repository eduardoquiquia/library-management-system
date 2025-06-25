package com.biblioteca.util;

import com.biblioteca.conexion.ConexionBD;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

public class ReporteUtil {

    public static void mostrarReporte(String ruta, Map<String, Object> parametros) {
        try {
            InputStream archivo = ReporteUtil.class.getClassLoader().getResourceAsStream(ruta);

            if (archivo == null) {
                throw new RuntimeException("No se encontró el archivo del reporte: " + ruta);
            }

            Connection conexion = ConexionBD.obtenerConexion(); // Asegúrate de tener esta clase

            JasperPrint print = JasperFillManager.fillReport(archivo, parametros, conexion);
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

