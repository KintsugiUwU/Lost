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
    public static void drawSword() {
        String[] swordArt;
        swordArt = new String[]{
                "*",
                "* *",
                " * *",
                "  ***",
                "    *",
        };
        for (String line : swordArt) {
            System.out.println(line);
        }
    }
}