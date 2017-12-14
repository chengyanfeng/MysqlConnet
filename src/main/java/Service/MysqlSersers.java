package Service;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/14 0014.
 */
@Service
public class MysqlSersers {
    public List<Map<String, Object>> RetrunList(ResultSet resultSet) throws  Exception{
        List<Map<String, Object>> datelist = new ArrayList<Map<String, Object>>();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int count = rsmd.getColumnCount();
        String[] name = new String[count];
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            name[i] = rsmd.getColumnName(i + 1);
            list.add(name[i]);

        }

        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < list.size(); i++) {

                Object object = resultSet.getObject(list.get(i));
                map.put(list.get(i), object);

            }
            datelist.add(map);

        }
        return datelist;
    }


}
