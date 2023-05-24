public class Graphics {
    public static void drawLostArt() {
        String[] lostArt;
        lostArt = new String[]{
                "*       *****  *****  *****",
                "*       *   *  *        *  ",
                "*       *   *  *****    *  ",
                "*       *   *      *    *  ",
                "******  *****  *****    *  ",
        };
        for (String line : lostArt) {
            System.out.println(line);
        }
    }

    public static void drawPotion() {
        String[] potionArt;
        potionArt = new String[]{
                "   ***   ",
                "  *   *  ",
                "   * *  ",
                " *     * ",
                " *******",
        };
        for (String line : potionArt) {
            System.out.println(line);
        }
    }
}