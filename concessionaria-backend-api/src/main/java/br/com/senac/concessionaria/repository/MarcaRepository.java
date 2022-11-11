package br.com.senac.concessionaria.repository;

import br.com.senac.concessionaria.modelo.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository <Marca, Long> {

}
