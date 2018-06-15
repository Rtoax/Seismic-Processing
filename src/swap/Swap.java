package src.swap;

    //a#################################
    //a##
    //a##       Swap  --> innerclass
    //a##
    //a#################################
    /**
     * 
     *       Author: Rong Tao
     *     Location: UPC
     *         Time: 2017.04
     *    Modify by: Rong Tao
     */
    public class Swap{
        /* float */
        public static float swap (float value){
            int intValue = Float.floatToRawIntBits (value);
            intValue = swap (intValue);
            return Float.intBitsToFloat (intValue);
            }
        /* int */
        public static int swap (int value){
            int b1 = (value >>  0) & 0xff;
            int b2 = (value >>  8) & 0xff;
            int b3 = (value >> 16) & 0xff;
            int b4 = (value >> 24) & 0xff;
            return b1 << 24 | b2 << 16 | b3 << 8 | b4 << 0;
            }
        /* short */
        public static short swap (short value){
            int b1 = value & 0xff;
            int b2 = (value >> 8) & 0xff;
            return (short) (b1 << 8 | b2 << 0);
            }
        /* long */
        public static long swap (long value){
            long b1 = (value >>  0) & 0xff;
            long b2 = (value >>  8) & 0xff;
            long b3 = (value >> 16) & 0xff;
            long b4 = (value >> 24) & 0xff;
            long b5 = (value >> 32) & 0xff;
            long b6 = (value >> 40) & 0xff;
            long b7 = (value >> 48) & 0xff;
            long b8 = (value >> 56) & 0xff;
            return b1 << 56 | b2 << 48 | b3 << 40 | b4 << 32 |
                   b5 << 24 | b6 << 16 | b7 <<  8 | b8 <<  0;
            }
        /* double */
        public static double swap (double value){
            long longValue = Double.doubleToLongBits (value);
            longValue = swap (longValue);
            return Double.longBitsToDouble (longValue);
            }
        /* short array */
        public static void swap (short[] array){
            for (int i = 0; i < array.length; i++)
                array[i] = swap (array[i]);
            }
        /* int array */
        public static void swap (int[] array){
            for (int i = 0; i < array.length; i++)
                array[i] = swap (array[i]);
            }
        /* long array */
        public static void swap (long[] array){
            for (int i = 0; i < array.length; i++)
                array[i] = swap (array[i]);
            }
        /* float array */
        public static void swap (float[] array){
            for (int i = 0; i < array.length; i++)
                array[i] = swap (array[i]);
            }
        /* double array */
        public static void swap (double[] array){
            for (int i = 0; i < array.length; i++)
                array[i] = swap (array[i]);
            }
      }
