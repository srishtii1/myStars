package controller;

import entity.Course;
import entity.Index;
import entity.Student;


// Most important class

public class UpdateManager {
    private ObjectEntityController studentRecordsMgr;
    private ObjectEntityController courseMgr;
    private ObjectEntityController indexMgr;

    public UpdateManager() {
        studentRecordsMgr = new StudentRecordsMgr();
        courseMgr = new CourseMgr();
    }

    public void updateStudentName(String userName, String name) {
        ((StudentRecordsMgr) studentRecordsMgr).loadStudentObjectList();
        Object existingStudent = studentRecordsMgr.getObjectFromList(userName);
        if (existingStudent != null) {
            ((Student) existingStudent).setName(name);
            for (Index index : ((Student) existingStudent).getIndexRegistered()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getStudentsRegistered()
                ) {
                    if (student.equals(existingStudent))
                        student.setName(name);
                }
            }

            for (Index index : ((Student) existingStudent).getIndexOnWaitList()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getWaitingList()
                ) {
                    if (student.equals(existingStudent))
                        student.setName(name);
                }
            }
            System.out.println("Student updated");
            ((CourseMgr) courseMgr).saveCourseObjectList();
            ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
            ((Student) existingStudent).print();
        } else {
            System.out.println("Student doesn't exist in the database... Cannot update student details");
        }
        System.out.println();
    }

    public void updateStudentNationality(String userName, String nationality) {
        ((StudentRecordsMgr) studentRecordsMgr).loadStudentObjectList();
        Object existingStudent = studentRecordsMgr.getObjectFromList(userName);
        if (existingStudent != null) {
            ((Student) existingStudent).setNationality(nationality);
            for (Index index : ((Student) existingStudent).getIndexRegistered()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getStudentsRegistered()
                ) {
                    if (student.equals(existingStudent))
                        student.setNationality(nationality);
                }
            }

            for (Index index : ((Student) existingStudent).getIndexOnWaitList()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getWaitingList()
                ) {
                    if (student.equals(existingStudent))
                        student.setNationality(nationality);
                }
            }
            System.out.println("Student updated");
            ((CourseMgr) courseMgr).saveCourseObjectList();
            ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
            ((Student) existingStudent).print();
        } else {
            System.out.println("Student doesn't exist in the database... Cannot update student details");
        }
        System.out.println();
    }

    public void updateStudentMaxAU(String userName, int maxAU) {
        ((StudentRecordsMgr) studentRecordsMgr).loadStudentObjectList();
        Object existingStudent = studentRecordsMgr.getObjectFromList(userName);
        if (existingStudent != null) {
            ((Student) existingStudent).setMaxAU(maxAU);
            for (Index index : ((Student) existingStudent).getIndexRegistered()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getStudentsRegistered()
                ) {
                    if (student.equals(existingStudent))
                        student.setMaxAU(maxAU);
                }
            }

            for (Index index : ((Student) existingStudent).getIndexOnWaitList()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getWaitingList()
                ) {
                    if (student.equals(existingStudent))
                        student.setMaxAU(maxAU);
                }
            }
            System.out.println("Student updated");
            ((CourseMgr) courseMgr).saveCourseObjectList();
            ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
            ((Student) existingStudent).print();
        } else {
            System.out.println("Student doesn't exist in the database... Cannot update student details");
        }
        System.out.println();
    }

    public void updateStudentSchool(String userName, String school) {
        ((StudentRecordsMgr) studentRecordsMgr).loadStudentObjectList();
        ((CourseMgr) courseMgr).loadCourseObjectList();
        Object existingStudent = studentRecordsMgr.getObjectFromList(userName);
        if (existingStudent != null) {
            ((Student) existingStudent).setSchool(school);
            for (Index index : ((Student) existingStudent).getIndexRegistered()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getStudentsRegistered()
                ) {
                    if (student.equals(existingStudent))
                        student.setSchool(school);
                }
            }

            for (Index index : ((Student) existingStudent).getIndexOnWaitList()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getWaitingList()
                ) {
                    if (student.equals(existingStudent))
                        student.setSchool(school);
                }
            }
            System.out.println("Student updated");
            ((CourseMgr) courseMgr).saveCourseObjectList();
            ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
            ((Student) existingStudent).print();
        } else {
            System.out.println("Student doesn't exist in the database... Cannot update student details");
        }
        System.out.println();
    }

    public void updateCourseCode(String courseCode, String newCourseCode) {
        ((CourseMgr) courseMgr).loadCourseObjectList();
        ((StudentRecordsMgr) studentRecordsMgr).loadStudentObjectList();
        Object newExistingCourse = courseMgr.getObjectFromList(newCourseCode);
        if (newExistingCourse != null) {
            System.out.println("A course with this course code already exists");
            return;
        }

        Object existingCourse = courseMgr.getObjectFromList(courseCode);

        if (existingCourse == null) {
            System.out.println("This course doesn't exist in the database");
            return;
        }
        if (existingCourse instanceof Course) {

            for (Object index : ((Course) existingCourse).getIndexNumberList()
            ) {
                for (Student student : ((Index) index).getStudentsRegistered()
                ) {
                    for (Index indexReg : student.getIndexRegistered()
                    ) {
                        if (indexReg.equals(index))
                            indexReg.setCourseCode(newCourseCode);
                    }

                }
                for (Student student : ((Index) index).getWaitingList()
                ) {
                    for (Index indexWait : student.getIndexOnWaitList()
                    ) {
                        if (indexWait.equals(index))
                            indexWait.setCourseCode(newCourseCode);
                    }

                }
                ((Index) index).setCourseCode(courseCode);
            }
            ((Course) existingCourse).setCourseCode(newCourseCode);
            System.out.println("Updated course");
            ((Course) existingCourse).print();

            ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
            ((CourseMgr) courseMgr).saveCourseObjectList();
        }

    }

    public void updateIndexNumber(String courseCode, String indexNumber, String newIndexNumber) {
        ((CourseMgr) courseMgr).loadCourseObjectList();
        Course existingCourse = (Course) courseMgr.getObjectFromList(courseCode);
        if (existingCourse == null) {
            System.out.println("No such course exists");
            return;
        }
        indexMgr = new IndexMgr(existingCourse);

        Index indexToUpdate = (Index) indexMgr.getObjectFromList(indexNumber);
        if (indexToUpdate == null) {
            System.out.println("No such index number exists");
            return;
        }

        int newIndexNum = ((IndexMgr) indexMgr).getIntegerValueOfIndex(newIndexNumber);
        if (newIndexNum == -1) {
            return;
        }

        Object newExistingIndex = ((IndexMgr) indexMgr).getObjectFromList(newIndexNumber);
        if (newExistingIndex != null) {
            System.out.println("An index with this index number already exists");
            return;
        }

        for (Student student : indexToUpdate.getStudentsRegistered()
        ) {
            Student existingStudent = (Student) studentRecordsMgr.getObjectFromList(student.getNetworkName());
            System.out.println(existingStudent.getNetworkName());
            for (Index indexReg : existingStudent.getIndexRegistered()
            ) {

                // Something wrong here
                System.out.println(indexReg.getIndexNumber());
                if (indexReg.equals(indexToUpdate))
                    indexReg.setIndexNumber(newIndexNum);
                System.out.println(indexReg.getIndexNumber());
            }

        }
        for (Student student : ((Index) indexToUpdate).getWaitingList()
        ) {
            Student existingStudent = (Student) studentRecordsMgr.getObjectFromList(student.getNetworkName());
            for (Index indexWait : existingStudent.getIndexOnWaitList()
            ) {
                if (indexWait.equals(indexToUpdate))
                    indexWait.setIndexNumber(newIndexNum);
            }

        }
        indexToUpdate.setIndexNumber(newIndexNum);

        ((CourseMgr) courseMgr).saveCourseObjectList();
        ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
    }


    public void updateIndexVacancy(String courseCode, String indexNumber, int vacancy) {
        ((CourseMgr) courseMgr).loadCourseObjectList();
        Course existingCourse = (Course) courseMgr.getObjectFromList(courseCode);
        if (existingCourse == null) {
            System.out.println("No such course exists");
            return;
        }
        indexMgr = new IndexMgr(existingCourse);

        Index indexToUpdate = (Index) indexMgr.getObjectFromList(indexNumber);
        if (indexToUpdate == null) {
            System.out.println("No such index number exists");
            return;
        }

        if (vacancy < indexToUpdate.getStudentsRegistered().size()) {
            System.out.println("Too many students have already registered");
            return;
        }

        for (Student student : ((Index) indexToUpdate).getStudentsRegistered()
        ) {
            Student existingStudent = (Student) studentRecordsMgr.getObjectFromList(student.getNetworkName());
            for (Index indexReg : existingStudent.getIndexRegistered()
            ) {
                if (indexReg.equals(indexToUpdate))
                    indexReg.setVacancy(vacancy);
            }

        }
        for (Student student : ((Index) indexToUpdate).getWaitingList()
        ) {
            Student existingStudent = (Student) studentRecordsMgr.getObjectFromList(student.getNetworkName());
            for (Index indexWait : existingStudent.getIndexOnWaitList()
            ) {
                if (indexWait.equals(indexToUpdate))
                    indexWait.setVacancy(vacancy);
            }

        }

        ((CourseMgr) courseMgr).saveCourseObjectList();
        ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();

        indexToUpdate.setVacancy(vacancy);
    }

}