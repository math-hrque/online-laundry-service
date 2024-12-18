package br.com.lol.lol.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.lol.lol.dtos.RoupaDTO;
import br.com.lol.lol.exeptions.ListaRoupaVaziaException;
import br.com.lol.lol.exeptions.RoupaNaoExisteException;
import br.com.lol.lol.models.Roupa;
import br.com.lol.lol.repositories.RoupaRepository;

@Service
public class RoupaService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RoupaRepository roupaRepository;
    
    public RoupaDTO cadastrar(@RequestBody RoupaDTO roupaDTO) {
        Roupa roupaCadastrar = mapper.map(roupaDTO, Roupa.class);
        roupaCadastrar.setIdRoupa(0L);
        Roupa roupaCadastrada = roupaRepository.save(roupaCadastrar);
        RoupaDTO roupaCadastradaDto = mapper.map(roupaCadastrada, RoupaDTO.class);
        return roupaCadastradaDto;
    }

    public RoupaDTO atualizar(Long idRoupa, RoupaDTO roupaDTO) throws RoupaNaoExisteException {
        Optional<Roupa> roupaBD = roupaRepository.findById(idRoupa);
        if (!roupaBD.isPresent()) {
            throw new RoupaNaoExisteException("Roupa com esse idRoupa nao existe!");
        }

        Roupa roupaAtualizar = mapper.map(roupaDTO, Roupa.class);
        roupaAtualizar.setIdRoupa(idRoupa);
        Roupa roupaAtualizada = roupaRepository.save(roupaAtualizar);
        RoupaDTO roupaAtualizadaDto = mapper.map(roupaAtualizada, RoupaDTO.class);
        return roupaAtualizadaDto;
    }

    public RoupaDTO inativar(Long idRoupa) throws RoupaNaoExisteException {
        Optional<Roupa> roupaBD = roupaRepository.findById(idRoupa);
        if (!roupaBD.isPresent()) {
            throw new RoupaNaoExisteException("Roupa com esse idRoupa nao existe!");
        }

        roupaBD.get().inativar();
        Roupa roupaInativada = roupaRepository.save(roupaBD.get());
        RoupaDTO roupaInativadaDto = mapper.map(roupaInativada, RoupaDTO.class);
        return roupaInativadaDto;
    }

    public List<RoupaDTO> listar() throws ListaRoupaVaziaException {
        Optional<List<Roupa>> listaRoupaBD = roupaRepository.findByAtivo(true);
        if (!listaRoupaBD.isPresent() && listaRoupaBD.get().isEmpty()) {
            throw new ListaRoupaVaziaException("Lista de roupas vazia!");
        }

        List<RoupaDTO> listaRoupaDto = listaRoupaBD.get().stream().map(roupaBD -> mapper.map(roupaBD, RoupaDTO.class)).collect(Collectors.toList());
        return listaRoupaDto;
    }

}
