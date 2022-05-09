package br.com.uniamerica.gajigo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter @Setter
@NoArgsConstructor
public class Bulk<T> {
    private List<T> content = new ArrayList<>();
}
