package entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Index implements Serializable {
    private int indexNumber;
    private int vacancy;
    private int academicUnits;
    private String courseCode;
    private ArrayList<Lesson> lessons;
    private Queue<Student> waitingList;
    private ArrayList<Student> studentsRegistered;

    public Index(int indexNumber, int vacancy, int academicUnits, String courseCode) {
        this.indexNumber = indexNumber;
        this.vacancy = vacancy;
        this.academicUnits = academicUnits;
        this.courseCode = courseCode;
        lessons = new ArrayList<>();
        waitingList = new LinkedList<>();
        studentsRegistered = new ArrayList<>();
    }

    public Index(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public int getAcademicUnits() {
        return academicUnits;
    }

    public void setAcademicUnits(int academicUnits) {
        this.academicUnits = academicUnits;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Queue<Student> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(Queue<Student> waitingList) {
        this.waitingList = waitingList;
    }

    public ArrayList<Student> getStudentsRegistered() {
        return studentsRegistered;
    }

    public void setStudentsRegistered(ArrayList<Student> studentsRegistered) {
        this.studentsRegistered = studentsRegistered;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy - this.getStudentsRegistered().size();
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void addLesson(int day, String venue, String lessonType, LocalTime startTime, LocalTime endTime) {
        if (startTime.compareTo(endTime) >= 0) {
            System.out.println("Error... Start time must be earlier than end time");
            System.out.println("System will not add this lesson");
            return;
        }
        // Check for clash with existing sessions
        Lesson session = new Lesson(day, venue, lessonType, startTime, endTime);
        for (Lesson lesson : lessons
        ) {
            if (session.checkCLash(lesson)) {
                System.out.println("Lesson clashes with an existing leeson in this index number");
                System.out.println("System will not add this lesson");
                return;
            }
        }
        if (checkLessonValidity(session))
            this.lessons.add(session);
        else {
            System.out.println("Illegal combination of classes");
            System.out.println("Can only have (Lecture) or (Lecture, Tutorial) or (Lecture, Tutorial, Lab)");
            System.out.println();
        }
    }

    public void addLesson(int day, String venue, String lessonType, LocalTime startTime,
                          LocalTime endTime, String labWeek) {
        if (startTime.isAfter(endTime)) {
            System.out.println("Error... Start time must be earlier than end time");
            System.out.println("System will not add this lesson");
            return;
        }
        // Check for clash with existing sessions
        Lesson session = new Lesson(day, venue, lessonType, labWeek, startTime, endTime);
        for (Lesson lesson : lessons
        ) {
            if (session.checkCLash(lesson)) {
                System.out.println("Lesson clashes with an existing lesson in this index number");
                System.out.println("System will not add this lesson");
                return;
            }
        }
        if (checkLessonValidity(session))
            this.lessons.add(session);
    }

    private boolean checkLessonValidity(Lesson lesson) {
        String lessonType = lesson.getLessonType();
        if (lessonType.equals("lecture"))
            return true;
        boolean isTutorial = false;
        boolean isLab = false;
        if (lessonType.equals("tutorial"))
            isTutorial = true;
        else if (lessonType.equals("lab"))
            isLab = true;
        boolean hasLecture = false;
        boolean hasTutorial = false;
        for (Lesson s : lessons) {
            String availableLesson = s.getLessonType();
            if (availableLesson.equals("lecture"))
                hasLecture = true;
            else if (availableLesson.equals("tutorial"))
                hasTutorial = true;
        }
        return (hasLecture && isTutorial) || (hasLecture && hasTutorial && isLab);
    }

    public void addToWaitingList(Student student) {
        waitingList.add(student);
        student.addIndexOnWaitList(this);
    }

    public void removeFromWaitingList(Student student) {
        waitingList.remove(student);
        student.removeIndexFromWaitList(this);
    }

    public void addStudent(Student student) {
        this.studentsRegistered.add(student);
        this.vacancy -= 1;
    }

    public void removeStudent(Student student) {
        this.studentsRegistered.remove(student);
        this.vacancy += 1;
    }


    public boolean isFilled() {
        return this.vacancy == 0;
    }

    public void print() {
        System.out.print("Index number: " + this.getIndexNumber());
        System.out.print(", Vacancy: " + this.getVacancy());
        System.out.print(" Students Registered: " + studentsRegistered.size());
        System.out.println(" Students on Waiting list: " + waitingList.size());

        for (Lesson lesson : lessons) {
            lesson.print();
        }
    }

    public boolean equals(Object object) {
        return (indexNumber == ((Index) object).getIndexNumber());
    }

    public static void printObjects(ArrayList<Object> indexes) {
        for (Object index : indexes
        ) {
            if (index instanceof Index)
                ((Index) index).print();
        }
    }

}
