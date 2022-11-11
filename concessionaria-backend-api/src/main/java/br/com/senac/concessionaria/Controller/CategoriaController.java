package br.com.senac.concessionaria.Controller;

import br.com.senac.concessionaria.dto.categoria.CategoriaRequest;
import br.com.senac.concessionaria.dto.categoria.CategoriaResponse;
import br.com.senac.concessionaria.modelo.categoria.Categoria;
import br.com.senac.concessionaria.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/categoria"})
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping

    public ResponseEntity<Void> criarCategoria(@RequestBody CategoriaRequest categoria){
        Categoria categoriaModel = new Categoria();

        categoriaModel.setNome(categoria.getNome());
        categoriaModel.setDescricao(categoria.getDescricao());

        categoriaRepository.save(categoriaModel);

        return ResponseEntity.ok().body(null);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> retornarCategoria(){

        List<Categoria> categoriaList= new ArrayList<>();

        categoriaList = categoriaRepository.findAll();

        List<CategoriaResponse> categoriaResponseList = new ArrayList<>();

        for (Categoria dadosCategoria :categoriaList){
            categoriaResponseList.add(new CategoriaResponse(dadosCategoria.getId(), dadosCategoria.getNome(), dadosCategoria.getDescricao()));

        }
        return ResponseEntity.ok().body(categoriaResponseList);

    }
}
