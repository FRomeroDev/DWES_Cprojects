package net.ausiasmarch.foxforumserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.foxforumserver.entity.ReplyEntity;
import net.ausiasmarch.foxforumserver.entity.ThreadEntity;
import net.ausiasmarch.foxforumserver.entity.UserEntity;
import net.ausiasmarch.foxforumserver.exception.ResourceNotFoundException;
import net.ausiasmarch.foxforumserver.repository.ReplyRepository;
import net.ausiasmarch.foxforumserver.repository.ThreadRepository;
import net.ausiasmarch.foxforumserver.repository.UserRepository;

@Service
public class ReplyService {
    @Autowired
    ReplyRepository oReplyRepository;
    @Autowired
    UserRepository oUserRepository;
    @Autowired
    ThreadRepository oThreadRepository;

    public ReplyEntity get(Long id) {
        return oReplyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reply not found"));
    }

    public Long create(ReplyEntity oReplyEntity) {
        oReplyEntity.setId(null);
        return oReplyRepository.save(oReplyEntity).getId();
    }

    public ReplyEntity update(ReplyEntity oReplyEntity) {
        return oReplyRepository.save(oReplyEntity);
    }

    public Long delete(Long id) {
        oReplyRepository.deleteById(id);
        return id;
    }

    public Page<ReplyEntity> getPage(Pageable oPageable) {
        return oReplyRepository.findAll(oPageable);
    }

    public Long populate(Integer amount) {
        // Supongamos que tienes IDs específicos de hilo y usuario
        Long userId = 1L; // ID del usuario al que deseas asociar las respuestas
        Long threadId = 1L; // ID del hilo al que deseas asociar las respuestas

        UserEntity user = oUserRepository.findById(userId).orElse(null);
        ThreadEntity thread = oThreadRepository.findById(threadId).orElse(null);

        UserEntity defaultUser = oUserRepository.findById(1L).orElse(null);
        ThreadEntity defaultThread = oThreadRepository.findById(1L).orElse(null);

        if (user == null || thread == null) {
            // Maneja la situación en la que el usuario o el hilo no existen.
            // Puede lanzar una excepción o tomar otro tipo de acción.
        }

        for (int i = 0; i < amount; i++) {
            ReplyEntity reply = new ReplyEntity("title" + i, "body" + i);
            reply.setUser(defaultUser); // Asigna el usuario predeterminado a cada hilo.
            reply.setThread(defaultThread); // Asigna el usuario predeterminado a cada hilo.
            oThreadRepository.save(thread);
            oUserRepository.save(user);
            oReplyRepository.save(reply);
        }

        return oReplyRepository.count();
    }
}
