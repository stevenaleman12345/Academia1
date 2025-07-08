package Gestores;

import Clases.Estudiante;
import java.io.*;
import java.util.ArrayList;

public class GestorEstudiantes {
    private ArrayList<Estudiante> estudiantes;
    private final String archivo = "estudiantes.txt";

    public GestorEstudiantes() {
        estudiantes = new ArrayList<>();
        cargarDesdeArchivo();
    }

    
    public void agregarEstudiante(Estudiante estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
            guardarEnArchivo();
            System.out.println("Estudiante registrado con gran exito.");
        } else {
            System.out.println("este estudiante ya esta registrado en el sistema.");
        }
    }

  
    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    
    public void mostrarEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudinates aun registrados.");
        } else {
            for (Estudiante e : estudiantes) {
                System.out.println(e);
            }
        }
    }


    private void cargarDesdeArchivo() {
        File file = new File(archivo);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
               
                String[] partes = linea.split(",");
                if (partes.length == 7) {
                    Estudiante e = new Estudiante(
                        partes[1], partes[2], partes[3], partes[4],
                        partes[0], partes[5], partes[6]
                    );
                    estudiantes.add(e);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo de estudiantes.");
        }
    }

    
    private void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Estudiante e : estudiantes) {
                pw.println(e.getCarnet() + "," +
                           e.getNombres() + "," +
                           e.getApellidos() + "," +
                           e.getEmail() + "," +
                           e.getTelefono() + "," +
                           e.getCarrera() + "," +
                           e.getNivel());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de estudiantes.");
        }
    }
}
