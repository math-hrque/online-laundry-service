package br.com.lol.lol.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.lol.lol.dtos.EnderecoViaCepDTO;
import br.com.lol.lol.dtos.EnderecoDTO;
import br.com.lol.lol.exeptions.CepInvalidoException;
import br.com.lol.lol.exeptions.CepNaoExisteException;

@Service
public class EnderecoService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    private static final String CEP_PATTERN = "\\d{8}";
    private static final String BASE_URL = "https://viacep.com.br/ws/";
    private static final String JSON_URL = "/json/";

    public EnderecoDTO consultar(String cep) throws CepNaoExisteException, CepInvalidoException {
        if (!cep.matches(CEP_PATTERN)) {
            throw new CepInvalidoException("CEP invalido!");
        }
    
        String url = BASE_URL + cep + JSON_URL;
        EnderecoViaCepDTO enderecoViaCepDto = null;

        try {
            enderecoViaCepDto = restTemplate.getForObject(url, EnderecoViaCepDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar o serviço de CEP: " + e.getMessage(), e);
        }

        if (enderecoViaCepDto == null || enderecoViaCepDto.getCep() == null) {
            throw new CepNaoExisteException("CEP nao existe!");
        } 
        EnderecoDTO enderecoDto = mapper.map(enderecoViaCepDto, EnderecoDTO.class);
        enderecoDto.setCidade(enderecoViaCepDto.getCidade());
        enderecoDto.setRua(enderecoViaCepDto.getRua());
        return enderecoDto;
    }
    
}
