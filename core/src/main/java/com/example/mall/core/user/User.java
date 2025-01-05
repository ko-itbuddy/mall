package com.example.mall.core.user;

import static com.google.common.base.Preconditions.checkArgument;

import com.example.mall.common.entity.BaseAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.util.Collection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
@Comment("사용자")
@Entity
public class User extends BaseAggregateRoot<User> implements UserDetails {

    @Comment("이메일")
    @Column(unique = true, length = MAX_EMAIL_LENGTH)
    private String email;

    @Comment("이름")
    @Column(length = MAX_FIRSTNAME_LENGTH)
    private String firstname;

    @Comment("성")
    @Column(length = MAX_LASTNAME_LENGTH)
    private String lastname;

    @Comment("비밀번호")
    @Column(length = 100)
    private String password;

    @Comment("역할")
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Comment("계정 만료 상태")
    private Boolean expired;

    @Comment("계정 잠금 상태")
    private Boolean locked;

    @Comment("회원 활성화 상태")
    private Boolean enabled;

    public static final int MAX_EMAIL_LENGTH = 320;
    public static final int MAX_FIRSTNAME_LENGTH = 50;
    public static final int MAX_LASTNAME_LENGTH = 50;
    public static final int MAX_PASSWORD_LENGTH = 20;

    public User(Long id, String email, String firstname, String lastname, String password, RoleType role) {
        checkArgument(email != null, "email must be provided.");
        checkArgument(firstname != null, "firstname must be provided.");
        checkArgument(lastname != null, "lastname must be provided.");
        checkArgument(password != null, "password must be provided.");
        checkArgument(role != null, "role must be provided.");

        checkArgument(!email.isBlank(), "email must not be blank.");
        checkArgument(!firstname.isBlank(), "firstname must not be blank.");
        checkArgument(!lastname.isBlank(), "lastname must not be blank.");
        checkArgument(!password.isBlank(), "password must not be blank.");

        checkArgument(email.length() <= MAX_EMAIL_LENGTH, "email length must be less or equal than %s characters.", MAX_EMAIL_LENGTH);
        checkArgument(firstname.length() <= MAX_FIRSTNAME_LENGTH, "firstname length must be less or equal than %s characters.", MAX_FIRSTNAME_LENGTH);
        checkArgument(lastname.length() <= MAX_LASTNAME_LENGTH, "lastname length must be less or equal than %s characters.", MAX_LASTNAME_LENGTH);
        checkArgument(password.length() <= MAX_PASSWORD_LENGTH, "password length must be less or equal than %s characters.", MAX_PASSWORD_LENGTH);

        checkArgument(EmailValidator.getInstance().isValid(email), "email must be valid email format.");

        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.role = role;
        this.enabled = false;
        this.expired = false;
        this.locked = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //TODO: Token 을 추가하여 로직 수정해야함
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
