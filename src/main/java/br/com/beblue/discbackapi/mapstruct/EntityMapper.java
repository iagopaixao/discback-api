package br.com.beblue.discbackapi.mapstruct;

import java.util.List;

public interface EntityMapper<V, E> {

    E toEntity(V vo);

    V toVO(E entity);

    List<E> toEntity(List<V> voList);

    List<V> toVO(List<E> entityList);
}