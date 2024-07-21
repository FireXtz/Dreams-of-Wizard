package utilz;

public class Constants {

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;


    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int ATACK_1 = 3;
        public static final int ATACK_2 = 4;
        public static final int CHARGE_1 = 5;
        public static final int CHARGE_2 = 6;
        public static final int DEAD = 7;
        public static final int HURT = 8;
        public static final int MAGIC_ARROW = 9;
        public static final int MAGIC_SPHERE = 10;
        public static final int WALK = 11;


        public static int getSpriteAmount(int playerAction) {
            switch (playerAction) {
                case RUNNING:
                    return 8;

                case IDLE:
                    return 8;

                case JUMP:
                    return 8;

                case ATACK_1:
                    return 7;

                case ATACK_2:
                    return 9;

                case CHARGE_1:
                    return 9;

                case CHARGE_2:
                    return 6;

                case DEAD:
                    return 4;

                case HURT:
                    return 4;

                case MAGIC_ARROW:
                    return 6;

                case MAGIC_SPHERE:
                    return 16;

                case WALK:
                    return 7;

                default:
                    return 1;

            }
        }
    }
}
