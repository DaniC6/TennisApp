package com.DaniC.TennisApp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
// jwt mezzo di trasporto codificato di alcune informazioni
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        if (request.getServletPath().contains("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader("Authorization"); // all' interno della request vedi dentro l'header una voce "Authorization" e la prende
        final String jwt;
        final String username;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) { // se l'header è null o l header non comincia con "Bearer "
            filterChain.doFilter(request, response); //applica filtro
            return; // non fa nulla
        }
        jwt = authHeader.substring(7); // esclude la scitta bearer e lo spazio in modo che jwt contiene soltanto il token
        username = jwtService.extractUsername(jwt); // estrae lo user impostato all interno del token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); // loadUserByUsername() fa una query sul db sulla tabella user cercando lo user appena estratto dal token
            if (jwtService.isTokenValid(jwt, userDetails)) { // se il token è valido
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken( // vedo che la password matcha e vado avanti
                        userDetails, // passando lo UserDetails (Iil nostro User)
                        null, //
                        userDetails.getAuthorities() // passando il ruolo
                );
                authToken.setDetails( // una volta verificato utente e ruoli setta i dati ch arrivano dalla request ( s prende username e passw)
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken); // setta l' auth token all'interno del SecurityContextHolder (L'ecosistema di spring security)
                // l'auth token viene memorizzato nell ecosistema di spring security
            }
        }
        filterChain.doFilter(request, response);
    }
}
