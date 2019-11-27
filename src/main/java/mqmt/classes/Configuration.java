package mqmt.classes;

import java.util.ArrayList;

public class Configuration {
    public String broker;
    public String clientId;
    public String topic;
    public ArrayList<WaterDisplayStatisticsDto> displays;

    public Configuration() {
    }
}
