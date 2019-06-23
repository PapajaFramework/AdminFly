package org.papaja.adminfly.service.security;

import org.papaja.adminfly.entity.security.RoleEntity;
import org.papaja.adminfly.repository.security.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public List<RoleEntity> getRoles() {
        return repository.getRoles();
    }

    public List<RoleEntity> getRoles(Integer... ids) {
        return getRoles(Arrays.asList(ids));
    }

    public List<RoleEntity> getRoles(List<Integer> ids) {
        ids.removeIf(Objects::isNull);

        return repository.getRoles(ids);
    }

    public void store(RoleEntity entity) {
        repository.merge(entity);
    }

}
