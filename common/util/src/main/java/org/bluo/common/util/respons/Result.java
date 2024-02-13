package org.bluo.common.util.respons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author boluo
 * @date 2024/01/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String message;
    private int status;
    private Object data;

    public static Result ok(Object data) {
        return new Result("成功", 1, data);
    }

    public static Result ok(int status, Object data) {
        return new Result("成功", status, data);
    }

    public static Result fail() {
        return new Result("失败", -1, null);
    }

    public static Result fail(String message) {
        return new Result(message, -1, null);
    }

    public static Result success() {
        return new Result("操作成功", 1, null);
    }

    public static Result warn(String message) {
        return new Result(message, 0, null);
    }
}
