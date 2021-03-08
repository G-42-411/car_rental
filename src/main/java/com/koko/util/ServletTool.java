package com.koko.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * Servlet工具
 */
public class ServletTool {
    /**
     * 获取HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }

    /**
     * 获取HttpSession
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取ContextPath
     */
    public static String getContextPath() {
        return getRequest().getContextPath();
    }

    /**
     * 获取完整请求url
     */
    public static String getCompletePath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getRequest().getScheme())
                .append("://")
                .append(getRequest().getServerName())
                .append(":")
                .append(getRequest().getServerPort())
                .append(getContextPath());
        return stringBuilder.toString();
    }
}
