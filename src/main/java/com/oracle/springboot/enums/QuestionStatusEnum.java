package com.oracle.springboot.enums;

public enum QuestionStatusEnum {
    QUESTION_EXIST(1,"存在"),
    QUESTION_VANISH(0,"消失"),
    ;
    private Integer type;
    private String name;


    public static String nameOfType(Integer type) {
        for (QuestionStatusEnum questionStatusEnum:QuestionStatusEnum.values()) {
            if (questionStatusEnum.getType()==type){
                return questionStatusEnum.getName();
            }

        }
        return "";
    }
    public Integer getType() { return type;}
    public String getName() {
        return name;
    }

    QuestionStatusEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }
}
