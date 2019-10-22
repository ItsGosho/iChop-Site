package ichop.tokens.domain.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("password_tokens")
public class PasswordToken extends Token {

}
