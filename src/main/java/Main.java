public class Main {
    public static void main(String[] args) {
        //  Zadanie01: napisz kod tak aby fragment ponizej dzialal i sie kompilowal. - ale nie mozesz uzywac tablic,
        //  list, setow, zadnych kolekcji czy kolejek, ani konkatenowac to w Stringi czy appendowac
        //  w string buildery

//tworzymy klaske String container ktora bedzie mogla przyjmowac tylko Stringi
// z okreslonym Patternem podanym jako argument.
//podczas tworzenia obiektu nalezy zdefinowac poprawnosc patternu i jesli pattern bedzie "zly- czyli taki ktory
// sie nie kompiluje" to ma zostac rzucony wyjatek InvalidStringContainerPatternException(badPattern)
//wszystkie wyjatki w programie maja dziedziczyc RuntimeException.
//tu w przykladzie dodajemy kody pocztowe
        StringContainer st = new StringContainer("\\d{2}[-]\\d{3}");

        st.add("02-495");//git
        st.add("01-120");//git
        st.add("05-123");//git
        st.add("00-000");//git
//st.add("ala ma kota"); //powinno sie wywalic wyjatkiem InvalidStringContainerValueException(badValue)
        for (int i = 0; i < st.size(); i++) {
            System.out.println(st.get(i)); //powinno wypisac dodane kody pocztowe
        }

        st.remove(0);  //usuwa "02-495"
        st.remove("00-000"); // usuwa "00-000"

        System.out.println("po usunieciu");
        for (int i = 0; i < st.size(); i++) {
            System.out.println(st.get(i)); //powinno wypisac dodane kody pocztowe
        }

        // nasza liste mozna tez parametryzowac tak aby nie dalo sie wrzucac powtorzen np:
        /*StringContainer*/
        st = new StringContainer("\\d{2}[-]\\d{3}", true);
        //jakis parametr np: duplicatedNotAllowed - domyslnie false
        // wtedy np:
        st.add("02-495");//git
        st.add("02-495");//powinno rzucic wyjatkiem DuplicatedElementOnListException
    }
}
