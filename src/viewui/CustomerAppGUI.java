package viewui;

import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Customer;
import model.ICustomerDAO;
import model.datastore.mysql.CustomerDAO;
import util.Validator;

/**
 * The "CustomerAppGUI" class functions the same as the "CustomerApp" class,
 * however, it adds a GUI (Graphical User Interface) into the program so that it
 * no longer runs on console-based input and output. This program has buttons
 * for each of the menu items implemented in the "CustomerApp" class, a TextArea
 * to display commands, and a TextField to read input from the user.
 *
 * @author Jason Whiting
 */
public class CustomerAppGUI extends Application {

    ICustomerDAO cstList = new CustomerDAO();
    Scanner sc = new Scanner(System.in);
    protected TextArea output = new TextArea();
    protected TextField input = new TextField();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Create the GUI window and configure its properties.
        Stage window = new Stage();
        window.setTitle("Customer Application");
        window.setResizable(false);

        //Create a pane to hold all the elements of the program.
        BorderPane outer = new BorderPane();
        outer.setPadding(new Insets(10, 10, 10, 10));

        //Create a VBox to hold all the program's buttons.
        VBox menu = new VBox();
        menu.setPadding(new Insets(10, 10, 10, 10));
        menu.setSpacing(15);

        //Create the buttons for the program and set their properties.
        Button list = new Button("List All Entries");
        list.setMaxWidth(Double.MAX_VALUE);
        list.setOnAction(e -> {
            System.out.println("\n");
            System.out.println(cstList.toString());
        });

        Button create = new Button("Create New Entry");
        create.setMaxWidth(Double.MAX_VALUE);
        create.setOnAction(e -> {
            System.out.println("\n");
            int id = Validator.getInt(sc, "New Customer ID: ");
            String firstName = Validator.getLine(sc, "New Customer First Name: ");
            String lastName = Validator.getLine(sc, "New Customer Last Name: ");
            int age = Validator.getInt(sc, "New Customer Age: ");
            String state = Validator.getLine(sc, "New Customer State: ");
            String purchase = Validator.getLine(sc, "New Customer Purchase: ");
            double price = Validator.getDouble(sc, "New Customer Payment Amount: ");
            cstList.createCustomer(new Customer(id, firstName, lastName, age, state, purchase, price));
            output.appendText("Customer Entry #" + id + ": "+ firstName + " "+ lastName + ", "+ age + ", "+ state + ". ");
            output.appendText("Purchased a " + purchase + " for $"+ price + ". ");
            output.appendText("\n\nEntry Successfully Created.");
        });

        Button search = new Button("Search For Entry");
        search.setMaxWidth(Double.MAX_VALUE);
        search.setOnAction(e -> {
            System.out.println("\n");
            int id = Validator.getInt(sc, "Customer ID to Retrieve: ");
            System.out.println(cstList.retrieveCustomerById(id));
        });

        Button update = new Button("Update Entry");
        update.setMaxWidth(Double.MAX_VALUE);
        update.setOnAction(e -> {
            System.out.println("\n");
            int id = Validator.getInt(sc, "Customer ID to Update: ");
            String firstName = Validator.getLine(sc, "Customer First Name: ");
            String lastName = Validator.getLine(sc, "Customer Last Name: ");
            int age = Validator.getInt(sc, "Customer Age: ");
            String state = Validator.getLine(sc, "Customer State: ");
            String purchase = Validator.getLine(sc, "Customer Purchase: ");
            double price = Validator.getDouble(sc, "Customer Price: ");
            cstList.updateCustomer(new Customer(id, firstName, lastName, age, state, purchase, price));
        });

        Button delete = new Button("Delete Entry");
        delete.setMaxWidth(Double.MAX_VALUE);
        delete.setOnAction(e -> {
            System.out.println("\n");
            int id = Validator.getInt(sc, "Customer ID to Delete: ");
            System.out.println(cstList.retrieveCustomerById(id));
            String ok = Validator.getLine(sc, "Delete this Customer? (y/n): ", "^[yYnN]$");
            if (ok.equalsIgnoreCase("Y")) {
                cstList.deleteCustomer(id);
            }
        });

        Button ageGroup = new Button("Customer Age Group");
        ageGroup.setMaxWidth(Double.MAX_VALUE);
        ageGroup.setOnAction(e -> {
            int min = Validator.getInt(sc, "Minimum Age: ");
            int max = Validator.getInt(sc, "Maximum Age: ");
            List<Customer> customers = cstList.getAgeGroup(min, max);
            for (Customer c : customers) {
                System.out.println(c);
            }
            output.appendText(String.format(("\n\nCustomers in the age range of %d to %d: %d"), min, max, customers.size()));
        });

        Button ageStats = new Button("Customer Age Range");
        ageStats.setMaxWidth(Double.MAX_VALUE);
        ageStats.setOnAction(e -> {
            output.appendText("\n\n" + cstList.customerAgeRange());
        });

        Button totalProfit = new Button("Show Total Profits");
        totalProfit.setMaxWidth(Double.MAX_VALUE);
        totalProfit.setOnAction(e -> {
            output.appendText("\n\nTotal profit of all purchases: $" + cstList.showTotalProfits());
        });

        Button averageProfit = new Button("Show Average Profits");
        averageProfit.setMaxWidth(Double.MAX_VALUE);
        averageProfit.setOnAction(e -> {
            output.appendText("\n\nAverage profits of all purchases: $" + cstList.showAvgProfits());
        });

        Button itemsBought = new Button("Show Items Bought");
        itemsBought.setMaxWidth(Double.MAX_VALUE);
        itemsBought.setOnAction(e -> {
            String item = Validator.getLine(sc, "Item to Search: ");
            output.appendText("\n\nTotal number of " + item + "'s purchased: " + cstList.showTotalPurchasedItems(item));
        });

        //Add all the buttons to the VBox and set the VBox in the pane.
        menu.getChildren().addAll(list, create, search, update, delete, ageGroup, ageStats, totalProfit, averageProfit, itemsBought);
        outer.setLeft(menu);

        //Add properties to TextArea to display the console commands.
        output.setWrapText(true);
        output.setEditable(false);
        outer.setCenter(output);
        
        output.setText("Welcome to the Team-5 Customer Information Application. ");
        output.appendText("-----------------------------------------"
                + "-----------------------------------------");

        //Create an HBox for the user's input field and its label.
        HBox user = new HBox();
        user.setAlignment(Pos.CENTER);
        user.setPadding(new Insets(10, 0, 10, 10));
        user.setSpacing(10);
        
        //Create a label to place next to the user text field.
        //I set this so that is would no appear on the actual window. 
        //This is because, once I had set up the spacing so that it looked
        //nice, I added this prompt to the text field itself.
        //I didn't want to mess up the spacing, to I just kept this here.
        Label label = new Label("Enter Commands Here: ");
        label.setVisible(false);
        
        //Add properties to TextField to read in user commands.
        input.setPromptText("Enter Your Commands Here.");
        input.setEditable(true);
        input.setMinWidth(403);
        
        //Place the HBox at the bottom of the pane.
        user.getChildren().addAll(label, input);
        outer.setBottom(user);

        //Set the stage and scene and then show it.
        Scene layout = new Scene(outer, 600, 500);
        window.setScene(layout);
        window.show();
    }
}
