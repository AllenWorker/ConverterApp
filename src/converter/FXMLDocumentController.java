/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author J392018
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button convertButton;
    @FXML
    private Label firstElementLabel;
    @FXML
    private Label secondElementLabel;
    @FXML
    private TextField firstElementText;
    @FXML
    private TextField secondElementText;
    @FXML
    private Label resultLabel;
    @FXML
    private ComboBox<String> convertComboBox;
    @FXML
    private Label converterLabel;
    
    double firstElementInput = 0;
    double secondElementInput = 0;
    ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Temperature",
        "Time",
        "Weight",
        "Length",
        "Bytes"
    );
    @FXML
    private Button showHistoryButton;
    @FXML
    private ListView<String> historyListView;
    private int arrayCount = 0;
    History historys[] = new History[10];
    
    private String symbol = "";
    private String result = "";
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        convertComboBox.setItems(options);
        firstElementLabel.setVisible(false);
        secondElementLabel.setVisible(false);
        firstElementText.setDisable(true);
        secondElementText.setDisable(true);
        for (int i=0; i<=historys.length-1; i++)
        {
            historys[i] = new History("", "", "", "");
        }
    }    

    @FXML
    private void handleConvertButtonAction(ActionEvent event) {
        
        if(validation())
        {
            // Check the text field if it's empty
            try
            {
                if (!firstElementText.getText().equals("")){ firstElementInput = Double.parseDouble(firstElementText.getText()); }
                if (!secondElementText.getText().equals("")){ secondElementInput = Double.parseDouble(secondElementText.getText()); }
                Converter convert = new Converter(firstElementInput, secondElementInput, firstElementText.getText().equals(""));
                switch(convertComboBox.getValue())
                {
                    case "Temperature":
                        convert.temperatureConvert();
                        if (convert.stat) // check from and to
                        {
                            result = Double.toString(convert.firstEle);
                            symbol = " °C";
                            recordHistory(Double.toString(convert.secondEle), "°F", Double.toString(convert.firstEle), "°C");
                        }
                        else
                        {
                            result = Double.toString(convert.secondEle);
                            symbol = " °F";
                            recordHistory(Double.toString(convert.firstEle), "°C", Double.toString(convert.secondEle), "°F");
                        }
                        resultLabel.setText(result + symbol);
                        break;

                    case "Time":
                        convert.dayTimeConvert();
                        if (convert.stat) // check from and to
                        {
                            result = Double.toString(convert.firstEle);
                            symbol = " day(s)";
                            recordHistory(Double.toString(convert.secondEle), " minute(s)", Double.toString(convert.firstEle), " day(s)");
                        }
                        else
                        {
                            result = Double.toString(convert.secondEle);
                            symbol = " minute(s)";
                            recordHistory(Double.toString(convert.firstEle), " day(s)", Double.toString(convert.secondEle), " minute(s)");
                        }
                        resultLabel.setText(result + symbol);
                        break;

                    case "Weight":
                        convert.weightConvert();
                        if (convert.stat) // check from and to
                        {
                            result = Double.toString(convert.firstEle);
                            symbol = " kg(s)";
                            recordHistory(Double.toString(convert.secondEle), " lb(s)", Double.toString(convert.firstEle), " kg(s)");
                        }
                        else
                        {
                            result = Double.toString(convert.secondEle);
                            symbol = " lb(s)";
                            recordHistory(Double.toString(convert.firstEle), " kg(s)", Double.toString(convert.secondEle), " lb(s)");
                        }
                        resultLabel.setText(result + symbol);
                        break;

                    case "Length":
                        convert.lengthConvert();
                        if (convert.stat) // check from and to
                        {
                            result = Double.toString(convert.firstEle);
                            symbol = " cm(s)";
                            recordHistory(Double.toString(convert.secondEle), " inch(es)", Double.toString(convert.firstEle), " cm(s)");
                        }
                        else
                        {
                            result = Double.toString(convert.secondEle);
                            symbol = " inch(es)";
                            recordHistory(Double.toString(convert.firstEle), " cm(s)", Double.toString(convert.secondEle), " inch(es)");
                        }
                        resultLabel.setText(result + symbol);
                        break;
                        
                    case "Bytes":
                        convert.bytesConvert();
                        if (convert.stat) // check from and to
                        {
                            result = Double.toString(convert.firstEle);
                            symbol = " Mb(s)";
                            recordHistory(Double.toString(convert.secondEle), " Gb(s)", Double.toString(convert.firstEle), " Mb(s)");
                        }
                        else
                        {
                            result = Double.toString(convert.secondEle);
                            symbol = " Gb(s)";
                            recordHistory(Double.toString(convert.firstEle), " Mb(s)", Double.toString(convert.secondEle), " Gb(s)");
                        }
                        resultLabel.setText(result + symbol);
                        break;


                    default:
                        break;
                } // End of switch
               
            } // End of try
            catch (NumberFormatException e)
            {
                showMessageDialog(null, "invaild Value");
            } // End of catch
            
        } // End if
        firstElementText.clear();
        secondElementText.clear();
    }
    
    private boolean validation()
    {
        
        // Check if both or none of it is filled (should be only fill one of it!)
        if (!(firstElementText.getText().equals("") ^ secondElementText.getText().equals(""))) //XNOR (both true or both false == true)
        {
            showMessageDialog(null, "Fill only one of the input!");
            return false;
        }
        else
        {
            return true;
        }
        
    }
    @FXML
    private void handleSelectComboBoxAction(ActionEvent event) {
        switch(convertComboBox.getValue())
        {
            case "Temperature":
                firstElementLabel.setVisible(true);
                secondElementLabel.setVisible(true);
                firstElementLabel.setText("Celcius:");
                secondElementLabel.setText("Fahrenheit:");
                firstElementText.setDisable(false);
                secondElementText.setDisable(false);
                break;
            
            case "Time":
                firstElementLabel.setVisible(true);
                secondElementLabel.setVisible(true);
                firstElementLabel.setText("Day(s):");
                secondElementLabel.setText("Minute(s):");
                firstElementText.setDisable(false);
                secondElementText.setDisable(false);
                break;
                
            case "Weight":
                firstElementLabel.setVisible(true);
                secondElementLabel.setVisible(true);
                firstElementLabel.setText("kilogram(s):");
                secondElementLabel.setText("Pound(s):");
                firstElementText.setDisable(false);
                secondElementText.setDisable(false);
                break;
                
            case "Length":
                firstElementLabel.setVisible(true);
                secondElementLabel.setVisible(true);
                firstElementLabel.setText("Centimetre(s):");
                secondElementLabel.setText("Inch(es):");
                firstElementText.setDisable(false);
                secondElementText.setDisable(false);
                break;
                
            case "Bytes":
                firstElementLabel.setVisible(true);
                secondElementLabel.setVisible(true);
                firstElementLabel.setText("Mb(s)");
                secondElementLabel.setText("Gb(s):");
                firstElementText.setDisable(false);
                secondElementText.setDisable(false);
                break;
            
            default:
                break;
        }
    }

    @FXML
    private void handleShowHistoryButtonAction(ActionEvent event) {
        historyListView.getItems().clear();
        for (int i=0; i<=historys.length-1; i++)
        {
             historyListView.getItems().add(historys[i].toString());
        }
    }
    
    private void recordHistory(String fromValue, String fromUnitOfMeasure, String toValue, String toUnitOfMeasure)
    {
        overwriteArray();
        historys[arrayCount] = new History(fromValue, fromUnitOfMeasure, toValue, toUnitOfMeasure);
        arrayCount++;
    }
    
    private void overwriteArray()
    {
        if (arrayCount >= historys.length) //overwrite start form 10th
        {
            for (int i = 1; i <= historys.length-1; i++)
            {
                historys[i-1] = historys[i];
            }
            arrayCount = historys.length-1; // arrayCount never lager than 10
        }
    }
    
}
