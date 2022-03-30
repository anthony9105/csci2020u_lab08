public class StudentRecord
{
    private String studentID;
    private float midterm;
    private float assignment;
    private float exam;
    private float finalMark;
    private char letterGrade;

    /**
     * StudentRecord constructor method
     * @param studentID - String student ID
     * @param assignment - float assignment grade (as a percentage)
     * @param midterm - float midterm grade (as a percentage)
     * @param exam - float exam grade (as a percentage)
     */
    public StudentRecord(String studentID, float assignment, float midterm, float exam)
    {
        this.studentID = studentID;
        this.midterm = midterm;
        this.assignment = assignment;
        this.exam = exam;
    }

    // GETTERS:
    public String getStudentID() {
        return studentID;
    }

    public float getMidterm() {
        return midterm;
    }

    public float getAssignments() {
        return assignment;
    }

    public float getExam() {
        return exam;
    }

    public float getFinalMark() {
        return finalMark;
    }

    public char getLetterGrade() {
        return letterGrade;
    }


    // SETTERS:
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setMidterm(float midterm) {
        this.midterm = midterm;
    }

    public void setAssignments(float assignments) {
        this.assignment = assignment;
    }

    public void setExam(float exam) {
        this.exam = exam;
    }

    public void setFinalMark(float finalMark) {
        this.finalMark = finalMark;
    }

    public void setLetterGrade(char letterGrade) {
        this.letterGrade = letterGrade;
    }
}