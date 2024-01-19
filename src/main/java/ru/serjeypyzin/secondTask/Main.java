package ru.serjeypyzin.secondTask;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
Предположить, что числа в исходном массиве из 9 элементов имеют диапазон[0, 3], и представляют собой, например,
состояния ячеек поля для игры в крестики-нолики, где 0 – это пустое поле, 1 – это поле с крестиком,
2 – это поле с ноликом, 3 – резервное значение. Такое предположение позволит хранить в одном числе типа int всё поле 3х3.
Записать в файл 9 значений так, чтобы они заняли три байта.
 */
public class Main {

    public static void main(String[] args) {
        int[] gameField = {1, 2, 0, 3, 1, 0, 2, 0, 3};

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("gameField.txt"))) {
            int compactedValue = 0;

            for (int i = 0; i < gameField.length; i++) {
                compactedValue |= (gameField[i] & 0x03) << (2 * (2 - i % 3));

                if (i % 3 == 2) {
                    dos.writeByte(compactedValue);
                    compactedValue = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
