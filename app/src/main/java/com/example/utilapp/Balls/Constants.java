package com.example.utilapp.Balls;

public class Constants {
    public static class game {
        //        public static final String base_url="http://192.168.1.7:8080/hello?id=";
        public static final String base_url = "http://121.89.215.65:8080/hello?id=";
    }

    public static class pad {
        public static final int inner_radius = 50;
        public static final int outter_radius = 100;
    }

    public static class ball {
        public static final int enlarge_socore = 1;
        public static final int default_minimum_id = 10000;
        public static final int map_rect_size = 5000;
        private static final int SHAPE_COUNT = 3;
        public static final float speed_enlarge = 0.5f;
        public static final float fixed_ball_size = 10f;
        public static final float user_initial_ball_size = 50f;
        public static final int shape_oval = 0;
        public static final int initial_ball_count = 10000;
        public static final float interpolate_speed = 0.01f;
        public static final boolean interpolate_mode = true;

        public static int randomShape() {
            return (int) (Math.random() * SHAPE_COUNT);
        }
    }

    public static class colors {
        public static final String[] BALL_COLORS = new String[]{
                "#ffdad049",
                "#ff63c33c",
                "#ffce3af4",
                "#ff3280db",
                "#ffa061ee",
                "#ff59a0d8",
                "#ffe03c5e",
                "#ff9800",
                "#607d8b",
                "#4caf50",
                "#f44336",
                "#9c27b0",
                "#2196f3",
                "#00bcd4",
                "#009688",
                "#3ffebb"
        };// 颜色表

        public static String randomlyPickOne() {
            int index = (int) (Math.random() * BALL_COLORS.length);
            return BALL_COLORS[index];
        }
    }
}
