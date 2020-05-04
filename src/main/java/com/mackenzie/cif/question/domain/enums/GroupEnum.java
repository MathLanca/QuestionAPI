package com.mackenzie.cif.question.domain.enums;

public enum GroupEnum {

    ACTIVITYANDPARTICIPATION("ATIVIDADE E PARTICIPAÇÃO"),
    BODYFUNCTIONS("FUNÇÕES DO CORPO"),
    BODYSTRUCTURES("ESTRUTURA DO CORPO"),
    ENVIRONMENTALFACTORS("FATORES AMBIENTAIS");

    public String label;

    private GroupEnum(String label) {
        this.label = label;
    }

    public static GroupEnum valueOfLabel(String label) {
        for (GroupEnum e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
