package aula;

import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) throws IOException {
        
        Connection connection = Jsoup.connect("https://pt.wikipedia.org/wiki/Porsche");
            
        Document doc = connection.get();

        Elements h2Elements = doc.getElementsByTag("h2");

        try {
            FileWriter escritor = new FileWriter("arquivo.txt");
            for (Element h2Element : h2Elements) {
                String texto = h2Element.text().replaceAll("\\[editar \\| editar código-fonte\\]", "").trim();
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
