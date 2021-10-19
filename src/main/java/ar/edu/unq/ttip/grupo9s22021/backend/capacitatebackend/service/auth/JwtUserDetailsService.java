package ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.service.auth;

import java.util.ArrayList;
import java.util.Optional;

import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.model.Usuario;
import ar.edu.unq.ttip.grupo9s22021.backend.capacitatebackend.persistence.UsuarioPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class    JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioPersistence repository;

    //@Autowired
    //PasswordEncoder bcryptEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByNombreDeUsuario(username);
        if(usuario.isPresent()) {
            Usuario result = usuario.get();
            return new User(result.getNombreDeUsuario(), result.getPassword(), new ArrayList<>());

        } else {
            throw new UsernameNotFoundException("No se encontro un usuario con el nombre de usuario: " + username);
        }
    }

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }
}