import com.alibaba.fastjson.JSONObject;
import edu.tstc.yy.model.Article;
import edu.tstc.yy.model.Comment;
import edu.tstc.yy.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by w_2 on 2016-11-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JsonTest {
    @Autowired
    ArticleService articleService;

    @Test
    public void ArticleJsonTest(){
        Article article=new Article();
        article.setArticleId(1);
        Article returnArticle=articleService.articleDetail(article,1,2);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("1",returnArticle);
        List<Comment> comments= (List<Comment>) jsonObject.getJSONObject("1").get("comments");
        if (comments!=null){
           for (int i=0;i<comments.size();i++){
               System.out.println(comments.get(i));
           }
        }else {
            System.out.print("NULL");
        }
    }
}
