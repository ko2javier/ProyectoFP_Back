package com.FP_Final.FP.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.FP_Final.FP.model.Authorities;
import com.FP_Final.FP.model.Users;
import com.FP_Final.FP.repository.AuthoritiesRepository;
import com.FP_Final.FP.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthoritiesRepository authorities_Repo;

	// Método para obtener todos los usuarios
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}

	// Método para obtener un usuario por su username
	public Optional<Users> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/* Metodo para borrar un usuario */
	public void deleteUserById(int id) {

		if (!userRepository.existsById(id)) {
			throw new RuntimeException("User no encontrado con id: " + id);
		}
		userRepository.deleteById(id);
	}

	public Users crearUsuario(String username, String rawPassword, String permiso) {

		// (String username, String password, boolean enabled, String permiso

		// Le meto mano al user y lo salvo !
		Users nuevoUsuario = new Users(username, passwordEncoder.encode(rawPassword), true, permiso);

		userRepository.save(nuevoUsuario);

		// Le hand al authority y lo salvo !
		authorities_Repo.save(new Authorities(username, "ROLE_" + permiso));

		return nuevoUsuario;

	}

	public Users Update_Usuario(Users user) {

		Users usuario_update = userRepository.findById(user.getId()).get();
		Authorities authority_update = authorities_Repo.findById(user.getId()).get();

		// Actualizo los 2 campos que vienen !!
		  usuario_update.setPermiso(user.getPermiso());
		  usuario_update.setPassword(passwordEncoder.encode(user.getPassword()));
		  userRepository.save(usuario_update);
		
		 authority_update.setRole("ROLE_" + user.getPermiso());
		 

		 authorities_Repo.save(authority_update);

		 

		return usuario_update;

	}

}
