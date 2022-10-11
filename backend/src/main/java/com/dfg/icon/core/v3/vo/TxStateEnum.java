package com.dfg.icon.core.v3.vo;


import lombok.Getter;

public enum  TxStateEnum {
    Pending(0, "Fail"),
    Success(1, "Success");

    @Getter
    private int state;

    @Getter
    private String detail;

    private TxStateEnum(int state, String detail ) {
        this.state = state;
        this.detail = detail;
    }

    public static String getDetailByState(int value) {
        for(TxStateEnum state : TxStateEnum.values()) {
            if(state.getState() == value) {
                return state.getDetail();
            }
        }
        return null;
    }
}
