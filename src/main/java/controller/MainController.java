package controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import db.Mysqlconnect;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import Service.MysqlSersers;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class MainController {
    private static Gson gson=new Gson();
   @Autowired
   MysqlSersers mysqlSersers;

    @RequestMapping("/")
  public  String mysql(@RequestParam(required = false,defaultValue = "0") String id ) {
     List<Map<String,Object>> rlist= new ArrayList<Map<String,Object>>();
        String sql="call mytest("+id+")";
        Mysqlconnect mysqlconnect = new Mysqlconnect(sql);
        ResultSet resultSet=null;

        try {
          resultSet = mysqlconnect.pst.executeQuery();
             rlist = mysqlSersers.RetrunList(resultSet);
            resultSet.close();//关闭连接
            mysqlconnect.close();//关闭连接
        } catch (Exception e) {
            e.printStackTrace();

        }


        return gson.toJson(rlist);

    }

}
