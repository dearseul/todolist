package todolist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class FriendConnection {
    @Id @GeneratedValue
    private long seq;
    private String writerId;
    private long friendSeq;
    private LocalDateTime createDt;
}
