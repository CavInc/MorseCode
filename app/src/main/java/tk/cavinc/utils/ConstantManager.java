package tk.cavinc.utils;

/**
 * Created by cav on 14.04.20.
 */

public interface ConstantManager {
    int ATTACK = 200; //Атака. Сглаживание переднего фронта
    int RELEASE = 500; // Релиз. Сглаживание заднего фронта
    int SAMPLE_RATE = 44100; // стандартная частота дискретизации для wav файла
    int SPEED = 60; // длительность звука в мс
}
