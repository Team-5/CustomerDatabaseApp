package viewui;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Stage window = new Stage();
        window.setTitle("Customer Application");
        window.setResizable(false);

        BorderPane outer = new BorderPane();
        outer.setPadding(new Insets(10, 10, 10, 10));

        VBox menu = new VBox();
        menu.setPadding(new Insets(10, 10, 10, 10));
        menu.setSpacing(15);

        Button list = new Button("List All Entries");
        list.setMaxWidth(Double.MAX_VALUE);
        list.setOnAction(e -> {
            output.appendText("All Customer Entries:\n\n");
            output.appendText(cstList.toString());
            output.appendText("\n");
        });

        Button create = new Button("Create New Entry");
        create.setMaxWidth(Double.MAX_VALUE);
        create.setOnAction(e -> {
            Dialog creation = new TextInputDialog();
            creation.setTitle("Customer Creation Window");
            creation.setHeaderText("Enter New Customer Information.");
            creation.setResizable(false);

            Label id = new Label("Customer ID: ");
            Label firstName = new Label("First Name: ");
            Label lastName = new Label("Last Name: ");
            Label age = new Label("Age: ");
            Label state = new Label("Home State: ");
            Label purchase = new Label("Purchase: ");
            Label price = new Label("Purchase Price: ");

            TextField idInput = new TextField();
            TextField firstNameInput = new TextField();
            TextField lastNameInput = new TextField();
            TextField ageInput = new TextField();
            TextField stateInput = new TextField();
            TextField purchaseInput = new TextField();
            TextField priceInput = new TextField();

            GridPane grid = new GridPane();
            grid.add(id, 1, 1);
            grid.add(idInput, 2, 1);
            grid.add(firstName, 1, 2);
            grid.add(firstNameInput, 2, 2);
            grid.add(lastName, 1, 3);
            grid.add(lastNameInput, 2, 3);
            grid.add(age, 1, 4);
            grid.add(ageInput, 2, 4);
            grid.add(state, 1, 5);
            grid.add(stateInput, 2, 5);
            grid.add(purchase, 1, 6);
            grid.add(purchaseInput, 2, 6);
            grid.add(price, 1, 7);
            grid.add(priceInput, 2, 7);
            creation.getDialogPane().setContent(grid);

            ButtonType submit = new ButtonType("Submit", ButtonData.OK_DONE);
            creation.getDialogPane().getButtonTypes().add(submit);

            creation.setResultConverter(new Callback<ButtonType, Customer>() {
                @Override
                public Customer call(ButtonType b) {
                    if (b == submit) {
                        Customer cust = new Customer(Integer.parseInt(idInput.getText()), firstNameInput.getText(),
                                lastNameInput.getText(), Integer.parseInt(ageInput.getText()), stateInput.getText(),
                                purchaseInput.getText(), Double.parseDouble(priceInput.getText()));
                        cstList.createCustomer(cust);
                        return cust;
                    }
                    return null;
                }
            });

            Optional<Customer> result = creation.showAndWait();

            if (result.isPresent()) {
                output.appendText("Customer Entry #" + idInput.getText() + ": "
                        + firstNameInput.getText() + " " + lastNameInput.getText() + ", " + ageInput.getText()
                        + ", " + stateInput.getText() + ".\n");
                output.appendText("Purchased a " + purchaseInput.getText() + " for $"
                        + priceInput.getText() + ".\n\n");
                output.appendText("Entry Successfully Created.\n\n");
            }
        });

        Button search = new Button("Search For Entry");
        search.setMaxWidth(Double.MAX_VALUE);
        search.setOnAction(e -> {
            Dialog find = new TextInputDialog();
            find.setTitle("Customer Search Window");
            find.setHeaderText("Enter Customer Information to Search.");
            find.setResizable(false);

            Label id = new Label("Enter an ID: ");
            TextField idSearch = new TextField();

            GridPane grid = new GridPane();
            grid.add(id, 1, 1);
            grid.add(idSearch, 2, 1);
            find.getDialogPane().setContent(grid);

            ButtonType submit = new ButtonType("Submit", ButtonData.OK_DONE);
            find.getDialogPane().getButtonTypes().add(submit);

            find.setResultConverter(new Callback<ButtonType, Customer>() {
                @Override
                public Customer call(ButtonType b) {
                    if (b == submit) {
                        Customer cust = cstList.retrieveCustomerById(Integer.parseInt(idSearch.getText()));
                        output.appendText("Customer Entry With ID of " + Integer.parseInt(idSearch.getText()) + ":\n\n");
                        output.appendText(String.format("%5d : %s, %s, %3d, %s, %s, %7.2f\n\n", cust.getId(), cust.getFirstName(),
                                cust.getLastName(), cust.getAge(), cust.getState(), cust.getPurchase(), cust.getPrice()));
                    }
                    return null;
                }
            });

            Optional<Customer> result = find.showAndWait();
        });

        Button update = new Button("Update Entry");
        update.setMaxWidth(Double.MAX_VALUE);
        update.setOnAction(e -> {
            Dialog creation = new TextInputDialog();
            creation.setTitle("Customer Update Window");
            creation.setHeaderText("Enter Updated Customer Information.");
            creation.setResizable(false);

            Label id = new Label("Customer ID: ");
            Label firstName = new Label("First Name: ");
            Label lastName = new Label("Last Name: ");
            Label age = new Label("Age: ");
            Label state = new Label("Home State: ");
            Label purchase = new Label("Purchase: ");
            Label price = new Label("Purchase Price: ");

            TextField idInput = new TextField();
            TextField firstNameInput = new TextField();
            TextField lastNameInput = new TextField();
            TextField ageInput = new TextField();
            TextField stateInput = new TextField();
            TextField purchaseInput = new TextField();
            TextField priceInput = new TextField();

            GridPane grid = new GridPane();
            grid.add(id, 1, 1);
            grid.add(idInput, 2, 1);
            grid.add(firstName, 1, 2);
            grid.add(firstNameInput, 2, 2);
            grid.add(lastName, 1, 3);
            grid.add(lastNameInput, 2, 3);
            grid.add(age, 1, 4);
            grid.add(ageInput, 2, 4);
            grid.add(state, 1, 5);
            grid.add(stateInput, 2, 5);
            grid.add(purchase, 1, 6);
            grid.add(purchaseInput, 2, 6);
            grid.add(price, 1, 7);
            grid.add(priceInput, 2, 7);
            creation.getDialogPane().setContent(grid);

            ButtonType submit = new ButtonType("Submit", ButtonData.OK_DONE);
            creation.getDialogPane().getButtonTypes().add(submit);

            creation.setResultConverter(new Callback<ButtonType, Customer>() {
                @Override
                public Customer call(ButtonType b) {
                    if (b == submit) {
                        Customer cust = new Customer(Integer.parseInt(idInput.getText()), firstNameInput.getText(),
                                lastNameInput.getText(), Integer.parseInt(ageInput.getText()), stateInput.getText(),
                                purchaseInput.getText(), Double.parseDouble(priceInput.getText()));
                        cstList.updateCustomer(cust);
                        return cust;
                    }
                    return null;
                }
            });

            Optional<Customer> result = creation.showAndWait();

            if (result.isPresent()) {
                output.appendText("Customer Entry #" + idInput.getText() + ": "
                        + firstNameInput.getText() + " " + lastNameInput.getText() + ", " + ageInput.getText()
                        + ", " + stateInput.getText() + ".\n");
                output.appendText("Purchased a " + purchaseInput.getText() + " for $"
                        + priceInput.getText() + ".\n\n");
                output.appendText("Entry Successfully Updated.\n\n");
            }
        });

        Button delete = new Button("Delete Entry");
        delete.setMaxWidth(Double.MAX_VALUE);
        delete.setOnAction(e -> {
            Dialog deletion = new TextInputDialog();
            deletion.setTitle("Customer Deletion Window");
            deletion.setHeaderText("Enter Customer to Delete.");
            deletion.setResizable(false);

            Label id = new Label("Customer ID: ");
            TextField idInput = new TextField();

            GridPane grid = new GridPane();
            grid.add(id, 1, 1);
            grid.add(idInput, 2, 1);
            deletion.getDialogPane().setContent(grid);

            ButtonType submit = new ButtonType("Submit", ButtonData.OK_DONE);
            deletion.getDialogPane().getButtonTypes().add(submit);

            deletion.setResultConverter(new Callback<ButtonType, Customer>() {
                @Override
                public Customer call(ButtonType b) {
                    if (b == submit) {
                        Customer cust = cstList.retrieveCustomerById(Integer.parseInt(idInput.getText()));
                        output.appendText("The Following Customer Entry Was Selected: \n\n");
                        output.appendText(String.format("%5d : %s, %s, %3d, %s, %s, %7.2f\n\n", cust.getId(), cust.getFirstName(),
                                cust.getLastName(), cust.getAge(), cust.getState(), cust.getPurchase(), cust.getPrice()));
                        cstList.deleteCustomer(Integer.parseInt(idInput.getText()));
                        output.appendText("Customer Successfully Deleted.\n\n");
                    }
                    return null;
                }
            });

            Optional<Customer> result = deletion.showAndWait();
        });

        Button ageGroup = new Button("Customer Age Group");
        ageGroup.setMaxWidth(Double.MAX_VALUE);
        ageGroup.setOnAction(e -> {
            Dialog group = new TextInputDialog();
            group.setTitle("Customer Age Group Window");
            group.setHeaderText("Enter Two Ages to Group Customers.");
            group.setResizable(false);

            Label low = new Label("Minimum Age: ");
            Label high = new Label("Maximum Age: ");

            TextField lowInput = new TextField();
            TextField highInput = new TextField();

            GridPane grid = new GridPane();
            grid.add(low, 1, 1);
            grid.add(lowInput, 2, 1);
            grid.add(high, 1, 2);
            grid.add(highInput, 2, 2);
            group.getDialogPane().setContent(grid);

            ButtonType submit = new ButtonType("Submit", ButtonData.OK_DONE);
            group.getDialogPane().getButtonTypes().add(submit);

            group.setResultConverter(new Callback<ButtonType, Customer>() {
                @Override
                public Customer call(ButtonType b) {
                    if (b == submit) {
                        List<Customer> customers = cstList.getAgeGroup(Integer.parseInt(lowInput.getText()), Integer.parseInt(highInput.getText()));
                        output.appendText(String.format(("Customers in the age range of %d to %d: %d\n\n"),
                                Integer.parseInt(lowInput.getText()), Integer.parseInt(highInput.getText()), customers.size()));
                        for (Customer c : customers) {
                            output.appendText(c.toString() + "\n");
                        }
                        output.appendText("\n");
                    }
                    return null;
                }
            });

            Optional<Customer> result = group.showAndWait();
        });

        Button ageStats = new Button("Customer Age Range");
        ageStats.setMaxWidth(Double.MAX_VALUE);
        ageStats.setOnAction(e -> {
            output.appendText(cstList.customerAgeRange());
            output.appendText("\n\n");
        });

        Button totalProfit = new Button("Show Total Profits");
        totalProfit.setMaxWidth(Double.MAX_VALUE);
        totalProfit.setOnAction(e -> {
            output.appendText("Total profit of all purchases: $" + cstList.showTotalProfits());
            output.appendText("\n\n");
        });

        Button averageProfit = new Button("Show Average Profits");
        averageProfit.setMaxWidth(Double.MAX_VALUE);
        averageProfit.setOnAction(e -> {
            output.appendText("Average profits of all purchases: $" + cstList.showAvgProfits());
            output.appendText("\n\n");
        });

        Button itemsBought = new Button("Show Items Bought");
        itemsBought.setMaxWidth(Double.MAX_VALUE);
        itemsBought.setOnAction(e -> {
            Dialog bought = new TextInputDialog();
            bought.setTitle("Item Search Window");
            bought.setHeaderText("Enter An Item to Search.");
            bought.setResizable(false);

            Label item = new Label("Enter Item: ");
            TextField itemInput = new TextField();

            GridPane grid = new GridPane();
            grid.add(item, 1, 1);
            grid.add(itemInput, 2, 1);
            bought.getDialogPane().setContent(grid);

            ButtonType submit = new ButtonType("Submit", ButtonData.OK_DONE);
            bought.getDialogPane().getButtonTypes().add(submit);

            bought.setResultConverter(new Callback<ButtonType, Customer>() {
                @Override
                public Customer call(ButtonType b) {
                    if (b == submit) {
                        output.appendText("Total number of " + itemInput.getText() + "'s purchased: " + cstList.showTotalPurchasedItems(itemInput.getText()));
                        output.appendText("\n\n");
                    }
                    return null;
                }
            });

            Optional<Customer> result = bought.showAndWait();
        });

        Button clear = new Button("Clear Text");
        clear.setMaxWidth(Double.MAX_VALUE);
        clear.setOnAction(e -> {
            output.clear();
            output.setText("Welcome to the Team-5 Customer Information Application. ");
            output.appendText("----------------------------------------------------"
                    + "---------------------------------------------------\n\n");
        });

        menu.getChildren().addAll(list, create, search, update, delete, ageGroup, ageStats, totalProfit, averageProfit, itemsBought, clear);
        outer.setLeft(menu);

        output.setWrapText(true);
        output.setEditable(false);
        outer.setCenter(output);

        output.setText("Welcome to the Team-5 Customer Information Application. ");
        output.appendText("----------------------------------------------------"
                + "---------------------------------------------------\n\n");

        Scene layout = new Scene(outer, 700, 600);
        window.setScene(layout);
        window.show();
    }
}
