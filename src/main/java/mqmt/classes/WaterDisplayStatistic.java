package mqmt.classes;

import javax.swing.*;
import java.util.ArrayList;

public class WaterDisplayStatistic  {
    private JLabel unitTypeLabel;
    private JLabel data;
    public ArrayList<JLabel> labelArray;

    public WaterDisplayStatistic(String unit) {
        this.unitTypeLabel = new JLabel("<html><div style='text-align:center;padding:20px 4px;width:200px;background-color:#e8c547;font-size:20px;color:#eaefee;margin-left:15px;'>" + unit + "</div></html>");
        this.data = new JLabel();

        this.unitTypeLabel.setSize(100,50);
        this.data.setSize(600,50);
        this.labelArray = new ArrayList<JLabel>();
        this.labelArray.add(this.unitTypeLabel);
        this.labelArray.add(this.data);
    }

    public boolean HasUnitType(String unitType) {
        return this.unitTypeLabel.getText().equals(unitType);
    }

    public ArrayList<JLabel> getLabelArray() {
        return this.labelArray;
    }

    public void updateIfCorrectType(Sensor sensor) {
        if(this.unitTypeLabel.getText().contains(sensor.unitType)){
            this.data.setText("<html><div style='text-align:center;display:block;background-color:#00baba;padding:15px;border-radius:5px;'>" +
                "<div>Start of range: " + sensor.StartOfRange + "</div> " +
                "<div>End of range: " + sensor.EndOfRange + "</div> " +
                "<div>Factor: " + sensor.Factor + "</div></div></html>" );
        }
    }
}
