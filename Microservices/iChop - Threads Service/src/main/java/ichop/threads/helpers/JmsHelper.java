package ichop.threads.helpers;

public interface JmsHelper {

   <S,R> R sendAndReceive(String destination, S model, Class<R> clazz);
}
