package com.juridico.config;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ModelMapperAbstratoConfigTest {

    @Test
    void deve_criar_novo_model_mapper() {
        ModelMapperConfig modelMapperConfig = new ModelMapperConfig();

        ModelMapper actual = modelMapperConfig.modelMapper();

        assertNotNull(actual);
    }
}