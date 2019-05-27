package net.cakecdn.api.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserProfile {
    @Id
    private Long userId;
    private Long unusedTrafficBytes;
    private Long usedTrafficBytes;
}
