package mate.academy.springboot.datajpa.service.mapper;

public interface ResponseDtoMapper <D, T> {
    D mapToDto (T t);
}
