package tk.cavinc.utils;

import java.util.ArrayList;

/**
 * Created by cav on 14.04.20.
 */

public class Func {


    // frequnce = 1000
    public static byte[] createDot(int frequence){
        byte[] FileDot;
        int durationDot = 6000 / ConstantManager.SPEED;
        int countDot = ((int) (((double) ConstantManager.SAMPLE_RATE) * 2.0d * (((double) durationDot) / 1000.0d))) & -2;
        int totalDataLen = countDot + 36;
        long byteRate = (long) ((ConstantManager.SAMPLE_RATE * 16) / 8);

        FileDot = new byte[(countDot + 44)];
        FileDot[0] = 82;
        FileDot[1] = 73;
        FileDot[2] = 70;
        FileDot[3] = 70;
        FileDot[4] = (byte) (totalDataLen & 255);
        FileDot[5] = (byte) ((totalDataLen >> 8) & 255);
        FileDot[6] = (byte) ((totalDataLen >> 16) & 255);
        FileDot[7] = (byte) ((totalDataLen >> 24) & 255);
        FileDot[8] = 87;
        FileDot[9] = 65;
        FileDot[10] = 86;
        FileDot[11] = 69;
        FileDot[12] = 102;
        FileDot[13] = 109;
        FileDot[14] = 116;
        FileDot[15] = 32;
        FileDot[16] = 16;
        FileDot[17] = 0;
        FileDot[18] = 0;
        FileDot[19] = 0;
        FileDot[20] = 1;
        FileDot[21] = 0;
        FileDot[22] = 1;
        FileDot[23] = 0;
        FileDot[24] = (byte) (ConstantManager.SAMPLE_RATE & 255);
        FileDot[26] = (byte) ((ConstantManager.SAMPLE_RATE >> 16) & 255);
        FileDot[25] = (byte) ((ConstantManager.SAMPLE_RATE >> 8) & 255);
        FileDot[27] = (byte) ((ConstantManager.SAMPLE_RATE >> 24) & 255);
        FileDot[28] = (byte) ((int) (255 & byteRate));
        FileDot[29] = (byte) ((int) ((byteRate >> 8) & 255));
        FileDot[30] = (byte) ((int) ((byteRate >> 16) & 255));
        FileDot[31] = (byte) ((int) ((byteRate >> 24) & 255));
        FileDot[32] = 2;
        FileDot[33] = 0;
        FileDot[34] = 16;
        FileDot[35] = 0;
        FileDot[36] = 100;
        FileDot[37] = 97;
        FileDot[38] = 116;
        FileDot[39] = 97;
        FileDot[40] = (byte) (countDot & 255);
        FileDot[41] = (byte) ((countDot >> 8) & 255);
        FileDot[42] = (byte) ((countDot >> 16) & 255);
        FileDot[43] = (byte) ((countDot >> 24) & 255);
        // генерируем тело wav файла
        //  начало с плавным возрастанием амплитуды
        for (int i = 0; i < countDot; i += 2) {
            if (i < ConstantManager.ATTACK) {
                short sample = (short) ((int) (((Math.sin((3.141592653589793d * ((double) i)) / ((double) (ConstantManager.SAMPLE_RATE / frequence))) * 32767.0d) * ((double) i)) / ((double) ConstantManager.ATTACK)));
                FileDot[i + 44] = (byte) (sample & 255);
                FileDot[i + 44 + 1] = (byte) ((sample >> 8) & 255);
            } else if (i > countDot - ConstantManager.RELEASE) {
                short sample2 = (short) ((int) (((Math.sin((3.141592653589793d * ((double) i)) / ((double) (ConstantManager.SAMPLE_RATE / frequence))) * 32767.0d) * ((double) (countDot - i))) / ((double) ConstantManager.RELEASE)));
                FileDot[i + 44] = (byte) (sample2 & 255);
                FileDot[i + 44 + 1] = (byte) ((sample2 >> 8) & 255);
            } else {
                short sample3 = (short) ((int) (Math.sin((3.141592653589793d * ((double) i)) / ((double) (ConstantManager.SAMPLE_RATE / frequence))) * 32767.0d));
                FileDot[i + 44] = (byte) (sample3 & 255);
                FileDot[i + 44 + 1] = (byte) ((sample3 >> 8) & 255);
            }
        }
        return FileDot;
    }
    public static byte[] createDash(int frequence){
        byte[] FileDash;

        int durationDot = 6000 / ConstantManager.SPEED;
        int durationDash = durationDot * 3;
        int countDash = ((int) (((double) ConstantManager.SAMPLE_RATE) * 2.0d * (((double) durationDash) / 1000.0d))) & -2;
        int totalDataLen2 = countDash + 36;
        long byteRate2 = (long) ((ConstantManager.SAMPLE_RATE * 16) / 8);

        FileDash = new byte[(countDash + 44)];

        FileDash[0] = 82;
        FileDash[1] = 73;
        FileDash[2] = 70;
        FileDash[3] = 70;
        FileDash[4] = (byte) (totalDataLen2 & 255);
        FileDash[5] = (byte) ((totalDataLen2 >> 8) & 255);
        FileDash[6] = (byte) ((totalDataLen2 >> 16) & 255);
        FileDash[7] = (byte) ((totalDataLen2 >> 24) & 255);
        FileDash[8] = 87;
        FileDash[9] = 65;
        FileDash[10] = 86;
        FileDash[11] = 69;
        FileDash[12] = 102;
        FileDash[13] = 109;
        FileDash[14] = 116;
        FileDash[15] = 32;
        FileDash[16] = 16;
        FileDash[17] = 0;
        FileDash[18] = 0;
        FileDash[19] = 0;
        FileDash[20] = 1;
        FileDash[21] = 0;
        FileDash[22] = 1;
        FileDash[23] = 0;
        FileDash[24] = (byte) (ConstantManager.SAMPLE_RATE & 255);
        FileDash[26] = (byte) ((ConstantManager.SAMPLE_RATE >> 16) & 255);
        FileDash[25] = (byte) ((ConstantManager.SAMPLE_RATE >> 8) & 255);
        FileDash[27] = (byte) ((ConstantManager.SAMPLE_RATE >> 24) & 255);
        FileDash[28] = (byte) ((int) (255 & byteRate2));
        FileDash[29] = (byte) ((int) ((byteRate2 >> 8) & 255));
        FileDash[30] = (byte) ((int) ((byteRate2 >> 16) & 255));
        FileDash[31] = (byte) ((int) ((byteRate2 >> 24) & 255));
        FileDash[32] = 2;
        FileDash[33] = 0;
        FileDash[34] = 16;
        FileDash[35] = 0;
        FileDash[36] = 100;
        FileDash[37] = 97;
        FileDash[38] = 116;
        FileDash[39] = 97;
        FileDash[40] = (byte) (countDash & 255);
        FileDash[41] = (byte) ((countDash >> 8) & 255);
        FileDash[42] = (byte) ((countDash >> 16) & 255);
        FileDash[43] = (byte) ((countDash >> 24) & 255);


        // генерируем тело wav файла
        for (int i2 = 0; i2 < countDash; i2 += 2) {
            if (i2 < ConstantManager.ATTACK) {
                short sample4 = (short) ((int) (((Math.sin((3.141592653589793d * ((double) i2)) / ((double) (ConstantManager.SAMPLE_RATE / frequence))) * 32767.0d) * ((double) i2)) / ((double) ConstantManager.ATTACK)));
                FileDash[i2 + 44] = (byte) (sample4 & 255);
                FileDash[i2 + 44 + 1] = (byte) ((sample4 >> 8) & 255);
            } else if (i2 > countDash - ConstantManager.RELEASE) {
                short sample5 = (short) ((int) (((Math.sin((3.141592653589793d * ((double) i2)) / ((double) (ConstantManager.SAMPLE_RATE / frequence))) * 32767.0d) * ((double) (countDash - i2))) / ((double) ConstantManager.RELEASE)));
                FileDash[i2 + 44] = (byte) (sample5 & 255);
                FileDash[i2 + 44 + 1] = (byte) ((sample5 >> 8) & 255);
            } else {
                short sample6 = (short) ((int) (Math.sin((3.141592653589793d * ((double) i2)) / ((double) (ConstantManager.SAMPLE_RATE / frequence))) * 32767.0d));
                FileDash[i2 + 44] = (byte) (sample6 & 255);
                FileDash[i2 + 44 + 1] = (byte) ((sample6 >> 8) & 255);
            }
        }
        return FileDash;
    }

    public static String[] splitLesson(String lesson){
        ArrayList<String> sb = new ArrayList<>();
        for (int i = 0;i<lesson.length();i++){
            sb.add(lesson.substring(i,i+1));
        }
        return (String[]) sb.toArray();
    }

}
