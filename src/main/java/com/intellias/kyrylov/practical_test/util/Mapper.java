package com.intellias.kyrylov.practical_test.util;

import java.util.Collection;

public interface Mapper<S,T> {

    public T convertToDTO(S source);

    public S convertToEntity(T target);

    public Collection<T> convertAllToDTO(Collection<S> sources);

    public Collection<S> convertAllToEntity(Collection<T> dtos);

}
