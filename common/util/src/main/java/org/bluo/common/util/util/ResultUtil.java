package org.bluo.common.util.util;

import cn.hutool.core.util.ObjectUtil;
import org.bluo.common.util.respons.Result;

/**
 * @author boluo
 * @date 2024/02/03
 */
public class ResultUtil {
    public static boolean success(Result result) {
        return ObjectUtil.isNotNull(result) && result.getStatus() == 1;
    }
}
