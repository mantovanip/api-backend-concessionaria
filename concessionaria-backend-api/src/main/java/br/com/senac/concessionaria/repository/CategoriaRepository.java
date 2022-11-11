package br.com.senac.concessionaria.repository;

import br.com.senac.concessionaria.modelo.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository <Categoria, Long> {
}
