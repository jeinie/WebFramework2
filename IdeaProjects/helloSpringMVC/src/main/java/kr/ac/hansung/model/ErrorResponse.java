package kr.ac.hansung.model;

import lombok.Data;

@Data // @Getter, @Setter 등 모두 가지고 있음
public class ErrorResponse {
    private String errorCode;
    private String errorMsg;
    private String requestURI;
}
