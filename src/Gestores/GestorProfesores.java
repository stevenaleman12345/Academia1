package Gestores;

import Clases.Profesor;
import java.io.*;
import java.util.ArrayList;

public class GestorProfesores {
    private ArrayList<Profesor> profesores;
    private final String archivo = "profesores.txt";

    public GestorProfesores() {
        profesores = new ArrayList<>();
        cargarDesdeArchivo();
    }

    
    public void agregarProfesor(Profesor profesor) {
        if (!profesores.contains(profesor)) {
            profesores.add(profesor);
            guardarEnArchivo();
            System.out.println("Profesor registrado exitosamente.");
        } else {
            System.out.println("El profesor ya está registrado.");
        }
    }

   
    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    
    public void mostrarProfesores() {
        if (profesores.isEmpty()) {
            System.out.println("No hay profesores registrados.");
        } else {
            for (Profesor p : profesores) {
                System.out.println(p);
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
                    Profesor p = new Profesor(
                        partes[1], partes[2], partes[3], partes[4],
                        partes[0], partes[5], partes[6]
                    );
                    profesores.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de profesores.");
        }
    }

 
    private void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Profesor p : profesores) {
                pw.println(p.getIdProfesor() + "," +
                           p.getNombres() + "," +
                           p.getApellidos() + "," +
                           p.getEmail() + "," +
                           p.getTelefono() + "," +
                           p.getEspecialidad() + "," +
                           p.getTipoContrato());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de profesores.");
        }
    }
}

