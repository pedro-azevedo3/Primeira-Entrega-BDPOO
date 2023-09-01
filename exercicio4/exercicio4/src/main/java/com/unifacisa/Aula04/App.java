package com.unifacisa.Aula04;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App 
{
	public static void main( String[] args ) throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Serialize
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, getPessoas());
        System.out.println(writer);

        //Deserialize
        String jsonInput = "[{\"id\":1,\"nome\":\"LucasJsoninput\",\"idade\":22,\"matricula\":123456,\"curso\":\"Medicina\",\"sexo\":\"Masculino\"},{\"id\":2,\"nome\":\"Maria\",\"idade\":24,\"matricula\":658749,\"curso\":\"Fisioterapia\",\"sexo\":\"Feminino\"},{\"id\":3,\"nome\":\"Jose\",\"idade\":28,\"matricula\":354187,\"curso\":\"Direito\",\"sexo\":\"Masculino\"}]";
        List<Pessoa> p = mapper.readValue(jsonInput, new TypeReference<List<Pessoa>>(){});
        System.out.println("Pessoa: " + p);
        
        // Gravando usando Deserialize
        try {
            FileWriter escritor = new FileWriter("arquivo.json");
            escritor.write(jsonInput);
            escritor.close();
            System.out.println("JSON gravado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo JSON: " + e.getMessage());
        }
        
       
        // Gravando usando o Serialize
        try {
            FileWriter escritor = new FileWriter("arquivo.json");
            escritor.write(writer.toString());
            escritor.close();
            System.out.println("JSON gravado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo JSON: " + e.getMessage());
        }

    
	
	}
    private static List<Pessoa> getPessoas() {

        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        Pessoa p1 = new Pessoa();
        p1.setId(1);
        p1.setNome("LucasWriter");
        p1.setIdade(22);
        p1.setMatricula(123456);
        p1.setCurso("Medicina");
        p1.setSexo("Masculino");

        Pessoa p2 = new Pessoa();
        p2.setId(2);
        p2.setNome("Maria");
        p2.setIdade(24);
        p2.setMatricula(658749);
        p2.setCurso("Fisioterapia");
        p2.setSexo("Feminino");

        Pessoa p3 = new Pessoa();
        p3.setId(3);
        p3.setNome("Jose");
        p3.setIdade(28);
        p3.setMatricula(354187);
        p3.setCurso("Direito");
        p3.setSexo("Masculino");

        pessoas.add(p1);
        pessoas.add(p2);
        pessoas.add(p3);

        return pessoas;

    }
}