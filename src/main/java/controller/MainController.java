package controller;
import db.Mysqlconnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import Service.MysqlSersers;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentScan
@Controller
public class MainController {
    @Autowired
   MysqlSersers mysqlSersers;
    @ResponseBody
    @RequestMapping("/")
  public  String home() {
       String rlist="";
        Mysqlconnect mysqlconnect = new Mysqlconnect("call mytest(0)");
        ResultSet resultSet=null;
        try {
          resultSet = mysqlconnect.pst.executeQuery();
            List<Map<String, Object>> maps = mysqlSersers.RetrunList(resultSet);
           rlist= maps.toString();

            resultSet.close();//关闭连接
            mysqlconnect.close();//关闭连接
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("--------------------");
        }


        return rlist;
    }

}
