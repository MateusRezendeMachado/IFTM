package br.edu.iftm.tspi.pbackorm.e_commerce.dto.mapper;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Categoria;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.CategoriaDTO;
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
public class CategoriaMapperImpl implements CategoriaMapper {

    @Override
    public Categoria toEntity(CategoriaDTO categoriaDTO) {
        if ( categoriaDTO == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setId( categoriaDTO.getId() );
        categoria.setNome( categoriaDTO.getNome() );
        categoria.setDescricao( categoriaDTO.getDescricao() );

        return categoria;
    }

    @Override
    public CategoriaDTO toDto(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        CategoriaDTO categoriaDTO = new CategoriaDTO();

        categoriaDTO.setId( categoria.getId() );
        categoriaDTO.setNome( categoria.getNome() );
        categoriaDTO.setDescricao( categoria.getDescricao() );

        return categoriaDTO;
    }

    @Override
    public List<Categoria> toEntityList(List<CategoriaDTO> categoriasDTO) {
        if ( categoriasDTO == null ) {
            return null;
        }

        List<Categoria> list = new ArrayList<Categoria>( categoriasDTO.size() );
        for ( CategoriaDTO categoriaDTO : categoriasDTO ) {
            list.add( toEntity( categoriaDTO ) );
        }

        return list;
    }

    @Override
    public List<CategoriaDTO> toDtoList(List<Categoria> categorias) {
        if ( categorias == null ) {
            return null;
        }

        List<CategoriaDTO> list = new ArrayList<CategoriaDTO>( categorias.size() );
        for ( Categoria categoria : categorias ) {
            list.add( toDto( categoria ) );
        }

        return list;
    }
}
