package me.superning.luntan.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用的响应对象
 * @author superning
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    /** 错误码，正确返回 0 */
    private Integer errorCode = 0;
    /** 错误信息，正确返回空*/
    private String errorMsg = "";
    /** 返回值对象*/
    private Object data ;

    public Response(Object data) {
        this.data = data;
    }
}
