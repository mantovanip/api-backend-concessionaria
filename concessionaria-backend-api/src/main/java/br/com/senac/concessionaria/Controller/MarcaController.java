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
import java.util.Optional;

@RestController
@RequestMapping({"/marca"})
public class MarcaController {

    @Autowired // Inicia o serviço do repositorio
    private MarcaRepository marcaRepository;

    @CrossOrigin(origins = "*")
    @PostMapping // tipo de metodo
    public ResponseEntity<Void> criarMarca(@RequestBody MarcaRequest marca){ //cria a marca e salva na base de dados
        Marca marcaModel = new Marca();

        marcaModel.setNome(marca.getNome());
        marcaModel.setDescricao(marca.getDescricao());
    try {


        marcaRepository.save(marcaModel); //salva o registro

        return ResponseEntity.ok().body(null); //retorno do metodo ok é que deu certo codigo:200
    }catch (Exception e){
        return ResponseEntity.badRequest().body(null);
    }
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

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> removerMarca(@PathVariable Long id){

        marcaRepository.deleteById(id);

        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> removerMarcaAll(){

        marcaRepository.deleteAll();

        return ResponseEntity.ok().body(null);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<Void> atualizarMarca(@RequestBody MarcaRequest marcaRequest, @PathVariable Long id){
        Optional<Object> marca;

        marca = marcaRepository.findById(id)
                .map(record -> {
                    record.setDescricao(marcaRequest.getDescricao());
                    record.setNome(marcaRequest.getNome());
                    return marcaRepository.save(record);
                });

        return ResponseEntity.ok().body(null);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<MarcaResponse> carregarMarcaById(@PathVariable Long id){

        Optional<Marca> marca = marcaRepository.findById(id);

        MarcaResponse marcaResponse = new MarcaResponse();
        marcaResponse.setId(marca.get().getId());
        marcaResponse.setNome(marca.get().getNome());
        marcaResponse.setDescricao(marca.get().getDescricao());

        return ResponseEntity.ok().body(marcaResponse);
    }
}