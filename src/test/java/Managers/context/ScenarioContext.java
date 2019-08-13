package Managers.context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String, Object> scenarioContext;

    public ScenarioContext(){
        scenarioContext= new HashMap<>();
    }

    public void setScenarioContext(Context key, Object value){
        scenarioContext.put(key.toString(), value);
    }

    public Object getScenarioContext(Context key){
        return scenarioContext.get(key.toString());
    }

    public boolean isConstains(Context key){
        return scenarioContext.containsKey(key.toString());
    }
}
