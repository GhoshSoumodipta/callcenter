package at.ac.tuwien.sepm.groupphase.backend.adapter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@WebFilter
@Component
public class ForwardServletFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ForwardServletFilter.class);
    public static final List<Locale> SUPPORTED_LOCALES = List.of(Locale.ENGLISH, Locale.GERMAN);
    public static final List<String> REST_PATHS = List.of("/rest", "/signalingsocket", "/actuator", "/swagger-ui.html", "/swagger-ui", "/v3");
    public static final List<String> LANGUAGE_PATHS = SUPPORTED_LOCALES.stream().map(myLocale -> String.format("/%s/", myLocale.getLanguage())).collect(Collectors.toList());
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest myRequest = (HttpServletRequest) request;
        if(REST_PATHS.stream().anyMatch(restEndPoint -> 0 == myRequest.getServletPath().indexOf(restEndPoint)) || (LANGUAGE_PATHS.stream().anyMatch(langPath -> 0 == myRequest.getServletPath().indexOf(langPath)) && (myRequest.getServletPath().contains(".") && !myRequest.getServletPath().contains("?")))){
            chain.doFilter(myRequest, response);
        }else{
            Iterable<Locale> iterable = () -> myRequest.getLocales().asIterator();
            Locale userLocale = StreamSupport.stream(iterable.spliterator(), false).filter(myLocale -> SUPPORTED_LOCALES.contains(myLocale)).findFirst().orElse(Locale.ENGLISH);
            String forwardPath = String.format("/%s/index.html", userLocale.getLanguage());
            RequestDispatcher dispatcher = myRequest.getServletContext().getRequestDispatcher(forwardPath);
            dispatcher.forward(myRequest, response);
            return;
        }
    }
}
