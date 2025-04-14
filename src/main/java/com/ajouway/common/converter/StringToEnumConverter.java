package com.ajouway.common.converter;

import com.ajouway.common.exception.CustomException;
import com.ajouway.common.exception.CustomExceptionInfo;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.CustomType;

@RequiredArgsConstructor
public class StringToEnumConverter<T extends Enum<T>> implements Converter<String, T> {

    private final Class<T> enumType;

    @Override
    public T convert(String source) {
        try {
            return Enum.valueOf(enumType, source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CustomException(CustomExceptionInfo.INVALID_ENUM);
        }
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructType(String.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(CustomType.class);
    }
}
