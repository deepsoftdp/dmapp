package pl.deepsoft.dmapp.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AppExceptionDto implements Serializable {

    String wiadomosc;
}
