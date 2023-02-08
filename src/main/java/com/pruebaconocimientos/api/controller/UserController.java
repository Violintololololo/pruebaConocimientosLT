package com.pruebaconocimientos.api.controller;

import com.pruebaconocimientos.api.exception.ResourseNotFoundException;
import com.pruebaconocimientos.api.model.User;
import com.pruebaconocimientos.api.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api_web/usuarios")
public class UserController {
    @Autowired
    private IUserRepository iUserRepository;


    @PostMapping("/crear")
    public User createUserName(@RequestBody User user){
        return iUserRepository.save(user);
    }

    @GetMapping("/lista")
    public List<User> listUsers(){
        return iUserRepository.findAll();
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User userDetail){
        User user = iUserRepository.findById(id).orElseThrow(() -> new ResourseNotFoundException("El usuario no existe:"
        + id));

        user.setName(userDetail.getName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());

        User updateUser = iUserRepository.save(user);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable long id){
        User user = iUserRepository.findById(id).orElseThrow(() -> new ResourseNotFoundException("El usuario con el id " +
                id + " No existe"));
        iUserRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

