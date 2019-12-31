package test;

import com.alibaba.fastjson.JSONObject;
import dao.SongDao;
import entity.Song;
import page.Operator;
import page.Page;
import page.SearchProperty;
import util.StringUtil;

import java.util.HashMap;
import java.util.Map;

public class Test {
//    private SongDao songDao = new SongDao();

    public static void main(String[] args) throws Exception {
        Map<String, Object> ret = new HashMap<String, Object>();
        Page<Song> page = new Page<Song>(1, 999);
        Song result= new Song();
        page.getSearchProporties().add(new SearchProperty("songname", "DanceMonkey", Operator.EQ));
        page =  new SongDao().findList(page);
        result= page.getContent().get(0);
        System.out.println(result.getPositive_num()+" "+result.getNegative_num());
    }
}
