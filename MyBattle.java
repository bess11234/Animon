
public class MyBattle {
    private static int num = 0;

    public static int getNum() {
        return num;
    }

    public static void setNum(int num) {
        MyBattle.num = num;
    }
    public static void selected(MyController ct){
        if(ct.down && num == 0){MyBattle.setNum(2);}
        if(ct.right && num == 0){MyBattle.setNum(1);}
        if(ct.down && num == 1){MyBattle.setNum(3);}
        if(ct.left && num == 1){MyBattle.setNum(0);}
        if(ct.up && num == 2){MyBattle.setNum(0);}
        if(ct.right && num == 2){MyBattle.setNum(3);}
        if(ct.up && num == 3){MyBattle.setNum(1);}
        if(ct.left && num == 3){MyBattle.setNum(2);}
    }
}
