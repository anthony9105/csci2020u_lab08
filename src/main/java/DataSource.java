import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSource
{
    // constant variables for assignment, midterm and exam weight percentages (as float decimal values)
    private static final float ASSIGNMENT_WEIGHT = 0.2f;
    private static final float MIDTERM_WEIGHT = 0.3f;
    private static final float EXAM_WEIGHT = 0.5f;

    // constant letter grade possibilities
    private static final char[] GRADES = {'A', 'B', 'C', 'D', 'F'};

    /**
     * getAllMarks method used to add StudentRecord objects (with assigned and calculated values) to the marks
     * observable list
     * @return - ObservableList<StudentRecord> marks
     */
    public static ObservableList<StudentRecord> getAllMarks()
    {
        // JavaFX ObservableList of StudentRecord objects
        ObservableList<StudentRecord> marks = FXCollections.observableArrayList();

        // Student ID, Assignments, Midterm, Final exam
        marks.add(new StudentRecord("100100100", 75.0f, 68.0f, 54.25f));
        marks.add(new StudentRecord("100100101", 70.0f, 69.25f, 51.5f));
        marks.add(new StudentRecord("100100102", 100.0f, 97.0f, 92.5f));
        marks.add(new StudentRecord("100100103", 90.0f, 88.5f, 68.75f));
        marks.add(new StudentRecord("100100104", 72.25f, 74.75f, 58.25f));
        marks.add(new StudentRecord("100100105", 85.0f, 56.0f, 62.5f));
        marks.add(new StudentRecord("100100106", 70.0f, 66.5f, 61.75f));
        marks.add(new StudentRecord("100100107", 55.0f, 47.0f, 50.5f));
        marks.add(new StudentRecord("100100108", 40.0f, 32.5f, 27.75f));
        marks.add(new StudentRecord("100100109", 82.5f, 77.0f, 74.25f));

        setFinalMarks(marks);   // to calculate all the students' final marks and set them in the ObservableList
        setLetterGrades(marks); // to assign all students' letter grade in the ObservableList

        return marks;
    }

    /**
     * setFinalMarks method used to calculate every students' final mark and set the finalMark attribute in the
     * StudentRecord object
     * @param marks - ObservableList of StudentRecord objects
     */
    public static void setFinalMarks(ObservableList<StudentRecord> marks)
    {
        float finalMark;
        for (int i=0; i < marks.toArray().length; i++)
        {
            finalMark = (marks.get(i).getAssignments() * ASSIGNMENT_WEIGHT) + (marks.get(i).getMidterm() * MIDTERM_WEIGHT)
                    + (marks.get(i).getExam() * EXAM_WEIGHT);
            marks.get(i).setFinalMark(finalMark);
        }
    }

    /**
     * setLetterGrades method used to assign each student's letter grade based off their final mark.  (Assumes that
     * the student final marks are calculated prior to this)
     * @param marks - ObservableList of StudentRecord objects
     */
    public static void setLetterGrades(ObservableList<StudentRecord> marks)
    {
        for (int i=0; i < marks.toArray().length; i++)
        {
            if (marks.get(i).getFinalMark() >= 80.0f)          // A grade (80-100%)
            {
                marks.get(i).setLetterGrade(GRADES[0]);
            }
            else if (marks.get(i).getFinalMark() >= 70.0f)      // B grade (70-79%)
            {
                marks.get(i).setLetterGrade(GRADES[1]);
            }
            else if (marks.get(i).getFinalMark() >= 60.0f)      // C grade (60-69%)
            {
                marks.get(i).setLetterGrade(GRADES[2]);
            }
            else if (marks.get(i).getFinalMark() >= 50.0f)      // D grade (50-59%)
            {
                marks.get(i).setLetterGrade(GRADES[3]);
            }
            else                                                // F grade (<50%)
            {
                marks.get(i).setLetterGrade(GRADES[4]);
            }
        }
    }

}