package Generics;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class ManejadorTablaFrontEnd {

    public static JsonArray extraerDatosDeTablaAJson(WebElement table){
        ArrayList<String> columnnas= getNameColumns(table);
        ArrayList<WebElement> filas = getFilasConDatos(table);
        return joinData(columnnas, filas);
    }

    public static JsonArray joinData(ArrayList<String> columnas, ArrayList<WebElement> filas){
        JsonArray tablaJson = new JsonArray();
        for (WebElement fila: filas){
            JsonObject filaJson = new JsonObject();
            ArrayList<String> datosFila= getDataFila(fila);
            for (int i=0; i<columnas.size();i++){
                filaJson.addProperty(columnas.get(i), datosFila.get(i));
            }
            tablaJson.add(filaJson);
        }
        return tablaJson;
    }

    public static ArrayList<String> getDataFila(WebElement fila){
        List<WebElement> datosFila = fila.findElements(By.xpath("//td"));
        ArrayList<String> datosReales = new ArrayList<>();
        for (WebElement element: datosFila){
            if(!element.getText().trim().equals("")){
                datosReales.add(extraerTexto(element));
            }
        }
        return datosReales;
    }

    public static ArrayList<String> getNameColumns(WebElement tabla){
        ArrayList<String> nombreColumnas = new ArrayList<>();
        List<WebElement> columnas = tabla.findElements(By.xpath(".//thead/tr/th"));
        for (WebElement columna: columnas){
            if(!columna.getText().trim().equals("")){
            nombreColumnas.add(columna.getText());
            }
        }
        return nombreColumnas;
    }

    public static ArrayList<WebElement> getFilasConDatos(WebElement tabla){
        List<WebElement> filas = tabla.findElements(By.xpath(".//tbody/tr"));
        ArrayList<WebElement> filaConDatos = new ArrayList<>();
        for (WebElement fila: filas){
            if(!fila.getText().trim().equals("")){
                filaConDatos.add(fila);
            }
        }
        return filaConDatos;
    }

    public static String extraerTexto(WebElement element) {
        return element.getText();
    }
}
