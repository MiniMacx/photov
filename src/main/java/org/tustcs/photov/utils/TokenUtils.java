package org.tustcs.photov.utils;



import org.json.JSONObject;
import org.tustcs.photov.service.TokenService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author L.key.
 * @Date 2017/7/24 18:02
 */
public class TokenUtils {


    public static boolean illegal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        TokenService tokenService = (TokenService) SpringContextUtil.getBean("TokenService");
        int userId = Integer.parseInt(httpServletRequest.getParameter("userId"));
        int recId = Integer.parseInt(httpServletRequest.getParameter("recId"));
        String token = httpServletRequest.getParameter("token");
        System.out.println(recId+" "+userId);
        if (!tokenService.expireToken(recId)) {
            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.print(new JSONObject().put("status", 0).put("msg", "已超时请重新登录！"));
            printWriter.close();
            return false;
        }

        if (!tokenService.tokenVerify(recId, userId, token)) {
            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.print(new JSONObject().put("status", 0).put("msg", "验证错误请重新登录！"));
            printWriter.close();
            return false;
        }
        return true;
    }
}
