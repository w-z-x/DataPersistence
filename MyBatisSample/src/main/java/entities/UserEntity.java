package entities;

import lombok.Data;

@Data
public class UserEntity extends Entity {
    private String name;
    private String address;
}
