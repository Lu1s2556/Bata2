package Imagenes;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import conexion.conexionSQL;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PDF {
    
    private Connection cn;
    public String recipe;
    
    public PDF(Connection cn){
        this.cn = cn;
    }

    public void generarPDF(String cedula) {
        try {
            String nombreDoctor = "Dr. Juan Pérez";
            String nombrePaciente = "";
            String recipe = "";
            String idRecipe = "";

            // === Obtener nombre del paciente ===
            String SQLnombre = "SELECT nombre FROM paciente WHERE cedula = ?";
            try (PreparedStatement psNombre = cn.prepareStatement(SQLnombre)) {
                psNombre.setString(1, cedula);
                ResultSet rsNombre = psNombre.executeQuery();
                if (rsNombre.next()) {
                    nombrePaciente = rsNombre.getString("nombre");
                } else {
                    nombrePaciente = "No registrado";
                }
            }

            // === Obtener receta e ID ===
            String SQLrecipe = "SELECT id_recipe, recipe FROM recipe WHERE cedula = ?";
            try (PreparedStatement psRecipe = cn.prepareStatement(SQLrecipe)) {
                psRecipe.setString(1, cedula);
                ResultSet rsRecipe = psRecipe.executeQuery();
                if (rsRecipe.next()) {
                    idRecipe = rsRecipe.getString("id_recipe");
                    recipe = rsRecipe.getString("recipe");
                }
            }

            PDDocument docu = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A5);
            docu.addPage(page);

            PDPageContentStream contenido = new PDPageContentStream(docu, page);
            float w = page.getMediaBox().getWidth();
            float h = page.getMediaBox().getHeight();

            float margenLineas = 30;   // posición de las líneas azules
            float margenTexto = 5;     // espacio entre texto y línea azul
            float zonaIzq = margenLineas + margenTexto;
            float zonaDer = w - margenLineas - margenTexto;

            // === Líneas azules ===
            contenido.setStrokingColor(0, 102, 204);
            contenido.setLineWidth(4);
            contenido.moveTo(margenLineas, 20);
            contenido.lineTo(margenLineas, h - 20);
            contenido.stroke();
            contenido.moveTo(w - margenLineas, 20);
            contenido.lineTo(w - margenLineas, h - 20);
            contenido.stroke();

            // === Logo ===
            InputStream logoInput = getClass().getResourceAsStream("/Imagenes/logo medicontrol.png");
            PDImageXObject logo = PDImageXObject.createFromByteArray(docu, logoInput.readAllBytes(), "logo");
            contenido.drawImage(logo, (w - 60) / 2, h - 85, 60, 60);

            // === Título centrado con mayor separación ===
            float y = h - 130;
            contenido.beginText();
            contenido.setFont(PDType1Font.HELVETICA_BOLD, 22);
            float anchoTitulo = PDType1Font.HELVETICA_BOLD.getStringWidth("Meditrack") / 1000 * 22;
            contenido.newLineAtOffset((w - anchoTitulo) / 2, y);
            contenido.showText("Meditrack");
            contenido.endText();

            // === Nombre del doctor ===
            y -= 35;
            contenido.beginText();
            contenido.setFont(PDType1Font.HELVETICA_BOLD, 13);
            contenido.newLineAtOffset(zonaIzq, y);
            contenido.showText(nombreDoctor);
            contenido.endText();

            // === Recipe centrado ===
            y -= 45;
            float leading = 14;
            if (recipe != null && !recipe.isEmpty()) {
                String[] lineas = recipe.replace("\r", "").split("\n");
                for (String linea : lineas) {
                    contenido.beginText();
                    contenido.setFont(PDType1Font.HELVETICA, 12);
                    float anchoLinea = PDType1Font.HELVETICA.getStringWidth(linea) / 1000 * 12;
                    float x = (w - anchoLinea) / 2;
                    if (x < zonaIzq) x = zonaIzq;
                    if (x + anchoLinea > zonaDer) x = zonaDer - anchoLinea;
                    contenido.newLineAtOffset(x, y);
                    contenido.showText(linea);
                    contenido.endText();
                    y -= leading;
                }
            }

            // === Pie con datos del paciente ===
            String pie = "Paciente: " + nombrePaciente + " | C.I.: " + cedula;
            contenido.beginText();
            contenido.setFont(PDType1Font.HELVETICA_OBLIQUE, 11);
            contenido.newLineAtOffset(zonaIzq, 30);
            contenido.showText(pie);
            contenido.endText();

            contenido.close();

            // === Guardar con nombre dinámico ===
            String nombreLimpio = nombrePaciente.replaceAll("[^a-zA-Z0-9]", "");
            String nombreArchivo = "C:\\Users\\Public\\Receta_" + nombreLimpio + "_" + idRecipe + ".pdf";
            docu.save(nombreArchivo);
            docu.close();

        } catch (Exception e) {
            System.err.println("Error al generar receta: " + e.getMessage());
        }


    }
}

