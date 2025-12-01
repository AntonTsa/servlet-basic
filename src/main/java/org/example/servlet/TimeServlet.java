package org.example.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/")
public class TimeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String zoneParam = req.getParameter("timezone");
        resp.setContentType("text/html; charset=utf-8");
        String zoneId;

        // Default timezone
        if (zoneParam == null || zoneParam.isBlank()) {
            zoneId = "UTC";
        } else {
            String sign = zoneParam.contains("-") ? "-" : "+";
            String time = zoneParam.substring(4);
            String hours;
            String minutes;
            if (!time.contains(":")) {
                hours = (time.length() == 1) ? "0" + time : time;
                minutes = "00";
            } else {
                hours = (time.split(":")[0].length() == 1)
                        ? "0" + time.split(":")[0]
                        : time.split(":")[0];
                minutes = (time.split(":")[1].length() == 1)
                        ? "0" + time.split(":")[1]
                        : time.split(":")[1];
            }
            zoneId = String.format("UTC%s%s:%s", sign, hours, minutes);
        }

        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of(zoneId));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        resp.getWriter().write(zdt.format(formatter));
        resp.getWriter().close();
    }
}
