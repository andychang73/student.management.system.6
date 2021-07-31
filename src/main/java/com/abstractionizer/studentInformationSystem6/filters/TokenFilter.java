package com.abstractionizer.studentInformationSystem6.filters;

import com.abstractionizer.studentInformationSystem6.enums.ErrorCode;
import com.abstractionizer.studentInformationSystem6.exceptions.CustomExceptions;
import com.abstractionizer.studentInformationSystem6.models.dto.user.UserInfo;
import com.abstractionizer.studentInformationSystem6.sis.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@AllArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

    private final LoginService loginService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String token = httpServletRequest.getHeader("token");

        if(Objects.isNull(token) || token.isEmpty()){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        final UserInfo userInfo = loginService.getUserInfoByToken(token).orElseThrow(() -> new CustomExceptions(ErrorCode.INVALID_TOKEN));

        if(Objects.isNull(SecurityContextHolder.getContext().getAuthentication())){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userInfo, null, userInfo.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        setAttribute(httpServletRequest, userInfo);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void setAttribute(HttpServletRequest request, UserInfo userInfo){
        switch(userInfo.getUserGroup()){
            case STAFF: request.setAttribute("Staff", userInfo);
            break;
            case STUDENT: request.setAttribute("Student", userInfo);
            break;
        }
    }
}
