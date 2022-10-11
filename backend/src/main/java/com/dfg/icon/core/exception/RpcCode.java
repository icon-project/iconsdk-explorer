package com.dfg.icon.core.exception;


import org.springframework.http.HttpStatus;

public enum RpcCode implements CustomErrorCode {

    PARSE_ERROR(-32700, "Invalid JSON was received by the server. An error occurred on the server while parsing the JSON text."),
    INVALID_REQUEST(-32600, "The JSON sent is not a valid Request object."),
    METHOD_NOT_FOUND(-32601, "The method does not exist / is not available."),
    INVALID_PARAMS(-32602, "Invalid method parameter(s)."),
    INTERNAL_ERROR(-32603, "Internal JSON-RPC error."),
    SERVER_ERROR(-32000, "IconServiceEngine 내부오류"),
    SCORE_ERROR(-32100, "Score 내부오류")

	;

    private HttpStatus status;
    private String message;
    private Integer code;

    private RpcCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
    	return code.toString();
    }
}
