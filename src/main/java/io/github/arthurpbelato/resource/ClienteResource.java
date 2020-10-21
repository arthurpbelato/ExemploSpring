package io.github.arthurpbelato.resource;

import ch.qos.logback.core.net.server.Client;
import io.github.arthurpbelato.model.Cliente;
import io.github.arthurpbelato.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigInteger;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteResource {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody @Valid Cliente cliente, HttpServletResponse response){
        Cliente clienteSalvo = clienteRepository.save(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{codigo}").buildAndExpand(clienteSalvo.getCodCliente()).toUri();

        response.setHeader("Location", uri.toASCIIString());
        return ResponseEntity.created(uri).body(clienteSalvo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Cliente>> buscarPeloCodigo(@PathVariable BigInteger codigo){
        Optional<Cliente> cliente = clienteRepository.findById(codigo);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerCliente(@PathVariable BigInteger codigo){
        clienteRepository.deleteById(codigo);
    }


}
