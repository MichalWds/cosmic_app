package michalwds.commons;

public interface Mapper<F,T> {  //mapper generyczny

    T map (F from); //   daje nam obiekt T z obiektu F
    F reverseMap (T to);

}
