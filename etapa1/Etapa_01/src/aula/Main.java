package aula;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) throws IOException {
        
        Connection connection = Jsoup.connect("https://www.imdb.com/chart/top")
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
            .timeout(10000) 
            .header("Accept-Language", "en-US,en;q=0.9");

        
        Document doc = connection.get();

        Elements h2Elements = doc.getElementsByTag("h3");

        try {
            FileWriter escritor = new FileWriter("arquivo.csv", StandardCharsets.UTF_8);
            for (Element h2Element : h2Elements) {
                String texto = h2Element.text();
                if (!texto.isEmpty()) { 
                    escritor.write(texto + "\n");
                }
            }
            escritor.close();
            System.out.println("Dados gravados com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
        
        
    }
}



