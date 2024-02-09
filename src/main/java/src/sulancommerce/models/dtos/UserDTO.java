package src.sulancommerce.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.sulancommerce.models.entities.auth.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;

    public UserDTO(User user){
        this.username = user.getUsername();
    }
}
