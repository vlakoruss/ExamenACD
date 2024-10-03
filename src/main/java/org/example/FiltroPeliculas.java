package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltroPeliculas {

    //id,titulo,año,director,genero

    private Integer id;
    private String title;
    private Integer year;
    private String director;
    private String genre;

     public void filtarPorAño(Integer año) {
    File moviesFiles = new File("peliculas.csv");
    ArrayList<FiltroPeliculas> movies = new ArrayList<>();
    try (BufferedReader bfr = new BufferedReader(new FileReader(moviesFiles))) {
        String linea;

        while ((linea = bfr.readLine()) != null) {

            var lineParts = linea.split(",");

            if (lineParts.length == 5 && !lineParts[0].isEmpty() && !lineParts[2].isEmpty()) {
                movies.add(new FiltroPeliculas(
                        Integer.parseInt(lineParts[0]),
                        lineParts[1],
                        Integer.parseInt(lineParts[2]),
                        lineParts[3],
                        lineParts[4]
                ));
            }
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    try (BufferedWriter bfw = new BufferedWriter(new FileWriter("PeliculasPosterioresA" + año + ".csv"))) {
        for (FiltroPeliculas m : movies) {
            if (m.getYear() > año) {
                bfw.write(
                        m.getId() + "," + m.getTitle() + "," + m.getYear() + "," + m.getDirector() + "," + m.getGenre() + System.lineSeparator()
                );
            }
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
}

