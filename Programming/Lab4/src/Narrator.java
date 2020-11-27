public class Narrator {
    private static Human mainPerson = Neznaika.neznaika();

    static void newChapter(int num, String name) {
        System.out.println();
        System.out.println("    Chapter " + num);
        System.out.println("    " + name);
        System.out.println();
    }

    static void changeMainPerson(Human human){
        mainPerson = human;
        System.out.println("What about the " + human + "? We forgotten about him.");
        System.out.println("This is not good, perhaps, since many readers may be interested in his fate.");
        System.out.print("We broke up with the " + human + " when " + human.getLastEvents());
    }

    static void println(String s){
        System.out.println(s);
        mainPerson.writelnToLastEvents(s);
    }

    static void print(String s){
        System.out.print(s);
        mainPerson.writeToLastEvents(s);
    }
}
