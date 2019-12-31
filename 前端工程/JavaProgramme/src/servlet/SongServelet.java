package servlet;

import com.alibaba.fastjson.JSONObject;
import dao.SongDao;
import entity.Song;
import page.Operator;
import page.Page;
import page.SearchProperty;
import util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongServelet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 4386109520796986005L;

    private SongDao songDao = new SongDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        Object attribute = request.getParameter("method");
        String method = "";
        if(attribute != null){
            method = attribute.toString();
        }
        if("GetSongData".equals(method)){
            getSongData(request,response);
            return;
        }
    }
    private void getSongData(HttpServletRequest request,
                                     HttpServletResponse response) {
        // TODO Auto-generated method stub
        Map<String, Object> ret = new HashMap<String, Object>();
        Page<Song> page = new Page<Song>(1, 999);
        Song result= new Song();
        response.setCharacterEncoding("UTF-8");
        String SongName = request.getParameter("songname");
//        System.out.println(SongName);
        page.getSearchProporties().add(new SearchProperty("songname", SongName, Operator.EQ));
        page = songDao.findList(page);
        result= page.getContent().get(0);
//        System.out.println(result.getPositive_num()+" "+result.getNegative_num());
        ret.put("type", "success");
        ret.put("values", result);
        StringUtil.writrToPage(response, JSONObject.toJSONString(ret));
    }
}
