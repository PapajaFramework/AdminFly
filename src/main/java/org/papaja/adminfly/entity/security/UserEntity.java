package org.papaja.adminfly.entity.security;

import org.hibernate.annotations.DynamicUpdate;
import org.papaja.adminfly.entity.AbstractEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@SuppressWarnings({"unused"})
@DynamicUpdate
@Entity
@Table(name = "security_users")
public class UserEntity extends AbstractEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled", columnDefinition = "TINYINT(1)")
    private Boolean enabled;

    @Column(name = "expired")
    private Timestamp expired;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "updated")
    private Timestamp updated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "security_users_roles",
        joinColumns = @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
            name = "role_id",
            referencedColumnName = "id"
        )
    )
    private Collection<RoleEntity> roles;

    public UserEntity() {
        roles = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Timestamp getExpired() {
        return expired;
    }

    public void setExpired(Timestamp expired) {
        this.expired = expired;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public Collection<Integer> getRolesIds() {
        return getRoles().stream().map(RoleEntity::getId).collect(Collectors.toList());
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }

    public void addRole(RoleEntity role) {
        roles.add(role);
    }

    public void addRole(Integer id) {
        if (nonNull(id)) {
            addRole(new RoleEntity(id));
        }
    }

    @Override
    public String toString() {
        return String.format("UserEntity{id=%d, username='%s', enabled='%s', roles=%s}", id, username, enabled, roles);
    }
}
