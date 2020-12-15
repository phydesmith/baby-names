package io.javasmithy.controller;

import io.javasmithy.data.Name;
import io.javasmithy.data.NameLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class MainController implements Initializable {

    private Stage stage;

    private char choice;

    private ObservableList<Name> namePool;
    private ObservableList<Name> discard;
    private ObservableList<Name> selections;

    private Name currentName;

    @FXML
    Label header, currentNameLabel, seedLabel;
    @FXML
    TextField seedTextField;
    @FXML
    Button nextNameButton, addButton, removeButton, print;
    @FXML
    AnchorPane root;
    @FXML
    HBox choiceButtonsHBox;
    @FXML
    ListView<Name> nameSelections;
    @FXML
    VBox addRemoveButtons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.currentName = new Name("", 'a');
    }

    @FXML
    public void boyChosen(){
        header.setText("Boy Names");
        this.choice = 'M';
        initializeLists();
        hideChoices();
        showMain();
    }
    @FXML
    public void girlChosen(){
        header.setText("Girl Names");
        this.choice = 'F';
        initializeLists();
        hideChoices();
        showMain();
    }
    @FXML
    public void bothChosen(){
        header.setText("All Names");
        this.choice = 'A';
        initializeLists();
        hideChoices();
        showMain();

    }
    private void hideChoices(){
        choiceButtonsHBox.setVisible(false);
        seedTextField.setVisible(false);
        seedLabel.setVisible(false);
    }
    private void showMain(){
        nameSelections.setVisible(true);
        addRemoveButtons.setVisible(true);
        getNextName();
        currentNameLabel.setVisible(true);
    }
    private void initializeLists(){
        this.namePool = FXCollections.observableArrayList(NameLoader.load(this.choice));
        this.selections = FXCollections.observableArrayList();
        this.discard = FXCollections.observableArrayList();

        try{
            Collections.shuffle(this.namePool, new Random( parseInt(seedTextField.getText())));
        } catch (NumberFormatException e){
            seedTextField.setText("2147483647");
            Collections.shuffle(this.namePool, new Random( 2147483647));
        }

        this.nameSelections.setItems(selections);
    }

    @FXML
    private void addName(){
        this.nameSelections.getItems().addAll(this.namePool.remove(0));
        this.addButton.setDisable(true);
    }
    @FXML
    private void getNextName(){
        this.currentNameLabel.setText(this.namePool.get(0).toString());
        this.addButton.setDisable(false);
    }
    @FXML
    private void skipName(){
        this.discard.add(this.namePool.remove(0));
        getNextName();
    }
    @FXML
    private void removeName(){
        try {
            this.discard.add(this.nameSelections.getItems().remove(this.nameSelections.getSelectionModel().getSelectedIndex()));
        } catch (IndexOutOfBoundsException e){
            System.out.println("Nothing Selected to remove.");
        }
    }
    @FXML
    private void addDiscardToNamePool(){
        this.namePool.addAll(this.discard);
        this.discard = FXCollections.observableArrayList();
    }

    @FXML
    private void exportFile(){

        String content = getSelectionsContent();


        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File file = fileChooser.showSaveDialog(this.stage);
        if (file != null){
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(content);
                bufferedWriter.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }
    private String getSelectionsContent(){
        StringBuilder sb = new StringBuilder();
        for (Name name: selections) {
            sb.append(name.toStringWithGender());
            sb.append("\n");
        }
        return sb.toString();
    }

    @FXML
    private void importFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        File file = fileChooser.showOpenDialog(this.stage);
        this.selections.addAll(NameLoader.load(file));
    }

    @FXML
    private void printSelections(){

    }

    public void setStage(Stage stage){
        this.stage = stage;
    }


}
