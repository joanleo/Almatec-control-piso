package com.almatec.controlpiso.utils.refactoring;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.almatec.controlpiso.erp.webservices.generated.Final;
import com.almatec.controlpiso.erp.webservices.generated.Inicial;
import com.almatec.controlpiso.erp.webservices.interfaces.Conector;

@Service
public class GeneradorArchivo {
	
	private ParametrosAppService parametros;

    public GeneradorArchivo(ParametrosAppService parametros) {
        this.parametros = parametros;
    }

    public void crearArchivo(List<Conector> datos, String nombre) throws IOException {
        String folderPath = "C:/IntegrApps/Almatec/xml";
        File folder = new File(folderPath);
        
        if (!folder.exists() && !folder.mkdirs()) {
             throw new IOException("No se pudo crear la carpeta " + folderPath);
        }
        String fileName = nombre + ".txt";

        File file = new File(folderPath, fileName);
        
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("No se pudo crear el archivo " + fileName);
        }
        
        FileWriter writer = new FileWriter(file);
        try {
            int cont = 1;
            Inicial inicio = new Inicial();
            inicio.setF_numero_reg(cont);
            inicio.setF_cia(parametros.getCompania());
            writer.write(inicio.getConector() + "\n");
            cont++;

            for (Conector dato : datos) {
                dato.setF_numero_reg(cont);
                writer.write(dato.getConector() + "\n");
                cont++;
            }

            Final fin = new Final();
            fin.setF_numero_reg(cont);
            fin.setF_cia(parametros.getCompania());
            writer.write(fin.getConector() + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

}
