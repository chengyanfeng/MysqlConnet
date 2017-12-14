package controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import db.Mysqlconnect;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import Service.MysqlSersers;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@EnableAutoConfiguration
@ComponentScan
@Controller
public class MainController {
    private static Gson gson=new Gson();
    @Autowired
   MysqlSersers mysqlSersers;
    @ResponseBody
    @RequestMapping("/")
  public  String Mysql() {
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

        }


        return gson.toJson(rlist);
    }

}
