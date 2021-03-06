package com.gestor.instituto.service;

import com.gestor.instituto.models.Alumno;
import com.gestor.instituto.models.Asignatura;
import com.gestor.instituto.models.Usuario;
import com.gestor.instituto.repository.AlumnoRepository;
import com.gestor.instituto.service.base.BaseService;

import com.gestor.instituto.upload.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService extends BaseService<Alumno, Long, AlumnoRepository> {

    public AlumnoService(AlumnoRepository repo) {
        super(repo);
    }


    ///metodo
    @Autowired
    UsuarioService uS;
    @Autowired
    EnvioEmail eE;

    public void nuevoAlumno(Usuario usu){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String pass;
        pass=uS.generateRandomString(8);
        usu.setValidacion(pass);
        usu.setPassword(encoder.encode(uS.generateRandomString(8)));
        uS.save(usu);
        eE.sendEmail(usu, "Clave de acceso", "Clave cambio de contraseña: " + usu.getValidacion() + " ");
    }

    public void estadoFalse(Alumno alumno){

       if(!alumno.isAccountNonLocked()){
           alumno.setCurso(null);
       }
    }
/*
    @Query("Select e from alumno e where curso.asignatura =: ASIGNATURA")
    public List<Alumno> alumnoAsignatura(Asignatura asignatura) {
        return repositorio.alumnoAsignatura(asignatura);
    }*/
}
