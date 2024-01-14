package org.jd2Lessons;

public interface Exceptions {

    class WrongStudentType extends Exception {
        public WrongStudentType(){
            super("Incorrect student type.");
        }
    }

}
