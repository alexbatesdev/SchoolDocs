public class Parameters {
    public static void DoIt() {
        Integer intResults;
        Integer x = 3;
        Integer y = 5;
        String strIn = "Bob";
        RefTest clsByRefTest = new RefTest();
        clsByRefTest.x = 99;
        int[] aryNums = {2, 4, 6};
        intResults = MaybeChange(x, y, strIn, clsByRefTest, aryNums);
        System.out.println(intResults);
    }

    public static int MaybeChange(int x, int y, String s, RefTest clsByRefTest, int[] aryNums) {

        x = x + 1;
        y++;
        s = "Han";
        clsByRefTest.x = clsByRefTest.x + 1;
        aryNums[0] = 40;

        return x + y;
    }
}
