// EmprestimoDTO.java
package com.bookflow.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmprestimoDTO {
    private Long alunoId;
    private List<ItemEmprestimoDTO> itens = new ArrayList<>();
    
    @Data
    public static class ItemEmprestimoDTO {
        private Long livroId;
        private Integer quantidade = 1;
    }
}