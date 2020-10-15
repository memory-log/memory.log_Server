package dgsw.memorylog.memorylog_Server.lib;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class MakeJson {
    private ObjectMapper mapper = new ObjectMapper();
    private HashMap<String, Object> map = new HashMap<String, Object>();

    public void setJson(String key, Object value) {
        map.put(key, value);
    }

    public Object getJson() {
        try {
            return mapper.writeValueAsString(map);
        } catch (Exception e) {
            return 0;
        } finally {
            map.clear();
        }
    }
}
