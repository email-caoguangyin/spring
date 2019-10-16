package com.oracle.springboot.enums;

public enum CommentTypeEnum {
    QUESETION(1),
    COMMENT(2);
    //属性
    private Integer type;
    //get方法
    public Integer getType(){
        return type;
    }
    //构造方法
    CommentTypeEnum (Integer type){
        this.type=type;
    }

    /** 静态判断type是否是需要的类型
     *
     * @param type 传入类型（1.问题 2.回复）
     * @return
     */
    public static boolean isExist(Integer type) {
        //for循环判断
        for (CommentTypeEnum commentTypeEnum:CommentTypeEnum.values()){
            //判断是否是需要的类型
            if (commentTypeEnum.getType()==type){
                return true;
            }
        }
        return false;
    }
}
