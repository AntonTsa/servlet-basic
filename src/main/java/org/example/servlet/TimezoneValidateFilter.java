package org.example.servlet;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;

@WebFilter(value = "/*")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String zoneParam = req.getParameter("timezone");
        if (zoneParam != null) {
            try {
                ZoneId.of(zoneParam.replace(' ', '+'));
                chain.doFilter(req, res);
            } catch (Exception e) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                res.setContentType("application/json; charset=utf-8");
                res.getWriter().write("{ \"message\" : \"Invalid timezone\" }");
                res.getWriter().close();
            }
        } else {
            chain.doFilter(req, res);
        }
    }

}
