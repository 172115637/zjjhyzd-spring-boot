package com.zjjhyzd.springboot.utils;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjjhyzd.springboot.commons.tuple.Tuple2;
import com.zjjhyzd.springboot.commons.tuple.Tuple3;
import com.zjjhyzd.springboot.commons.tuple.Tuples;
import com.zjjhyzd.springboot.commons.utils.IdUtil;
import com.zjjhyzd.springboot.commons.utils.JwtTokenUtil;
import com.zjjhyzd.springboot.commons.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Requests implements HandlerInterceptor {

    private static ThreadLocal<Tuple3<HttpServletRequest, HttpServletResponse, List<Callable<String>>>>
            ctx = new ThreadLocal<>();

    // --- String --- //

    /**
     * req.getParameter(name)
     */
    public static @Nullable
    String param(String name) {
        return req().getParameter(name);
    }

    /**
     * req.getParameterValues(name) || {}
     */
    public static final String[] EMPTY_STR_ARR = {};

    public static String[] params(String name) {
        String[] args = req().getParameterValues(name);
        return args != null ? args : EMPTY_STR_ARR;
    }


    public static List<Tuple2<String, String>> paramTuples(String param1, String param2) {
        String[] args1 = params(param1);
        String[] args2 = params(param2);
        List<Tuple2<String, String>> list = new ArrayList<>();
        int max = Math.min(args1.length, args2.length);
        for (int i = 0; i < max; i++) {
            list.add(Tuples.of(args1[i], args2[i]));
        }
        return list;
    }

    public static List<Tuple3<String, String, String>> paramTuples(String param1, String param2, String param3) {
        String[] args1 = params(param1);
        String[] args2 = params(param2);
        String[] args3 = params(param3);
        List<Tuple3<String, String, String>> list = new ArrayList<>();
        int max = Math.min(Math.min(args1.length, args2.length), args3.length);
        for (int i = 0; i < max; i++) {
            list.add(Tuples.of(args1[i], args2[i], args3[i]));
        }
        return list;
    }

    public static double[] paramsDouble(String name) {
        String param[] = params(name);
        double arr[] = new double[param.length];
        for (int i = 0; i < param.length; i++) {
            try {
                arr[i] = Double.parseDouble(param[i]);
            } catch (NumberFormatException e) {
            }
        }
        return arr;
    }

    public static double[] paramsDouble(String name, double defaultValaue) {
        String param[] = params(name);
        double arr[] = new double[param.length];
        for (int i = 0; i < param.length; i++) {
            try {
                arr[i] = Double.parseDouble(param[i]);
            } catch (NumberFormatException e) {
                arr[i] = defaultValaue;
            }
        }
        return arr;
    }

    public static BigDecimal[] paramsBigDecimal(String name, BigDecimal defaultValue) {
        String param[] = params(name);
        if (param != null) {
            BigDecimal arr[] = new BigDecimal[param.length];
            for (int i = 0; i < param.length; i++) {
                arr[i] = StringUtil.convertToBigDecimal(param[i], defaultValue);
            }
            return arr;
        }
        return null;
    }

    public static BigDecimal[] paramsBigDecimal(String name) {
        String param[] = params(name);
        if (param != null) {
            BigDecimal arr[] = new BigDecimal[param.length];
            for (int i = 0; i < param.length; i++) {
                try {
                    arr[i] = new BigDecimal(param[i]);
                } catch (NumberFormatException e) {

                }
            }
            return arr;
        }
        return null;
    }

    public static int[] paramsInt(String name, int defalutValue) {
        String param[] = params(name);
        int arr[] = new int[param.length];
        for (int i = 0; i < param.length; i++) {
            try {
                arr[i] = Integer.parseInt(param[i]);
            } catch (NumberFormatException e) {
                arr[i] = defalutValue;
            }
        }
        return arr;
    }

    public static int[] paramsInt(String name) {
        String param[] = params(name);
        int arr[] = new int[param.length];
        for (int i = 0; i < param.length; i++) {
            try {
                arr[i] = Integer.parseInt(param[i]);
            } catch (NumberFormatException e) {
            }
        }
        return arr;
    }

    /**
     * req.getParameter(name) || defaultVal
     * 如果value和defaultVal皆为null，则前往400页面
     */
    public static String param(String name, @Nullable String defaultVal) {
        String param = param(name);
        if (param == null) {
            if (defaultVal == null)
                throw new BadReq();
            else
                return defaultVal;
        }
        return param;
    }

    // --- Integer --- //

    /**
     * Integer.parseInt(req.getParameter(name)) || null
     */
    public static @Nullable
    Integer paramInt(String name) {
        String param = param(name);
        if (param != null && !param.isEmpty())
            try {
                return Integer.parseInt(param);
            } catch (NumberFormatException e) {
            }
        return null;
    }

    /**
     * Integer.parseInt(req.getParameter(name)) || defaultVal
     */
    public static int paramInt(String name, int defaultVal) {
        Integer i = paramInt(name);
        return i != null ? i.intValue() : defaultVal;
    }

    public static @Nullable
    Long paramLong(String name) {
        String param = param(name);
        if (param != null && !param.isEmpty())
            try {
                return Long.parseLong(param);
            } catch (NumberFormatException e) {
            }
        return null;
    }

    public static long paramLong(String name, long defaultVal) {
        Long i = paramLong(name);
        return i != null ? i.longValue() : defaultVal;
    }

    public static @Nullable
    Double paramDouble(String name) {
        String param = param(name);
        if (param != null && !param.isEmpty())
            try {
                return Double.parseDouble(param);
            } catch (NumberFormatException e) {
            }
        return null;
    }

    public static Double paramDouble(String name, long defaultVal) {
        Double i = paramDouble(name);
        return i != null ? i.doubleValue() : defaultVal;
    }

    // --- Boolean --- //

    /**
     * Boolean.parseBool(req.getParameter(name))
     */
    public static @Nullable
    Boolean paramBool(String name) {
        String param = param(name);
        if ("true".equalsIgnoreCase(param))
            return Boolean.TRUE;
        if ("false".equalsIgnoreCase(param))
            return Boolean.FALSE;
        return null;
    }

    /**
     * Boolean.parseBool(req.getParameter(name) || defaultVal)
     */
    public static boolean paramBool(String name, boolean defaultVal) {
        Boolean bool = paramBool(name);
        return bool != null ? bool.booleanValue() : defaultVal;
    }

    public static boolean[] paramsBools(String paraName) {
        String param[] = params(paraName);
        boolean arr[] = new boolean[param.length];
        for (int i = 0; i < param.length; i++) {
            if ("true".equals(param[i])) {
                arr[i] = true;
            }
        }
        return arr;
    }

    // --- BigDecimal --- //

    /**
     * BigDecimal.parse(req.getParameter(name)) || null
     */
    public static @Nullable
    BigDecimal paramDecimal(String name) {
        String param = param(name);
        if (param != null && !param.isEmpty())
            try {
                return new BigDecimal(param);
            } catch (NumberFormatException e) {
            }
        return null;
    }

    /**
     * BigDecimal.parse(req.getParameter(name)) || defaultVal
     */
    public static BigDecimal paramDecimal(String name, long defaultVal) {
        BigDecimal i = paramDecimal(name);
        if (i == null)
            i = BigDecimal.valueOf(defaultVal);
        assert i != null;
        return i;
    }

    /**
     * BigDecimal.parse(req.getParameter(name)) || defaultVal
     */
    public static BigDecimal paramDecimal(String name, double defaultVal) {
        BigDecimal i = paramDecimal(name);
        if (i == null)
            i = BigDecimal.valueOf(defaultVal);
        assert i != null;
        return i;
    }

    /**
     * BigDecimal.parse(req.getParameter(name)) || defaultVal
     */
    public static BigDecimal paramDecimal(String name, String defaultVal) {
        BigDecimal i = paramDecimal(name);
        if (i != null)
            return i;
        else
            return new BigDecimal(defaultVal);
    }

    // --- Date --- //

    /**
     * DateUtil.parse(req.getParameter(name)) || null
     */
    public static @Nullable
    Date paramDate(String name) {
        return DateUtil.parse(param(name));
    }

    /**
     * DateFormatUtils.fromat(Date.parse(req.getParameter(name)))
     */
    public static @Nullable
    String paramDate2String(String name, String format) {
        Date date = paramDate(name);
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, "yyyy-MM-dd");
    }

    /**
     * DateUtil.parse(req.getParameter(name)) || defaultDate
     */
    public static Date paramDate(String name, Date defaultDate) {
        Date date = DateUtil.parse(param(name));
        return date != null ? date : defaultDate;
    }

    public static @Nullable
    String paramDate2String(String name, Date defaultDate, String format) {
        String dateString = paramDate2String(name, format);
        return dateString != null ? dateString : DateFormatUtils.format(defaultDate, format);
    }

    // --- JSON --- //

    /**
     * JSON.parseObject(req.getParameter(name)) || null
     */
    public static @Nullable
    JSONObject paramJsonObject(String name) {
        try {
            return JSON.parseObject(param(name));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * JSON.parseObject(req.getParameter(name)).toBean(clazz) || null
     */
    public static @Nullable
    <T> T paramJsonObject(String name, Class<T> clazz) {
        try {
            JSONObject jobj = paramJsonObject(name);
            if (jobj == null) {
                return null;
            }
            return jobj.toJavaObject(clazz);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * JSON.parseArray(req.getParameter(name)) || null
     */
    public static @Nullable
    JSONArray paramJsonArray(String name) {
        try {
            return JSON.parseArray(param(name));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * JSON.parseArray(req.getParameter(name)).toList(clazz) || null
     */
    public static @Nullable
    <T> List<T> paramJsonArray(String name, Class<T> clazz) {
        try {
            JSONArray jarr = paramJsonArray(name);
            if (jarr == null) {
                return null;
            }
            return jarr.toJavaList(clazz);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * req.getParameter(name).split(separator)
     */
    public static String[] params(String name, String separator) {
        String param = Requests.param(name, "");
        return StringUtils.split(param, separator);
    }

    /**
     * req.getParameterMap()
     */
    public static Map<String, String[]> params() {
        return req().getParameterMap();
    }

    // --- requestScope --- //

    /**
     * req.getAttribute(name)
     */
    @SuppressWarnings("unchecked")
    public static @Nullable
    <T> T attr(String name) {
        return (T) req().getAttribute(name);
    }

    /**
     * req.setAttribute(name, value)
     */
    public static void attr(String name, @Nullable Object value) {
        req().setAttribute(name, value);
    }

    /**
     * req.setAttribute(name, value || defaultVal)
     * 如果value和defaultVal皆为null
     */
    public static void attr(String name, @Nullable Object value, @Nullable Object defaultVal) {
        if (value != null) {
            attr(name, value);
        } else if (defaultVal != null) {
            attr(name, defaultVal);
        }
    }

    // --- sessionScope --- //

    /**
     * req.getSession()
     */

    public static HttpSession session() {
        return req().getSession();
    }

    /**
     * session.getAttribute(name)
     */
    @SuppressWarnings("unchecked")
    public static <T> Optional<T> session(String name) {
        return Optional.ofNullable((T) session().getAttribute(name));
    }

    /**
     * session.setAttribute(name, value)
     */
    public static void session(String name, @Nullable Object value) {
        session().setAttribute(name, value);
    }

    // --- 杂项 --- //

    /**
     * rsp.sendRedirect(url)
     */
    public static void redirect(String url) throws Redirect {
        rsp().setHeader("Location", url);
        throw new Redirect();
    }

    /**
     * @return request的method是否应当是只读的
     */
    public static boolean isReadOnly() {
        String method = req().getMethod();
        return !"POST".equals(method);
    }

    /**
     * @return 请求的ID（自动生成）
     */
    public static String id() {
        Object id = attr("requestId");
        if (!(id instanceof String))
            attr("requestId", id = IdUtil.newId());
        return id.toString();
    }

    public static void setMaxAge(int seconds) {
        setMaxAge(seconds, TimeUnit.SECONDS);
    }

    public static void setMaxAge(int time, TimeUnit unit) {
        int secs = (int) unit.toSeconds(time);
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        c.add(Calendar.SECOND, secs);
        HttpServletResponse resp = rsp();
        resp.setHeader("Expires", DateFormatUtils.format(c, "EEE, dd MMM yyyy HH:mm:ss") + " GMT");
        resp.setHeader("Cache-Control", "max-age=" + secs);
    }

    public static HttpServletRequest req() {
        Tuple3<HttpServletRequest, HttpServletResponse, List<Callable<String>>> t = ctx.get();
        if (t == null)
            throw new IllegalStateException();
        else
            return t._0;
    }

    public static HttpServletResponse rsp() {
        Tuple3<HttpServletRequest, HttpServletResponse, List<Callable<String>>> t = ctx.get();
        if (t == null)
            throw new IllegalStateException();
        else
            return t._1;
    }

    @SuppressWarnings("serial")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private static final class BadReq extends RuntimeException {
    }

    @SuppressWarnings("serial")
    @ResponseStatus(HttpStatus.FOUND)
    private static final class Redirect extends RuntimeException {
    }

    public static <T> T with(HttpServletRequest req, HttpServletResponse rsp, Callable<T> fn) throws Exception {
        Tuple3<HttpServletRequest, HttpServletResponse, List<Callable<String>>> t0 = ctx.get();
        ctx.set(Tuples.of(req, rsp, new ArrayList<>()));
        try {
            return fn.call();
        } finally {
            ctx.set(t0);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse rsp, Object hanlder) {
        ctx.set(Tuples.of(req, rsp, new ArrayList<>()));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse rsp, Object handler, ModelAndView mv) {

    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getUsername() {
        return getAuthentication().getName();
    }

    public static String getUserId() {
        return JwtTokenUtil.getClaimsFromToken(req().getHeader("token")).getId();
    }
}