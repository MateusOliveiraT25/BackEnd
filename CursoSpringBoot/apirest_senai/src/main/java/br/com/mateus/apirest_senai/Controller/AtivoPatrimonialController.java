package br.com.mateus.apirest_senai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mateus.apirest_senai.Model.AtivoPatrimonial;
import br.com.mateus.apirest_senai.Repository.AtivoPatrimonialRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ativo")
public class AtivoPatrimonialController {
    
    @Autowired
    AtivoPatrimonialRepository repository;

    @GetMapping()
    public List<AtivoPatrimonial> getAtivoPatrimonial() {
        return (List<AtivoPatrimonial>) repository.findAll();
    }
    
    @PostMapping()
    public AtivoPatrimonial postAtivoPatrimonial(@RequestBody AtivoPatrimonial ativo) {      
        return repository.save(ativo);
    }
    
    @GetMapping("/{id}")
    public Optional<AtivoPatrimonial> getAtivoPatrimonialById(@PathVariable Long id) {
        return repository.findById(id);
    }
    
    @PutMapping("/{id}")
    public AtivoPatrimonial putAtivoPatrimonial(@PathVariable Long id, @RequestBody AtivoPatrimonial ativo) {
        Optional<AtivoPatrimonial> busca = repository.findById(id);
        if (busca.isPresent()) {
            ativo.setId(id);
            return repository.save(ativo);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAtivoPatrimonial(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
