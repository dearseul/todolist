package todolist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jdk.jshell.Snippet;
import org.hibernate.annotations.CollectionId;
import org.springframework.security.crypto.password.PasswordEncoder;
import todolist.model.commons.BaseEntity;

import java.time.LocalDateTime;

@Entity // todo Builder
public class Users extends BaseEntity {
    @Id @GeneratedValue
    @Column
    private Long seq;
    @Column
    private String userId;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String imgUrl;
    @Column
    private int loginCnt;

//    public static Snippet builder() {
//    }

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

}
