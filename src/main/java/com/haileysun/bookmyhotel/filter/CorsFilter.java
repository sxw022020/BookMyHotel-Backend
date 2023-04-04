package com.haileysun.bookmyhotel.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * To Enable CORS on the backend,
 * we need to handle the CORS *preflight requests* (see Notes.md) and
 * return the dedicated CORS header to the frontend.
 */
@Component
// the filter will be executed before all other filters that
// have not been assigned a specific order or have a lower precedence
@Order(Ordered.HIGHEST_PRECEDENCE)
/**
 * OncePerRequestFilter：
 *  - Filter base class that aims to guarantee
 *    a single execution per request dispatch,
 *    on any servlet container.
 *    --> ensures that the filter is *applied only once per request*
 */
public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // allows any domain to access the resources
        response.setHeader("Access-Control-Allow-Origin", "*");

        // allow POST, GET, OPTIONS, and DELETE HTTP methods for cross-origin requests
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

        // allow the "Authorization" and "Content-Type" headers in cross-origin requests.
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(response.SC_OK);
        // If the request is not an OPTIONS request,
        // the filter chain is continued by calling filterChain.doFilter(request, response),
        // which allows further processing of the request.
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
