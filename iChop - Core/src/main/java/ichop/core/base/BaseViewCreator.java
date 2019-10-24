package ichop.core.base;

import org.modelmapper.ModelMapper;

public abstract class BaseViewCreator {

    protected final ModelMapper modelMapper;

    protected BaseViewCreator(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
