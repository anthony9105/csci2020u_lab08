/**
 Anthony Liscio
 Lab 08
 */

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application
{
    // default global starting filename variable
    public String currentFileName = "TableData.csv";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception
    {
        /**
         * Table
         */
        // TableView object of StudentRecord objects
        TableView<StudentRecord> table = new TableView<StudentRecord>();

        // creating DataSource object and local observable list created by the DataSource class
        DataSource ds = new DataSource();
        ObservableList<StudentRecord> marks = ds.getAllMarks();

        // Scene layout component
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("Lab08 Solutions");
        primaryStage.setWidth(700);
        primaryStage.setHeight(350);

        table.setEditable(false);

        // ** Student ID column **
        TableColumn studentIDCol = new TableColumn("SID");      // creating a TableColumn object with title: "SID"
        studentIDCol.setMinWidth(100);
        // setting the column values from the StudentRecord object and keyword "studentID" attribute
        studentIDCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("studentID"));

        // ** Assignment column **
        TableColumn assignmentCol = new TableColumn("Assignment");  // creating a TableColumn object with title: "Assignment"
        assignmentCol.setMinWidth(90);
        // setting the column values from the StudentRecord object and keyword "assignment" attribute
        assignmentCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("assignments"));

        // ** Midterm column **
        TableColumn midtermCol = new TableColumn("Midterm");        // creating a TableColumn object with title: "Midterm"
        midtermCol.setMinWidth(90);
        // setting the column values from the StudentRecord object and keyword "midterm" attribute
        midtermCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("midterm"));

        // ** Final Exam column **
        TableColumn examCol = new TableColumn("Final Exam");        // creating a TableColumn object with title: "Final Exam"
        examCol.setMinWidth(90);
        // setting the column values from the StudentRecord object and keyword "exam" attribute
        examCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("exam"));

        // ** Final Grade column **
        TableColumn finalGradeCol = new TableColumn("Final Grade"); // creating a TableColumn object with title: "Final Grade"
        finalGradeCol.setMinWidth(90);
        // setting the column values from the StudentRecord object and keyword "finalMark" attribute
        finalGradeCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("finalMark"));

        // ** Letter Grade column **
        TableColumn letterGradeCol = new TableColumn("Letter Grade"); // creating a TableColumn object with title: "Letter Grade"
        letterGradeCol.setMinWidth(90);
        // setting the column values from the StudentRecord object and keyword "letterGrade" attribute
        letterGradeCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("letterGrade"));

        // setting items and columns
        table.setItems(marks);
        table.getColumns().addAll(studentIDCol, assignmentCol, midtermCol, examCol, finalGradeCol, letterGradeCol);


        /**
         * Menu bar and items
         */
        // adding the table to a VBox layout component
        VBox vbox = new VBox();

        // MenuBar
        MenuBar mainMenuBar = new MenuBar();
        // Menu
        Menu fileMenu = new Menu("File");
        // Menu Items
        MenuItem newItem = new Menu("New");
        MenuItem openItem = new Menu("Open");
        MenuItem saveItem = new Menu("Save");
        MenuItem saveAsItem = new Menu("Save As");
        MenuItem exitItem = new Menu("Exit");

        // add menuItems to the Menu and adding the Menu to the MenuBar
        fileMenu.getItems().addAll(newItem, openItem, saveItem, saveAsItem, exitItem);
        mainMenuBar.getMenus().add(fileMenu);

        vbox.getChildren().addAll(mainMenuBar, table);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        // setting up the menu items actions when clicked
        setUpNewItem(table, newItem, primaryStage);
        setUpOpenItem(table, openItem, primaryStage, mainMenuBar);
        setUpSaveItem(table, saveItem);
        setUpSaveAsItem(table, saveAsItem, primaryStage, scene);
        setUpExitItem(exitItem, primaryStage);


        /**
         * Option for the user to add new row(s) to the table
         */
        // label and text field for each of the 4 categories to add (SID, assignments, midterm, exam)
        HBox hbox1 = new HBox();
        Label addSIDLabel = new Label("\tSID:\t       ");
        TextField addSID = new TextField();
        addSID.setPrefWidth(130);
        addSID.setPromptText("SID");

        Label assignmentLabel = new Label("\t\tAssignments:");
        TextField addAssignment = new TextField();
        addAssignment.setPrefWidth(130);
        addAssignment.setPromptText("Assignments/100");

        hbox1.getChildren().addAll(addSIDLabel, addSID, assignmentLabel, addAssignment);
        hbox1.setSpacing(20);

        HBox hbox2 = new HBox();
        Label midtermLabel = new Label("\tMidterm:");
        TextField addMidterm = new TextField();
        addMidterm.setPrefWidth(130);
        addMidterm.setPromptText("Midterm/100");

        Label examLabel = new Label("\t\tExam:\t      ");
        TextField addExam = new TextField();
        addExam.setPrefWidth(130);
        addExam.setPromptText("Exam/100");

        hbox2.getChildren().addAll(midtermLabel, addMidterm, examLabel, addExam);
        hbox2.setSpacing(20);

        // add button
        HBox hbox3 = new HBox();
        Button addRowButton = new Button("Add");
        ArrayList<TextField> addRowInfo = new ArrayList<TextField>();
        addRowInfo.add(addSID);
        addRowInfo.add(addAssignment);
        addRowInfo.add(addMidterm);
        addRowInfo.add(addExam);
        setUpAddRowButton(table, addRowButton, addRowInfo);     // set up the action when clicked
        hbox3.getChildren().add(addRowButton);

        // adding the hboxes to the scene
        ((Group) scene.getRoot()).getChildren().addAll(hbox1, hbox2, hbox3);

        /**
         * Setting the scene to the primary stage and setting heights/widths
         */
        primaryStage.setHeight(600);
        primaryStage.setWidth(570);
        hbox1.setLayoutY(primaryStage.getHeight() - 170);       // setting hbox layouts
        hbox2.setLayoutY(primaryStage.getHeight() - 120);
        hbox3.setLayoutY(primaryStage.getHeight() - 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * setUpAddRowButton method to set the action for addButton when clicked (which is to add the entered data to the table as a new row)
     * @param table - the TableView<StudentRecord> table
     * @param addButton - the Button
     * @param addRowInfo - ArrayList<TextField> addRowInfo of the text entered into the TextFields by the user
     */
    public void setUpAddRowButton(TableView<StudentRecord> table, Button addButton, ArrayList<TextField> addRowInfo)
    {
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                try {
                    // add row to the table
                    table.getItems().add(new StudentRecord(addRowInfo.get(0).getText(), Float.parseFloat(addRowInfo.get(2).getText()),
                            Float.parseFloat(addRowInfo.get(1).getText()), Float.parseFloat(addRowInfo.get(3).getText())));

                    // calculate the new final marks and letter grades
                    DataSource ds3 = new DataSource();
                    ds3.setFinalMarks(table.getItems());
                    ds3.setLetterGrades(table.getItems());

                    // reset the add new row section
                    for (int i = 0; i < addRowInfo.size(); i++) {
                        addRowInfo.get(i).setText("");
                    }
                }
                catch (NumberFormatException nfe)
                {
                    System.out.println("Assignment, midterm, and exam marks must be numbers (float)");
                }
            }
        });
    }

    /**
     * setUpOpenItem method to set the action for the open option when clicked, which opens a fileChooser for the user
     * to select a file to load data from
     * @param table - the TableView<StudentRecord> table
     * @param openItem - the MenuItem to set the action for
     * @param primaryStage - Stage
     * @param mainMenuBar - MenuBar
     */
    public void setUpOpenItem(TableView<StudentRecord> table, MenuItem openItem, Stage primaryStage, MenuBar mainMenuBar)
    {
        openItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                load(table, primaryStage, mainMenuBar);
            }
        });
    }

    /**
     * load method used to open a fileChooser and load the data into the table
     * @param table - the TableView<StudentRecord> table
     * @param primaryStage - Stage
     * @param mainMenuBar - MenuBar
     */
    public void load(TableView<StudentRecord> table, Stage primaryStage, MenuBar mainMenuBar)
    {
        // getting the file
        FileChooser fileChooser = new FileChooser();
        String currentPath = System.getProperty("user.dir");
        currentPath += "/src/main/resources/";
        fileChooser.setInitialDirectory(new File(currentPath));     // start in the directory where the csv files will be stored
        File chosenFile = fileChooser.showOpenDialog(primaryStage);

        try
        {
            // using a BufferedReader to read line-by-line from the file
            FileReader fileReader = new FileReader(chosenFile);
            BufferedReader input = new BufferedReader(fileReader);
            String line = null;
            String[] lineArray = new String[4];
            int i = 0;
            while ((line = input.readLine()) != null)
            {
                // splitting the data (commas as delimiters) into the String array
                lineArray = line.split(",");

                if (i < table.getItems().size())
                {
                    table.getItems().set(i, new StudentRecord(lineArray[0], Float.parseFloat(lineArray[1]),
                            Float.parseFloat(lineArray[2]), Float.parseFloat(lineArray[3])));
                }
                else    // if all the rows are used, then create a new row with the data
                {
                    table.getItems().add(i, new StudentRecord(lineArray[0], Float.parseFloat(lineArray[1]),
                            Float.parseFloat(lineArray[2]), Float.parseFloat(lineArray[3])));
                }
                i++;
            }
            fileReader.close();
            input.close();

            // calculate the final marks and letter grades again
            DataSource ds2 = new DataSource();
            ds2.setFinalMarks(table.getItems());
            ds2.setLetterGrades(table.getItems());
        }
        catch (IOException io)
        {
            System.out.println("IO Exception");
        }
        catch (NullPointerException npe)
        {
            System.out.println("Don't exit the select file screen like this");
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("The data values and file format is not as expected and does not work");
        }
        catch (ArrayIndexOutOfBoundsException aiob)
        {
            System.out.println("The file format is not as expected and does not work");
        }
    }

    /**
     * save method used to write the table data to the csv file (based off whatever the currentFileName is.  Uses
     * the created writeToCSV method.
     * @param table - TableView<StudentRecord>
     */
    public void save(TableView<StudentRecord> table)
    {
        try
        {
            writeToFile(table.getItems());
        }
        catch (Exception io)
        {
            System.out.println("Problem writing to file");
        }
    }

    /**
     * setUpNewItem method used to set the action for when the new option when clicked
     * @param table - TableView
     * @param newItem - the MenuItem to set
     * @param primaryStage - Stage
     */
    public void setUpNewItem(TableView table, MenuItem newItem, Stage primaryStage)
    {
        newItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                for (int i=0; i < table.getItems().size(); i++)
                {
                    // "empty" the values in each row
                    table.getItems().set(i, new StudentRecord("", (char)0 , (char)0, (char)0));
                }
            }
        });
    }

    /**
     * setUpSaveItem method to set the action for the save option when clicked.  Uses the created save method
     * @param table - TableView
     * @param saveItem - the MenuItem to set
     */
    public void setUpSaveItem(TableView table, MenuItem saveItem)
    {
        // when the button is clicked write to the file
        saveItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                save(table);
            }
        });
    }

    /**
     * setUpSaveAsItem method used to set the action for the Save As option when clicked.  Uses the created save method
     * @param table - TableView
     * @param saveAsItem - the MenuItem to set
     * @param primaryStage - Stage
     * @param mainScene - Scene
     */
    public void setUpSaveAsItem(TableView table, MenuItem saveAsItem, Stage primaryStage, Scene mainScene)
    {
        saveAsItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    // getting the new file name from the file the user selects
                    FileChooser fileChooser = new FileChooser();
                    String currentPath = System.getProperty("user.dir");
                    currentPath += "/src/main/resources/";
                    fileChooser.setInitialDirectory(new File(currentPath));
                    File chosenFile = fileChooser.showOpenDialog(primaryStage);
                    currentFileName = chosenFile.getName();

                    save(table);
                }
                catch (NullPointerException npe)
                {
                    System.out.println("Don't exit the select file screen like this");
                }
            }
        });
    }

    /**
     * setUpExitItem method to set the action for the Exit option when clicked
     * @param exitItem - the MenuItem to set
     * @param primaryStage - Stage
     */
    public void setUpExitItem(MenuItem exitItem, Stage primaryStage)
    {
        exitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                // close primary stage and program
                primaryStage.close();
                System.exit(0);
            }
        });
    }

    /**
     * writeToFile method to write the data from the table to the file based off the currentFileName
     * @param tableItem
     */
    public void writeToFile (ObservableList<StudentRecord> tableItem)
    {
        try {
            String currentPath = System.getProperty("user.dir");
            currentPath += "/src/main/resources/" + currentFileName;

            // PrintWriter to write to the file line by line
            PrintWriter output = new PrintWriter(currentPath);
            boolean keepGoing = true;
            int i = 0;
            String line = "";
            while (keepGoing) {
                // update line with new data
                line += tableItem.get(i).getStudentID() + ",";    // SID
                line += tableItem.get(i).getAssignments() + ",";  // assignment
                line += tableItem.get(i).getMidterm() + ",";      // midterm
                line += tableItem.get(i).getExam();               // final exam
                output.println(line);                             // printing the line to csv file
                line = "";                                        // resetting line

                // increase i
                i++;

                // update keepGoing, if no more data to save
                if (i >= tableItem.size()) {
                    keepGoing = false;
                }
            }
            output.close();
        }
        catch (FileNotFoundException fnf)
        {
            System.out.println("File not found");
        }
    }
}
