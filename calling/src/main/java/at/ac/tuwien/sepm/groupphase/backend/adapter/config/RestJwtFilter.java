package at.ac.tuwien.sepm.groupphase.backend.adapter.config;

import at.ac.tuwien.sepm.groupphase.backend.domain.common.JwtTokenProvider;
import at.ac.tuwien.sepm.groupphase.backend.domain.common.Role;
import at.ac.tuwien.sepm.groupphase.backend.domain.common.Tuple;
import at.ac.tuwien.sepm.groupphase.backend.domain.common.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class RestJwtFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RestJwtFilter.class);

    private final JwtTokenProvider jwtTokenProvider;

    public RestJwtFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if(httpServletRequest.getRequestURI().contains("/rest") && !httpServletRequest.getRequestURI().contains("/rest/auth")){
            Tuple<String, String> tokenTuple = WebUtils.getTokenUserRoles(createHeaderMap(request), jwtTokenProvider);
            if(tokenTuple.getB() == null || !tokenTuple.getB().contains(Role.USERS.name())){
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(401);
                logger.info("Request denied : ", httpServletRequest.getRequestURL().toString());
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private Map<String, String> createHeaderMap(ServletRequest request){
        Map<String, String> header = new HashMap<>();
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        for(Iterator<String> iterator = httpServletRequest.getHeaderNames().asIterator(); iterator.hasNext();){
            String key = iterator.next();
            header.put(key, httpServletRequest.getHeader(key));
        }
        return header;
    }
}
