package org.jd2Lessons;

import java.util.Random;

public class Student{

    private StudentType studentType;
    private Random random;
    private float talent;

    public Student(int type) throws Exceptions.WrongStudentType {
        if ((type >= 1 && type <= 3)) {
            setStudentType(type);
        } else {
            throw new Exceptions.WrongStudentType();
        }
        this.talent = (float) (random.nextInt(10) + 1) / 10;
        System.out.println(this.talent);
    }

    private void setStudentType(int type){
        this.studentType = (type == 1) ? new StudentType1() : (type == 2) ? new StudentType2() : new StudentType3();
    }

}
