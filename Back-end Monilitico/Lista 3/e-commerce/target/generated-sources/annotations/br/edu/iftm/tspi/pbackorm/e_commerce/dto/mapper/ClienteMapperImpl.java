package br.edu.iftm.tspi.pbackorm.e_commerce.dto.mapper;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Cliente;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.ClienteDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-28T10:57:02-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toEntity(ClienteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setId( dto.getId() );
        cliente.setNome( dto.getNome() );
        cliente.setCargo( dto.getCargo() );
        cliente.setEndereco( dto.getEndereco() );
        cliente.setCidade( dto.getCidade() );
        cliente.setCep( dto.getCep() );
        cliente.setPais( dto.getPais() );
        cliente.setTelefone( dto.getTelefone() );
        cliente.setFax( dto.getFax() );

        return cliente;
    }

    @Override
    public ClienteDTO toDto(Cliente entity) {
        if ( entity == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId( entity.getId() );
        clienteDTO.setNome( entity.getNome() );
        clienteDTO.setCargo( entity.getCargo() );
        clienteDTO.setEndereco( entity.getEndereco() );
        clienteDTO.setCidade( entity.getCidade() );
        clienteDTO.setCep( entity.getCep() );
        clienteDTO.setPais( entity.getPais() );
        clienteDTO.setTelefone( entity.getTelefone() );
        clienteDTO.setFax( entity.getFax() );

        return clienteDTO;
    }

    @Override
    public List<ClienteDTO> toDtoList(List<Cliente> clientes) {
        if ( clientes == null ) {
            return null;
        }

        List<ClienteDTO> list = new ArrayList<ClienteDTO>( clientes.size() );
        for ( Cliente cliente : clientes ) {
            list.add( toDto( cliente ) );
        }

        return list;
    }
}
