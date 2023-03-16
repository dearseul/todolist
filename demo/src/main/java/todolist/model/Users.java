package todolist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity // todo Builder
public class Users {
    @Id @GeneratedValue
    private Long seq;
    private String userId;
    private String password;
    private String name;
    private LocalDateTime createDt;
    private LocalDateTime lastLoginDt;
    private String imgUrl;
    private int loginCnt;

}
