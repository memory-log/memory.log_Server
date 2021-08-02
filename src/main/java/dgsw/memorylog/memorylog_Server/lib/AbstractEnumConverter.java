package dgsw.memorylog.memorylog_Server.lib;

import javax.persistence.AttributeConverter;

public abstract class AbstractEnumConverter<T extends Enum<T> & PersistableEnum<E>, E>
        implements AttributeConverter<T, E> {

    private final Class<T> clazz;

    public AbstractEnumConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E convertToDatabaseColumn(T attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public T convertToEntityAttribute(E dbData) {
        T[] enums = clazz.getEnumConstants();

        for (T e : enums) {
            if (e.getCode().equals(dbData)) {
                return e;
            }
        }

        throw new UnsupportedOperationException(
                String.format("%s cannot convert to %s enum value.", dbData, clazz));
    }
}