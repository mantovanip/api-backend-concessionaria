package br.com.senac.concessionaria.Controller;


import br.com.senac.concessionaria.dto.MarcaRequest;
import br.com.senac.concessionaria.dto.MarcaResponse;
import br.com.senac.concessionaria.modelo.Marca;
import br.com.senac.concessionaria.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/marca"})
public class MarcaController {

    @Autowired // Inicia o serviço do repositorio
    private MarcaRepository marcaRepository;

    @PostMapping // tipo de metodo
    public ResponseEntity<Void> criarMarca(@RequestBody MarcaRequest marca){ //cria a marca e salva na base de dados
        Marca marcaModel = new Marca();

        marcaModel.setNome(marca.getNome());
        marcaModel.setDescricao(marca.getDescricao());

        marcaRepository.save(marcaModel); //salva o registro

        return ResponseEntity.ok().body(null); //retorno do metodo ok é que deu certo codigo:200

    }
    @GetMapping
    public ResponseEntity<List<MarcaResponse>> retornarMarcas(){
            // Criar lista vazia
        List<Marca> marcaList = new ArrayList<>();
        //Carrega informações do db e add na lista vazia
        marcaList = marcaRepository.findAll();
        // Cria lista vazia para retorno
        List<MarcaResponse> marcaResponseList =new ArrayList<>();
        // Laço de repetição para percorrer a lista de marcas
        for (Marca dadosMarca : marcaList){
            marcaResponseList.add(new MarcaResponse(dadosMarca.getId(), dadosMarca.getNome(), dadosMarca.getDescricao()));
        }
        return ResponseEntity.ok().body(marcaResponseList);
    }
}
