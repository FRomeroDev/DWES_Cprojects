package net.ausiasmarch.foxforumserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.foxforumserver.entity.ThreadEntity;
import net.ausiasmarch.foxforumserver.entity.UserEntity;
import net.ausiasmarch.foxforumserver.exception.ResourceNotFoundException;
import net.ausiasmarch.foxforumserver.repository.ThreadRepository;
import net.ausiasmarch.foxforumserver.repository.UserRepository;

@Service
public class ThreadService {
    @Autowired
    ThreadRepository oThreadRepository;
    @Autowired
    UserRepository oUserRepository;

    public ThreadEntity get(Long id) {
        return oThreadRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Thread not found"));
    }

    public Long create(ThreadEntity oThreadEntity) {
        oThreadEntity.setId(null);
        return oThreadRepository.save(oThreadEntity).getId();
    }

    public ThreadEntity update(ThreadEntity oThreadEntity) {
        return oThreadRepository.save(oThreadEntity);
    }

    public Long delete(Long id) {
        oThreadRepository.deleteById(id);
        return id;
    }

    public Page<ThreadEntity> getPage(Pageable oPageable) {
        return oThreadRepository.findAll(oPageable);
    }

    public Long populate(Integer amount) {
        // Supongamos que tienes un usuario predeterminado con un ID conocido, por
        // ejemplo, 1.
        UserEntity defaultUser = oUserRepository.findById(1L).orElse(null);

        if (defaultUser == null) {
            // Maneja la situación en la que el usuario predeterminado no existe.
            // Puede lanzar una excepción o tomar otro tipo de acción.
        }

        for (int i = 0; i < amount; i++) {
            ThreadEntity thread = new ThreadEntity("title" + i);
            thread.setUser(defaultUser); // Asigna el usuario predeterminado a cada hilo.
            oThreadRepository.save(thread);
        }
        return oThreadRepository.count();
    }
}
