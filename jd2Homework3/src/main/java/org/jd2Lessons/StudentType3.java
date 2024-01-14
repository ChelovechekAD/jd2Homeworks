package org.jd2Lessons;

public class StudentType3 implements StudentType {

    public void learnNewSkill(float time, float talent) {
        float time_part = (time / talent) / ConstValues.DETERMINANT_1_TYPE;
        System.out.println("На разбор будет затрачено: " + time_part);
    }
}
