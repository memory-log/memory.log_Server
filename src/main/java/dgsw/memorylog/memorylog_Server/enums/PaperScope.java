package dgsw.memorylog.memorylog_Server.enums;

import dgsw.memorylog.memorylog_Server.lib.AbstractEnumConverter;
import dgsw.memorylog.memorylog_Server.lib.PersistableEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaperScope implements PersistableEnum<Byte> {
    PUBLIC((byte)0),
    ONLY_CODE((byte)1),
    PRIVATE((byte)2);

    private final Byte code;

    // JPA converter
    @javax.persistence.Converter(autoApply = true)
    public static class Converter extends AbstractEnumConverter<PaperScope, Byte> {
        public Converter() {
            super(PaperScope.class);
        }
    }
}
