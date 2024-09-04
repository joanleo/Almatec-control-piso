package com.almatec.controlpiso.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.webservices.generated.Final;
import com.almatec.controlpiso.erp.webservices.generated.Inicial;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;
import com.almatec.controlpiso.security.entities.Usuario;
import com.almatec.controlpiso.security.services.UsuarioService;

@Service
public class UtilitiesApp {
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final String XML_LOG_FOLDER = "C:/IntegrApps/Almatec/xml_logs";
	private static final String FOLDER_PATH = "C:/IntegrApps/Almatec/archivos_planos_logs";

	public Usuario obtenerUsuarioAtenticado() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return usuarioService.ObtenerUsuarioPorNombreUsuario(authentication.getName());
	}
	
	public String obtenerFechaFormateada() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
        return formatoFecha.format(new Date());
    }
	
	public String obtenerFechaFormateada(Date fecha) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");
		return formatoFecha.format(fecha);
	}
	
	public void crearArchivoPlano(List<Conector> datos, String nombre, Integer cia) throws IOException {
        Path folderPath = Paths.get(FOLDER_PATH);
        Files.createDirectories(folderPath);

        String fileName = nombre + ".txt";
        Path filePath = folderPath.resolve(fileName);

        try (var writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            escribirContenidoArchivo(writer, datos, cia);
            logger.info("Archivo plano creado exitosamente: {}", filePath);
        } catch (IOException e) {
            logger.error("Error al crear el archivo plano: {}", fileName, e);
            throw e; // Relanzamos la excepción original
        }
    }

    private void escribirContenidoArchivo(BufferedWriter writer, List<Conector> datos, Integer cia) throws IOException {
        int cont = 1;

        Inicial inicio = new Inicial();
        inicio.setF_numero_reg(cont++);
        inicio.setF_cia(cia);
        writer.write(inicio.getConector());
        writer.newLine();

        for (Conector dato : datos) {
            dato.setF_numero_reg(cont++);
            writer.write(dato.getConector());
            writer.newLine();
        }

        Final fin = new Final();
        fin.setF_numero_reg(cont);
        fin.setF_cia(cia);
        writer.write(fin.getConector());
        writer.newLine();
    }
	
	public void guardarRegistroXml(String contenidoXml, String nombreBase) {
        try {
            Path folderPath = Paths.get(XML_LOG_FOLDER);
            Files.createDirectories(folderPath);

            String timestamp  = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = nombreBase + "_" + timestamp + ".xml";
            Path filePath = folderPath.resolve(fileName);

            Files.write(filePath, contenidoXml.getBytes(StandardCharsets.UTF_8));
            logger.info("Registro XML guardado: {}", filePath);
        } catch (IOException e) {
            logger.error("Error al guardar el registro XML: {}", e.getMessage());
        }
    }
}
